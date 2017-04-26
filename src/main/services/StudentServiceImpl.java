package main.services;

import main.controllers.LoginServlet;
import main.controllers.listeners.NewAppStartListener;
import main.models.dao.StudentDao;
import main.models.dao.StudentDaoImpl;
import main.models.pojo.Student;
import main.utils.Benchmark;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 19.04.2017.
 */
@Service
@Benchmark
public class StudentServiceImpl implements StudentService {

    static {
        PropertyConfigurator.configure(NewAppStartListener.class.getClassLoader().getResource("log4j.xml"));
    }
    private static final Logger logger = Logger.getLogger(StudentServiceImpl.class);

    private StudentDao studentDao;

    public StudentServiceImpl() {
    }


    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public StudentDao getStudentDao() {
        return studentDao;
    }

    @Autowired
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public List<Student> getAllStudents() {
        //logger.info("getAllStudents()");
        return studentDao.getAll();
    }

    public boolean addStudent(int id, String name, int age, int group_id) {
        return studentDao.insert(id, name, age, group_id);
    }
}
