/**
 * A filter to parse the auth request to access the id of the employee that is currently lopgged in
 */
package com.portfolioprj.humanresourcemanagementapi.helpers;

import com.portfolioprj.humanresourcemanagementapi.CONSTANTS;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class AuthFilter extends GenericFilterBean {

    // We will use the string "Bearer " to extract the actual bearer token from the auth header in the request
    private static final String BEARER_CONSTANT = "Bearer ";
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String token = extractBearerToken(httpServletRequest);

        if (token == null) {
            throw new ServletException("Missing or invalid Bearer Token");
        }

        if (token.length() > 1) {
            try {
                // will contain user details set from evaluating token
                Claims claims = Jwts.parser().setSigningKey(CONSTANTS.API_STRING_KEY)
                        .parseClaimsJws(token).getBody();
                // allows us to attach the userId to the request method
                httpServletRequest.setAttribute("emplid", Integer.parseInt(claims.get("emplid").toString()));
            } catch (Exception e) {
                httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "Token is expired");
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
    private String extractBearerToken(HttpServletRequest httpRequest) {
        String bearerToken = httpRequest.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith(BEARER_CONSTANT)) {
            return bearerToken.substring(BEARER_CONSTANT.length());
        }
        return null;
    }
}

