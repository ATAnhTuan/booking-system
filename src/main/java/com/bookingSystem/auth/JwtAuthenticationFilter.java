package com.bookingSystem.auth;

import com.bookingSystem.users.userEnum.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    private final JwtTokenProvider jwtTokenProvider;
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public void setRequestMappingHandlerMapping(RequestMappingHandlerMapping requestMappingHandlerMapping) {
        this.requestMappingHandlerMapping = requestMappingHandlerMapping;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        try {
            RequireRoles requireRoles = getRequiredRoles(request);

            if (requireRoles == null) {
                filterChain.doFilter(request, response);
                return;
            }

            String authHeader = request.getHeader(AUTHORIZATION_HEADER);
            if (authHeader == null || !authHeader.startsWith(TOKEN_PREFIX)) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing bearer token");
                return;
            }

            Claims claims = jwtTokenProvider.validateAccessToken(authHeader);
            AuthenticatedUser authenticatedUser = jwtTokenProvider.toAuthenticatedUser(claims);
            SecurityContext.setCurrentUser(authenticatedUser);

            if (!isAuthorized(authenticatedUser.getRole(), requireRoles.value())) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
                return;
            }

            filterChain.doFilter(request, response);
        } catch (JwtException | IllegalArgumentException | ResponseStatusException ex) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
        } catch (Exception ex) {
            throw new ServletException("Unable to resolve authorization rules", ex);
        } finally {
            SecurityContext.clear();
        }
    }

    private RequireRoles getRequiredRoles(HttpServletRequest request) throws Exception {
        if (requestMappingHandlerMapping == null) {
            return null;
        }

        HandlerExecutionChain handler = requestMappingHandlerMapping.getHandler(request);
        if (handler == null || !(handler.getHandler() instanceof HandlerMethod)) {
            return null;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler.getHandler();
        RequireRoles methodRoles = handlerMethod.getMethodAnnotation(RequireRoles.class);
        if (methodRoles != null) {
            return methodRoles;
        }

        return handlerMethod.getBeanType().getAnnotation(RequireRoles.class);
    }

    private boolean isAuthorized(UserRole currentRole, UserRole[] allowedRoles) {
        return allowedRoles.length == 0 || Arrays.asList(allowedRoles).contains(currentRole);
    }
}
