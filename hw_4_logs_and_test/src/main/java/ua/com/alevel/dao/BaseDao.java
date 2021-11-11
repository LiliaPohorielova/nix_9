package ua.com.alevel.dao;

import ua.com.alevel.entity.BaseEntity;
import ua.com.alevel.util.MyList;

public interface BaseDao<ENTITY extends BaseEntity>{

    void create(ENTITY entity);
    void update(ENTITY entity);
    void delete(String id);
    ENTITY findById(String id);
    MyList<ENTITY> findAll();
}
