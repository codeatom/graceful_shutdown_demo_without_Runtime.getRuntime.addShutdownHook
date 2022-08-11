package application.dao;

import java.util.ArrayList;
import java.util.List;

import application.maintenence.CollectionDataSaver;
import application.model.Course;
import application.sequencer.CourseSequencer;


public class CourseManager {

    private static List<Course> courses = new ArrayList<>();

    private CollectionDataSaver collectionDataSaver = new CollectionDataSaver();

    public static List<Course> getCourses() {
        return CourseManager.courses;
    }

    public static void setCourses(List<Course> courses) {
        CourseManager.courses = courses;
    }

    public Course createCourse(String courseName, int weekDuration) {
        Course course = new Course(CourseSequencer.nextCourseId(), courseName, weekDuration);
        courses.add(course);

        collectionDataSaver.saveCourses();
        collectionDataSaver.saveSequencerValue();

        return course;
    }

    public Course updateCourse(int id) {
        Course course = findById(id);

        course.setCourseName("newCourseName");
        course.setWeekDuration(3);

        collectionDataSaver.saveCourses();

        return course;
    }

    public Course findById(int id) {
        for (Course course : courses){
            if(course.getId() == id){
                return course;
            }
        }

        return null;
    }

}
