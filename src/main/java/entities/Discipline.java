package entities;

import entities.enums.DisciplineTypes;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Enumerated(EnumType.STRING)
    private DisciplineTypes Name;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "discipline", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> users;

    @EqualsAndHashCode.Exclude
    @OneToOne
    private User headOfDiscipline;

    public Discipline(DisciplineTypes name) {
        Name = name;
    }

}
