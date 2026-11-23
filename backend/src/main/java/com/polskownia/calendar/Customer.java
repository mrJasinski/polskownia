package com.polskownia.calendar;

import com.polskownia.calendar.dto.CustomerWriteModel;
import jakarta.persistence.*;

@Entity
@Table(name = "customers")
class Customer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    Customer()
    {
    }

    Customer(final String firstName, final String lastName, final String email, final String phone)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public int getId()
    {
        return this.id;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public String getEmail()
    {
        return this.email;
    }

    public String getPhone()
    {
        return this.phone;
    }
}
