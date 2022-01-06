package repository;

import model.Teacher;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class TeachersRepositoryTest {

    @Test
    void getAll() throws FileNotFoundException {
        TeacherJdbcRepository teachersRepository = new TeacherJdbcRepository();
        teachersRepository.getAll().forEach(System.out::println);
        assert(teachersRepository.getAll().size() == 2);
        assert(teachersRepository.getAll().get(0).getTeacherID() == 1);
    }

    @Test
    void create() throws FileNotFoundException {
        TeacherJdbcRepository teachersRepository = new TeacherJdbcRepository();

        List<Integer> courses = new ArrayList<>();
        courses.add(1);
        courses.add(2);
        Teacher testCaseTeacher = new Teacher(3,"Cristea", "Diana", courses);

        try {
            teachersRepository.create(testCaseTeacher);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert(teachersRepository.getAll().size() == 3);
    }

    @Test
    void delete() throws FileNotFoundException {
        TeacherJdbcRepository teachersRepository = new TeacherJdbcRepository();

        List<Integer> courses = new ArrayList<>();
        courses.add(1);
        courses.add(2);
        Teacher testCaseTeacher = new Teacher(3,"Cristea", "Diana", courses);

        try {
            teachersRepository.delete(testCaseTeacher);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert(teachersRepository.getAll().size() == 2);
    }

    @Test
    void update() throws FileNotFoundException {
        TeacherJdbcRepository teachersRepository = new TeacherJdbcRepository();

        List<Integer> allCourses = new ArrayList<>();
        allCourses.add(1);
        allCourses.add(2);

        Teacher oldTeacher = new Teacher(5, "Sacarea", "Christian", allCourses);

        try {
            teachersRepository.create(oldTeacher);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Teacher newTeacher = new Teacher(5, "Mada", "Dicu", allCourses);

        try {
            teachersRepository.update(oldTeacher,newTeacher);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert(teachersRepository.getAll().get(2).getLastName().equals("Dicu"));
        assert(teachersRepository.getAll().get(2).getFirstName().equals("Mada"));

        try {
            teachersRepository.delete(newTeacher);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}