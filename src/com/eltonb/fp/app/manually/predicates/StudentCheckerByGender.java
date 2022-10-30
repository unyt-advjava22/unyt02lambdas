package com.eltonb.fp.app.manually.predicates;

import com.eltonb.fp.model.Gender;
import com.eltonb.fp.model.Student;

public class StudentCheckerByGender implements StudentChecker {
    private final Gender gender;

    public StudentCheckerByGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public boolean check(Student s) {
        return this.gender == s.gender();
    }
}
