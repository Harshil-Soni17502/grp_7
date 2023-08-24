package com.bank.OnlinebankingSystem.filter;

import com.bank.OnlinebankingSystem.Service.AdminService;
import com.bank.OnlinebankingSystem.Service.UserService;
import com.bank.OnlinebankingSystem.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter
        extends OncePerRequestFilter
{

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AdminService adminService;
    
    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        String token = null;
        String email = null;
        System.out.println("filter bp 1");
        if(authorization != null && authorization.startsWith("Bearer ")){
            token = authorization.substring(7);
            email = jwtUtility.getUsernameFromToken(token);
            System.out.println("filter bp 2");
            System.out.println(email);
        }

        if(email != null && SecurityContextHolder.getContext().getAuthentication() ==null){
            System.out.println("filter bp 3");
            UserDetails userDetails = null;
//            if(email.equals("admin2@gmail.com")) {
//            	userDetails =
//                        adminService.loadUserByUsername(email);
//            }
            //else {
            	userDetails =
                        userService.loadUserByUsername(email);
            //}
            
            if(jwtUtility.validateToken(token,userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                System.out.println("filter bp 4");
            }

            System.out.println("filter bp 5");

        }
        filterChain.doFilter(request, response);

    }
}
