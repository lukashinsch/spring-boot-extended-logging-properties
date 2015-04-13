# Changelog

## 0.2.0
- Breaking change: no longer transform key. Fixes [#1](https://github.com/lukashinsch/spring-boot-extended-logging-properties/issues/1) 

## 0.1.1
- force re-init of logging system after properties set (as it's not possible to reliably do it between environment properties available and spring boot default logging listener)
- require spring boot >= 1.2.1

## 0.1.0
- First version
