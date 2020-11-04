package entities;

import lombok.*;


import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ToString.Exclude
    @ManyToMany(mappedBy = "roleSet")
    private Set<User> userSet;

    public Role(String name) {
        this.name = name;
    }

}
