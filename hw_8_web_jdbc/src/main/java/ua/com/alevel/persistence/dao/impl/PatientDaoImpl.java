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
        List<Patient> doctors = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();
        int limit = (request.getCurrentPage() - 1) * request.getPageSize();

        String sql = FIND_ALL_PATIENTS_JOIN_DECLARATION_QUERY +
                request.getSort() + " " +
                request.getOrder() + " limit " +
                limit + "," +
                request.getPageSize();

        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(sql)) {
            while (resultSet.next()) {
                PatientDaoImpl.PatientResultSet doctorResultSet = convertResultSetToPatient(resultSet);
                doctors.add(doctorResultSet.getPatient());
                otherParamMap.put(doctorResultSet.getPatient().getId(), doctorResultSet.getDoctorCount());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DataTableResponse<Patient> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(doctors);
        tableResponse.setOtherParamMap(otherParamMap);
        return tableResponse;
    }

    @Override
    public List<Patient> findAll() {
        List<Patient> patients = new ArrayList<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_PATIENTS_QUERY)) {
            while (resultSet.next()) {
                patients.add(initPatientByResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return patients;
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

    private PatientDaoImpl.PatientResultSet convertResultSetToPatient(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Timestamp created = resultSet.getTimestamp("created");
        Timestamp updated = resultSet.getTimestamp("updated");
        Boolean visible = resultSet.getBoolean("visible");
        String patientLastName = resultSet.getString("last_name");
        String patientFirstName = resultSet.getString("first_name");
        int patientAge = resultSet.getInt("age");
        int doctorCount = resultSet.getInt("doctorCount");

        Patient patient = new Patient();
        patient.setId(id);
        patient.setCreated(created);
        patient.setUpdated(updated);
        patient.setVisible(visible);
        patient.setLastname(patientLastName);
        patient.setFirstname(patientFirstName);
        patient.setAge(patientAge);

        return new PatientDaoImpl.PatientResultSet(patient, doctorCount);
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

    private static class PatientResultSet {

        private final Patient patient;
        private final int doctorCount;

        private PatientResultSet(Patient patient, int doctorCount) {
            this.patient = patient;
            this.doctorCount = doctorCount;
        }

        public Patient getPatient() {
            return patient;
        }

        public int getDoctorCount() {
            return doctorCount;
        }
    }
}