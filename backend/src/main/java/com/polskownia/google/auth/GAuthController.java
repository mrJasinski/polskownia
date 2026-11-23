package com.polskownia.google.auth;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/oauth2")
class GAuthController
{
    private static final String REDIRECT_URI = "http://localhost:8080/oauth2/callback";

    private final GoogleAuthorizationCodeFlow flow;

    GAuthController(final GoogleAuthorizationCodeFlow flow)
    {
        this.flow = flow;
    }

//    TODO wyniesienie logiki do serwisu

    @GetMapping("/authorize")
    public void authorize(HttpServletRequest request, HttpServletResponse response) throws IOException
    {

        try {
            String state = UUID.randomUUID().toString();

            request.getSession().setAttribute("oauth_state", state);

            var authorizationUrl = this.flow.newAuthorizationUrl()
                    .setRedirectUri(REDIRECT_URI)
                    .setAccessType("offline")
                    .set("prompt", "consent")
                    .setState(state)
                    .build();

            response.sendRedirect(authorizationUrl);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    e.getMessage()
            );
        }
    }

    @GetMapping("/callback")
    public ResponseEntity<String> callback(
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String error,
            HttpServletRequest request) throws IOException
    {
        if (error != null)
            return ResponseEntity.badRequest().body("Google OAuth error: " + error);

        var expectedState = (String) request.getSession().getAttribute("oauth_state");

        if (expectedState == null || !expectedState.equals(state))
            return ResponseEntity.badRequest().body("Invalid OAuth state");

        request.getSession().removeAttribute("oauth_state");

        var tokenResponse = this.flow.newTokenRequest(code)
                .setRedirectUri(REDIRECT_URI)
                .execute();

        this.flow.createAndStoreCredential(tokenResponse, "gmail-user");

        return ResponseEntity.ok("Google authorization successful!");
    }
}
