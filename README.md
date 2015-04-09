# spring-boot-extended-logging-properties

Makes properties from spring environment configuration available in logging configuration

## Howto use

### Maven
```
<dependency>
  <groupId>eu.hinsch</groupId>
  <artifactId>spring-boot-extended-logging-properties</artifactId>
  <version>0.1.1</version>
</dependency>
```

### Gradle
```
runtime("spring-boot-extended-logging-properties:0.1.1")
```

### Configuration

In application.properties

```
logging.properties.SOME_KEY=Some Value
```

In logback.xml

```
... ${LOGGING_SOME_KEY}
```