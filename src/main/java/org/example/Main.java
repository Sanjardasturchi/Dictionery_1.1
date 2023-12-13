package org.example;

import org.example.config.ApplicationConfig;
import org.example.controller.Controller;
import org.example.db.DataBase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        DataBase dataBase= context.getBean(DataBase.class);
        dataBase.init();
        Controller controller = context.getBean(Controller.class);
        controller.start();
    }
}