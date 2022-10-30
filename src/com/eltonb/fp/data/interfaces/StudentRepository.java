package com.eltonb.fp.data.interfaces;

import com.eltonb.fp.model.Student;

import java.util.List;

public interface StudentRepository {

    List<Student> getAll();
}
