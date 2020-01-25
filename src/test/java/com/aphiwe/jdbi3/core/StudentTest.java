package com.aphiwe.jdbi3.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.jupiter.api.Test;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void deserializesFromJSON() throws Exception {
        final Student student = new Student(1,"Aphiwe",66);

        assertThat(MAPPER.readValue(fixture("fixtures/student.json"), Student.class))
                .isEqualTo(student);
    }
    @Test
    public void serializesToJSON() throws Exception {
        final Student student = new Student(1,"Aphiwe",66);

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/student.json"), Student.class));
        assertThat(MAPPER.writeValueAsString(student)).isEqualTo(expected);
    }


}