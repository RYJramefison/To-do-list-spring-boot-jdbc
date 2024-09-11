package school.hei.todolistapp.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode

public class Todo {
    private int id;

    private String title;

    private String description;

    private LocalDateTime create_date;

    private LocalDateTime deadline;

    private LocalDateTime executionDate;

    private Priority priority;

    private Status status;
}
