package com.guidelines.demo.util.interceptor;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;

public class HeaderInterceptor implements HandlerInterceptor {
    private final HeaderHolder headerHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        Map<String, String> tokenValue = JwtUtil.getValueFromToken(token, new String[]{"nip", "name", "given_name", "preferred_username", "role", "kode_kantor"});
        String nip = tokenValue.getOrDefault("nip","");
        String name = tokenValue.getOrDefault("name","");
        String givenName = tokenValue.getOrDefault("given_name","");
        String username = tokenValue.getOrDefault("preferred_username", "");
        String remoteAddress = request.getRemoteAddr() == null ? "127.0.0.1": request.getRemoteAddr();
        String role = tokenValue.getOrDefault("role", "");
        String kodeKantor = tokenValue.getOrDefault("kode_kantor", "");
        headerHolder.setNip(nip);
        headerHolder.setName(name);
        headerHolder.setGivenName(givenName);
        headerHolder.setUsername(username);
        headerHolder.setRemoteAddress(remoteAddress);
        headerHolder.setKodeKantor(kodeKantor);
        headerHolder.setRoles(role.split(","));
        return true;
    }

    public HeaderInterceptor(HeaderHolder headerHolder) {
        this.headerHolder = headerHolder;
    }
}
