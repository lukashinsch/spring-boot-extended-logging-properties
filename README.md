[![Maven Central](https://maven-badges.herokuapp.com/maven-central/eu.hinsch/spring-boot-extended-logging-properties/badge.svg)](https://maven-badges.herokuapp.com/maven-central/eu.hinsch/spring-boot-extended-logging-properties/)

# spring-boot-extended-logging-properties

Makes properties from spring environment configuration available in logging configuration

## Howto use

### Maven
```
<dependency>
  <groupId>eu.hinsch</groupId>
  <artifactId>spring-boot-extended-logging-properties</artifactId>
  <version>0.2.0</version>
</dependency>
```

### Gradle
```
runtime("spring-boot-extended-logging-properties:0.2.0")
```

### Configuration

In application.properties

```
logging.properties.some.key=Some Value
```

In logback.xml

```
${some.key}
```