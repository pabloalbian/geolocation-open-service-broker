package com.example.geolocationopenservicebroker.config;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String key = req.getHeader("X-Broker-API-Version") == null ? null : req.getHeader("X-Broker-API-Version");

        if(key != null) {
            filterChain.doFilter(request, response);
        } else {
            HttpServletResponse resp = (HttpServletResponse) response;
            String error = "Header not present";

            resp.reset();
            resp.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
            response.setContentLength(error .length());
            response.getWriter().write(error);
        }


    }
}