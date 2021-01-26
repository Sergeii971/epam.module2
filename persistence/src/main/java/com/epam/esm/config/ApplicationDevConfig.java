package com.epam.esm.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * The type ApplicationDevConfig.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
@Configuration
@Profile("dev")
public class ApplicationDevConfig {
    private static final String PROPERTIES_FILENAME = "/config/databaseDev.properties";

    @Bean
    public JdbcTemplate jdbcTemplate() {
        HikariConfig config = new HikariConfig(PROPERTIES_FILENAME);
        HikariDataSource pool = new HikariDataSource(config);
        return new JdbcTemplate(pool);
    }
}
