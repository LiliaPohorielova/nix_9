package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.persistence.dao.DeclarationDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Declaration;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ua.com.alevel.persistence.dao.query.JpaQueryUtil.*;
import static ua.com.alevel.persistence.dao.query.JpaQueryUtil.FIND_DECLARATION_BY_PATIENT_ID_AND_DOCTOR_ID_QUERY;

@Service
public class DeclarationDaoImpl implements DeclarationDao {
    private final JpaConfig jpaConfig;

    public DeclarationDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(Declaration declaration) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(CREATE_DECLARATION_QUERY)) {
            preparedStatement.setLong(1, declaration.getDoctorId());
            preparedStatement.setLong(2, declaration.getPatientId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void update(Declaration declaration) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(UPDATE_DECLARATION_BY_ID_QUERY + declaration.getId())) {
            preparedStatement.setLong(1, declaration.getDoctorId());
            preparedStatement.setLong(2, declaration.getPatientId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(DELETE_DECLARATION_BY_ID_QUERY + id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public boolean existById(Long id) {
        long count = 0;
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(EXIST_DECLARATION_BY_ID_QUERY + id)) {
            while (resultSet.next()) {
                count = resultSet.getLong("COUNT(*)");
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return count == 1;
    }

    @Override
    public Declaration findById(Long id) {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_DECLARATION_BY_ID_QUERY + id)) {
            while (resultSet.next()) {
                return initDeclarationByResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Declaration> findByDoctorId(Long doctorId) {
        List<Declaration> declarations = new ArrayList<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_PATIENTS_BY_DOCTOR_ID_QUERY + doctorId)) {
            while (resultSet.next()) {
                declarations.add(initDeclarationByResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return declarations;
    }

    @Override
    public List<Declaration> findByPatientId(Long patientId) {
        List<Declaration> declarations = new ArrayList<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_DOCTORS_BY_PATIENT_ID_QUERY + patientId)) {
            while (resultSet.next()) {
                declarations.add(initDeclarationByResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return declarations;
    }

    private Declaration initDeclarationByResultSet(ResultSet resultSet) throws SQLException {
        Declaration declaration = new Declaration();
        long id = resultSet.getLong("id");
        long doctorId = resultSet.getLong("doctor_id");
        long patientId = resultSet.getLong("patient_id");
        declaration.setId(id);
        declaration.setDoctorId(doctorId);
        declaration.setPatientId(patientId);
        return declaration;
    }

    public Declaration findByDoctorIdAndPatientId(Long patientId, Long doctorId) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(FIND_DECLARATION_BY_PATIENT_ID_AND_DOCTOR_ID_QUERY)) {
            preparedStatement.setLong(1, patientId);
            preparedStatement.setLong(2, doctorId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return initDeclarationByResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return null;
    }

    @Override
    public DataTableResponse<Declaration> findAll(DataTableRequest request) {
        List<Declaration> declarations = new ArrayList<>();

        int limit = (request.getCurrentPage() - 1) * request.getPageSize();

        String sql = "select id, doctor_id, patient_id from declaration by " +
                request.getSort() + " " +
                request.getOrder() + " limit " +
                limit + "," +
                request.getPageSize();

        System.out.println("sql = " + sql);

        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(sql)) {
            while (resultSet.next()) {
                DeclarationResultSet declarationResultSet = convertResultSetToDeclaration(resultSet);
                declarations.add(declarationResultSet.getDeclaration());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DataTableResponse<Declaration> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(declarations);
        return tableResponse;
    }

    @Override
    public long count() {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery("select count(*) as count from declaration")) {
            while (resultSet.next()) {
                return resultSet.getLong("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private DeclarationResultSet convertResultSetToDeclaration(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Long doctorId = resultSet.getLong("doctor_id");
        Long patientId = resultSet.getLong("patient_id");

        Declaration declaration = new Declaration();
        declaration.setId(id);
        declaration.setDoctorId(doctorId);
        declaration.setPatientId(patientId);
        return new DeclarationResultSet(declaration);
    }

    private static class DeclarationResultSet {

        private final Declaration declaration;

        private DeclarationResultSet(Declaration declaration) {
            this.declaration = declaration;
        }

        public Declaration getDeclaration() {
            return declaration;
        }

    }
}
