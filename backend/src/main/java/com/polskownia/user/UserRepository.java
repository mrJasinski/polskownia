package com.polskownia.user;

import java.util.Optional;

interface UserRepository
{
    User save(User toSave);

    Optional<User> findByEmail(String mail);
}
