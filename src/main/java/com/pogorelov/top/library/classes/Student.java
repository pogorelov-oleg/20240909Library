package com.pogorelov.top.library.classes;

public class Student {
    private final String fullName;
    private final int age;
    private final int groupID;
    private int libraryCardId;

    public Student(String fullName, int age, int groupId) {
        this.fullName = fullName;
        this.age = age;
        this.groupID = groupId;
        this.libraryCardId = 0;
    }

    public String getFullName() {
        return fullName;
    }

    public int getGroupID() {
        return groupID;
    }

    public int getLibraryCardId() {
        return libraryCardId;
    }

    public void setLibraryCardId(int libraryCardId) {
        this.libraryCardId = libraryCardId;
    }

    @Override
    public String toString() {
        return String.format("ФИО: %s\nВозраст: %d\nНомер группы: %d", fullName, age, groupID);
    }
}
