package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.entity.ConfigJournalApp;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalApp, ObjectId> {

}




/*
    1. View(React, JSP, Thymeleaf) --->
    2. Presentation Layer(Controller) --->
    3. Business Logic Layer(Service) --->
    4. Repository Layer(Spring Data JPA/Spring Data MongoDB Repository's interfaces :- JpaRepository, CrudRepository for Data
       Access Abstraction or DAO(Data Access Object) pattern) which use persistence layer internally to perform operations. --->
    5. Persistence Layer(interacts with the database, such as JPA/Hibernate/JDBC or any other ORM framework) --->
    6. Domain Model (Holds core business objects of an application, where Entity classes(for Represents persistent data stored
       in the database which requires a database connection) or POJOs (Plain Old Java Objects) used to hold data as a DTO(Data Transfer Object) or for business logic
       like Used for data transfer between layers (Controller → Service, etc.), so No Database Dependency) --->
    7. Static Resources (CSS, SCSS JS, Images).
*/




