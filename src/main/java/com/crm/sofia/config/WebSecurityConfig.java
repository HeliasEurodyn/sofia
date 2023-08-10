package com.crm.sofia.config;

import com.crm.sofia.security.jwt.TokenAuthenticationFilter;
import com.crm.sofia.security.oauth2.*;
import com.crm.sofia.services.user.LocalUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.http.OAuth2ErrorResponseErrorHandler;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LocalUserDetailService localUserDetailService;

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Autowired
    CustomOidcUserService customOidcUserService;

    @Autowired
    private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    @Autowired
    private OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(localUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Value("${cors_url}")
    private String corsUrl;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .headers().frameOptions().disable().and()
                .cors()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .exceptionHandling()
                .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                .and()
                .authorizeRequests()
//              .antMatchers("*").permitAll();
                .antMatchers("/", "/error",
                        "/all",
                        "/auth/**",
                        "/settings/**",
                        "/user/auth/**",
                        "/user/logout",
                        "/user/current",
                        "/api/all",
                        "/api/auth/**",
                        "/health-page",
                        "/oauth2/**",
                        "/api/oauth2/**",
                        "/list/dynamic-cssscript/**",
                        "/list/dynamic-javascripts/**",
                        "/list/dynamic-javascript/**",
                        "/form/dynamic-cssscript/**",
                        "/form/dynamic-javascripts/**",
                        "/form/dynamic-javascript/**",
                        "/info-card/dynamic-javascripts/**",
                        "/info-card/dynamic-javascript/**",
                        "/html-dashboard/dynamic-javascripts/**",
                        "/html-dashboard/dynamic-javascript/**",
                        "/v3/api-docs/**",
                        "/v3/api-docs.yaml",
                        "/privacy-policy.html",
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/html-template/preview-page.html",
                        "/sockjs/**"
                ).permitAll()
                .antMatchers(HttpMethod.GET,"/form/instance-version/**").hasAnyAuthority("unrestricted_role", "read_role")
                .antMatchers(HttpMethod.GET,"/form/clone-data/**").hasAnyAuthority("unrestricted_role", "read_role")
                .antMatchers(HttpMethod.GET,"/form/ui/**").hasAnyAuthority("unrestricted_role", "read_role")
                .antMatchers(HttpMethod.GET,"/form/data/**").hasAnyAuthority("unrestricted_role", "read_role")
                .antMatchers(HttpMethod.POST,"/form").hasAnyAuthority("unrestricted_role", "create_role")
                .antMatchers(HttpMethod.PUT,"/form").hasAnyAuthority("unrestricted_role", "update_role")
                .antMatchers(HttpMethod.DELETE,"/form").hasAnyAuthority("unrestricted_role", "delete_role")

                .antMatchers(HttpMethod.GET,"/list/instance-version/**").hasAnyAuthority("unrestricted_role", "read_role")
                .antMatchers(HttpMethod.GET,"/list/results/**").hasAnyAuthority("unrestricted_role", "read_role")
                .antMatchers(HttpMethod.GET,"/list/ui/**").hasAnyAuthority("unrestricted_role", "read_role")

                .antMatchers(HttpMethod.DELETE,"/component").hasAnyAuthority("unrestricted_role", "delete_role")
                .antMatchers(HttpMethod.GET,"/component/by-id").hasAnyAuthority("unrestricted_role", "read_role")
                .antMatchers(HttpMethod.POST,"/component").hasAnyAuthority("unrestricted_role", "create_role")

                .antMatchers(HttpMethod.GET,"/menu/by-id").hasAnyAuthority("unrestricted_role", "read_role")

                .antMatchers(HttpMethod.GET,"/dashboard/by-id").hasAnyAuthority("unrestricted_role", "read_role")

                .antMatchers(HttpMethod.GET,"/report/type").hasAnyAuthority("unrestricted_role", "read_role")
                .antMatchers(HttpMethod.POST,"/report/print").hasAnyAuthority("unrestricted_role", "read_role")

                .antMatchers(HttpMethod.POST,"/xls-import").hasAnyAuthority("unrestricted_role", "read_role")
                .antMatchers(HttpMethod.GET,"/xls-import/by-id").hasAnyAuthority("unrestricted_role", "read_role")

                .antMatchers(HttpMethod.GET,"/search/data").hasAnyAuthority("unrestricted_role", "read_role")

                .antMatchers(HttpMethod.POST,"/custom-query/data").hasAnyAuthority("unrestricted_role", "read_role")
                .antMatchers(HttpMethod.GET,"/custom-query/data").hasAnyAuthority("unrestricted_role", "read_role")

                .anyRequest()
                .authenticated()
                .and()
                .oauth2Login()
                    .authorizationEndpoint()
                    .baseUri("/oauth2/authorization")
                    .authorizationRequestRepository(cookieAuthorizationRequestRepository())
                    .and()
                .redirectionEndpoint()
                    .baseUri("/oauth2/callback/*")
                    .and()
                .userInfoEndpoint()
                    .oidcUserService(customOidcUserService)
                    .userService(customOAuth2UserService)
                    .and()
                .tokenEndpoint()
                    .accessTokenResponseClient(authorizationCodeTokenResponseClient())
                .and()
                .successHandler(oAuth2AuthenticationSuccessHandler)
                .failureHandler(oAuth2AuthenticationFailureHandler);

        // Add our custom Token based authentication filter
        http.addFilterAfter(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        // Add our custom Token based authentication filter
        //http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(corsUrl.replace(" ", "").split(",")));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type", "content-type"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }

    /*
     * By default, Spring OAuth2 uses
     * HttpSessionOAuth2AuthorizationRequestRepository to save the authorization
     * request. But, since our service is stateless, we can't save it in the
     * session. We'll save the request in a Base64 encoded cookie instead.
     */
    @Bean
    public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }

    // This bean is load the user specific data when form login is used.
    @Override
    public UserDetailsService userDetailsService() {
        return localUserDetailService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    private OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> authorizationCodeTokenResponseClient() {
        OAuth2AccessTokenResponseHttpMessageConverter tokenResponseHttpMessageConverter = new OAuth2AccessTokenResponseHttpMessageConverter();
        tokenResponseHttpMessageConverter.setTokenResponseConverter(new OAuth2AccessTokenResponseConverterWithDefaults());
        RestTemplate restTemplate = new RestTemplate(Arrays.asList(new FormHttpMessageConverter(), tokenResponseHttpMessageConverter));
        restTemplate.setErrorHandler(new OAuth2ErrorResponseErrorHandler());
        DefaultAuthorizationCodeTokenResponseClient tokenResponseClient = new DefaultAuthorizationCodeTokenResponseClient();
        tokenResponseClient.setRestOperations(restTemplate);
        return tokenResponseClient;
    }
}
