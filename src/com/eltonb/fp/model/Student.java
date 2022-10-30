package com.eltonb.fp.model;

import java.util.Objects;

public class Student {

    private final int id;
    private final String name;
    private final String surname;
    private final Level level;
    private final String email;
    private final boolean graduated;
    private final Gender gender;
    private final double gpa;
    private final int earnedCredits;
    private final String department;

    public Student(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.department = builder.department;
        this.email = builder.email;
        this.level = builder.level;
        this.gender = builder.gender;
        this.gpa = builder.gpa;
        this.earnedCredits = builder.earnedCredits;
        this.graduated = builder.graduated;
    }

    public int id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String surname() {
        return surname;
    }

    public Level level() {
        return level;
    }

    public String email() {
        return email;
    }

    public boolean graduated() {
        return graduated;
    }

    public Gender gender() {
        return gender;
    }

    public double gpa() {
        return gpa;
    }

    public int earnedCredits() {
        return earnedCredits;
    }

    public String department() {return department;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id &&
                graduated == student.graduated &&
                Double.compare(student.gpa, gpa) == 0 &&
                earnedCredits == student.earnedCredits &&
                Objects.equals(name, student.name) &&
                Objects.equals(surname, student.surname) &&
                level == student.level &&
                Objects.equals(email, student.email) &&
                gender == student.gender &&
                Objects.equals(department, student.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, level, email, graduated, gender, gpa, earnedCredits, department);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", level=" + level +
                ", email='" + email + '\'' +
                ", graduated=" + graduated +
                ", gender=" + gender +
                ", gpa=" + gpa +
                ", earnedCredits=" + earnedCredits +
                ", department=" + department +
                '}';
    }

    public static class Builder {
        private final int id;
        private final String name;
        private final String surname;
        private Level level = Level.UNDERGRAD;
        private Gender gender = null;
        private String email = null;
        private boolean graduated = false;
        private double gpa = 0.0;
        private int earnedCredits = 0;
        private String department = null;

        public Builder(int id, String name, String surname) {
            this.id = id;
            this.name = name;
            this.surname = surname;
        }

        public Builder gender(Gender gender) {
            this.gender = gender;
            return this;
        }
        public Builder level(Level level) {
            this.level = level;
            return this;
        }
        public Builder email(String email) {
            this.email = email;
            return this;
        }
        public Builder department(String department) {
            this.department = department;
            return this;
        }
        public Builder gpa(double gpa) {
            this.gpa = gpa;
            return this;
        }
        public Builder earnedCredits(int earnedCredits) {
            this.earnedCredits = earnedCredits;
            return this;
        }
        public Builder graduated(boolean graduated) {
            this.graduated = graduated;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }
}
