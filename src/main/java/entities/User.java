package entities;

import entities.enums.DisciplineTypes;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLUpdate;

import javax.management.relation.RoleStatus;
import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Table(name = "t_user")
@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
@SQLDelete(sql = "UPDATE t_user SET name='DELETED',lastName = 'DELETED',email='DELETED', userName = 'DELETED',enabled = false WHERE id =?")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String lastName;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String userName;
    private Date createdAt;
    private boolean enabled;

    @ToString.Exclude
    @ManyToOne(fetch=FetchType.LAZY)
    private Discipline discipline;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "userID")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roleSet;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="user_Id")
    private List<Task> taskList;


    public User(String name, String lastName, String email, String userName, Date createdAt,
                    boolean enabled, Set<Role> roleSet, List<Task> taskList, Discipline discipline) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.createdAt = createdAt;
        this.enabled = enabled;
        this.roleSet = roleSet;
        this.taskList = taskList;
        this.discipline = discipline;
    }


}