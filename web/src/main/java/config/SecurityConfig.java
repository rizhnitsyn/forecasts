package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/home").permitAll()
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
                    .logoutSuccessUrl("/home");

//        http
//                .authorizeRequests()
////                    .antMatchers("/home")
////                        .authenticated()
////                    .antMatchers("/admin")
////                        .hasAuthority("ADMIN")
//                    .anyRequest()
//                        .authenticated()
//                .and()
//                    .formLogin()
//                        .loginPage("/login")
////                        .loginProcessingUrl("/customLoginUrl")
//                        .defaultSuccessUrl("/home")
//                        .usernameParameter("login")
//                        .passwordParameter("password")
////                .and()
////                    .logout()
////                    .logoutUrl("/logout") //POST!
////                .and()
////                    .httpBasic()
//                .and()
//                .csrf().disable(); // To make logout work with GET - Don't use!!!!

        http.userDetailsService(userDetailsService);
    }
}
