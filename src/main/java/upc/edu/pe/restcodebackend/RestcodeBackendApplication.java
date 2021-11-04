package upc.edu.pe.restcodebackend;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import upc.edu.pe.restcodebackend.security.JWTAuthorizationFilter;


@SpringBootApplication
public class RestcodeBackendApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(RestcodeBackendApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(RestcodeBackendApplication.class);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    @EnableWebSecurity
    @Configuration
    class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.cors()
                    .and()
                    .csrf().disable()
                    .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/users/**").permitAll()
                    .antMatchers("/v2/api-docs",
                            "/configuration/ui",
                            "/swagger-resources/**",
                            "/configuration/security",
                            "/swagger-ui.html",
                            "/webjars/**").permitAll()
                    .antMatchers("/api/**").authenticated();

        }

    }
}
