package com.infoshare.lumato.filters;

import com.infoshare.lumato.logic.utils.HttpUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter
public class RestrictPageFilter implements Filter {

    public void init(FilterConfig filterConfig) {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(true);

        if (session.getAttribute("currentUser") == null) {
            resp.sendRedirect(HttpUtils.getRequest().getContextPath() + "/login.xhtml");
        } else {
            chain.doFilter(request, response);
        }
    }

    public void destroy() {
    }
}
