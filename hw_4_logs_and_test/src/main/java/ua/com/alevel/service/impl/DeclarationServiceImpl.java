package ua.com.alevel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dao.impl.DeclarationDaoImpl;
import ua.com.alevel.entity.Declaration;
import ua.com.alevel.service.DeclarationService;
import ua.com.alevel.util.MyList;

public class DeclarationServiceImpl implements DeclarationService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");
    private final DeclarationDaoImpl declarationDao = new DeclarationDaoImpl();

    public void create(Declaration declaration) {
        LOGGER_INFO.info("declaration start created");
        declarationDao.create(declaration);
        LOGGER_INFO.info("declaration finish created, id: " + declaration.getId());
    }

    public void update(Declaration declaration) {
        try {
            LOGGER_INFO.info("declaration start updated, id: " + declaration.getId());
            declarationDao.update(declaration);
            LOGGER_INFO.info("declaration finish updated, id: " + declaration.getId());
        } catch (RuntimeException e) {
            LOGGER_ERROR.error("declaration NOT be updated " + declaration.getId() + "; problem = " + e.getMessage());
            throw e;
        }
    }

    public void delete(String id) {
        try {
            LOGGER_WARN.warn("declaration start deleted " + id);
            declarationDao.delete(id);
            LOGGER_WARN.warn("declaration start deleted " + id);
        } catch (RuntimeException e) {
            LOGGER_ERROR.error("declaration NOT be deleted " + id + "; problem = " + e.getMessage());
            throw e;
        }
    }

    public Declaration findById(String id) {
        try {
            return declarationDao.findById(id);
        } catch (RuntimeException exception) {
            LOGGER_ERROR.error("declaration NOT be found " + id + "; problem = " + exception.getMessage());
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
