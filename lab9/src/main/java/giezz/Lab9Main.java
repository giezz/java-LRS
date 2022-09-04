package giezz;

import giezz.entity.Student;
import giezz.framework.Session;

public class Lab9Main {
    public static void main(String[] args) {
        Student student = new Student();
        student.setId(1);
        student.setFirstName("Misha");
        student.setMiddleName("Alekseevich");
        student.setLastName("Batukhtin");
        Session.persist(student);
    }
}