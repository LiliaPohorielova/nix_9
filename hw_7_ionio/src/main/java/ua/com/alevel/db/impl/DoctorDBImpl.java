package ua.com.alevel.db.impl;

import ua.com.alevel.db.DoctorDB;
import ua.com.alevel.entity.Doctor;
import static ua.com.alevel.ParseCSVUtil.*;
import static ua.com.alevel.util.CheckFile.checkFilePath;
import static ua.com.alevel.util.FindByID.findObjectByID;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DoctorDBImpl implements DoctorDB {

    private final ArrayList<Doctor> doctors;
    private static DoctorDBImpl instance;

    private DoctorDBImpl() {
        doctors = new ArrayList<>();
    }

    public static DoctorDBImpl getInstance() {
        if (instance == null) {
            instance = new DoctorDBImpl();
        }
        return instance;
    }

    public void create(Doctor doctor) {
        doctor.setId(generateId());
        //doctors.add(doctor);
        try {
            saveObjectToCSV(doctor);
        } catch (IOException e) {
            System.out.println("error = " + e.getMessage());
        }
    }

    public void update(Doctor doctor) {
//        Doctor temp = findById(doctor.getId());
//        temp.setName(doctor.getName());
//        temp.setSpecialization(doctor.getSpecialization());
        Doctor temp;
        try {
            //id = doctor.getClass().getSuperclass().getField("id").get(doctor).toString();
            if (findObjectByID(Doctor.class,doctor.getId()) != null)
                deleteInDB(doctor.getClass(), doctor.getId());
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        try {
            saveObjectToCSV(doctor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(String id) {
        try {
            if (findObjectByID(Doctor.class,id) != null)
                deleteInDB(Doctor.class, id);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }

    public Doctor findById(String id) {
        try {
            return findObjectByID(Doctor.class,id);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Not found Doctor's ID");
    }

    public ArrayList<Doctor> findAll() {
        try {
            return getObjectsFromCSV(Doctor.class);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getId().equals(id)) {
                return generateId();
            }
        }
        return id;
    }

    public static <T> void deleteInDB(Class<T> clazz, String id) throws InstantiationException, IllegalAccessException {
        String fileName = clazz.getSimpleName().toLowerCase() + ".csv";
        List<String> input = checkFilePath(fileName);
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).contains(id) ) {
                input.remove(i);
                i--;
            } else {
                output.append(input.get(i));
                output.append("\n");
            }
        }
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(output.toString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}