package com.aphiwe.jdbi3;

import com.aphiwe.jdbi3.core.StudentService;
import com.aphiwe.jdbi3.resources.StudentResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
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

            bootstrap.addBundle(new MigrationsBundle<StudentRecordJBConfiguration>() {
                @Override
                public DataSourceFactory getDataSourceFactory(StudentRecordJBConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            });
    }

    @Override
    public void run(final StudentRecordJBConfiguration configuration,
                    final Environment environment) {
        final DataSource dataSource =
                configuration.getDataSourceFactory().build(environment.metrics(), "postgresql");
        DBI dbi = new DBI(dataSource);
        environment.jersey().register(new StudentResource(dbi.onDemand(StudentService.class)));
    }

}
