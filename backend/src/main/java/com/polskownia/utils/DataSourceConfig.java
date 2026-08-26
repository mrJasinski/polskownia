package com.polskownia.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceConfig
{
    private String url;
    private String username;
    private String password;


//    @Bean
//    public DataSource getDataSource()
//    {
//        return DataSourceBuilder.create()
//                .driverClassName("org.postgresql.Driver")
//                .url(url)
//                .username(username)
//                .password(password)
//                .build();
//    }

    @Bean
    public DataSource dataSource()
    {
        return DataSourceBuilder.create()
                .url("jdbc:h2:mem:testDb;DB_CLOSE_DELAY=-1")
                .username("sa")
                .password("")
                .driverClassName("org.h2.Driver")
                .build();
    }

    public String getUrl()
    {
        return this.url;
    }

    public void setUrl(final String url)
    {
        this.url = url;
    }

    public String getUsername()
    {
        return this.username;
    }

    public void setUsername(final String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(final String password)
    {
        this.password = password;
    }
}
