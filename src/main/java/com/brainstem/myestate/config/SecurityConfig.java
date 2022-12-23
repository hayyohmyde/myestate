package com.brainstem.myestate.config;

import com.brainstem.myestate.security.JwtAuthenticationFilter;
import com.brainstem.myestate.security.JwtAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * from spring security 5.7 upward, WebSecurityConfigurerAdapter is deprecated
 * Spring team are advised to move to Component-based security configuration
 *Configure HttpSecurity using SecurityFilterChain
 */

@Configuration  // define this class as spring config and we can define spring security config
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)  // used whenever you wanna permit method label security
public class SecurityConfig{ // WebSecurityConfigurerAdapter was removed bcos it was deprecated

/**
* not needed in component basedsecurity configuration
// @Autowired
    private UserDetailsService userDetailsService;
*/
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //Add authenticationManager Bean
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests((authorize) -> authorize
                        .antMatchers(HttpMethod.GET, "/api/**").permitAll()
                        .antMatchers(HttpMethod.POST, "/api/v1/auth/**").permitAll()
//                        .antMatchers(HttpMethod.POST, "/api/v1/apartments/**").permitAll()
//                        .antMatchers(HttpMethod.HEAD, "api/**").permitAll()
                        .antMatchers("/v2/api-docs/**").permitAll()
                        .antMatchers("/swagger-ui/**").permitAll()
                        .antMatchers("/swagger-resources/**").permitAll()
                        .antMatchers("/swagger-ui.html").permitAll()
                        .antMatchers("/webjars/**").permitAll()
                        .anyRequest()
                        .authenticated()
                );
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }




/**
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                //add jwt auth config
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //end jwt auth config
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/**").permitAll()
                .anyRequest()
                .authenticated();
        //remove basic auth and replace with Jwt token auth
//                .and()
//                .httpBasic();

        //add JwtAuthenticationFilter
        http.addFilterBefore(jwtAthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
*/

  /**  Inmemory User details configuration
    @Override
    @Bean
    protected UserDetailsService userDetailsService(){
        UserDetails user = User.builder()
                .username("user")
                .password(
                        passwordEncoder()
                        .encode("user"))
                .roles("USER").build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder()
                        .encode("user"))
                .roles("ADMIN").build();

        return new InMemoryUserDetailsManager(user, admin);
    }
  */
}
