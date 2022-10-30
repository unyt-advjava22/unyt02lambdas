package com.eltonb.fp.motivation;

import com.eltonb.fp.data.impl.StudentRepositoryFromFileImpl;
import com.eltonb.fp.data.interfaces.StudentRepository;
import com.eltonb.fp.model.Student;

import java.util.*;

public class BestStudentsAppNoLambdas {
    /*
        Implement the following requirement:
        From the currently active students
        For each department
        Find the student with highest gpa (if tied, next consider credits)
        And list them in increasing order of surname (if tied, next consider name)
     */

    private List<Student> students;

    public BestStudentsAppNoLambdas() {
        StudentRepository repo = new StudentRepositoryFromFileImpl();
        students = repo.getAll();
    }

    public static void main(String[] args) {
        try {
            var app = new BestStudentsAppNoLambdas();
            app.listBestStudents();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listBestStudents() {
        Map<String, Student> topStudentsMap = new HashMap<>();
        for (Student s : students) {
            if ( ! s.graduated() ) {
                if (! topStudentsMap.containsKey(s.department())) {
                    topStudentsMap.put(s.department(), s);
                } else {
                    Student top = topStudentsMap.get(s.department());
                    if (isBetter(s, top))
                        topStudentsMap.put(s.department(), s);
                }
            }
        }

        Comparator<Student> surnameThenNameComparator = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                int surnameResult = o1.surname().compareTo(o2.surname());
                if (surnameResult == 0)
                    return o1.name().compareTo(o2.name());
                return surnameResult;
            }
        };

        List<Student> topStudents = new ArrayList<>(topStudentsMap.values());
        Collections.sort(topStudents, surnameThenNameComparator);

        System.out.println("Best student for each departments are: ");
        for (Student s : topStudents) {
            System.out.printf("%5s: %15s %15s [%.2f / %3d]\n", s.department(), s.name(), s.surname(), s.gpa(), s.earnedCredits());
        }

    }
    private boolean isBetter(Student s1, Student s2) {
        return  s1.gpa() > s2.gpa() ||
                (s1.gpa() == s2.gpa() && s1.earnedCredits() > s2.earnedCredits());
    }
}
