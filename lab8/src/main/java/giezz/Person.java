package giezz;

public class Person {
    private String name;
    private int age;

    private double salary;
    private Position position;

    private Gender gender;

    public Person(String name, int age, double salary, Position position, Gender gender) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.position = position;
        this.gender = gender;
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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    enum Position {
        ENGINEER, MANAGER, CEO
    }

    enum Gender {
        MALE, FEMALE
    }
}
