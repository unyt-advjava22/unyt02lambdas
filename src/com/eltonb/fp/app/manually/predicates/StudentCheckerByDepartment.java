package com.eltonb.fp.app.manually.predicates;

import com.eltonb.fp.model.Gender;
import com.eltonb.fp.model.Student;

public class StudentCheckerByDepartment implements StudentChecker {
    private final String department;

    public StudentCheckerByDepartment(String department) {
        this.department = department;
    }

    @Override
    public boolean check(Student s) {
        return this.department.equals(s.department());
    }
}
