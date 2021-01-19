package com.epam.esm.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * The type ApplicationConfig.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
@Configuration
public class ApplicationConfig {
    private static final String PROPERTIES_FILENAME = "/config/database.properties";

    @Bean
    public JdbcTemplate jdbcTemplate() {
        HikariConfig config = new HikariConfig(PROPERTIES_FILENAME);
        HikariDataSource pool = new HikariDataSource(config);
        return new JdbcTemplate(pool);
    }
}
