package ua.com.alevel.db.impl;

import ua.com.alevel.db.DeclarationDB;
import ua.com.alevel.entity.Declaration;

import java.util.ArrayList;
import java.util.UUID;

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
        declarations.add(declaration);
    }

    public void update(Declaration declaration) {
        Declaration temp = findById(declaration.getId());
        temp.setIdPatient(declaration.getIdPatient());
        temp.setIdDoctor(declaration.getIdDoctor());
    }

    public void delete(String id) {
        for (int i = 0; i < declarations.size(); i++) {
            if (declarations.get(i).getId().equals(id)) {
                declarations.remove(i);
            }
        }

    }

    public Declaration findById(String id) {
        for (int i = 0; i < declarations.size(); i++) {
            if (declarations.get(i).getId().equals(id)) {
                return declarations.get(i);
            }
        }
        throw new RuntimeException("Declaration not found by id");
    }

    public ArrayList<Declaration> findAll() {
        return declarations;
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        for (int i = 0; i < declarations.size(); i++) {
            if (declarations.get(i).getId().equals(id)) {
                return generateId();
            }
        }
        return id;
    }
}