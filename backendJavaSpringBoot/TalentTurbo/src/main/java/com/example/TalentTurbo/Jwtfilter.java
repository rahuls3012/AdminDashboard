package com.example.TalentTurbo;

import com.example.TalentTurbo.utils.Jwtutil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class Jwtfilter extends OncePerRequestFilter {
    @Autowired
    private Jwtutil jwtutil;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader=request.getHeader("Authorization");
        if(authHeader!=null&&authHeader.startsWith("Bearer ")){
            String token=authHeader.substring(7);
            if(jwtutil.validateJWT(token)){
               String mail=jwtutil.extractMail(token);
                var auth=new UsernamePasswordAuthenticationToken(mail,null,List.of());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }

        }
        filterChain.doFilter(request,response);
    }
}