package ua.com.alevel.persistence.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.dao.PatientDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Doctor;
import ua.com.alevel.persistence.entity.Patient;

import javax.persistence.OptimisticLockException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class PatientDaoImpl implements PatientDao {

    private final SessionFactory sessionFactory;

    public PatientDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Doctor> getDoctors(Long id) {
        return sessionFactory.getCurrentSession().find(Patient.class, id).getDoctors();
    }

    @Override
    public void create(Patient entity) {
        sessionFactory.getCurrentSession().persist(entity);
    }

    @Override
    public void update(Patient entity) {
        sessionFactory.getCurrentSession().merge(entity);
    }

    @Override
    public void delete(Long id) {
        int isSuccessful = sessionFactory.getCurrentSession().createQuery("delete from Patient p where p.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        if (isSuccessful == 0) {
            throw new OptimisticLockException("patient modified concurrently");
        }
    }

    @Override
    public boolean existById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("select count(p.id) from Patient p where p.id = :id")
                .setParameter("id", id);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    public Patient findById(Long id) {
        return sessionFactory.getCurrentSession().find(Patient.class, id);
    }

    @Override
    public DataTableResponse<Patient> findAll(DataTableRequest request) {
        CriteriaBuilder criteriaBuilder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Patient> criteriaQuery = criteriaBuilder.createQuery(Patient.class);
        Root<Patient> from = criteriaQuery.from(Patient.class);
        if (request.getOrder().equals("desc")) {
            criteriaQuery.orderBy(criteriaBuilder.desc(from.get(request.getSort())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(request.getSort())));
        }

        int page = (request.getCurrentPage() - 1) * request.getPageSize();
        int size = page + request.getPageSize();

        List<Patient> patients = sessionFactory.getCurrentSession().createQuery(criteriaQuery)
                .setFirstResult(page)
                .setMaxResults(size)
                .getResultList();

        DataTableResponse<Patient> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(patients);
        return tableResponse;
    }

    @Override
    public long count() {
        Query query = sessionFactory.getCurrentSession().createQuery("select count(p.id) from Patient p");
        return (Long) query.getSingleResult();
    }

    @Override
    public List<Patient> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Patient").list();
    }
}
