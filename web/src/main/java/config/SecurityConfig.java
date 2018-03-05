package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    private static final String ENCODING = "UTF-8";

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/js/**")
                .antMatchers("/css/**")
                .antMatchers("/jpg/**");
    }

    private CharacterEncodingFilter encodingFilter(String encoding) {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding(encoding);
        filter.setForceEncoding(true);
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(encodingFilter(ENCODING),CsrfFilter.class);
        http
                .authorizeRequests()
                    .antMatchers("/home").permitAll()
                    .antMatchers("/login").permitAll()
                    .antMatchers("/userList").access("hasAuthority('ADMIN')")
                .anyRequest().authenticated()
                .and()
                    .formLogin()
                        .loginPage("/login")
                        .defaultSuccessUrl("/home")
                        .usernameParameter("login")
                        .passwordParameter("password")
                        .permitAll()
                .and()
                    .logout()
                        .logoutUrl("/logout")//POST!
                        .logoutSuccessUrl("/home")
                        .permitAll();
        http
                .userDetailsService(userDetailsService);
    }
}
