[![Coverage Status](https://coveralls.io/repos/lukashinsch/spring-boot-extended-logging-properties/badge.svg?branch=master)](https://coveralls.io/r/lukashinsch/spring-boot-extended-logging-properties?branch=master)
[![Build Status](https://travis-ci.org/lukashinsch/spring-boot-extended-logging-properties.svg?branch=master)](https://travis-ci.org/lukashinsch/spring-boot-extended-logging-properties)
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

## Notes

- There is an open [issue](https://github.com/spring-projects/spring-boot/issues/1788) 
(targeted for 1.3 but as of 1.3.0.M1 still unresolved) for spring boot to support 
additional logging properties via environment. What is asked for there should all be covered by this library. 
