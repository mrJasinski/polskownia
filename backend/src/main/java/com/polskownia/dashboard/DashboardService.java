package com.polskownia.dashboard;

import com.polskownia.dashboard.dto.DashboardDTO;
import com.polskownia.google.gCalendar.GCalendarService;
import com.polskownia.security.JwtService;
import com.polskownia.user.UserService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.util.List;

@Service
class DashboardService
{
    private final GCalendarService gCalendarService;
    private final UserService userService;
    private final JwtService jwtService;

    DashboardService(final GCalendarService gCalendarService, final UserService userService, final JwtService jwtService)
    {
        this.gCalendarService = gCalendarService;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    //        TODo prosty przykłądowy dashboard bez udziwnień i ról aby jak najszybciej tworzyć - nie komplikuj złotko!

    DashboardDTO getDashboard(final LocalDate startDate, final LocalDate endDate) throws GeneralSecurityException, IOException
    {
        var events = gCalendarService.getEventsByDates(startDate, endDate);

        var messages = List.of("Juan zarezerwował audyt!", "Mbote uzupełnił zadanie domowe!");

        var orders = "orders";

        var tasks = "tasks";

        var articles = "articles";

        return new DashboardDTO(events, messages, orders, tasks, articles);
    }

}
