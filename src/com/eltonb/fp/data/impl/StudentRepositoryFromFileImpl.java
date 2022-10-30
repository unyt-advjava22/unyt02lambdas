package com.eltonb.fp.data.impl;

import com.eltonb.fp.data.interfaces.StudentRepository;
import com.eltonb.fp.model.Gender;
import com.eltonb.fp.model.Level;
import com.eltonb.fp.model.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryFromFileImpl implements StudentRepository {

    @Override
    public List<Student> getAll() {
        try {
            return retrieveStudents();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<Student> retrieveStudents() throws IOException {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("data/students.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Student student = newStudent(line);
                students.add(student);
            }
        }
        return students;
    }

    private Student newStudent(String dataLine) {
        String[] data = dataLine.split(" ");
        int id = Integer.parseInt(data[0]);
        String name = data[1];
        String surname = data[2];
        return new Student
                        .Builder(id, name, surname)
                        .department(data[3])
                        .level(Level.valueOf(data[4]))
                        .email(data[5])
                        .graduated("YES".equals(data[6]))
                        .gender(Gender.fromCode(data[7]))
                        .gpa(Double.parseDouble(data[8]))
                        .earnedCredits(Integer.parseInt(data[9]))
                        .build();
    }

}
