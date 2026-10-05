package com.LearningFilters.LearningFilters.filerLecture1;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

//@Component
//@Order(1)
public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request =
                (HttpServletRequest) servletRequest;

        HttpServletResponse response =
                (HttpServletResponse) servletResponse;

        String token = request.getHeader("token");
        String apiKey = request.getHeader("x-api-key");

        if (token == null || !token.equals("12345")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        if (apiKey == null || !apiKey.equals("secret123")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            response.setContentType("application/json");
            response.getWriter().write(
                    "{\n" +
                            "    \"message\" : \"Invalid or missing Api Key\"\n" +
                            "}"
            );
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);

    }
}
