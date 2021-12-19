package ua.com.alevel.persistence.dao.query;

public class JpaQueryUtil {

    private JpaQueryUtil() { }

    public static final String CREATE_DOCTOR_QUERY = "INSERT INTO doctor VALUES(default,?,?,?,?,?,?,?)";
    public static final String INSERT_DECLARATION = "INSERT INTO declaration VALUES(default,?,?)";
    public static final String UPDATE_DOCTOR_BY_ID_QUERY = "UPDATE doctor SET last_name = ?, first_name = ?, middle_name = ?, specialization = ?, updated = ? WHERE id = ";
    public static final String DELETE_DOCTOR_BY_ID_QUERY = "DELETE FROM doctor WHERE id = ";
    public static final String EXIST_DOCTOR_BY_ID_QUERY = "SELECT COUNT(*) FROM doctor WHERE id = ";
    public static final String FIND_ALL_DOCTORS_QUERY = "SELECT * FROM doctor";
    public static final String FIND_DOCTOR_BY_ID_QUERY = "SELECT * FROM doctor WHERE id = ";
    public static final String FIND_ALL_DOCTORS_BY_PATIENT_ID_QUERY = "SELECT * FROM doctor AS d JOIN declaration AS dc ON d.id = dc.doctor_id WHERE d.id =";

    public static final String CREATE_PATIENT_QUERY = "INSERT INTO patient VALUES(default,?,?,?,?,?,?)";
    public static final String UPDATE_PATIENT_BY_ID_QUERY = "UPDATE patient SET first_name = ?, last_name = ?,age = ?, updated = ? WHERE id = ";
    public static final String DELETE_PATIENT_BY_ID_QUERY = "DELETE FROM patient WHERE id = ";
    public static final String EXIST_PATIENT_BY_ID_QUERY = "SELECT COUNT(*) FROM patient WHERE id = ";
    public static final String FIND_PATIENT_BY_ID_QUERY = "SELECT * FROM patient WHERE id = ";
    public static final String FIND_ALL_PATIENTS_QUERY = "SELECT * FROM patient";
    public static final String FIND_ALL_PATIENTS_BY_DOCTOR_ID_QUERY = "SELECT * FROM patient AS p JOIN declaration AS d ON p.id = d.patient_id WHERE d.doctor_id = ";
}
