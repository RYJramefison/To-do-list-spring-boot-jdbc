package school.hei.todolistapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
 @ComponentScan({"school.hei.todolistapp.controller","school.hei.todolistapp.service","school.hei.todolistapp.repository"}) // vas chercher dans ce package
public class ToDoListAppApplication {
    /*
    * Cette application spring boot détecte tous les @Component qu'il y a dans le même dossier
    * qu'elle, ainsi que dans tous les sous-dossier présents.
    * */
    public static void main(String[] args) {
        SpringApplication.run(ToDoListAppApplication.class, args);
    }
    /*
    * L'application srping boot detecte tout les component dans les même dossier qu'ell
    * Les @Component peuvent être des @ResteController, des @Service, ou @Repository.
    *
    * TODO :
    *  - id: int
    *  - title: String
    *  - description: String
    *  - creationDate: LocalDateTime / Instant
    *  - deadline: LocalDateTime / Instant
    *  - executionDate: LocalDateTime / Instant
    *  - priority: HIGH, MEDIUM, LOW
    *  - status: DONE, IN_PROGRESS.
    * */
}
