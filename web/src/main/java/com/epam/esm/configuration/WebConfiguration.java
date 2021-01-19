package com.epam.esm.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The type WebConfiguration.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.epam.esm.controller", "com.epam.esm" })
public class WebConfiguration implements WebMvcConfigurer {
}
