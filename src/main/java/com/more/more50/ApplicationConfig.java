package com.more.more50;

import java.util.Arrays;
import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableAsync
public class ApplicationConfig 
{
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception
    {
        http.
        csrf((csrf) -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
        // csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        
        http.cors(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    CorsConfigurationSource configurationSource()
    {
        CorsConfiguration configuration =new CorsConfiguration();
        configuration.setMaxAge((long)1800);
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080", "http://localhost:5173"));
        configuration.setAllowedMethods(Arrays.asList("POST","GET"));
        configuration.setExposedHeaders(Arrays.asList("Access-Control-Allow-Origin","Access-Control-Allow-Credentials"));
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return null;
    }

    @Bean
    public Executor taskExecutor()
    {
        ThreadPoolTaskExecutor executor =  new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(16);
        executor.setQueueCapacity(8000);
        executor.setThreadNamePrefix("more_5-0");
        executor.initialize();
        return executor;
    }
}
