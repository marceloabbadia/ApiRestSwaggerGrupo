package org.serratec.grupo1.projetofinal.security;

import java.io.IOException;
import java.util.Date;


import org.serratec.grupo1.projetofinal.common.ConversorDataHora;
import org.serratec.grupo1.projetofinal.dto.usuario.LoginRequestDto;
import org.serratec.grupo1.projetofinal.dto.usuario.LoginResponseDto;
import org.serratec.grupo1.projetofinal.dto.usuario.UsuarioResponseDto;
import org.serratec.grupo1.projetofinal.model.ErrorResposta;
import org.serratec.grupo1.projetofinal.model.Usuario;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	
	private AuthenticationManager authenticationManager;
	
	private JwtUtil jwtUtil;
	
	
	public JwtAuthenticationFilter (AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	
	setFilterProcessesUrl("/api/auth");
	
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
	
		try {
			
		
		LoginRequestDto login = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDto.class);
		
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(login.getLogin(), login.getSenha());
		
		Authentication auth = authenticationManager.authenticate(authToken);
		
		return auth;
		
		} catch (BadCredentialsException e) {
			
			throw new BadCredentialsException("Usuario ou senha invalidos!");
					
		} catch (Exception e) {
			
			throw new InternalAuthenticationServiceException(e.getMessage());
		}
	}
	
	

	@Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            FilterChain chain, Authentication authResult) throws IOException, ServletException {
		
               
				Usuario usuario = (Usuario) authResult.getPrincipal();
                String token = jwtUtil.gerarToken(authResult);

                UsuarioResponseDto usuarioResponse =  new UsuarioResponseDto();
                usuarioResponse.setId(usuario.getId());
                usuarioResponse.setEmail(usuario.getEmail());
                usuarioResponse.setNome(usuario.getNome());
                usuarioResponse.setDataCadastro(usuario.getDataCadastro());
                
                LoginResponseDto loginResponseDto = new LoginResponseDto();
                loginResponseDto.setToken("Bearer " + token);
                loginResponseDto.setUsuario(usuarioResponse);

                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json");
                response.getWriter().write(new Gson().toJson(loginResponseDto));
    }


			@Override
		    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, 
		        AuthenticationException failed)  throws IOException, ServletException {

		            String dataHora = ConversorDataHora.converterDateParaDataHora(new Date());

		            ErrorResposta erro = new ErrorResposta(dataHora, 401, "Unauthorized", failed.getMessage());
		            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		    
		            response.setCharacterEncoding("UTF-8"); 
		            response.setContentType("application/json");
		            response.getWriter().print(new Gson().toJson(erro));
		    }

		}
