package io.github.shirohoo.modelattribute.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Person {

    private String name;
    private int age;

    private Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static Person of(String name, int age) {
        return new Person(name, age);
    }

}
