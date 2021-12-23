package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Patient;

import java.util.List;

public interface PatientDao extends BaseDao<Patient> {

    List<Patient> findAll();
}