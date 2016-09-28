package com.onyshchenko.artem.easyzno.model;


import java.util.ArrayList;
import java.util.List;

public class SubjectContainer {
    private static SubjectContainer ourInstance = new SubjectContainer();
    private List<Subject> subjects;

    public static SubjectContainer getInstance() {
        return ourInstance;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    private SubjectContainer() {
        subjects = new ArrayList<>();
        subjects.add(new Subject(0, "Математика"));
        subjects.add(new Subject(1, "Українська мова і література"));
        subjects.add(new Subject(2, "Англійська мова"));
    }
}
