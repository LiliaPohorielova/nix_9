package ua.com.alevel.service;

import ua.com.alevel.entity.BaseEntity;

import java.util.ArrayList;

public interface BaseService<ENTITY extends BaseEntity> {

    void create(ENTITY entity);
    void update(ENTITY entity);
    void delete(String id);
    ENTITY findById(String id);
    ArrayList<ENTITY> findAll();
}
