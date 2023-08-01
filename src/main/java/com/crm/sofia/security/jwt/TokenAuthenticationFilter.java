package com.crm.sofia.security.jwt;

import com.crm.sofia.services.user.LocalUserDetailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private LocalUserDetailService customUserDetailsService;

    @Autowired
    private EntityManager entityManager;

    private void raiseTokenInvalidationException(HttpServletResponse response)
            throws IOException {

        byte[] body = new ObjectMapper().writeValueAsBytes(Map.of(
                "code", "t_invalid",
                "message", "Invalid Token",
                "category", "005-01",
                "isVisible", false
        ));

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getOutputStream().write(body);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwt = getJwtFromRequest(request);
        String contextPath = request.getRequestURI();

        if (StringUtils.hasText(jwt)) {

            boolean isTokenValid = tokenProvider.validateToken(jwt);
            if (!isTokenValid) {
                this.raiseTokenInvalidationException(response);
                return;
            }

            String userId = tokenProvider.getUserIdFromToken(jwt);
            UserDetails userDetails = customUserDetailsService.loadUserById(userId);
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();

            Map<String, String[]> parameters = request.getParameterMap();

            String type = "";

            if (contextPath.startsWith("/api/form")) {
                type = "form";
            }

            if (contextPath.startsWith("/api/list")) {
                type = "list";
            }

            if (contextPath.startsWith("/api/component")) {
                type = "component";
            }

            if (contextPath.startsWith("/api/menu")) {
                type = "menu";
            }

            if (contextPath.startsWith("/api/dashboard")) {
                type = "dashboard";
            }

            if (contextPath.startsWith("/api/report")) {
                type = "report";
            }

            if (contextPath.startsWith("/api/xls-import")) {
                type = "xls_import";
            }

            if (contextPath.startsWith("/api/search")) {
                type = "search";
            }

            if (contextPath.startsWith("/api/custom-query")) {
                type = "custom_query";
            }

            if (!type.equals("") && parameters.containsKey("id")) {
                String entityId = parameters.get("id")[0];

                if (!this.isEntityRestricted(type, entityId)) {
                    authorities.add(new SimpleGrantedAuthority("unrestricted_role"));
                } else {
                    authorities = this.getAuthorities(type, entityId, userId);
                }
            }

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private Boolean isEntityRestricted(String type, String id) {
        Query query = entityManager.createNativeQuery("SELECT access_control_enabled FROM " + type + " WHERE id = :id");
        query.setParameter("id", id);
        List<Object[]> fields = query.getResultList();

        for (Object field : fields) {
            return (Boolean) (field == null ? false : field);
        }

        return false;
    }

    private List<SimpleGrantedAuthority> getAuthorities(String type, String entityId, String userId) {

        String queryString =
                " SELECT " +
                        " ac.create_entity, " +
                        " ac.delete_entity, " +
                        " ac.read_entity, " +
                        " ac.update_entity " +
                        " FROM access_control ac " +
                        " WHERE ac.type = :type " +
                        " AND ac.entity_id = :entityId " +
                        " AND ac.role_id IN ( SELECT role_id FROM user_role WHERE user_id = :userId);   ";

        Query query = entityManager.createNativeQuery(queryString);

        query.setParameter("type", type);
        query.setParameter("entityId", entityId);
        query.setParameter("userId", userId);

        List<Object[]> fields = query.getResultList();

        Boolean createEntity = false;
        Boolean deleteEntity = false;
        Boolean readEntity = false;
        Boolean updateEntity = false;

        for (Object[] field : fields) {
            Boolean createEntityRow = (Boolean) field[0];
            Boolean deleteEntityRow = (Boolean) field[1];
            Boolean readEntityRow = (Boolean) field[2];
            Boolean updateEntityRow = (Boolean) field[3];

            if (createEntityRow) createEntity = true;
            if (deleteEntityRow) deleteEntity = true;
            if (readEntityRow) readEntity = true;
            if (updateEntityRow) updateEntity = true;
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        if (createEntity) {
            authorities.add(new SimpleGrantedAuthority("create_role"));
        }

        if (readEntity) {
            authorities.add(new SimpleGrantedAuthority("read_role"));
        }

        if (updateEntity) {
            authorities.add(new SimpleGrantedAuthority("update_role"));
        }

        if (deleteEntity) {
            authorities.add(new SimpleGrantedAuthority("delete_role"));
        }

        return authorities;
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
