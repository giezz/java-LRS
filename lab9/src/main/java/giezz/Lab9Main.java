package giezz;

import giezz.entity.Person;
import giezz.framework.Session;
import giezz.framework.TableBuilder;
import giezz.framework.annotation.Table;

public class Lab9Main {
    public static void main(String[] args) {
        TableBuilder.buildTable(Person.class);
        Session.persist(new Person(1, "name", Person.Gender.FEMALE));
    }
}