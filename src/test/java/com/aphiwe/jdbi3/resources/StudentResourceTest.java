package com.aphiwe.jdbi3.resources;
import com.aphiwe.jdbi3.api.Student;
import com.aphiwe.jdbi3.core.StudentService;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class StudentResourceTest {
    private static final StudentService dao = mock(StudentService.class);
    @Rule
    public  ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new StudentResource(dao))
            .build();
    static Student student;
    static  List<Student> students;
    @Before
    public void setup() {
        student = new Student(1,"John Smith",85);
        students= Collections.singletonList(student);
        when(dao.getAll()).thenReturn(students);
        when(dao.addStudent(any(Student.class))).thenReturn(200);
        when(dao.deleteStudent(eq(1))).thenReturn(200);
        when(dao.findStudent(eq(1))).thenReturn(Optional.ofNullable(student));
    }

    @After
    public void tearDown(){
        reset(dao);
    }

    @Test
    public void testGetStudent() {
        Student newStudent=resources.target("/students/getByID/1").request().get(Student.class);
        assertThat(newStudent.getName())
                .isEqualTo(student.getName());
        assertThat(newStudent.getId())
                .isEqualTo(student.getId());
        assertThat(newStudent.getGpa())
                .isEqualTo(student.getGpa());
        verify(dao).findStudent(1);
    }
    @Test
    public void testInsertStudent(){
        Response response= resources.target("/students/add").request()
                .post(Entity.entity(student, MediaType.APPLICATION_JSON_TYPE));
        System.out.println(response);
        assertThat(response.getStatus()).isEqualTo(200);
        verify(dao).addStudent(any(Student.class));
    }
    @Test
    public void testFindAll(){
        final List<Student> response = resources.target("/students/all")
                .request().get(new GenericType<List<Student>>() {});
        verify(dao).getAll();
        assertThat(response).containsAll(students);
    }
    @Test
    public void testUpdateStudent(){
        Student updatedStudent= resources.target("/students/edit/1").request()
                .put(Entity.entity(student, MediaType.APPLICATION_JSON_TYPE), Student.class);
        assertNotNull(updatedStudent);
        assertThat(updatedStudent.getId()).isEqualTo(student.getId());
        assertThat(updatedStudent.getName()).isEqualTo(student.getName());
        assertThat(updatedStudent.getGpa()).isEqualTo(student.getGpa());
        verify(dao).updateStudent(any(Student.class));
    }
    @Test
    public void testDeleteStudent(){
        Response response=resources.target("/students/delete/1").request().delete();
        System.out.println(response);
        assertThat(response.getStatus()).isEqualTo(200);
        verify(dao).deleteStudent(1);
    }
}