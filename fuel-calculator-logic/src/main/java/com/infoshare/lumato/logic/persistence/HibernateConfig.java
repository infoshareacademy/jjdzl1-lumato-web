package com.infoshare.lumato.logic.persistence;



import com.infoshare.lumato.logic.model.Car;
import com.infoshare.lumato.logic.model.ExtraCosts;
import com.infoshare.lumato.logic.model.FuelCosts;
import com.infoshare.lumato.logic.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@ApplicationScoped
public class HibernateConfig {

    private static SessionFactory sessionFactory;

    private static String  url;
    private static String user;
    private static String password;

    @PostConstruct
    private void initializeHibernateConfig() {
        File file = new File("fuel-calculator-config/db.properties");
        System.out.println("FILE TEST: " + file.getAbsolutePath());
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(file));
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("COULD NOT OPEN FILE: " + file.getAbsolutePath());
        }
        initializeSessionFactory();
    }


    private static void initializeSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            // Hibernate settings equivalent to hibernate.cfg.xml's properties
            Properties settings = new Properties();
            settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            settings.put(Environment.URL, url);
            settings.put(Environment.USER, user);
            settings.put(Environment.PASS, password);
            settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
            settings.put(Environment.SHOW_SQL, "true");
            settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

//                settings.put(Environment.HBM2DDL_AUTO, "create");
            configuration.setProperties(settings);

            configuration.addAnnotatedClass(Car.class);
            configuration.addAnnotatedClass(ExtraCosts.class);
            configuration.addAnnotatedClass(FuelCosts.class);
            configuration.addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Initialization of Hibernate SessionFactory failed!");
        }
    }

    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            initializeSessionFactory();
        }
        return sessionFactory;
    }
}