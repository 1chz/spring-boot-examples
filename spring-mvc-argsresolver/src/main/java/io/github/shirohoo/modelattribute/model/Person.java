package io.github.shirohoo.modelattribute.model;

import lombok.ToString;

@ToString
public class Person {

    private String name;

    private int age;

    public Person(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
