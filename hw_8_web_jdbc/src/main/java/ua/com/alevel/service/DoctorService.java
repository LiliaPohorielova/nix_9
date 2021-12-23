package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Doctor;

import java.util.List;

public interface DoctorService extends BaseService<Doctor> {

    List<Doctor> findAll();
}