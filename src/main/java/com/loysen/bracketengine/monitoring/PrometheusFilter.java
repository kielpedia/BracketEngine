package com.loysen.bracketengine.monitoring;


import io.prometheus.client.Counter;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kielpedia on 8/15/15.
 */
@Component
public class PrometheusFilter implements Filter{


    static final Counter c = Counter.build().name("request_counter").help("counter for tracking requests in the " +
            "bracket engine")
            .labelNames("method", "handler")
            .register();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        if(httpServletRequest.getRequestURI().startsWith("/api")) {
            Counter.Child child = c.labels(httpServletRequest.getMethod(), httpServletRequest.getRequestURI());
            child.inc();
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
