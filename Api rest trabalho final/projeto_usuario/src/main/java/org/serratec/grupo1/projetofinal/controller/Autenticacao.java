package org.serratec.grupo1.projetofinal.controller;

import java.util.Objects;

import org.serratec.grupo1.projetofinal.dto.usuario.UsuarioResponseDto;
import org.serratec.grupo1.projetofinal.model.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/auth")
public class Autenticacao {

		
	@PostMapping
	@Operation(summary = "Autenticação de usuário", description = "Realiza a autenticação de um usuário", tags = {"Autenticação"}, responses = {
	    @ApiResponse(description = "Sucesso", responseCode = "200", content = {
	        @Content(
	            mediaType = "application/json",
	            schema = @Schema(implementation = UsuarioResponseDto.class)
	        )
	    }),
	    @ApiResponse(responseCode = "400", description = "Solicitação incorreta"),
	    @ApiResponse(responseCode = "401", description = "Não autorizado"),
	    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	

	public ResponseEntity<Usuario> verificar(@RequestBody Usuario usuario) {
			
		Usuario usuarioStatus = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (Objects.isNull(usuarioStatus)) {
				return new ResponseEntity<>(usuarioStatus, HttpStatus.BAD_REQUEST);
		}
			return new ResponseEntity<>(usuarioStatus, HttpStatus.BAD_REQUEST);
	}

}
