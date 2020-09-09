package com.crm.sofia.filters;

import com.crm.sofia.dto.auth.JWTClaimsResponseDTO;
import com.crm.sofia.repository.user.UserRepository;
import com.crm.sofia.services.auth.JWTService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;


@Component
public class JWTAuthFilter extends GenericFilterBean {

    // The name of the token when it comes in the headers.
    private static final String HEADER_NAME = "Authorization";

    // The prefix of the token's value when it comes in the headers.
    private static final String HEADER_TOKEN_PREFIX = "Bearer";

    // The name of the token when it comes as a url param.
    private static final String PARAM_NAME = "bearer";

    // The name of the token when it comes as a url param.
    private static final String WEBSOCKET_PARAM_NAME = "wst";

    private final JWTService jwtService;

    private final UserRepository userRepository;

    public JWTAuthFilter(JWTService jwtService,
                         UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    //  /**
    //   * Parses a JWT token (compact or normal) and returns its String representation while it is also
    //   * validating the token.
    //   *
    //   * @param jwt The JWT to decode.
    //   * @param secret The secret used to sign the JWT.
    //   * @return Returns the String representation of the JWT.
    //   */
    //  private static String tokenToString(String jwt, String secret) {
    //    return Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(secret.getBytes()))
    //        .parse(jwt)
    //        .toString();
    //  }

  private static String getRawToken(HttpServletRequest request) {
    // Try to obtain the token from the headers.
    String token = request.getHeader(HEADER_NAME);
    if (StringUtils.isNotBlank(token)) {
      return token.replace(HEADER_TOKEN_PREFIX, "");
    }

    // Next, try to obtain the token from a URL param.
    token = request.getParameter(PARAM_NAME);
    if (StringUtils.isNotBlank(token)) {
      return token;
    }

    token = request.getParameter(WEBSOCKET_PARAM_NAME);
    if (StringUtils.isNotBlank(token)) {
      byte[] decodedBytes = Base64.getDecoder().decode(token);
      return new String(decodedBytes);
    }

    // If no token found, return null.
    return null;
  }

  private Authentication getAuthentication(HttpServletRequest request) {
    String jwtToken = getRawToken(request);
    final JWTClaimsResponseDTO jwtClaimsResponseDTO = jwtService.getClaims(jwtToken);
    if (jwtClaimsResponseDTO != null && StringUtils.isNotBlank(jwtClaimsResponseDTO.getSubject())) {
      // Create a Spring Authentication token, with the username of the user as a Principal
      // and the User Id as credentials).

//      List<GrantedAuthority> grantedAuths = null;
//
//      return new UsernamePasswordAuthenticationToken(jwtClaimsResponseDTO.getSubject(), jwtToken,
//              grantedAuths);

      return new UsernamePasswordAuthenticationToken(jwtClaimsResponseDTO.getSubject(), jwtToken);

    } else {
      return null;
    }
  }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        Authentication authentication = getAuthentication((HttpServletRequest) request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
