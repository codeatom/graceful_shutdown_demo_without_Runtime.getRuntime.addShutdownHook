package application.maintenence;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import application.dao.CourseManager;
import application.dao.StudentManager;
import application.model.Course;
import application.model.Student;
import application.sequencer.CourseSequencer;
import application.sequencer.StudentSequencer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import static application.maintenence.StaticResources.*;

public class CollectionDataLoader {

    public void init(){
        loadLatestStudentId();
        loadStudents();
        loadCourses();
    }

    public static void loadLatestStudentId(){
        Properties properties = new Properties();

        try(FileReader reader = new FileReader(SEQUENCERS_FILE)){
            properties.load(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String currentStudentId = properties.getProperty("currentStudentId");
        String currentCourseId = properties.getProperty("currentCourseId");

        StudentSequencer.setStudentSequencer(Integer.parseInt(currentStudentId));
        CourseSequencer.setCourseSequencer(Integer.parseInt(currentCourseId));
    }

    public void loadStudents(){
        List<Student> students = new ArrayList<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            students = objectMapper.readValue(STUDENT_FILE, new TypeReference<List<Student>>() {});
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        StudentManager.setStudents(students);
    }

    public void loadCourses() {
        List<Course> courses = new ArrayList<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            courses = objectMapper.readValue(COURSE_FILE, new TypeReference<List<Course>>() {
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        CourseManager.setCourses(courses);
    }

}
