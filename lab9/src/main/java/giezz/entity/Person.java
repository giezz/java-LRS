package giezz.entity;

import giezz.framework.annotation.Column;
import giezz.framework.annotation.Table;

import java.util.Date;

@Table("person")
public class Person {
    @Column(name = "id")
    private int id;
    @Column
    private String name;

    private Date date;
}
