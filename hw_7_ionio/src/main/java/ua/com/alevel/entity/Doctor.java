package ua.com.alevel.entity;

public class Doctor extends BaseEntity {

    public String name;
    public String specialization;

    public Doctor() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "ID: " + super.getId() + "\n" +
                "Name: " + name + "\n" +
                "Specialization: " + specialization + "\n";
    }
}
