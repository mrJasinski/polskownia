package com.polskownia.auth;

import com.polskownia.user.UserService;
import com.polskownia.user.dto.UserLoggedDTO;
import org.springframework.stereotype.Service;

@Service
class AuthService
{
    private final UserService userService;

    AuthService(final UserService userService)
    {
        this.userService = userService;
    }

    UserLoggedDTO getLoggedUserDataByEmail(final String email, final String token)
    {
        var user = this.userService.getUserByEmailAsDto(email);

        return new UserLoggedDTO(
            user.getName()
            , user.getEmail()
            , token
            , user.getRole().toString());
    }
}
