package entities;

import entities.enums.DisciplineTypes;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Enumerated(EnumType.STRING)
    private DisciplineTypes Name;

    @ToString.Exclude
    @OneToMany(mappedBy = "discipline", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> users;


    @OneToOne
    private User headOfDiscipline;

    public Discipline(DisciplineTypes name) {
        Name = name;
    }

}
