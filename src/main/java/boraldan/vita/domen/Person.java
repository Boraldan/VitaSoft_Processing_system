package boraldan.vita.domen;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Set;


@Data
@NoArgsConstructor
@Entity
@Table(name = "t_person")
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long personId;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 30, message = "Имя должно быть от 2 до 30 символов длиной")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Не должно быть пустым")
    @Email
    @Column(name = "email")
    private String email;

    @NotEmpty(message = "Не должно быть пустым")
    @Column(name = "password")
    private String password;

    @Column(name = "admin_role")
    private Boolean adminRole;

    @Column(name = "operator_role")
    private Boolean operatorRole;

    @Column(name = "user_role")
    private Boolean userRole;

    @OneToMany(mappedBy = "person")
    private List<Request> requests;
}
