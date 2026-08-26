package com.polskownia.user;

import com.polskownia.user.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService
{
    private final UserRepository userRepo;

    public UserService(final UserRepository userRepo)
    {
        this.userRepo = userRepo;
    }

    public User getUserByEmail(final String email)
    {
        return this.userRepo.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("User with given email not found!"));
    }

    public UserDTO getUserByEmailAsDto(final String email)
    {
        return getUserByEmail(email).toDto();
    }
}
