package giezz.entity;

import giezz.framework.annotation.Column;
import giezz.framework.annotation.Table;

@Table("person1")
public class Person {
    @Column(name = "id")
    private int id;
    @Column
    private String name;

    @Column
    private Gender gender;

    public Person(int id, String name, Gender gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }

    public enum Gender {
        MALE, FEMALE
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
