package org.serratec.grupo1.projetofinal.security;
import org.serratec.grupo1.projetofinal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationConfiguration  authenticationConfiguration;
	
	@Autowired
	private UsuarioService usuarioService;
	
	

	@Bean
	AuthenticationManager authenticationManager (AuthenticationConfiguration  authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .headers().frameOptions().disable().and()
                .cors().and().csrf().disable()
                .authorizeHttpRequests((auth) ->
                 	auth.requestMatchers(HttpMethod.POST, "/api/usuarios").permitAll()
                 	.requestMatchers(HttpMethod.POST, "/api/auth").permitAll()
					 .requestMatchers(
						"/auth/signin",
						"/auth/refresh/**",
						"/swagger-ui/**",
						"/v3/api-docs/**"
				).permitAll()
                    .anyRequest().authenticated())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            	
			
		http.addFilter(new JwtAuthenticationFilter(authenticationManager(authenticationConfiguration),jwtUtil));
		http.addFilter(new JwtAuthorizationFilter(authenticationManager(authenticationConfiguration),jwtUtil,usuarioService));

		return http.build();
		
	}
	
	
}
