package com.example.schedule_develop.config.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class LoginFilter implements Filter {

    private static final String[] openUrls = {
            "/users",
            "/users/login",
            "/users/test"
    };

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpServletResponse httpRes = (HttpServletResponse) res;

        String uri = httpReq.getRequestURI();

        for (String open : openUrls) {
            if (uri.startsWith(open)) {
                chain.doFilter(req, res);
                return;
            }
        }

        HttpSession session = httpReq.getSession(false);
        Object loginUser = (session != null) ? session.getAttribute("loginUser") : null;

        if (loginUser == null) {
            httpRes.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpRes.getWriter().write("로그인이 필요합니다.");
            return;
        }

        chain.doFilter(req, res);
    }
}