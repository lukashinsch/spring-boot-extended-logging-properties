package eu.hinsch.spring.boot.logging;

import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.logging.LoggingApplicationListener;
import org.springframework.context.ApplicationEvent;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

/**
 * Listener to make properties from spring environment available in logging configuration.
 * <p>
 * It re-uses the concept from spring boot of setting (system) properties and then reloading
 * the logging configuration. As there is no stable way to hook into the existing reload by boot
 * the same behavior is triggered once again reloading and initializing the logging framework twice
 * (which would not be necessary if code like this would be integrated into LoggingApplicationListener).
 * <p>
 * <b>Howto use</b><p>
 * In application.properties:<p>
 * {@code logging.properties.some.key=Some Value}<p>
 * In logback.xml:<p>
 * {@code ${some.key}}
 *
 * @author Lukas Hinsch
 */
public class ExtendedLoggingPropertiesListener extends LoggingApplicationListener {

    private static final String KEY_PREFIX = "logging.properties.";

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationEnvironmentPreparedEvent) {
            addLoggingProperties((ApplicationEnvironmentPreparedEvent) event);
        }
        super.onApplicationEvent(event);
    }

    private void addLoggingProperties(ApplicationEnvironmentPreparedEvent event) {
        ConfigurableEnvironment environment = event.getEnvironment();

        new RelaxedPropertyResolver(environment)
                .getSubProperties(KEY_PREFIX)
                .entrySet()
                .stream()
                .forEach(entry -> System.setProperty(entry.getKey(), entry.getValue().toString()));
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
