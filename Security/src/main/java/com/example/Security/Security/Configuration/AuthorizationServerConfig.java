package com.example.Security.Security.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;
import java.security.KeyPair;

//public class AuthorizationServerConfig extends  AuthorizationServerConfigurerAdapter {


//    private static final String RESOURCE_ID="couponservice";
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private UserDetails userDetailsService;
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Value("${keyFile}")
//    private String keyFile;
//    @Value("${password}")
//    private String password;
//    @Value("${alias}")
//    private String alias;
////    private JwtAccessTokenConverter jwtAccessTokenConverter;
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.tokenStore(tokenStore()).accessTokenConverter(jwtAccessTokenConverter())
//                .authenticationManager(authenticationManager)
//                .userDetailsService((UserDetailsService) userDetailsService);
//    }
//
////Q1 Implement oauth 2 using spring security and authenticate a user which is saved in database using spring data jpa.
//    //AS we know JWT will take key as a input and thn it will generate token and will pass it.
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory().withClient("couponclientapp").secret(passwordEncoder.encode("9999"))
//                .authorizedGrantTypes("password","refresh_token").scopes("read","write").resourceIds(RESOURCE_ID);
//    }
//
//
//
//
////    @Bean
////    public TokenStore tokenStore(){
////        JwtAccessTokenConverter jwtAccessTokenConverter=new JwtAccessTokenConverter();
////        return new JwtTokenStore(jwtAccessTokenConverter);
////    }
//    @Bean
//    public TokenStore tokenStore(){
//        return new JwtTokenStore(jwtAccessTokenConverter());
//    }
//
//
//
////JWTToken
//    @Bean
//    public AccessTokenConverter jwtAccessTokenConverter(){
//        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
//
//        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource(keyFile),
//                password.toCharArray());
//        KeyPair keyPair = keyStoreKeyFactory.getKeyPair(alias);
//        jwtAccessTokenConverter.setKeyPair(keyPair);
//        return jwtAccessTokenConverter;
//
//    }
//
//


    @Configuration
    @EnableAuthorizationServer
    public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

        private static final String RESOURCE_ID = "couponservice";

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private UserDetailsService userDetailsService;

        @Autowired
        private DataSource dataSource;

        @Value("${keyFile}")
        private String keyFile;
        @Value("${password}")
        private String password;
        @Value("${alias}")
        private String alias;

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints.tokenStore(tokenStore()).accessTokenConverter(jwtAccessTokenConverter())
                    .authenticationManager(authenticationManager)
                    .userDetailsService(userDetailsService);
        }

//Q1 Implement oauth 2 using spring security and authenticate a user which is saved in database using spring data jpa.
        //    //AS we know JWT will take key as a input and thn it will generate token and will pass it.
        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.inMemory().withClient("couponclientapp").secret(passwordEncoder.encode("9999"))
                    .authorizedGrantTypes("password", "refresh_token").scopes("read", "write").resourceIds(RESOURCE_ID);
        }


        @Bean
        public TokenStore tokenStore() {
            return new JwtTokenStore(jwtAccessTokenConverter());
        }


        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter() {
            JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();

            KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource(keyFile),
                    password.toCharArray());
            KeyPair keyPair = keyStoreKeyFactory.getKeyPair(alias);
            jwtAccessTokenConverter.setKeyPair(keyPair);
            return jwtAccessTokenConverter;

        }


    }


