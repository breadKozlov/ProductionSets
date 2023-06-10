package by.kozlov.annotation;

import by.kozlov.TestApplicationRunner;
import by.kozlov.spring.ApplicationRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = {TestApplicationRunner.class, ApplicationRunner.class})
@Transactional
@Sql({"/sql/init.sql","/sql/test.sql"})
public @interface IT {
}
