package com.eltonb.fp.motivation;

import com.eltonb.fp.data.impl.StudentRepositoryFromFileImpl;
import com.eltonb.fp.data.interfaces.StudentRepository;
import com.eltonb.fp.model.Student;
import static com.eltonb.fp.app.Utils.not;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class BestStudentsAppWithLambdas {
    /*
        Implement the following requirement:
        From the currently active students
        For each department
        Find the student with highest gpa (if tied, next consider credits)
        And list them in increasing order of surname (if tied, next consider name)
     */

    private List<Student> students;

    public BestStudentsAppWithLambdas() {
        StudentRepository repo = new StudentRepositoryFromFileImpl();
        students = repo.getAll();
    }

    public static void main(String[] args) {
        try {
            BestStudentsAppWithLambdas app = new BestStudentsAppWithLambdas();
            app.listBestStudents();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listBestStudents() {
        students.stream()
                .filter(s -> ! s.graduated())
                .collect(Collectors.groupingBy(
                        Student::department,
                        maxBy(Comparator.comparing(Student::gpa)
                                .thenComparing(Student::earnedCredits))
                        )
                )
                .values()
                .stream()
                .map(Optional::get)
                .sorted(Comparator
                        .comparing(Student::surname)
                        .thenComparing(Student::name))
                .forEach(this::displayStudent);
    }

    private void displayStudent(Student s) {
        System.out.printf("%5s: %15s %15s [%.2f / %3d]\n", s.department(), s.name(), s.surname(), s.gpa(), s.earnedCredits());
    }

}
