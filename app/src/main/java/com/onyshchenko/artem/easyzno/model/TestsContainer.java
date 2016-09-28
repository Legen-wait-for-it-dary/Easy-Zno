package com.onyshchenko.artem.easyzno.model;

import java.util.ArrayList;
import java.util.List;

public class TestsContainer {
    private static TestsContainer ourInstance = new TestsContainer();
    private List<Test> tests;

    public static TestsContainer getInstance() {
        return ourInstance;
    }

    public void addTest(Test test) {
        tests.add(test);
    }

    public List<Test> getTests(int subjectId) {
        List<Test> specificTests = new ArrayList<>();
        for(Test item:tests) {
            if(item.getSubjectId() == subjectId) {
                specificTests.add(item);
            }
        }
        return specificTests;
    }

    private TestsContainer() {
        tests = new ArrayList<>();
        tests.add(new Test(0, "Основний тест", 2007));

        tests.add(new Test(1, "Основний тест", 2008));
        tests.add(new Test(1, "Основний тест", 2009));

        tests.add(new Test(2, "Основний тест", 2010));
        tests.add(new Test(2, "Основний тест", 2010));
        tests.add(new Test(2, "Основний тест", 2010));
    }
}
