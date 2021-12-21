package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.persistence.dao.PatientDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Doctor;
import ua.com.alevel.persistence.entity.Patient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

import static ua.com.alevel.persistence.dao.query.JpaQueryUtil.*;

@Service
public class PatientDaoImpl implements PatientDao {

    private final JpaConfig jpaConfig;

    public PatientDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(Patient patient) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(CREATE_PATIENT_QUERY)) {
            preparedStatement.setTimestamp(1, new Timestamp(patient.getCreated().getTime()));
            preparedStatement.setTimestamp(2, new Timestamp(patient.getUpdated().getTime()));
            preparedStatement.setBoolean(3, new Boolean(patient.getVisible()));
            preparedStatement.setString(4, patient.getFirstname());
            preparedStatement.setString(5, patient.getLastname());
            preparedStatement.setInt(6, patient.getAge());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem new: = " + e.getMessage());
        }
    }

    private void insertPatientInDoctor(Doctor doctor, Patient patient) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(INSERT_DECLARATION)) {
            preparedStatement.setLong(1, doctor.getId());
            preparedStatement.setLong(2, patient.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem new: = " + e.getMessage());
        }
    }

    @Override
    public void update(Patient patient) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(UPDATE_PATIENT_BY_ID_QUERY + patient.getId())) {
            preparedStatement.setString(1, patient.getFirstname());
            preparedStatement.setString(2, patient.getLastname());
            preparedStatement.setInt(3, patient.getAge());
            preparedStatement.setTimestamp(4, new Timestamp(new Date().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(DELETE_PATIENT_BY_ID_QUERY + id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public boolean existById(Long id) {
        long count = 0;
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(EXIST_PATIENT_BY_ID_QUERY + id)) {
            while (resultSet.next()) {
                count = resultSet.getLong("COUNT(*)");
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return count == 1;
    }

    @Override
    public Patient findById(Long id) {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_PATIENT_BY_ID_QUERY + id)) {
            while (resultSet.next()) {
                return initPatientByResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return null;
    }

    @Override
    public DataTableResponse<Patient> findAll(DataTableRequest request) {
        List<Patient> patients = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();
        Long doctorId = null;
        if (request.getQueryMap().get("doctorId") != null) {
            doctorId = (Long) request.getQueryMap().get("doctorId");
            System.out.println("doctorId = " + doctorId);
        }
        try (ResultSet resultSet = doctorId == null ?
                jpaConfig.getStatement().executeQuery(FIND_ALL_PATIENTS_QUERY) :
                jpaConfig.getStatement().executeQuery(FIND_ALL_PATIENTS_BY_DOCTOR_ID_QUERY + doctorId)) {
            while (resultSet.next()) {
                patients.add(initPatientByResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }

        DataTableResponse<Patient> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setSort(request.getSort());
        dataTableResponse.setOrder(request.getOrder());
        dataTableResponse.setCurrentPage(request.getPageSize());
        dataTableResponse.setItems(patients);
        dataTableResponse.setOtherParamMap(otherParamMap);

        return dataTableResponse;
    }

    @Override
    public long count() {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery("select count(*) as count from patient")) {
            while (resultSet.next()) {
                return resultSet.getLong("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Patient initPatientByResultSet(ResultSet resultSet) throws SQLException {

        long id = resultSet.getLong("id");
        Timestamp created = resultSet.getTimestamp("created");
        Timestamp updated = resultSet.getTimestamp("updated");
        Boolean visible = resultSet.getBoolean("visible");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        Integer age = resultSet.getInt("age");

        Patient patient = new Patient();
        patient.setId(id);
        patient.setCreated(created);
        patient.setUpdated(updated);
        patient.setVisible(visible);
        patient.setFirstname(firstName);
        patient.setLastname(lastName);
        patient.setAge(age);

        return patient;
    }
}