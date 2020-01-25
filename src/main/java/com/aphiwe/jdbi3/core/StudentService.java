package com.aphiwe.jdbi3.core;

import com.aphiwe.jdbi3.api.Student;
import com.aphiwe.jdbi3.db.StudentDAO;
import org.skife.jdbi.v2.sqlobject.CreateSqlObject;

import java.util.List;

public abstract class StudentService {
    @CreateSqlObject
    abstract StudentDAO studentDAO();
    public List<Student> getAll(){
        return studentDAO().getAll();
    }
    public Student findStudent(int id){
        return studentDAO().findById(id);
    }
    public int deleteStudent(int id){
        return studentDAO().deleteById(id);

    }
    public Student updateStudent(Student student){
        studentDAO().update(student);
        return student;

    }
    public int addStudent(Student student){
       return studentDAO().insert(student);
    }
}
