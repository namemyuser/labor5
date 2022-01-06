package repository;

import model.Course;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


class CoursesRepositoryTest {
    @Test
    void getAll() throws FileNotFoundException {
        CourseJdbcRepository coursesRepository = new CourseJdbcRepository();
        assert(coursesRepository.getAll().size() == 2);
        assert(coursesRepository.getAll().get(0).getCourseID() == 1);
    }

    @Test
    void create() throws FileNotFoundException {
        CourseJdbcRepository coursesRepository = new CourseJdbcRepository();
        List<Integer> studentList = new ArrayList<>();
        studentList.add(0);
        studentList.add(1);
        studentList.add(2);
        Course testCourse = new Course(3, "asc", 1, 10, studentList,30);

        try {
            coursesRepository.create(testCourse);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert(coursesRepository.getAll().size() == 3);

    }

    @Test
    void update() throws FileNotFoundException {
        CourseJdbcRepository coursesRepository = new CourseJdbcRepository();
        List<Integer> studentList1 = new ArrayList<>();
        studentList1.add(1);
        studentList1.add(2);
        studentList1.add(3);
        Course newVersion = new Course(3, "DSA", 1, 10, studentList1,30);

        List<Integer> studentList2 = new ArrayList<>();
        studentList2.add(0);
        studentList2.add(1);
        studentList2.add(2);
        Course oldVersion = new Course(3, "asc", 1, 10, studentList2,30);


        try {
            coursesRepository.update(oldVersion, newVersion);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert(coursesRepository.getAll().get(2).getName().equals("DSA"));


    }


    @Test
    void delete() throws FileNotFoundException {
        CourseJdbcRepository coursesRepository = new CourseJdbcRepository();
        List<Integer> studentList = new ArrayList<>();
        Course testCourse = new Course(3, "DSA", 1, 10, studentList,30);

        try {
            coursesRepository.delete(testCourse);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert(coursesRepository.getAll().size() == 2);
    }


}