package controller;

import com.google.common.collect.Ordering;
import model.Course;
import model.Student;
import model.Teacher;
import org.junit.jupiter.api.Test;

import java.util.List;

class ControllerTest {
    @Test
    void getAllCourses() {
        Controller controller = new Controller();
        assert(controller.getAllCourses().size() == 2);
    }

    @Test
    void getStudentsEnrolledForCourse() {
        Controller controller = new Controller();
        assert(controller.getStudentsEnrolledForCourse(2).size() == 2);
    }

    @Test
    void registerStudentToCourse() {
        Controller controller = new Controller();



        try {
            controller.registerStudentToCourse(controller.getAllStudents().get(2),controller.getAllCourses().get(1));
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(controller.getAllCourses().get(1).getCourseID());
        System.out.println(controller.getAllStudents().get(2).getStudentId());

        assert(controller.getAllStudents().get(2).getEnrolledCourses().contains(controller.getAllCourses().get(1).getCourseID()));
    }

    @Test
    void modifyCourseCredits() {
        /*
         * FOR THIS TEST, COPY PASTE DATA FROM test/java/repo/data IN EACH CORRESPONDING FOLDER
         * */
        Controller controller = new Controller();
        Course testCourse = controller.getAllCourses().get(0);
        List<Student> allStudentsBeforeUpdate = controller.getAllStudents();

        controller.modifyCourseCredits(testCourse, testCourse.getCredits() + 3);

        int i = 0;
        for (Student student: controller.getAllStudents()) {
            for(Integer course: student.getEnrolledCourses()){
                if(course == testCourse.getCourseID()){
                    assert(allStudentsBeforeUpdate.get(i).getTotalCredits() + 3 == student.getTotalCredits());
                    break;
                }
            }

            i+=1;

        }

        controller.modifyCourseCredits(testCourse, testCourse.getCredits() - 3);

        i = 0;
        for (Student student: controller.getAllStudents()) {
            for(Integer course: student.getEnrolledCourses()){
                if(course == testCourse.getCourseID()){
                    assert(allStudentsBeforeUpdate.get(i).getTotalCredits()  == student.getTotalCredits());
                    break;
                }
            }

            i+=1;

        }
    }

    @Test
    void deleteCourse() {
        Controller controller = new Controller();

        Course deletedCourse = controller.getAllCourses().get(0);

        controller.deleteCourse(deletedCourse);

        assert(controller.getAllCourses().size() == 1);

        for(Student student : controller.getAllStudents()){
            assert(!student.getEnrolledCourses().contains(deletedCourse.getCourseID()));
        }

        for(Teacher teacher : controller.getAllTeachers()){
            assert(!teacher.getCourses().contains(deletedCourse.getCourseID()));
        }
    }

    @Test
    void studentsSortedByName() {
        Controller controller = new Controller();
        assert(Ordering.natural().isOrdered(controller.studentsSortedByName()));
    }

    @Test
    void coursesSortedByCredits() {
        Controller controller = new Controller();
        assert(Ordering.natural().isOrdered(controller.coursesSortedByCredits()));
    }

    @Test
    void filterStudentsWithMinXCredits() {
        Controller controller = new Controller();
        assert(controller.filterStudentsWithMinXCredits(19).size() == 1);
    }

    @Test
    void filterCoursesWithFreePlaces() {
        Controller controller = new Controller();
        assert(controller.filterCoursesWithFreePlaces().size() == 2);
    }

    @Test
    void createTeacher() {
        Controller controller = new Controller();

        List<Teacher> teachersList = controller.getAllTeachers();

        controller.createTeacher(new Teacher());

        assert(controller.getAllTeachers().size() == teachersList.size() + 1);
    }

    @Test
    void createCourse() {
        Controller controller = new Controller();

        List<Course> coursesList = controller.getAllCourses();

        controller.createCourse(new Course());

        assert(controller.getAllCourses().size() == coursesList.size() + 1);
    }

    @Test
    void createStudent() {
        Controller controller = new Controller();

        List<Student> studentList = controller.getAllStudents();

        controller.createStudent(new Student());

        assert(controller.getAllStudents().size() == studentList.size() + 1);
    }

}