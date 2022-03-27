package tn.esprit.spring.security.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.security.jwt.JwtAuthorizationFilter;
import tn.esprit.spring.security.sevices.UserDetailsServiceImpl;
import org.springframework.security.config.BeanIds;






@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userService;
    private UserRepository userRepository;


    @Autowired
    public SpringSecurityConfig(UserDetailsServiceImpl userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtAuthorizationFilter(authenticationManager(),userRepository))
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                .antMatchers("/api/auth/signin").permitAll()
                .antMatchers("/api/auth/signup").permitAll()
                .antMatchers("/api/auth/active").permitAll()
                .antMatchers("/api/auth/activated").permitAll()
                .antMatchers("/api/auth/checkEmail").permitAll()
                .antMatchers("/api/auth/resetPassword").permitAll()
                .antMatchers("/api/auth/social/**").permitAll()
                .antMatchers("/api/csv/upload").permitAll()
                .antMatchers("/api/file/**").permitAll()
                .antMatchers("/qrcode/**").permitAll()
                
                
                .anyRequest().authenticated();
    }
    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}