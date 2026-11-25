package com.polskownia.security.filter;

import com.polskownia.utils.Utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

class JWTTokenValidatorFilter extends OncePerRequestFilter
{

    private final String secret;
    private final String secretDefault;

    JWTTokenValidatorFilter(@Value("${jwt.secret}") final String secret,  @Value("${jwt.secret_default}") final String secretDefault)
    {
        this.secret = secret;
        this.secretDefault = secretDefault;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        String jwt = request.getHeader(Utils.JWT_HEADER);
        if(null != jwt)
        {
            try
            {
                var env = getEnvironment();

                if (null != env)
                {
                    var secret = env.getProperty(this.secret, this.secretDefault);

                    var secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

                    if(null != secretKey)
                    {
                        var claims = Jwts.parser()
                                .verifyWith(secretKey)
                                .build()
                                .parseSignedClaims(jwt)
                                .getPayload();

                        var username = String.valueOf(claims.get("username"));
                        var authorities = String.valueOf(claims.get("authorities"));
                        var authentication = new UsernamePasswordAuthenticationToken(username, null, AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }

            }
            catch (Exception exception)
            {
                throw new BadCredentialsException("Invalid Token received!");
            }
        }

        filterChain.doFilter(request,response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException
    {
        return request.getServletPath().equals("/auth");
    }
}
