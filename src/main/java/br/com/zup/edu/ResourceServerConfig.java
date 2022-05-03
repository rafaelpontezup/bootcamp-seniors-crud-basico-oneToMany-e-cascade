package br.com.zup.edu;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers(HttpMethod.GET, "/api/funcionarios/**").hasAuthority("SCOPE_funcionarios:read")
            .antMatchers(HttpMethod.POST, "/api/funcionarios").hasAuthority("SCOPE_funcionarios:write")
            .anyRequest().authenticated()
            .and()
                .oauth2ResourceServer()
                    .jwt(); // atencao: necessario pois estamos sobrescrevendo a conf do .properties
                ;
    }

}
