[![Coverage Status](https://coveralls.io/repos/lukashinsch/spring-boot-extended-logging-properties/badge.svg?branch=master)](https://coveralls.io/r/lukashinsch/spring-boot-extended-logging-properties?branch=master)
[![Build Status](https://travis-ci.org/lukashinsch/spring-boot-extended-logging-properties.svg?branch=master)](https://travis-ci.org/lukashinsch/spring-boot-extended-logging-properties)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/eu.hinsch/spring-boot-extended-logging-properties/badge.svg)](https://maven-badges.herokuapp.com/maven-central/eu.hinsch/spring-boot-extended-logging-properties/)

# spring-boot-extended-logging-properties

[![Join the chat at https://gitter.im/lukashinsch/spring-boot-extended-logging-properties](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/lukashinsch/spring-boot-extended-logging-properties?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

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

- Starting with release 1.3 (>=M2) spring boot supports access to any environment property in logback configuration out of the box (for details see the official [documentation](http://docs.spring.io/spring-boot/docs/1.3.0.BUILD-SNAPSHOT/reference/htmlsingle/#boot-features-logback-extensions))
