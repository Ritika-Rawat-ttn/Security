package com.example.Security.Security.Configuration;

import com.example.Security.Security.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailServiceImpl userDetailService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .mvcMatchers(HttpMethod.GET,"/couponapi/coupons","/index","/showGetCoupon","/getCoupon","/couponDetails").hasAnyRole("USER","ADMIN")
                .mvcMatchers(HttpMethod.GET,"/showCreateCoupon","/createCoupon","createResponse").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/getCoupon").hasAnyRole("USER", "ADMIN")
                .mvcMatchers(HttpMethod.POST,"/couponapi/coupons","/saveCoupon").hasRole("ADMIN")
                .mvcMatchers("/","/login","/logout","/showReg","/registerUser").permitAll()
                .anyRequest().denyAll().and().csrf().disable().logout().logoutSuccessUrl("/");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
