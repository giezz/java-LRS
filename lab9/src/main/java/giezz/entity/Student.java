package giezz.entity;

import giezz.framework.annotation.Column;
import giezz.framework.annotation.Table;

@Table("student")
public class Student {
    @Column
    private int id;
    @Column
    private String firstName;
    @Column
    private String middleName;
    @Column
    private String lastName;

    public Student() {
    }

    public Student(int id, String firstName, String middleName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
