package org.serratec.grupo1.projetofinal.controller;


import java.util.List;

import org.serratec.grupo1.projetofinal.dto.perfilusuario.PerfilUsuarioRequestDto;
import org.serratec.grupo1.projetofinal.dto.perfilusuario.PerfilUsuarioResponseDto;
import org.serratec.grupo1.projetofinal.service.PerfilUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


@RestController
@RequestMapping("/api/perfilUsuarios")
public class PerfilUsuarioController {

	
	@Autowired
	private PerfilUsuarioService perfilUsuarioService;
	
	@GetMapping
	@Operation(summary ="Retorna todos os perfils de usuário", description = "Retorna todos os perfils de usuário", tags = {"PerfilUsuario"}, responses = {
    @ApiResponse(description = "Sucesso", responseCode="200", content = {
			@Content(
				mediaType = "application/json",
				array = @ArraySchema(schema = @Schema(implementation = PerfilUsuarioResponseDto.class))
			)}),
    @ApiResponse(responseCode = "400", description = "Solicitação incorreta"),
    @ApiResponse(responseCode = "401", description = "Não autorizado"),
    @ApiResponse(responseCode = "403", description = "Proibido"),
    @ApiResponse(responseCode = "404", description = "Não encontrado"),
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),
    })
	public ResponseEntity<List<PerfilUsuarioResponseDto>> obterTodos (){
		return ResponseEntity.ok(perfilUsuarioService.obterTodos());
	}
	
	@GetMapping ("/{id}")
	@Operation(summary ="Busca um perfil de usuário", description = "Busca um perfil de usuário", tags = {"PerfilUsuario"}, responses = {
	 @ApiResponse(description = "Sucesso", responseCode="200", content = {
			@Content(
				mediaType = "application/json",
				array = @ArraySchema(schema = @Schema(implementation = PerfilUsuarioResponseDto.class))
			)}),
    @ApiResponse(responseCode = "204", description = "Solicitação bem sucedida"),
    @ApiResponse(responseCode = "400", description = "Solicitação incorreta"),
    @ApiResponse(responseCode = "401", description = "Não autorizado"),
    @ApiResponse(responseCode = "403", description = "Proibido"),
    @ApiResponse(responseCode = "404", description = "Não encontrado"),
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),
	})
	public ResponseEntity<PerfilUsuarioResponseDto> obterPorId (@PathVariable Long id ){
		return  ResponseEntity.ok(perfilUsuarioService.obterPorId(id));
		
	}
	
	@PostMapping
	@Operation(summary ="Adiciona um novo perfil de usuário", description = "Adiciona um novo perfil de usuário", tags = {"PerfilUsuario"}, responses = {
    @ApiResponse(description = "Sucesso", responseCode="200", content = {
			@Content(
				mediaType = "application/json",
				array = @ArraySchema(schema = @Schema(implementation = PerfilUsuarioResponseDto.class))
			)}),
    @ApiResponse(responseCode = "201", description = "Requisição bem sucedida"),
    @ApiResponse(responseCode = "400", description = "Não autorizado"),
    @ApiResponse(responseCode = "401", description = "Proibido"),
    @ApiResponse(responseCode = "403", description = "Não encontrado"),
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),
	})
	public ResponseEntity<PerfilUsuarioResponseDto> adicionar(@RequestBody PerfilUsuarioRequestDto dto) {
		PerfilUsuarioResponseDto perfilUsuario = perfilUsuarioService.cadastrar(dto);
		return new ResponseEntity<>(perfilUsuario,HttpStatus.CREATED);
	}
	
	
	@PutMapping ("/{id}")
	@Operation(summary ="Atualiza dados de um perfil de usuário", description = "Atualiza dados de um perfil de usuário", tags = {"PerfilUsuario"}, responses = {
    @ApiResponse(description = "Sucesso", responseCode="200", content = {
			@Content(
				mediaType = "application/json",
				array = @ArraySchema(schema = @Schema(implementation = PerfilUsuarioResponseDto.class))
			)}),
    @ApiResponse(responseCode = "400", description = "Não autorizado"),
    @ApiResponse(responseCode = "401", description = "Proibido"),
    @ApiResponse(responseCode = "403", description = "Não encontrado"),
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),
	})
	public ResponseEntity<PerfilUsuarioResponseDto> atualizar(@PathVariable Long id, @RequestBody PerfilUsuarioRequestDto dto){
		PerfilUsuarioResponseDto perfilUsuario = perfilUsuarioService.atualizar(id,dto);
		return new ResponseEntity<>(perfilUsuario,HttpStatus.OK);
	}
	
	@DeleteMapping ("/{id}")
	@Operation(summary ="Deleta um perfil de usuário", description = "Deleta um perfil de usuário", tags = {"PerfilUsuario"}, responses = {
    @ApiResponse(description = "Sucesso", responseCode="200", content = {
			@Content(
				mediaType = "application/json",
				array = @ArraySchema(schema = @Schema(implementation = PerfilUsuarioResponseDto.class))
			)}),
    @ApiResponse(responseCode = "204", description = "Solicitação bem sucedida"),
    @ApiResponse(responseCode = "400", description = "Não autorizado"),
    @ApiResponse(responseCode = "401", description = "Proibido"),
    @ApiResponse(responseCode = "403", description = "Não encontrado"),
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),
	})
	public ResponseEntity<?> deletar(@PathVariable Long id){
		perfilUsuarioService.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
