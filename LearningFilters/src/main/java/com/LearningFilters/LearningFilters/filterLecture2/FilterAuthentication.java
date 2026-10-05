package com.LearningFilters.LearningFilters.filterLecture2;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class FilterAuthentication implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest =
                (HttpServletRequest) servletRequest;

        HttpServletResponse httpResponse =
                (HttpServletResponse) servletResponse;

        String token = httpRequest.getHeader("token");

        if (token == null || !token.equals("12345")) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            httpResponse.setContentType("application/json");
            httpResponse.getWriter().write(
                    "{\n" +
                            "    \"message\" : \"Authentication is required\"\n" +
                            "}"
            );
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
