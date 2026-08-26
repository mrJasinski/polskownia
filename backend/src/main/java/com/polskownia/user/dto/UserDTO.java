package com.polskownia.user.dto;

import com.polskownia.user.UserRole;

public class UserDTO
{
    private String name;
    private String email;
    private String password;
    private UserRole role;

    public UserDTO()
    {
    }

    public UserDTO(final String email, final String password)
    {
        this.email = email;
        this.password = password;
    }

    public UserDTO(final String email, final String password, final UserRole role)
    {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getName()
    {
        return this.name;
    }

    public String getEmail()
    {
        return this.email;
    }

    public String getPassword()
    {
        return this.password;
    }

    public UserRole getRole()
    {
        return this.role;
    }
}

