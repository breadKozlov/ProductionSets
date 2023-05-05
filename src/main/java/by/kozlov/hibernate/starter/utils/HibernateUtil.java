package by.kozlov.hibernate.starter.utils;


import lombok.experimental.UtilityClass;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateUtil {
    public static Configuration getConfig() {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        return configuration;
    }
}
