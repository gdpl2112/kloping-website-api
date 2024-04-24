package io.github.kloping.mywebsite.config;

import io.github.kloping.mywebsite.github.*;
import io.github.kloping.mywebsite.mapper.UserTempMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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

    @Autowired
    GitHubRequestUtils utils;

    @Value("${spring.security.oauth2.client.registration.github.clientId}")
    String clientId;

    @Value("${spring.security.oauth2.client.registration.github.clientSecret}")
    String clientSecret;

    @Value("${spring.security.oauth2.client.registration.github.redirectUri}")
    String redirectUri;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    UserTempMapper userTempMapper;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login.html")
                .loginProcessingUrl("/login")
                .failureForwardUrl("/fail")
                .failureUrl("/login.html?tips=error")
                .defaultSuccessUrl("/");

        http.logout().logoutUrl("/logout");

        http.authorizeRequests()
                //匹配这些地址
                .mvcMatchers(NEED_AUTH_PAGES.toArray(new String[0]))
                //需要认证
                .authenticated()
                //其他的
                .anyRequest()
                //全部放行
                .permitAll();

        http.csrf().disable();

        GithubCodeAuthenticationProcessingFilter githubCodeAuthenticationFilter =
                new GithubCodeAuthenticationProcessingFilter(clientId, clientSecret, redirectUri, utils);

        githubCodeAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
        githubCodeAuthenticationFilter.setAuthenticationSuccessHandler(new GithubCodeAuthenticationSuccessHandler());
        githubCodeAuthenticationFilter.setAuthenticationFailureHandler(new GithubCodeAuthenticationFailureHandler());

        GithubCodeAuthenticationProvider githubCodeAuthenticationProvider =
                new GithubCodeAuthenticationProvider(userDetailsService, userTempMapper);

        http.authenticationProvider(githubCodeAuthenticationProvider)
                .addFilterAfter(githubCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public SessionRegistryImpl sessionRegistry() {
        return new SessionRegistryImpl();
    }
}