package com.admin.Filter;

import com.admin.wrapper.ParameterRequestWrapper;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
@WebFilter(urlPatterns = "/**", filterName = "ParamsFilter",dispatcherTypes=DispatcherType.REQUEST)
@Order(1)
public class ParamsFilter  implements Filter {


    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("/Alarm/SaveOrUpdateAlarm", "/Reminder/SaveOrUpdateReminder", "/Report/SaveOrUpdateReport","/Person/insertprison")));
    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;
        ParameterRequestWrapper parmsRequest = new ParameterRequestWrapper((HttpServletRequest) arg0);
        String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");
        boolean allowedPath = ALLOWED_PATHS.contains(path);
        System.out.println(path);
        if (allowedPath) {
            arg2.doFilter(arg0, arg1);
        }
        else {
            arg2.doFilter(parmsRequest, arg1);
        }
    }


    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

}
