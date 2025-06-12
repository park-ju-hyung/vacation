package com.example.vacation.common.config;

import org.apache.ibatis.annotations.Mapper;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapper
public @interface DBMapper {

}
