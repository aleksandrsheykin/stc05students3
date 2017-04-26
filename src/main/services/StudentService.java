package main.services;

import main.models.pojo.Student;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

/**
 * Created by admin on 19.04.2017.
 */
public interface StudentService {

    public List<Student> getAllStudents();
    public boolean addStudent(int id, String name, int age, int group_id);
}
