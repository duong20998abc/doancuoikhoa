package application.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    private static Logger logger = LoggerFactory.getLogger(SpringSecurityConfig.class);

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("userDetailsService") //giam tien cho lap trinh vien, tranh viec lap trinh vien config qua nhieu
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.info("----configure(HttpSecurity http)");

        //ten role phai dat ten trong DB la ROLE_ADMIN, ROLE_SUPPADMIN, ROLE_USER
        http.authorizeRequests()
                .antMatchers("/","/register-user","/about","/product/**","/api/**"
                        ,"/signin/**","/signup/**","/link/**","/list-products/**", "/user/**","/cart/**").permitAll()
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .antMatchers().hasAnyRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/")
                .permitAll()
                .and().csrf()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

    @Override
    public void configure(WebSecurity web)throws Exception {
        logger.info("---configure(WebSecurity web");
        web.ignoring()
                .antMatchers("/resources/**")
                .antMatchers("/static/**")
                .antMatchers("/public/**")
                .antMatchers("/img/**")
                .antMatchers("/plugins/**")
                .antMatchers("/uploaded/**")
                .antMatchers(HttpMethod.POST, "/api/**");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        logger.info("---configureGlobal(AuthenticationManagerBuilder auth)");
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
