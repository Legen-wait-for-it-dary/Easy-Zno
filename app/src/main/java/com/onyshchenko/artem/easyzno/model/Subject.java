package com.onyshchenko.artem.easyzno.model;

import java.util.Random;

public class Subject {
    private int id;
    private String name;
    private int numberTests;

    public Subject() {
    }

    public Subject(int id, String name) {
        this.id = id;
        this.name = name;
        numberTests = new Random().nextInt(30);
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

    public int getNumberTests() {
        return numberTests;
    }

    public void setNumberTests(int numberTests) {
        this.numberTests = numberTests;
    }
}
