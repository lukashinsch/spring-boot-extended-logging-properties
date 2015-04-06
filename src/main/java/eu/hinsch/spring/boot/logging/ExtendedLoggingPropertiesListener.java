package eu.hinsch.spring.boot.logging;

import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

/**
 * Created by lh on 06/04/15.
 */
public class ExtendedLoggingPropertiesListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent>, Ordered {

    private static final String KEY_PREFIX = "logging.properties.";
    private static final String PROPERTY_PREFIX = "LOGGING_";

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        ConfigurableEnvironment environment = event.getEnvironment();

        new RelaxedPropertyResolver(environment)
                .getSubProperties(KEY_PREFIX)
                .entrySet()
                .stream()
                .forEach(this::setSystemProperty);
    }

    private String setSystemProperty(Map.Entry<String, Object> entry) {
        String loggingKey = createLoggingKey(entry.getKey());
        String value = entry.getValue().toString();
        return System.setProperty(loggingKey, value);
    }

    private String createLoggingKey(String key) {
        return PROPERTY_PREFIX + key.toUpperCase().replace(".", "_");
    }

    /**
      * TODO this must run before org.springframework.boot.logging.LoggingApplicationListener (+11)
      * but after org.springframework.boot.context.config.ConfigFileApplicationListener (+10)
     * so it might be entirely accidental that this actually works
      */
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 11;
    }
}
