package io.github.kloping.mywebsite.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author github-kloping
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Set<String> NEED_AUTH_PAGES = new CopyOnWriteArraySet<>();

    static {
        NEED_AUTH_PAGES.add("/upload.html");
        NEED_AUTH_PAGES.add("/upload");
        NEED_AUTH_PAGES.add("/pcm");
        NEED_AUTH_PAGES.add("/v0.html");
        NEED_AUTH_PAGES.add("/user");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .failureForwardUrl("/fail")
                .failureUrl("/login.html?tips=error")
                .defaultSuccessUrl("/")
                .and()
                .logout()
                .logoutUrl("/logout")
                .and()
                .authorizeRequests()
                .mvcMatchers(NEED_AUTH_PAGES.toArray(new String[0]))
                .authenticated()
                .anyRequest()
                .permitAll()
                .and()
                .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}