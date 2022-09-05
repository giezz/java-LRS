package giezz;

import giezz.entity.Person;
import giezz.framework.Session;

public class Lab9Main {
    public static void main(String[] args) {
        Person person = new Person(
                1,
                "Dasha",
                Person.Gender.FEMALE
        );
        Session.persist(person);
//        System.out.println(Enum.class.getSimpleName());
    }
}