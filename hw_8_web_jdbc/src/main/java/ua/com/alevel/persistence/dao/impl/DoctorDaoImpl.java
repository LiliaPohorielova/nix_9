package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.persistence.dao.DoctorDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Doctor;
import ua.com.alevel.type.DoctorSpecialization;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

import static ua.com.alevel.persistence.dao.query.JpaQueryUtil.*;

@Service
public class DoctorDaoImpl implements DoctorDao {

    private final JpaConfig jpaConfig;

    public DoctorDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(Doctor doctor) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(CREATE_DOCTOR_QUERY)) {
            preparedStatement.setTimestamp(1, new Timestamp(doctor.getCreated().getTime()));
            preparedStatement.setTimestamp(2, new Timestamp(doctor.getUpdated().getTime()));
            preparedStatement.setBoolean(3, doctor.getVisible());
            preparedStatement.setString(4, doctor.getLastname());
            preparedStatement.setString(5, doctor.getFirstname());
            preparedStatement.setString(6, doctor.getMiddleName());
            preparedStatement.setString(7, doctor.getSpecialization().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    @Override
    public void update(Doctor doctor) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(UPDATE_DOCTOR_BY_ID_QUERY + doctor.getId())) {
            preparedStatement.setString(1, doctor.getLastname());
            preparedStatement.setString(2, doctor.getFirstname());
            preparedStatement.setString(3, doctor.getMiddleName());
            preparedStatement.setString(4, doctor.getSpecialization().toString());
            preparedStatement.setTimestamp(5, new Timestamp(new Date().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            PreparedStatement preparedStatementDoctor = jpaConfig.getConnection().prepareStatement(DELETE_DOCTOR_BY_ID_QUERY + id);
            preparedStatementDoctor.executeUpdate();
        } catch (SQLException e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    @Override
    public boolean existById(Long id) {
        long count = 0;
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(EXIST_DOCTOR_BY_ID_QUERY + id)) {
            while (resultSet.next()) {
                count = resultSet.getLong("COUNT(*)");
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return count == 1;
    }

    @Override
    public Doctor findById(Long id) {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_DOCTOR_BY_ID_QUERY + id)) {
            while (resultSet.next()) {
                return initDoctorByResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Doctor();
    }

    @Override
    public DataTableResponse<Doctor> findAll(DataTableRequest request) {
        List<Doctor> doctors = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        int limit = (request.getCurrentPage() - 1) * request.getPageSize();

        String sql = "select id, created, updated, visible, last_name, first_name, middle_name, specialization, count(*) as patientCount " +
                "from doctor left join declaration as decl on doctor.id = decl.doctor_id " +
                "group by doctor_id order by " +
                request.getSort() + " " +
                request.getOrder() + " limit " +
                limit + "," +
                request.getPageSize();

        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(sql)) {
            System.out.println(resultSet.getFetchSize());
            while (resultSet.next()) {
                DoctorResultSet doctorResultSet = convertResultSetToDoctor(resultSet);
                doctors.add(doctorResultSet.getDoctor());
                otherParamMap.put(doctorResultSet.getDoctor().getId(), doctorResultSet.getPatientCount());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DataTableResponse<Doctor> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(doctors);
        tableResponse.setOtherParamMap(otherParamMap);
        return tableResponse;
    }

    @Override
    public long count() {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery("select count(*) as count from doctor")) {
            while (resultSet.next()) {
                return resultSet.getLong("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private DoctorResultSet convertResultSetToDoctor(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Timestamp created = resultSet.getTimestamp("created");
        Timestamp updated = resultSet.getTimestamp("updated");
        Boolean visible = resultSet.getBoolean("visible");
        String doctorLastName = resultSet.getString("last_name");
        String doctorFirstName = resultSet.getString("first_name");
        String doctorMiddleName = resultSet.getString("middle_name");
        int patientCount = resultSet.getInt("patientCount");

        Doctor doctor = new Doctor();
        doctor.setId(id);
        doctor.setCreated(created);
        doctor.setUpdated(updated);
        doctor.setVisible(visible);
        doctor.setLastname(doctorLastName);
        doctor.setFirstname(doctorFirstName);
        doctor.setMiddleName(doctorMiddleName);

        return new DoctorResultSet(doctor, patientCount);
    }

    private Doctor initDoctorByResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        Timestamp created = resultSet.getTimestamp("created");
        Timestamp updated = resultSet.getTimestamp("updated");
        Boolean visible = resultSet.getBoolean("visible");
        String doctorLastName = resultSet.getString("last_name");
        String doctorFirstName = resultSet.getString("first_name");
        String doctorMiddleName = resultSet.getString("middle_name");
        String doctorSpecialization = resultSet.getString("specialization");

        Doctor doctor = new Doctor();
        doctor.setId(id);
        doctor.setCreated(new Date(created.getTime()));
        doctor.setUpdated(new Date(updated.getTime()));
        doctor.setVisible(visible);
        doctor.setLastname(doctorLastName);
        doctor.setFirstname(doctorFirstName);
        doctor.setMiddleName(doctorMiddleName);
        doctor.setSpecialization(DoctorSpecialization.valueOf(doctorSpecialization));

        return doctor;
    }

    private static class DoctorResultSet {

        private final Doctor doctor;
        private final int patientCount;

        private DoctorResultSet(Doctor doctor, int patientCount) {
            this.doctor = doctor;
            this.patientCount = patientCount;
        }

        public Doctor getDoctor() {
            return doctor;
        }

        public int getPatientCount() {
            return patientCount;
        }
    }
}