package ua.com.alevel.db.impl;

import ua.com.alevel.db.DeclarationDB;
import ua.com.alevel.entity.Declaration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import static ua.com.alevel.ParseCSVUtil.getObjectsFromCSV;
import static ua.com.alevel.ParseCSVUtil.saveObjectToCSV;
import static ua.com.alevel.util.DeleteUtil.deleteInDB;
import static ua.com.alevel.util.FindByID.findObjectByID;

public class DeclarationDBImpl implements DeclarationDB {

    private final ArrayList<Declaration> declarations;
    private static DeclarationDBImpl instance;

    private DeclarationDBImpl() {
        declarations = new ArrayList<>();
    }

    public static DeclarationDBImpl getInstance() {
        if (instance == null) {
            instance = new DeclarationDBImpl();
        }
        return instance;
    }

    public void create(Declaration declaration) {
        declaration.setId(generateId());
        try {
            saveObjectToCSV(declaration);
        } catch (IOException e) {
            System.out.println("error = " + e.getMessage());
        }
    }

    public void update(Declaration declaration) {
        try {
            findObjectByID(Declaration.class, declaration.getId());
            deleteInDB(declaration.getClass(), declaration.getId());
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        try {
            saveObjectToCSV(declaration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(String id) {
        try {
            findObjectByID(Declaration.class, id);
            deleteInDB(Declaration.class, id);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }

    public Declaration findById(String id) {
        try {
            return findObjectByID(Declaration.class, id);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Not found Doctor's ID");
    }

    public ArrayList<Declaration> findAll() {
        try {
            return getObjectsFromCSV(Declaration.class);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        for (Declaration declaration : declarations) {
            if (declaration.getId().equals(id)) {
                return generateId();
            }
        }
        return id;
    }
}