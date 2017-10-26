package io.dropwizard.logging.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import io.dropwizard.configuration.YamlConfigurationFactory;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.logging.FileAppenderFactory;
import io.dropwizard.validation.BaseValidator;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class LayoutDeserializationTests {

    private final ObjectMapper objectMapper = Jackson.newObjectMapper();
    private final YamlConfigurationFactory<FileAppenderFactory> factory = new YamlConfigurationFactory<>(
        FileAppenderFactory.class, BaseValidator.newValidator(), objectMapper, "dw");

    private FileAppenderFactory config;

    @Before
    public void setUp() throws Exception {
        objectMapper.getSubtypeResolver().registerSubtypes(FileAppenderFactory.class,
            AccessJsonLayoutBaseFactory.class, JsonLayoutBaseFactory.class);
    }

    @Test
    public void testDeserializeJson() throws Exception {
        config = factory.build(new File(Resources.getResource("yaml/json-file-log.yml").toURI()));
        System.out.println(config.getLogFormat());
    }

    @Test
    public void testDeserializeAccessJson() throws Exception {
        config = factory.build(new File(Resources.getResource("yaml/json-file-access-log.yml").toURI()));
        System.out.println(config.getLogFormat());
    }
}
