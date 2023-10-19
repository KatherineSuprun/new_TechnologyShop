package com.example.mainapp.config;


import com.example.mainapp.security.JwtFilter;
import com.example.mainapp.service.UserDetailsServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsServiceImpl service;
    private final JwtFilter jwtFilter;

    public SecurityConfig(UserDetailsServiceImpl service, JwtFilter jwtFilter) {
        this.service = service;
        this.jwtFilter = jwtFilter;
    }

/*    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/registration").permitAll()
                        .requestMatchers("/product/**", "/images/**", "/registration", "/user/**")
                        .hasAnyAuthority("ADMIN","USER")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/product")
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }*/
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

    public SecurityConfig(JwtFilter jwtFilter,
                          UserDetailsServiceImpl service) {
        this.jwtFilter = jwtFilter;
        this.service = service;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(encoder());
        provider.setUserDetailsService(service);
        return provider;
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(final AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = "ROLE_ADMIN > ROLE_USER";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/auth/**", "/logout")
                                .permitAll()
                                .requestMatchers("/admin/**")
                                .hasAuthority("ROLE_ADMIN")
                                .anyRequest().authenticated())
                .logout(out -> out.logoutUrl("/logout"));

        http.exceptionHandling(entry -> entry.authenticationEntryPoint(
                        (req, res, ex) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                                ex.getMessage())))
                .authenticationProvider(authenticationProvider());
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}


