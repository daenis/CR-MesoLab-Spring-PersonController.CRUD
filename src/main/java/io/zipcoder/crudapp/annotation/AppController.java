package io.zipcoder.crudapp.annotation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation to be placed on controller. Every controller will need the
   @RestController annotation, and every controller's endpoint mapping will begin
   with "/api". Therefore, we can create an annotation to handle these configurations
   for us.
 * */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@RestController
@RequestMapping(value = "/api")
public @interface AppController {
}
