package repository;

import model.Student;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class StudentsRepositoryTest {
    @Test
    void getAll() throws FileNotFoundException {
        StudentJdbcRepository studentsRepository = new StudentJdbcRepository();
        assert(studentsRepository.getAll().size() == 2);
        assert(studentsRepository.getAll().get(0).getStudentId() == 1);
    }

    @Test
    void create() throws FileNotFoundException {
        StudentJdbcRepository studentsRepository = new StudentJdbcRepository();

        List<Integer> courses = new ArrayList<>();
        courses.add(1);
        courses.add(2);
        Student testStudent = new Student("Andrei", "Lapuste", 5, 20, courses);

        try {
            studentsRepository.create(testStudent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert(studentsRepository.getAll().size() == 3);
    }

    @Test
    void delete() throws FileNotFoundException {
        StudentJdbcRepository studentsRepository = new StudentJdbcRepository();

        List<Integer> courses = new ArrayList<>();
        courses.add(1);
        courses.add(2);
        Student testStudent = new Student("Andrei", "Lapuste", 5, 20, courses);

        try {
            studentsRepository.delete(testStudent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert(studentsRepository.getAll().size() == 2);
    }

    @Test
    void update() throws FileNotFoundException {
        StudentJdbcRepository studentsRepository = new StudentJdbcRepository();

        List<Integer> coursesList = new ArrayList<>();
        coursesList.add(1);
        coursesList.add(2);
        Student oldStudent = new Student("Lucas", "Purcsa", 6, 40, coursesList);

        try {
            studentsRepository.create(oldStudent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Student newStudent = new Student("Andrei", "Raulea", 6, 40, coursesList);

        try {
            studentsRepository.update(oldStudent,newStudent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert(studentsRepository.getAll().get(4).getLastName().equals("Raulea"));
        assert(studentsRepository.getAll().get(4).getFirstName().equals("Andrei"));

        try {
            studentsRepository.delete(newStudent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}