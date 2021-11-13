package ua.com.alevel.service.impl;

import ua.com.alevel.dao.impl.DeclarationDaoImpl;
import ua.com.alevel.entity.Declaration;
import ua.com.alevel.service.DeclarationService;
import ua.com.alevel.util.MyList;

public class DeclarationServiceImpl implements DeclarationService {

    private final DeclarationDaoImpl declarationDao = new DeclarationDaoImpl();

    public void create(Declaration declaration) {
        declarationDao.create(declaration);
    }

    public void update(Declaration declaration) {
        declarationDao.update(declaration);
    }

    public void delete(String id) {
        declarationDao.delete(id);
    }

    public Declaration findById(String id) {
        try {
            return declarationDao.findById(id);
        } catch (RuntimeException exception) {
            throw exception;
        }
    }

    public MyList<Declaration> findAll() {
        try {
            return declarationDao.findAll();
        } catch (RuntimeException exception) {
            throw exception;
        }
    }
}
