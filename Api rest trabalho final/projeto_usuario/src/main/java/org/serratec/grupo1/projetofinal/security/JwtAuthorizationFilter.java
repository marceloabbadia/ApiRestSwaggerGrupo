package org.serratec.grupo1.projetofinal.security;

import java.io.IOException;

import org.serratec.grupo1.projetofinal.model.Usuario;
import org.serratec.grupo1.projetofinal.service.UsuarioService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

	private JwtUtil jwtUtil;
	
	private UsuarioService usuarioService;


	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil,UsuarioService usuarioService) {
		super(authenticationManager);
		this.jwtUtil = jwtUtil;
		this.usuarioService = usuarioService;

	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader("Authorization");

		if (header != null && header.startsWith("Bearer ")) {
			UsernamePasswordAuthenticationToken auth = getAuthentication(header.substring(7));

			if (auth != null && auth.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}

		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(String token) {

		if (jwtUtil.isValidToken(token)) {

			String email = jwtUtil.getUserName(token);
			
			
			Usuario loadUserByUsername = (Usuario) usuarioService.loadUserByUsername(email);
				
			return new UsernamePasswordAuthenticationToken(loadUserByUsername, null, loadUserByUsername.getAuthorities());
		}

		return null;
	}

}