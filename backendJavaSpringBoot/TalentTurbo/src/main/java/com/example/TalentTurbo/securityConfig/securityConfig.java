package com.example.TalentTurbo.securityConfig;

import com.example.TalentTurbo.Jwtfilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class securityConfig {
    @Bean
    public PasswordEncoder passwordEncoder()throws Exception{
        return new BCryptPasswordEncoder();
    }
@Bean
    public SecurityFilterChain filterChain(HttpSecurity http, Jwtfilter jwtfilter)throws Exception{
    http
            .csrf(AbstractHttpConfigurer::disable)
            .cors(cors->cors.configurationSource(corsConfigurationSource()))
            .authorizeHttpRequests((auth)-> auth
                    .requestMatchers("/auth/**")
                    .permitAll()
                    .anyRequest().authenticated())
            .addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
}
@Bean
public CorsConfigurationSource corsConfigurationSource(){
    CorsConfiguration corsConfiguration=new CorsConfiguration();
    corsConfiguration.setAllowedOrigins(List.of("*"));
    corsConfiguration.setAllowedMethods(List.of("GET","PUT","DELETE","POST"));
    corsConfiguration.setAllowedHeaders(List.of("*"));
    UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**",corsConfiguration);
    return source;
}

}
