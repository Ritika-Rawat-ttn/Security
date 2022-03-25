package com.example.Security.Security.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID="couponservice";


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID);

    }
//Q2Grant different Roles to different users and make sure that only authorized users of a given type can access the resource.
    //Grant given  is hasRole and hasAnyRole given to amny user.
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().mvcMatchers(HttpMethod.GET, "/couponapi/coupons/{code:^[A-Z]*$}").hasAnyRole("USER", "ADMIN")
                .mvcMatchers(HttpMethod.POST, "/couponapi/coupons").hasRole("ADMIN")
                .anyRequest().denyAll().and().csrf().disable();

    }
}
