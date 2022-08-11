package application;

import application.dao.CourseManager;
import application.dao.StudentManager;
import application.maintenence.CollectionDataLoader;
import application.model.Course;
import application.model.Student;


public class Main {

    public static void main(String[] args) {
        StudentManager studentManager = new StudentManager();
        CourseManager courseManager = new CourseManager();

        //Load data from JSON and Properties files
        CollectionDataLoader collectionDataLoader = new CollectionDataLoader();
        collectionDataLoader.init();


        for(Student student : StudentManager.getStudents()){
            System.out.println(student.toString() + "\n");
        }

        for(Course course : CourseManager.getCourses()){
            System.out.println(course.toString() + "\n");
        }

//
//        Student student_1 = studentManager.createStudent("Chris Lucky", "cl@yahoo.com");
//        Student student_2 = studentManager.createStudent("Vic Anders", "va@gmail.com");
//        Student student_3 = studentManager.createStudent("Harris Zander", "hz@provider.com");
//
//        studentManager.updateStudent(3);
//
//
//
//
//        Course course_1 = courseManager.createCourse("Java", 10);
//        Course course_2 = courseManager.createCourse("C sharp", 10);
//        Course course_3 = courseManager.createCourse("Javascript", 3);
//
//        course_1.getStudents().add(student_1);
//        course_1.getStudents().add(student_2);
//        course_1.getStudents().add(student_3);
//
//        courseManager.updateCourse(3);
    }

}