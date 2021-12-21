package ua.com.alevel.persistence.dao.query;

public class JpaQueryUtil {

    private JpaQueryUtil() {
    }

    public static final String CREATE_DOCTOR_QUERY = "INSERT INTO doctor VALUES(default,?,?,?,?,?,?,?)";
    public static final String INSERT_DECLARATION = "INSERT INTO declaration VALUES(default,?,?)";
    public static final String UPDATE_DOCTOR_BY_ID_QUERY = "UPDATE doctor SET last_name = ?, first_name = ?, middle_name = ?, specialization = ?, updated = ? WHERE id = ";
    public static final String DELETE_DOCTOR_BY_ID_QUERY = "DELETE FROM doctor WHERE id = ";
    public static final String EXIST_DOCTOR_BY_ID_QUERY = "SELECT COUNT(*) FROM doctor WHERE id = ";
    public static final String FIND_ALL_DOCTORS_QUERY = "SELECT * FROM doctor";
    public static final String FIND_DOCTOR_BY_ID_QUERY = "SELECT * FROM doctor WHERE id = ";
    public static final String FIND_ALL_DOCTORS_BY_PATIENT_ID_QUERY = "SELECT * FROM declaration WHERE patient_id = ";

    public static final String CREATE_PATIENT_QUERY = "INSERT INTO patient VALUES(default,?,?,?,?,?,?)";
    public static final String UPDATE_PATIENT_BY_ID_QUERY = "UPDATE patient SET first_name = ?, last_name = ?,age = ?, updated = ? WHERE id = ";
    public static final String DELETE_PATIENT_BY_ID_QUERY = "DELETE FROM patient WHERE id = ";
    public static final String EXIST_PATIENT_BY_ID_QUERY = "SELECT COUNT(*) FROM patient WHERE id = ";
    public static final String FIND_PATIENT_BY_ID_QUERY = "SELECT * FROM patient WHERE id = ";
    public static final String FIND_ALL_PATIENTS_QUERY = "SELECT * FROM patient";
    public static final String FIND_ALL_PATIENTS_BY_DOCTOR_ID_QUERY = "SELECT * FROM declaration WHERE doctor_id = ";

    public static final String CREATE_DECLARATION_QUERY = "INSERT INTO declaration VALUES(default,?,?)";
    public static final String UPDATE_DECLARATION_BY_ID_QUERY = "UPDATE declaration SET doctor_id = ?, patient_id = ? WHERE id = ";
    public static final String DELETE_DECLARATION_BY_ID_QUERY = "DELETE FROM declaration WHERE id = ";
    public static final String EXIST_DECLARATION_BY_ID_QUERY = "SELECT COUNT(*) FROM declaration WHERE id = ";
    public static final String FIND_ALL_DECLARATIONS_QUERY = "SELECT * FROM declaration";
    public static final String FIND_DECLARATION_BY_ID_QUERY = "SELECT * FROM declaration WHERE id = ";
    public static final String FIND_DECLARATION_BY_PATIENT_ID_AND_DOCTOR_ID_QUERY = "SELECT * FROM declaration WHERE patient_id = ? AND doctor_id = ?";
}
