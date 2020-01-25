package com.aphiwe.jdbi3;

import com.aphiwe.jdbi3.core.StudentService;
import com.aphiwe.jdbi3.db.StudentDAO;
import com.aphiwe.jdbi3.resources.StudentResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

import javax.sql.DataSource;

public class StudentRecordJBApplication extends Application<StudentRecordJBConfiguration> {

    public static void main(final String[] args) throws Exception {
        new StudentRecordJBApplication().run(args);
    }

    @Override
    public String getName() {
        return "StudentRecordJB";
    }

    @Override
    public void initialize(final Bootstrap<StudentRecordJBConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final StudentRecordJBConfiguration configuration,
                    final Environment environment) {
//        final DBIFactory factory = new DBIFactory();
//        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");
//
//        final StudentDAO studentDAO = jdbi.onDemand(StudentDAO.class);
//
//        environment.jersey().register(new StudentResource(studentDAO));


//        final DataSource dataSource =configuration.getDataSourceFactory().build(environment.metrics(), "mysql");
//        DBI dbi = new DBI(dataSource);
//        environment.jersey().register(new StudentResource(dbi.onDemand(StudentDAO.class)));

        final DataSource dataSource =
                configuration.getDataSourceFactory().build(environment.metrics(), "mysql");
        DBI dbi = new DBI(dataSource);
        environment.jersey().register(new StudentResource(dbi.onDemand(StudentService.class)));
    }

}
