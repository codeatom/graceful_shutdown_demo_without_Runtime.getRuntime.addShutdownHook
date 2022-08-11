package application.maintenence;

import java.io.*;
import java.util.Properties;

import application.dao.CourseManager;
import application.dao.StudentManager;
import application.sequencer.CourseSequencer;
import application.sequencer.StudentSequencer;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import static application.maintenence.StaticResources.*;


public class CollectionDataSaver {

    public void saveSequencerValue(){
        Properties properties = new Properties();
        properties.setProperty("currentStudentId", String.valueOf(StudentSequencer.getStudentSequencer()));
        properties.setProperty("currentCourseId", String.valueOf(CourseSequencer.getCourseSequencer()));

        try(FileWriter writer = new FileWriter(SEQUENCERS_FILE)){
            properties.store(writer, "Latest sequencer values");
        }catch (IOException ex){
            ex.getMessage();
        }
    }

    public void saveStudents(){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
            objectWriter.writeValue(STUDENT_FILE, StudentManager.getStudents());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void saveCourses() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
            objectWriter.writeValue(COURSE_FILE, CourseManager.getCourses());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}