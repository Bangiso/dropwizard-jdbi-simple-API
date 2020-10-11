package com.aphiwe.jdbi3.resources;

import com.aphiwe.jdbi3.api.Student;
import com.aphiwe.jdbi3.core.StudentService;
import com.codahale.metrics.annotation.Timed;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

;

@Path("/students")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@RolesAllowed("ADMIN")
public class StudentResource {

    StudentService studentService;

    public StudentResource() {
    }

    public StudentResource(StudentService studentService) {
        this.studentService = studentService;
    }

    @GET
    @Path("/all/")
    @Timed
    public List<Student> get() {
        return studentService.getAll();

    }


    @GET
    @Path("/getByID/{id}")
    @Timed
    public Student get(@PathParam("id") Integer id) {
        return studentService.findStudent(id).orElse(null);
    }

    @POST
    @Path("/add")
    @Timed
    public Response add(@Valid Student student) {
        if (get(student.getId()) == null) {
            studentService.addStudent(student);
            return Response.accepted("student " + student.getName() + " added").build();

        } else {
            return Response.ok("Student already exists").build();
        }
    }

    @PUT
    @Path("/edit/{id}")
    @Timed
    public Response update(@PathParam("id") Integer id, @Valid Student student) {
        studentService.updateStudent(student);
        return Response.accepted("resource updated").build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Timed

    public Response delete(@PathParam("id") Integer id) {
        studentService.deleteStudent(id);
        return Response.accepted("resource deleted").build();
    }
}
