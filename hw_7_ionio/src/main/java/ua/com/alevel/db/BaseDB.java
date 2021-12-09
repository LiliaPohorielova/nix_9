package ua.com.alevel.db;

import ua.com.alevel.entity.BaseEntity;

import java.util.ArrayList;

public interface BaseDB<ENTITY extends BaseEntity> {

    void create(ENTITY entity);
    void update(ENTITY entity);
    void delete(String id);
    ENTITY findById(String id);
    ArrayList<ENTITY> findAll();
}
