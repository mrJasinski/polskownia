package com.polskownia.security.filter;

import com.polskownia.utils.Utils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

class JWTTokenGeneratorFilter extends OncePerRequestFilter
{
    private final String secret;
    private final String secretDefault;
    private final int exp;

    JWTTokenGeneratorFilter(@Value("${jwt.secret}") final String secret, @Value("${jwt.secret_default}") final String secretDefault, @Value("${jwt.exp}") final int exp)
    {
        this.secret = secret;
        this.secretDefault = secretDefault;
        this.exp = exp;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (null != authentication)
        {
            var env = getEnvironment();

            if (null != env)
            {
                var secret = env.getProperty(this.secret, this.secretDefault);

                var secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

                var jwt = Jwts.builder().issuer("Eazy Bank").subject("JWT Token")
                        .claim("username", authentication.getName())
                        .claim("authorities", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
                        .issuedAt(new Date())
                        .expiration(new Date((new Date()).getTime() + this.exp))
                        .signWith(secretKey).compact();
                response.setHeader(Utils.JWT_HEADER, jwt);
            }
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException
    {
        return !request.getServletPath().equals("/auth");
    }
}
