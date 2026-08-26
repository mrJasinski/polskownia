package com.polskownia.user;

import com.polskownia.user.dto.UserDTO;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;

    User()
    {
    }

    User(final String email, final String password, final UserRole role)
    {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    User(final String name, final String email, final String password, final UserRole role)
    {
        this(email, password, role);
        this.name = name;
    }

    UserDTO toDto()
    {
        return new UserDTO(
                this.email
                , this.password
                , this.role);
    }

    public int getId()
    {
        return this.id;
    }

    String getName()
    {
        return this.name;
    }

    String getEmail()
    {
        return this.email;
    }

    String getPassword()
    {
        return this.password;
    }

    UserRole getRole()
    {
        return this.role;
    }

//    UserReadModel toReadModel()
//    {
//        return new UserReadModel(
//                this.name
//                , this.email
//        );
//    }
}
