//package com.cristi.pixogram.infra.configurations.jwt;
//
//import org.springframework.context.annotation.Primary;
//import org.springframework.context.annotation.Profile;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//
//@Service
//@Profile("JWT")
//@Primary
//public class JwtDummyUserService implements UserDetailsService {
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return new User(
//                username, "", true, true, true,
//                true, Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))
//        );
//    }
//}
