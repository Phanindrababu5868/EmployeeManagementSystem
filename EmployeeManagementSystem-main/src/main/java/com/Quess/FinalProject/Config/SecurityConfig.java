package com.Quess.FinalProject.Config;

//import com.Quess.FinalProject.Security.userdetails;
import com.Quess.FinalProject.Security.userdetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private userdetails employee;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable().httpBasic().and().authorizeRequests()
                .antMatchers("/Assets/getall/*").hasAnyAuthority("Admin","Manager","Employee")

                .antMatchers("/Organization/getbyid/*","/Assets/getbyid/**").hasAnyAuthority("Admin","Manager")

                .antMatchers("/Employee/getbyid/*").hasAnyAuthority("Admin","Manager")

                .antMatchers("Organization/getall/*").hasAnyAuthority("Admin","Manager")

                .antMatchers("/Employee/delete/**","/Organization/delete/**","/Assets/delete/**").hasAnyAuthority("Admin")

                .antMatchers("/Employee/put/**","/Organization/put/**","/Assets/put/**").hasAnyAuthority("Admin","Manager")

                .antMatchers("/Employee/post/**","/Organization/post/**","/Assets/post/**").hasAnyAuthority("Admin")

                .antMatchers("/Employee/getall/**").hasAnyAuthority("Manager","Admin")

                .antMatchers("/Employee/get/*").hasAnyAuthority("Admin","Employee","Manager")




                .anyRequest().authenticated();

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.employee).passwordEncoder(passwordEncoder());
    }
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
