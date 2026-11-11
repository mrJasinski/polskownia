package com.polskownia.google.gMail;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

import jakarta.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Collections;

@Service
class GMailService
{
//    private final JSONObject gmailOAuthProperties;

//    private String getClientId()
//    {
//        return gmailOAuthProperties.getString("client_id");
//    }
//
//    private String getClientSecret()
//    {
//        return gmailOAuthProperties.getString("client_secret");
//    }
//
//    private String getRedirectUri()
//    {
//        return gmailOAuthProperties.getString("redirect_uri");
//    }

//    private static final String GOOGLE_AUTH_URL = "https://accounts.google.com/o/oauth2/v2/auth";
//
//    private static final String GOOGLE_TOKEN_URL = "https://oauth2.googleapis.com/token";
//
//    private static final String GMAIL_SCOPE = "https://www.googleapis.com/auth/gmail.readonly";

//    private final SecureRandom secureRandom = new SecureRandom();

//    @Value("${google.calendar.credentials}")
//    private String credentialsPath;
//
//    private final GoogleAuthorizationCodeFlow flow;

    GMailService()
    {

    }

//    public String generateAuthorizationUrl(HttpSession session)
//    {
//        byte[] randomBytes = new byte[32];
//        secureRandom.nextBytes(randomBytes);
//
//        String state = Base64.getUrlEncoder()
//                .withoutPadding()
//                .encodeToString(randomBytes);
//
//        session.setAttribute("gmail_oauth_state", state);
//
////        TODO test
//        System.out.println("xxxxxxxxxxxxxxxxxxx");
//        System.out.println("Redirect URI: " + getRedirectUri());
//
//        return new GoogleAuthorizationCodeRequestUrl(
//                GOOGLE_AUTH_URL,
//                getClientId(),
//                getRedirectUri(),
//                Collections.singletonList(GMAIL_SCOPE)
//        )
//                .setAccessType("offline")
//                .setState(state)
//                .build();
//    }

//    public void handleCallback(String code, String state, HttpSession session) throws IOException, GeneralSecurityException
//    {
//
//        // 1. Weryfikacja state
//        var expectedState = (String) session.getAttribute("gmail_oauth_state");
//
//        session.removeAttribute("gmail_oauth_state");
//
//        if (expectedState == null || !expectedState.equals(state))
//            throw new SecurityException("Invalid OAuth state");
//
//        // 2. Wymiana authorization code na tokeny
//        var tokenResponse =
//                new GoogleAuthorizationCodeTokenRequest(
//                        GoogleNetHttpTransport.newTrustedTransport(),
//                        GsonFactory.getDefaultInstance(),
//                        GOOGLE_TOKEN_URL,
//                        getClientId(),
//                        getClientSecret(),
//                        code,
//                        getRedirectUri()
//                ).execute();
//
//        // 3. Odczyt tokenów
//        String accessToken = tokenResponse.getAccessToken();
//        String refreshToken = tokenResponse.getRefreshToken();
//    }
}
