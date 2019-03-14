package com.cristi.mentool.mentool.exposition;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/persons", produces = APPLICATION_JSON_UTF8_VALUE)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@RestController
@Transactional
public @interface MentoolRequestMapping {
}
