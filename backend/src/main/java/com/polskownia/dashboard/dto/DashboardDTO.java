package com.polskownia.dashboard.dto;

import com.google.api.services.calendar.model.Events;

import java.util.List;

public class DashboardDTO
{
    private Events events;
    private List<String> messages;
    private String orders;
    private String tasks;
    private String articles;

    DashboardDTO()
    {
    }

    public DashboardDTO(final Events events, final List<String> messages, final String orders, final String tasks, final String articles)
    {
        this.events = events;
        this.messages = messages;
        this.orders = orders;
        this.tasks = tasks;
        this.articles = articles;
    }

    public Events getEvents()
    {
        return this.events;
    }

    public List<String> getMessages()
    {
        return this.messages;
    }

    public String getOrders()
    {
        return this.orders;
    }

    public String getTasks()
    {
        return this.tasks;
    }

    public String getArticles()
    {
        return this.articles;
    }
}
