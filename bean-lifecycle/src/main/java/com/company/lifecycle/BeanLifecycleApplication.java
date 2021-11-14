package com.company.lifecycle;

import com.company.lifecycle.configuration.AppConfiguration;
import com.company.lifecycle.manager.LibraryManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanLifecycleApplication {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.register(AppConfiguration.class);
		applicationContext.refresh();

		LibraryManager manager = applicationContext.getBean(LibraryManager.class);
		System.out.println("Context and beans are set up and ready to work");
		applicationContext.close();
	}

}
