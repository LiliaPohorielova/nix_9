package ua.com.alevel.entity;

public class Patient extends BaseEntity {

    private String name;
    private int age;

    public Patient() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id='" + super.getId() + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}