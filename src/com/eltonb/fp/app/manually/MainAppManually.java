package com.eltonb.fp.app.manually;

import com.eltonb.fp.app.Utils;
import com.eltonb.fp.app.manually.comparators.StudentComparatorByGpa;
import com.eltonb.fp.app.manually.consumers.StudentHonorsIssuer;
import com.eltonb.fp.app.manually.consumers.StudentNotifSender;
import com.eltonb.fp.app.manually.consumers.StudentProcessor;
import com.eltonb.fp.app.manually.consumers.StudentTranscriptPrinter;
import com.eltonb.fp.app.manually.predicates.*;
import com.eltonb.fp.data.impl.StudentRepositoryFromFileImpl;
import com.eltonb.fp.data.interfaces.StudentRepository;
import com.eltonb.fp.model.Gender;
import com.eltonb.fp.model.Level;
import com.eltonb.fp.model.Student;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainAppManually {

    private final StudentRepository studentRepository;

    public MainAppManually() {
        studentRepository = new StudentRepositoryFromFileImpl();
    }

    public static void main(String[] args) {
        try {
            MainAppManually app = new MainAppManually();
            app.go();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void go() {
        List<Student> students = studentRepository.getAll();
        printTranscriptForFemaleUndergrads(students);
        sendNotifToCSStudentsInGpaRangeSortedByGpa(students, 2.00, 2.5);
        issueHonorsToActiveStudents(students);
    }

    private void sendNotifToCSStudentsInGpaRangeSortedByGpa(List<Student> students, double lo, double hi) {
        doStuff(students,
                new StudentNotifSender(),
                new StudentComparatorByGpa(),
                new StudentCheckerByDepartment("CS"),
                new StudentCheckerByGpaRange(2, 2.5)
        );
    }

    private void sendNotifToCSStudentsInGpaRangeSortedByGpa2(List<Student> students, double lo, double hi) {
        doStuff(students,
                s -> Utils.sendNotification(s),
                Comparator.comparing(Student::gpa),
                s -> "CS".equals(s.department()),
                s -> lo <= s.gpa() && s.gpa() <= hi
        );
    }

    private void printTranscriptForFemaleUndergrads(List<Student> students) {
        doStuff(students,
                new StudentTranscriptPrinter(),
                new StudentCheckerByGender(Gender.FEMALE),
                new StudentCheckerByLevel(Level.UNDERGRAD)
        );
    }

    private void issueHonorsToActiveStudents(List<Student> students) {
        doStuff(students, new StudentHonorsIssuer(), new StudentCheckerNotYetGraduated(), new StudentCheckerByGpaRange(3.5, 4.00));
    }

    private void doStuff(List<Student> students, StudentProcessor processor, StudentChecker ... checkers) {
        doStuff(students, processor, null, checkers);
    }

    private void doStuff(List<Student> students, StudentProcessor processor, Comparator<Student> comparator, StudentChecker ... checkers) {
        List<Student> checkedStudents = new ArrayList<>();
        for (Student s : students) {
            if (satisfiesAll(s, checkers))
                checkedStudents.add(s);
        }

        if (comparator != null)
            checkedStudents.sort(comparator);

        for (Student s : checkedStudents)
            processor.process(s);
    }

    private boolean satisfiesAll(Student s, StudentChecker ... checkers) {
        for (StudentChecker checker : checkers) {
            if (! checker.check(s) )
                return false;
        }
        return true;
    }


}
