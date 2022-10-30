package com.eltonb.fp.app.manually.predicates;

import com.eltonb.fp.model.Student;

@FunctionalInterface
public interface StudentChecker {
    boolean check(Student s);
}
