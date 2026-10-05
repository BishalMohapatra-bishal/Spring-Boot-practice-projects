package com.LearningFilters.LearningFilters.filerLecture1;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

//@Component
//@Order(2)
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        long startTime = System.currentTimeMillis();

        HttpServletRequest httpServletRequest =
                (HttpServletRequest) servletRequest;

        HttpServletResponse httpServletResponse =
                (HttpServletResponse) servletResponse;

        String requestID = UUID.randomUUID().toString();

        httpServletResponse.setHeader("X-Request-ID", requestID);


        System.out.println("Incoming Request : "
                + httpServletRequest.getMethod() + "  "
                + httpServletRequest.getRequestURI());

        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            long duration = System.currentTimeMillis() - startTime;

            System.out.println("Response status: " + httpServletResponse.getStatus());

            System.out.println("API Response time : " + duration);
        }



//        System.out.println("Request entered in Logging filter");
//
//        filterChain.doFilter(servletRequest, servletResponse);
//
//        System.out.println("Request exiting in Logging filter");

    }
}
