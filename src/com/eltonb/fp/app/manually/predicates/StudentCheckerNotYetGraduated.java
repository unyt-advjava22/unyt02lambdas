package com.eltonb.fp.app.manually.predicates;

import com.eltonb.fp.model.Student;

public class StudentCheckerNotYetGraduated implements StudentChecker {
    @Override
    public boolean check(Student s) {
        return ! s.graduated();
    }
}
