package com.aphiwe.jdbi3.resources;
import com.aphiwe.jdbi3.api.Student;
import com.aphiwe.jdbi3.core.StudentService;
import com.codahale.metrics.annotation.Timed;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

;

@Path("/students")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@RolesAllowed("ADMIN")
public class StudentResource {

    StudentService studentService;
    public StudentResource(){
    }
    public StudentResource(StudentService studentService) {
        this.studentService  = studentService;
    }
    @GET
    @Path("/all/")
    @Timed
    public List<Student> get(){
        return studentService.getAll();

    }


    @GET
    @Path("/getByID/{id}")
    @Timed
    public Student get(@PathParam("id") Integer id){
        return studentService.findStudent(id).orElse(null);
    }

    @POST
    @Path("/add")
    @Timed
    public int add(@Valid Student student){

        studentService.addStudent(student);
        return 200;}
//
//
    @PUT
    @Path("/edit/{id}")
    @Timed
    public Student update(@PathParam("id") Integer id, @Valid Student student) {
        Student updateStudent = new Student(id, student.getName(),student.getGpa());
        studentService.updateStudent(updateStudent);
        return updateStudent;
    }

    @DELETE
    @Path("/delete/{id}")
    @Timed
    public String delete(@PathParam("id") Integer id) {
        studentService.deleteStudent(id);
        return "DELETE_WAS_SUCCESS";
    }
}
