package com.aphiwe.jdbi3.db;
import com.aphiwe.jdbi3.core.StudentMapper;
import java.util.List;
import com.aphiwe.jdbi3.core.Student;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
@RegisterMapper(StudentMapper.class)
public interface StudentDAO {
    @SqlQuery("select * from STUDENT;")
    List<Student> getAll();
    @SqlQuery("select * from STUDENT where ID = :id;")
    Student findById(@Bind("id") int id);
    @SqlUpdate("delete from STUDENT where ID = :id;")
    int deleteById(@Bind("id") int id);
    @SqlUpdate("update STUDENT set NAME = :name, GPA=:gpa where ID = :id;")
    int update(@BindBean Student student);
    @SqlUpdate("insert into STUDENT (ID, NAME, GPA) values (:id, :name, :gpa);")
    int insert(@BindBean Student student);
}
