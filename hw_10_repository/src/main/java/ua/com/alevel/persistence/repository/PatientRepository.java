package ua.com.alevel.persistence.repository;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.Patient;

@Repository
public interface PatientRepository extends AbstractRepository<Patient> { }
