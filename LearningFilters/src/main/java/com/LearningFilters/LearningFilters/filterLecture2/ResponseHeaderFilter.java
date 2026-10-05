package com.LearningFilters.LearningFilters.filterLecture2;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.UUID;

//@Controller
public class ResponseHeaderFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest =
                (HttpServletRequest) servletRequest;

        HttpServletResponse httpResponse =
                (HttpServletResponse) servletResponse;

        String requestId = UUID.randomUUID().toString();

        httpResponse.setHeader("x-request-id", requestId);


        filterChain.doFilter(servletRequest, servletResponse);
    }
}
