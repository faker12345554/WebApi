package com.admin.config;

import com.admin.tool.HttpHelper;
import com.admin.wrapper.MAPIHttpServletRequestWrapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class CorsConfig extends OncePerRequestFilter {

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 防止流读取一次后就没有了, 所以需要将流继续写出去，提供后续使用
        ServletRequest requestWrapper = new MAPIHttpServletRequestWrapper(request);
        String json = HttpHelper.getBodyString(requestWrapper);
        filterChain.doFilter(requestWrapper, response);
    }

}
