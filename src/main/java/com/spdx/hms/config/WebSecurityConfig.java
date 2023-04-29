package com.spdx.hms.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.spdx.hms.security.AuthEntryPointJwt;
import com.spdx.hms.security.AuthTokenFilter;
import com.spdx.hms.service.UserDetailsServiceImpl;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

	 /*@Override
	  public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
	    authenticationManagerBuilder.userDetailsService(userDetailsService);//.passwordEncoder(passwordEncoder());
	  }*/


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.cors().configurationSource(corsConfigurationSource());
        //other config

        http.cors().configurationSource(corsConfigurationSource())
                .and().csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/api/hms/user/login").permitAll()
                .antMatchers("/api/hms/user/forgot").permitAll()
                .antMatchers("/api/hms/user/otp").permitAll()
                .antMatchers("/api/hms/user/register").permitAll()
                .antMatchers("/api/hms/user/otp/verify").permitAll()
                .antMatchers("/api/hms/user/**").permitAll()
                .antMatchers("/api/hms/colleges/images/**").permitAll()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/api/hms/colleges/**").permitAll()
                .antMatchers("/api/hms/colleges/all/**").permitAll()
                .antMatchers("/api/hms/students/**").permitAll()
                .antMatchers("/api/hms/students/all/**").permitAll()
                .antMatchers("/api/hms/qualifications/**").permitAll()
                .antMatchers("/api/hms/courses/**").permitAll()
                .antMatchers("/api/hms/skills/**").permitAll()
                .antMatchers("/api/hms/universities/**").permitAll()
                .antMatchers("/api/hms/studentsreportinginfo/**").permitAll()
                .antMatchers("/api/hms/student/counsellor/**").permitAll()
                .antMatchers("/api/hms/students/studentadmission/**").permitAll()
                .antMatchers("/api/hms/colleges/studentcontact/**").permitAll()
                .antMatchers("/api/hms/colleges/studentfollowups/**").permitAll()
                .antMatchers("/api/hms/student/counsellor/followup/**").permitAll()
                .antMatchers("/api/hms/students/admission/payment/transactions/**").permitAll()
                .antMatchers("/api/hms/student/admission/journey/**").permitAll()
                .antMatchers("/api/hms/questionnaires/**").permitAll()
                .antMatchers("/api/hms/questionnaires/reports/**").permitAll()
                .antMatchers("/api/hms/colleges/user/**").permitAll()
                .antMatchers("/api/hms/counsellor/**").permitAll()
                
                //.antMatchers("/index.html").permitAll()
                //.antMatchers("/v1/user/*").permitAll()
                //.antMatchers("/swagger-ui.html/**").permitAll()
                .antMatchers("/v2/api-docs/**",
                             "/configuration/ui",
                             "/swagger-resources/**",
                             "/configuration/security",
                             "/swagger-ui.html/**", "/v3/api-docs/**",
                             "/swagger-ui/**",
                             "/webjars/**").permitAll()
                .anyRequest().authenticated().and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ;
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
  /*
   * 
   * http.cors().and().csrf().disable()
      .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
      .authorizeRequests().antMatchers("/api/auth/**").permitAll()
      .antMatchers("/api/test/**").permitAll()
      .anyRequest().authenticated();

    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
   */

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        //configuration.se
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
        // configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList(
                "Accept", "Origin", "Content-Type", "Depth", "User-Agent", "If-Modified-Since,",
                "Cache-Control", "Authorization", "X-Req", "X-File-Size", "X-Requested-With", "X-File-Name"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterRegistrationBean() {
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(
                new CorsFilter(corsConfigurationSource()));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
    @Bean
    public HttpFirewall allowSemicolonHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowSemicolon(true);
        return firewall;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.httpFirewall(allowSemicolonHttpFirewall());
    }
}