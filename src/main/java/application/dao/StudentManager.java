package application.dao;

import java.util.ArrayList;
import java.util.List;

import application.maintenence.CollectionDataSaver;
import application.model.Student;
import application.sequencer.StudentSequencer;


public class StudentManager {

    private static List<Student> students = new ArrayList<>();

    private CollectionDataSaver collectionDataSaver = new CollectionDataSaver();

    public static List<Student> getStudents() {
        return StudentManager.students;
    }

    public static void setStudents(List<Student> students) {
        StudentManager.students = students;
    }

    public Student createStudent(String name, String email) {
        Student student = new Student(StudentSequencer.nextStudentId(), name, email);
        students.add(student);

        collectionDataSaver.saveStudents();
        collectionDataSaver.saveSequencerValue();

        return student;
    }

    public Student updateStudent(int id) {
        Student student = findById(id);

        student.setName("newName");
        student.setEmail("new email");

        collectionDataSaver.saveStudents();

        return student;
    }

    public Student findById(int id) {
        for (Student student : students){
            if(student.getId() == id){
                return student;
            }
        }

        return null;
    }

}