package com.eltonb.fp.app.nolambdas;

import com.eltonb.fp.app.Utils;
import com.eltonb.fp.data.interfaces.StudentRepository;
import com.eltonb.fp.data.impl.StudentRepositoryFromFileImpl;
import com.eltonb.fp.model.Gender;
import com.eltonb.fp.model.Level;
import com.eltonb.fp.model.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainAppNoLambdas {

    private final StudentRepository studentRepository;

    public MainAppNoLambdas() {
        studentRepository = new StudentRepositoryFromFileImpl();
    }

    public static void main(String[] args) {
        try {
            MainAppNoLambdas app = new MainAppNoLambdas();
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
        List<Student> sortedStudents = new ArrayList<>(students);
        sortedStudents.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (o1.gpa() < o2.gpa())
                    return -1;
                if (o1.gpa() > o2.gpa())
                    return 1;
                return 0;
            }
        });
        for (Student s : students) {
            if ("CS".equals(s.department()) && s.gpa() >= lo && s.gpa() <= hi) {
                Utils.sendNotification(s);
            }
        }
    }

    private void printTranscriptForFemaleUndergrads(List<Student> students) {
        for (Student s : students) {
            if (s.gender() == Gender.FEMALE && s.level() == Level.UNDERGRAD) {
                Utils.printTranscript(s);
            }
        }
    }

    private void issueHonorsToActiveStudents(List<Student> students) {
        for (Student s : students) {
            if (! s.graduated() && s.gpa() >= 3.5) {
                Utils.issueHonors(s);
            }
        }
    }


}
