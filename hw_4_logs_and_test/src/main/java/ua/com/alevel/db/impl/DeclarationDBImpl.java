package ua.com.alevel.db.impl;

import ua.com.alevel.db.DeclarationDB;
import ua.com.alevel.entity.Declaration;
import ua.com.alevel.util.MyList;

import java.util.UUID;

public class DeclarationDBImpl implements DeclarationDB {

    private final MyList<Declaration> declarations;
    private static DeclarationDBImpl instance;

    private DeclarationDBImpl() {
        declarations = new MyList<>();
    }

    public static DeclarationDBImpl getInstance() {
        if (instance == null) {
            instance = new DeclarationDBImpl();
        }
        return instance;
    }

    public void create(Declaration declaration) {
        declaration.setId(generateId());
        declarations.add(declaration);
    }

    public void update(Declaration declaration) {
        Declaration temp = findById(declaration.getId());
        temp.setIdPatient(declaration.getIdPatient());
        temp.setIdDoctor(declaration.getIdDoctor());
    }

    public void delete(String id) {
        for (int i = 0; i < declarations.getCountOfEntities(); i++) {
            if (declarations.getEntity(i).getId().equals(id)) {
                declarations.delete(i);
            }
        }

    }

    public Declaration findById(String id) {
        for (int i = 0; i < declarations.getCountOfEntities(); i++) {
            if (declarations.getEntity(i).getId().equals(id)) {
                return declarations.getEntity(i);
            }
        }
        throw new RuntimeException("Declaration not found by id");
    }

    public MyList<Declaration> findAll() {

        return declarations;
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        for (int i = 0; i < declarations.getCountOfEntities(); i++) {
            if (declarations.getEntity(i).getId().equals(id)) {
                return generateId();
            }
        }
        return id;
    }

    public void showAllToConsole() {
        if (declarations.getCountOfEntities() != 0) {
            for (int i = 0; i < declarations.getCountOfEntities(); i++) {
                System.out.println(declarations.getEntity(i));
            }
        } else {
            System.out.println("[empty]");
        }
    }
}