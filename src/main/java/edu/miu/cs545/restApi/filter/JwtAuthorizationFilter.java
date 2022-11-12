package edu.miu.cs545.restApi.filter;

import edu.miu.cs545.restApi.service.impl.MyUserDetailsService;
import edu.miu.cs545.restApi.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
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

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {


    private final JwtUtil jwtUtil;

    private final MyUserDetailsService myUserDetailsService;

    public JwtAuthorizationFilter(JwtUtil jwtUtil, MyUserDetailsService myUserDetailsService) {
        this.jwtUtil = jwtUtil;
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().equals("/api/v1/authenticate") || request.getServletPath().equals("/api/v1/authenticate/refreshToken")) {
            filterChain.doFilter(request, response);
        } else {
            final String authorizationHeader = request.getHeader(AUTHORIZATION);
            String accessToken = null;
            String username = null;
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                accessToken = authorizationHeader.substring(7);
                try {
                    username = jwtUtil.getUsernameFromToken(accessToken);
                } catch (ExpiredJwtException e) {//TODO
                    String isRefreshToken = request.getHeader("isRefreshToken");
                }
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
                boolean isValid = jwtUtil.validateToken(accessToken);
                if (isValid) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }

            filterChain.doFilter(request, response);
        }
    }
}
