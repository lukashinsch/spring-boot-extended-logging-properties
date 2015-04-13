package eu.hinsch.spring.boot.logging;

import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.logging.LoggingApplicationListener;
import org.springframework.context.ApplicationEvent;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

/**
 * Created by lh on 06/04/15.
 */
public class ExtendedLoggingPropertiesListener extends LoggingApplicationListener {

    private static final String KEY_PREFIX = "logging.properties.";
    private static final String PROPERTY_PREFIX = "LOGGING_";

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationEnvironmentPreparedEvent) {
            addLoggingProperties((ApplicationEnvironmentPreparedEvent) event);
        }
        super.onApplicationEvent(event);
    }

    public void addLoggingProperties(ApplicationEnvironmentPreparedEvent event) {
        ConfigurableEnvironment environment = event.getEnvironment();

        new RelaxedPropertyResolver(environment)
                .getSubProperties(KEY_PREFIX)
                .entrySet()
                .stream()
                .forEach(this::setSystemProperty);
    }

    private String setSystemProperty(Map.Entry<String, Object> entry) {
        String key = entry.getKey();
        String value = entry.getValue().toString();
        return System.setProperty(key, value);
    }

    /**
      * ideally this would be run before org.springframework.boot.logging.LoggingApplicationListener (+11)
      * but after org.springframework.boot.context.config.ConfigFileApplicationListener (+10)
      * so we could simply insert the properties in between those steps, but unfortunately there is
      * no guaranteed way to do that, so we run afterwards and re-initialize the entire thing via super class.
      */
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 12;
    }
}
