package com.polskownia.user.dto;

public class UserLoggedDTO
{
    private String name;
    private String email;
    private String token;
    private String role;    //or should be enum? used as flag for frontend dashboard selection

    public UserLoggedDTO(final String email, final String token)
    {
        this.email = email;
        this.token = token;
    }

    public UserLoggedDTO(final String name, final String email, final String token, final String role)
    {
        this.name = name;
        this.email = email;
        this.token = token;
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

    public String getToken()
    {
        return this.token;
    }

    public String getRole()
    {
        return this.role;
    }
}
