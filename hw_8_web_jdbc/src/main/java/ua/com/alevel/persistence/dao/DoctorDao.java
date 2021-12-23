package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Doctor;

import java.util.List;

public interface DoctorDao extends BaseDao<Doctor> {

    List<Doctor> findAll();
}