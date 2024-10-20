package com.website.online.sale.config.security;

import com.website.online.sale.base.ResponseBuilder;
import com.website.online.sale.service.security.PermissionService;
import com.website.online.sale.utils.JsonUtils;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Component
@Primary
public class JwtAuthFilter extends OncePerRequestFilter {

    public static final String HEADER_STRING = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    private final PermissionService permissionService;

    public JwtAuthFilter(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(HEADER_STRING);
        String authToken;
        if (!StringUtils.isBlank(header) && header.startsWith(TOKEN_PREFIX)) {
            authToken = header.replace(TOKEN_PREFIX, "");
        } else {
            authToken = header;
        }
        UserDetails userDetails = null;
        if (!StringUtils.isEmpty(authToken)) {
            authToken = authToken.trim();
//            userDetails = serverSecurityService.get(authToken);
            //TODO: verify token
            var httpStatus = this.permissionService.verifyToken(authToken);
            if (Objects.equals(httpStatus, 403)) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/plain; charset=UTF-8");
                response.getWriter().write(JsonUtils.stringify(ResponseBuilder.error(401,"Token quá hạn hoặc không hợp lệ để thực hiện chức năng này." )));
                return;
            }
        } else {
            throw new AuthenticationCredentialsNotFoundException("Không tìm thấy token");
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails == null ? List.of() : userDetails.getAuthorities());

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return !path.startsWith("/api") || path.startsWith("/api/ping") || path.startsWith("/api/v1/auth/token");
    }
}