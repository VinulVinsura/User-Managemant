package com.example.usermanagement.configuration;

import com.example.usermanagement.Exception.ForbiddenException;
import com.example.usermanagement.dto.ResponseDto;
import com.example.usermanagement.service.AuthenticationService;
import com.example.usermanagement.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;
    private final ModelMapper modelMapper;
    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request,
                                    @Nonnull HttpServletResponse response,
                                    @Nonnull FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");



            if (authHeader ==null || !authHeader.startsWith("Bearer ")){
                if(request.getServletPath().equals("/api/v1/users/login") ||
                        request.getServletPath().equals("/api/v1/users/signup")){
                    filterChain.doFilter(request,response);
                    return;
                }
                ResponseDto responseDto=new ResponseDto("03","Not Authorized",null);
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setContentType("application/json");
                ObjectMapper mapper=new ObjectMapper();
                response.getWriter().write(mapper.writeValueAsString(responseDto));

                return;

            }




        String token = authHeader.substring(7);
        String userName = jwtService.extractUserName(token);
        if (userName!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = authenticationService.loadUserByUsername(userName);
            if (jwtService.isValid(token,userDetails)){
                UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(
                        userDetails,null,userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);

            }
        }
        filterChain.doFilter(request,response);


    }
}
