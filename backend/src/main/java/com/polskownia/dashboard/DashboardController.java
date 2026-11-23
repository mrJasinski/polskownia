package com.polskownia.dashboard;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDate;

@RestController
@RequestMapping("/dashboard")
class DashboardController
{
    private final DashboardService dashboardService;

    DashboardController(final DashboardService dashboardService)
    {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    ResponseEntity<?> getDashboard(HttpServletRequest request) throws GeneralSecurityException, IOException
    {
//        TODO przykładowe daty jako placeholder

        return ResponseEntity.ok(this.dashboardService.getDashboard(LocalDate.now(), LocalDate.now().plusDays(1)));
    }
}
