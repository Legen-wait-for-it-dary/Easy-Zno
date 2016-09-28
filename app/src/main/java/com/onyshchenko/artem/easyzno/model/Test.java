package com.onyshchenko.artem.easyzno.model;

import java.util.Random;
import java.util.UUID;

public class Test {
    private UUID id;
    private int subjectId;
    private String name;
    private int year;
    private int numberQuestions;
    private int averageMark;
    private int averageTime;

    public Test() {
    }

    public Test(int subjectId, String name, int year) {
        id = UUID.randomUUID();
        numberQuestions = new Random().nextInt(70);
        averageMark = new Random().nextInt(200);
        averageTime = new Random().nextInt(180);
        this.subjectId = subjectId;
        this.name = name;
        this.year = year;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getNumberQuestions() {
        return numberQuestions;
    }

    public void setNumberQuestions(int numberQuestions) {
        this.numberQuestions = numberQuestions;
    }

    public int getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(int averageMark) {
        this.averageMark = averageMark;
    }

    public int getAverageTime() {
        return averageTime;
    }

    public void setAverageTime(int averageTime) {
        this.averageTime = averageTime;
    }
}
