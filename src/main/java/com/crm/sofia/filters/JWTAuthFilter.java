package com.crm.sofia.filters;

public class JWTAuthFilter{}
//
//@Component
//public class JWTAuthFilter extends GenericFilterBean {
//
//    // The name of the token when it comes in the headers.
//    private static final String HEADER_NAME = "Authorization";
//
//    // The prefix of the token's value when it comes in the headers.
//    private static final String HEADER_TOKEN_PREFIX = "Bearer";
//
//    // The name of the token when it comes as a url param.
//    private static final String PARAM_NAME = "bearer";
//
//    // The name of the token when it comes as a url param.
//    private static final String WEBSOCKET_PARAM_NAME = "wtk";
//
//    private final JWTService jwtService;
//
//    public JWTAuthFilter(JWTService jwtService) {
//        this.jwtService = jwtService;
//    }
//
//    //  /**
//    //   * Parses a JWT token (compact or normal) and returns its String representation while it is also
//    //   * validating the token.
//    //   *
//    //   * @param jwt The JWT to decode.
//    //   * @param secret The secret used to sign the JWT.
//    //   * @return Returns the String representation of the JWT.
//    //   */
//    //  private static String tokenToString(String jwt, String secret) {
//    //    return Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(secret.getBytes()))
//    //        .parse(jwt)
//    //        .toString();
//    //  }
//
//    private static String getRawToken(HttpServletRequest request) {
//        // Try to obtain the token from the headers.
//        String token = request.getHeader(HEADER_NAME);
//        if (StringUtils.isNotBlank(token)) {
//            return token.replace(HEADER_TOKEN_PREFIX, "");
//        }
//
//        // Next, try to obtain the token from a URL param.
//        token = request.getParameter(PARAM_NAME);
//        if (StringUtils.isNotBlank(token)) {
//            return token;
//        }
//
//        token = request.getParameter(WEBSOCKET_PARAM_NAME);
//        if (StringUtils.isNotBlank(token)) {
//           // byte[] decodedBytes = Base64.getDecoder().decode(token);
//          //  return new String(decodedBytes);
//            return token;
//        }
//
//        // If no token found, return null.
//        return null;
//    }
//
//    private Authentication getAuthentication(HttpServletRequest request) {
//        String jwtToken = getRawToken(request);
//        final JWTClaimsResponseDTO jwtClaimsResponseDTO = jwtService.getClaims(jwtToken);
//        if (jwtClaimsResponseDTO != null && StringUtils.isNotBlank(jwtClaimsResponseDTO.getSubject())) {
//            // Create a Spring Authentication token, with the username of the user as a Principal
//            // and the User Id as credentials).
//
//            return new UsernamePasswordAuthenticationToken(jwtClaimsResponseDTO.getSubject(), jwtToken, Collections.emptyList());
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
//            throws IOException, ServletException {
//        Authentication authentication = getAuthentication((HttpServletRequest) request);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        filterChain.doFilter(request, response);
//    }
//}
