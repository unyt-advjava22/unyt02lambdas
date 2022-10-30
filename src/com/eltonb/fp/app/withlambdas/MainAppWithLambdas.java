package com.eltonb.fp.app.withlambdas;

import com.eltonb.fp.app.Utils;
import com.eltonb.fp.data.impl.StudentRepositoryFromFileImpl;
import com.eltonb.fp.data.interfaces.StudentRepository;
import com.eltonb.fp.model.Gender;
import com.eltonb.fp.model.Level;
import com.eltonb.fp.model.Student;

import java.util.Comparator;
import java.util.List;
import static com.eltonb.fp.app.Utils.not;

public class MainAppWithLambdas {

    private final StudentRepository studentRepository;

    public MainAppWithLambdas() {
        studentRepository = new StudentRepositoryFromFileImpl();
    }

    public static void main(String[] args) {
        try {
            MainAppWithLambdas app = new MainAppWithLambdas();
            app.go();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void go() {
        List<Student> students = studentRepository.getAll();
        printTranscriptForFemaleUndergrads(students);
        sendNotifToCSStudentsInGpaRangeSortedByGpa(students, 2.00, 2.50);
        issueHonorsToActiveStudents(students);
    }

    private void sendNotifToCSStudentsInGpaRangeSortedByGpa(List<Student> students, double lo, double hi) {
        students.stream()
                .filter(s -> "CS".equals(s.department()))
                .filter(s -> s.gpa() >= lo)
                .filter(s -> s.gpa() <= hi)
                .sorted(Comparator.comparing(Student::gpa))
                .forEach(Utils::sendNotification);
    }

    private void printTranscriptForFemaleUndergrads(List<Student> students) {
        students.stream()
                .filter(s -> s.gender() == Gender.FEMALE)
                .filter(s -> s.level() == Level.UNDERGRAD)
                .forEach(Utils::printTranscript);
    }

    private void issueHonorsToActiveStudents(List<Student> students) {
        students.stream()
                .filter(not(Student::graduated))
                .filter(s -> s.gpa() >= 3.5)
                .forEach(Utils::issueHonors);
    }

}
