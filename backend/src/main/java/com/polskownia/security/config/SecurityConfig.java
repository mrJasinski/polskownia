package com.polskownia.security.config;

import com.polskownia.security.filter.CsrfCookieFilter;
import com.polskownia.security.filter.JwtAuthFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;
import java.util.List;

@EnableWebSecurity
@Configuration
class SecurityConfig
{
    private final JwtAuthFilter jwtAuthFilter;

    SecurityConfig(final JwtAuthFilter jwtAuthFilter)
    {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception
    {
        var requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");

        http.securityContext(context -> context.requireExplicitSave(false))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource()
                {
                    @Override
                    public CorsConfiguration getCorsConfiguration(final HttpServletRequest request)
                    {
                        var config = new CorsConfiguration();

                        config.setAllowedOrigins(List.of("http://localhost:5173", "http://127.0.0.1:5173"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setExposedHeaders(Collections.singletonList(HttpHeaders.CONTENT_DISPOSITION));
                        config.setMaxAge(3600L);

                        return config;
                    }
                }))
                .csrf(csrf -> csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers(
                                "/register", "/auth", "/dashboard"
//                                , "/createCourse", "/myCourses/**", "/allCourses"
                                , "/shop/**"
//                                , "/getShopItemCategories", "/addShopItem/**", "/shopItem/**", "/getDiscountCodes"
//                                , "/sendMail", "/students", "/generateDiscountCode", "/getDiscount"
//                                ,"/restorePassword", "/changePassword", "/downloadFile/**", "/fileManager", "/uploadFile**", "/getFiles"
                                , "/articles/**"
                                , "/mailTest"
                                , "/test/**"
                                , "/calendar**"
                                , "/g-calendar/**", "/error"
                        )
//                        , "/addShopItem/**", "/getShopItem/**")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(this.jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(
                                "/dashboard"
                                , "/articles/add" , "/articles/dashboard"
//                                , "/addShopItem/**"
//                                , "/getDiscountCodes", "/students", "/generateDiscountCode", "/changePassword", "/downloadFile/**", "/fileManager", "/uploadFile**"
//                                , "/getFiles", "/createOrder", "/orders", "/myShelf", "/allOrders", "/order/**"
//                                , "/sendCustomerData", "/getCustomerDataList", "/getDefaultCustomerData"
                        ).authenticated()
                        .requestMatchers(
                                "/register", "/auth"
                                , "/shop/**"
//                                , "/sendMail"
                                , "/articles/**"
                                , "/mailTest"
                                ,"/test/**"
//                                TODO dla testów noAuth
                                , "/calendar**"
                                , "/g-calendar/**", "/error"
                        ).permitAll())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
