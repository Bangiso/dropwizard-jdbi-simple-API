package com.aphiwe.jdbi3.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public  class Student{
    @JsonProperty
    private int id;
    @JsonProperty
    private String name;
    @JsonProperty
    private double gpa;


    public Student(int id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }
    public Student() {
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
    public double getGpa() {
        return gpa;
    }
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return getId() == student.getId() &&
                Double.compare(student.getGpa(), getGpa()) == 0 &&
                getName().equals(student.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getGpa());
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gpa=" + gpa +
                '}';
    }


}
