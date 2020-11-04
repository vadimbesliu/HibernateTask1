package entities;

import entities.enums.StatusTypes;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.sql.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    @Enumerated(EnumType.STRING)
    private StatusTypes status;


    public Task(String name, String description, Date startDate, Date endDate, StatusTypes status) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

}
