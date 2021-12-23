package ua.com.alevel.persistence.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.dao.DoctorDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Doctor;
import ua.com.alevel.persistence.entity.Patient;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class DoctorDaoImpl implements DoctorDao {

    private final SessionFactory sessionFactory;

    public DoctorDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Patient> getPatients(Long id) {
        return sessionFactory.getCurrentSession().find(Doctor.class, id).getPatients();
    }

    @Override
    public void addPatient(Long doctorId, Long patientId) {
        Doctor doctor = sessionFactory.getCurrentSession().find(Doctor.class, doctorId);
        Patient patient = sessionFactory.getCurrentSession().find(Patient.class, patientId);
        doctor.addPatient(patient);
    }

    @Override
    public void removePatient(Long doctorId, Long patientId) {
        Doctor doctor = sessionFactory.getCurrentSession().find(Doctor.class, doctorId);
        Patient patient = sessionFactory.getCurrentSession().find(Patient.class, patientId);
        doctor.removePatient(patient);
    }

    @Override
    public void create(Doctor entity) {
        sessionFactory.getCurrentSession().persist(entity);
    }

    @Override
    public void update(Doctor entity) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(entity);
    }

    @Override
    public void delete(Long id) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("delete from Doctor o where o.id = :id")
                .setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public boolean existById(Long id) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("select count(o.id) from Doctor o where o.id = :id")
                .setParameter("id", id);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    public Doctor findById(Long id) {
        return sessionFactory.getCurrentSession().find(Doctor.class, id);
    }

    @Override
    public DataTableResponse<Doctor> findAll(DataTableRequest request) {
        int page = (request.getCurrentPage() - 1) * request.getPageSize();
        int size = request.getPageSize();
        CriteriaBuilder criteriaBuilder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Doctor> criteriaQuery = criteriaBuilder.createQuery(Doctor.class);
        Root<Doctor> from = criteriaQuery.from(Doctor.class);
        if (request.getOrder().equals("desc")) {
            criteriaQuery.orderBy(criteriaBuilder.desc(from.get(request.getSort())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(request.getSort())));
        }

        List<Doctor> doctors = sessionFactory.getCurrentSession()
                .createQuery(criteriaQuery)
                .setFirstResult(page)
                .setMaxResults(size)
                .getResultList();

        DataTableResponse<Doctor> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(doctors);
        return tableResponse;
    }

    @Override
    public long count() {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("select count(o.id) from Doctor o");
        return (Long) query.getSingleResult();
    }
}
