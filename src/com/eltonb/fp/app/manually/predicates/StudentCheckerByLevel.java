package com.eltonb.fp.app.manually.predicates;

import com.eltonb.fp.model.Gender;
import com.eltonb.fp.model.Level;
import com.eltonb.fp.model.Student;

public class StudentCheckerByLevel implements StudentChecker {
    private final Level level;

    public StudentCheckerByLevel(Level level) {
        this.level = level;
    }

    @Override
    public boolean check(Student s) {
        return level == s.level();
    }
}
