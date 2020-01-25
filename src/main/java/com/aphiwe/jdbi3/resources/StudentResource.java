package com.aphiwe.jdbi3.resources;
import com.aphiwe.jdbi3.core.Student;;
import com.aphiwe.jdbi3.db.StudentDAO;
import com.codahale.metrics.annotation.Timed;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/students")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class StudentResource {

    StudentDAO studentDAO;
    public StudentResource(){
    }
    public StudentResource(StudentDAO studentDAO) {
        this.studentDAO  = studentDAO;
    }
    @GET
    @Path("/all/")
    @Timed
    public List<Student> get(){
        return studentDAO.getAll();

    }


    @GET
    @Path("/getByID/")
    @Timed
    public Student get(@QueryParam("id") Integer id){
        return studentDAO.findById(id);
    }

    @POST
    @Path("/add")
    @Timed
    public int add(@Valid Student student){

        studentDAO.insert(student);
        return 200;}


    @PUT
    @Path("/edit/{id}")
    @Timed
    public Student update(@PathParam("id") Integer id, @Valid Student student) {
        Student updateStudent = new Student(id, student.getName(),student.getGpa());
        studentDAO.update(updateStudent);
        return updateStudent;
    }

    @DELETE
    @Path("/delete/{id}")
    @Timed
    public String delete(@PathParam("id") Integer id) {
        studentDAO.deleteById(id);
        return "DELETE_WAS_SUCCESS";
    }
}
