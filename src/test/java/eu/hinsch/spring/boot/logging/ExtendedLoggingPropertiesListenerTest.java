package eu.hinsch.spring.boot.logging;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.OutputCapture;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;

/**
 * Created by lh on 13/04/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(properties = "logging.properties.my.property = someValue")
@SpringApplicationConfiguration(classes = ExtendedLoggingPropertiesListenerTest.TestConfig.class)
public class ExtendedLoggingPropertiesListenerTest {

    private static final Logger logger = LoggerFactory.getLogger(ExtendedLoggingPropertiesListenerTest.class);

    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @SpringBootApplication
    static class TestConfig {
        @Bean
        public LoggingBean loggingBean() {
            return new LoggingBean();
        }
    }

    static class LoggingBean {
        public void log(String msg) {
            logger.info(msg);
        }
    }

    @Autowired
    private LoggingBean loggingBean;

    @Test
    public void shouldIncludePropertyInLogOutput() {
        // when
        loggingBean.log("some logging");

        // then
        assertThat(outputCapture.toString(), containsString("someValue - some logging"));
    }
}