package net.engineeringdigest.journalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@SpringBootApplication // ----> @Configuration, @EnableAutoConfiguration and @ComponentScan
@EnableTransactionManagement
public class JournalApplication {

	public static void main(String[] args) {

		Logger logger = Logger.getLogger(JournalApplication.class.getName());

		ConfigurableApplicationContext context = SpringApplication.run(JournalApplication.class, args);
		ConfigurableEnvironment environment = context.getEnvironment();
		logger.info(environment.getActiveProfiles()[0]);
	}

	@Bean
	public PlatformTransactionManager add(MongoDatabaseFactory dbFactory) {
		return new MongoTransactionManager(dbFactory);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}



/*
    1. View(React, JSP, Thymeleaf) --->
    2. Presentation Layer(Controller) --->
    3. Business Logic Layer(Service) --->
    4. Repository Layer(Spring Data JPA/Spring Data MongoDB Repository's interfaces :- JpaRepository, CrudRepository for Data
       Access Abstraction or DAO(Data Access Object) pattern)--->
    5. Persistence Layer(interacts with the database, such as JPA/Hibernate/JDBC or any other ORM framework) --->
    6. Domain Model (Holds core business objects of an application, where Entity classes(for Represents persistent data stored
       in the database which requires a database connection) or POJOs (Plain Old Java Objects) used to hold data as a DTO(Data Transfer Object) or for business logic
       like Used for data transfer between layers (Controller → Service, etc.), so No Database Dependency) --->
    7. Static Resources (CSS, SCSS JS, Images).
*/



