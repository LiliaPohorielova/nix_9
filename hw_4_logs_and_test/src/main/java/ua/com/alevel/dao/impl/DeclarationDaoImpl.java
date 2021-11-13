package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.DeclarationDao;
import ua.com.alevel.db.impl.DeclarationDBImpl;
import ua.com.alevel.entity.Declaration;
import ua.com.alevel.util.MyList;

public class DeclarationDaoImpl implements DeclarationDao {

    public void create(Declaration declaration) {
        DeclarationDBImpl.getInstance().create(declaration);
    }

    public void update(Declaration declaration) {
        DeclarationDBImpl.getInstance().update(declaration);
    }

    public void delete(String id) {
        DeclarationDBImpl.getInstance().delete(id);
    }

    public Declaration findById(String id) {
        return DeclarationDBImpl.getInstance().findById(id);
    }

    public MyList<Declaration> findAll() {
        return DeclarationDBImpl.getInstance().findAll();
    }
}
