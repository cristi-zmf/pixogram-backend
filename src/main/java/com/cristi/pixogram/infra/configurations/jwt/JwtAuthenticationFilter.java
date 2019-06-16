//package com.cristi.pixogram.infra.configurations.jwt;
//
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.SignatureException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Profile;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//@Slf4j
//@Profile("JWT")
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    private static final String TOKEN_PREFIX = "Bearer ";
//    private static final String HEADER_STRING = "Authorization";
//    @Autowired
//    private UserDetailsService userDetailsService;
//    @Autowired
//    private TokenProvider jwtTokenUtil;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
//        String header = req.getHeader(HEADER_STRING);
//        String username = null;
//        String authToken = null;
//        if (header != null && header.startsWith(TOKEN_PREFIX)) {
//            authToken = header.replace(TOKEN_PREFIX, "");
//            try {
//                username = jwtTokenUtil.getUsernameFromToken(authToken);
//            } catch (IllegalArgumentException e) {
//                logger.error("An error occured during getting username from token", e);
//            } catch (ExpiredJwtException e) {
//                logger.warn("The token is expired and not valid anymore", e);
//            } catch (SignatureException e) {
//                logger.warn("Authentication failed. Username or Password not valid");
//            }
//        } else {
//            logger.warn("Could not find bearer string, will ignore the header");
//        }
//
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
//                UsernamePasswordAuthenticationToken authentication = jwtTokenUtil.getAuthentication(
//                        authToken, SecurityContextHolder.getContext().getAuthentication(), userDetails
//                );
//                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
//                logger.info(String.format("Authenticated user %s setting security context", username));
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//        }
//        chain.doFilter(req, response);
//    }
//}
