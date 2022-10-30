package com.eltonb.fp.app.manually.comparators;

import com.eltonb.fp.model.Student;

import java.util.Comparator;

public class StudentComparatorByGpa implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        if (o1.gpa() < o2.gpa())
            return -1;
        if (o1.gpa() > o2.gpa())
            return 1;
        return 0;
    }
}
