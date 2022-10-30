package com.eltonb.fp.app.manually.predicates;

import com.eltonb.fp.model.Student;

public class StudentCheckerByGpaRange implements StudentChecker {
    private final double lo;
    private final double hi;

    public StudentCheckerByGpaRange(double lo, double hi) {
        this.lo = lo;
        this.hi = hi;
    }

    @Override
    public boolean check(Student s) {
        return s.gpa() >= lo && s.gpa() <= hi;
    }
}
