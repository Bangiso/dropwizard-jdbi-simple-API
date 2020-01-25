package com.aphiwe.jdbi3.resources;
import static org.assertj.core.api.Assertions.assertThat;

import com.aphiwe.jdbi3.core.Student;
import com.aphiwe.jdbi3.db.StudentDAO;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import static org.mockito.Mockito.*;

class StudentResourceTest {
    private static final StudentDAO dao = mock(StudentDAO.class);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new StudentResource(dao))
            .build();
    static Student student;
    static  List<Student> students;
    @BeforeEach
    public void setup() {
        student = new Student(1,"John Smith",85);
        students= Collections.singletonList(student);
        when(dao.getAll()).thenReturn(students);
        when(dao.insert(any(Student.class))).thenReturn(200);
        when(dao.findById(eq(1))).thenReturn(student);
    }

    @AfterEach
    public void tearDown(){
        reset(dao);
    }

    @Test
    public void testGetStudent() {

        assertThat(resources.target("/students/getByID/1").request().get(Student.class))
                .isEqualTo(student);
        verify(dao).findById(1);
    }
    @Test
    public void testInsertStudent(){

        Response response= resources.target("/students/add").request()
                .post(Entity.entity(student, MediaType.APPLICATION_JSON_TYPE));
        assertThat(response).isEqualTo(200);
        verify(dao).insert(any(Student.class));
    }
    @Test
    public void testFindAll(){
        final List<Student> response = resources.target("/students/all")
                .request().get(new GenericType<List<Student>>() {});
        verify(dao).getAll();
        assertThat(response).containsAll(students);
    }


}