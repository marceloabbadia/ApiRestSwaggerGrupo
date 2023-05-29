package org.serratec.grupo1.projetofinal.controller;

import java.util.List;

import org.serratec.grupo1.projetofinal.dto.usuario.UsuarioRequestDto;
import org.serratec.grupo1.projetofinal.dto.usuario.UsuarioResponseDto;
import org.serratec.grupo1.projetofinal.model.Usuario;
import org.serratec.grupo1.projetofinal.service.UsuarioService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuários", description = "Endpoints for Managing Usuários")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	@Operation(summary ="Retorna todos os usuários", description = "Retorna todos os usuários", tags = {"Usuários"}, responses = {
    @ApiResponse(description = "Sucesso", responseCode="200", content = {
			@Content(
				mediaType = "application/json",
				array = @ArraySchema(schema = @Schema(implementation = UsuarioResponseDto.class))
			)}),
    @ApiResponse(responseCode = "400", description = "Solicitação incorreta"),
    @ApiResponse(responseCode = "401", description = "Não autorizado"),
    @ApiResponse(responseCode = "403", description = "Proibido"),
    @ApiResponse(responseCode = "404", description = "Não encontrado"),
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),
    })
	
	public ResponseEntity<List<UsuarioResponseDto>> obterTodos (){
		return ResponseEntity.ok(usuarioService.obterTodos());
	}

	@GetMapping ("/usuarioTarefas")
	@Operation(summary ="Busca um usuário e mostra suas tarefas ", description = "Busca um usuário e mostra suas tarefas ", tags = {"Usuário/Tarefa"}, responses = {
    @ApiResponse(description = "Sucesso", responseCode="200", content = {
			@Content(
				mediaType = "application/json",
				array = @ArraySchema(schema = @Schema(implementation = UsuarioResponseDto.class))
			)}),
    @ApiResponse(responseCode = "204", description = "Solicitação bem sucedida"),
    @ApiResponse(responseCode = "400", description = "Solicitação incorreta"),
    @ApiResponse(responseCode = "401", description = "Não autorizado"),
    @ApiResponse(responseCode = "403", description = "Proibido"),
    @ApiResponse(responseCode = "404", description = "Não encontrado"),
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),
	})
	public ResponseEntity<Usuario> usuarioTarefas(@RequestParam Long idUsuario) {
		return ResponseEntity.ok(usuarioService.usuarioTarefa(idUsuario));
	}
	
	
	@GetMapping ("/{id}")
	@Operation(summary ="Busca um usuário", description = "Busca um usuário", tags = {"Usuários"}, responses = {
    @ApiResponse(description = "Sucesso", responseCode="200", content = {
			@Content(
				mediaType = "application/json",
				array = @ArraySchema(schema = @Schema(implementation = UsuarioResponseDto.class))
			)}),
    @ApiResponse(responseCode = "204", description = "Solicitação bem sucedida"),
    @ApiResponse(responseCode = "400", description = "Solicitação incorreta"),
    @ApiResponse(responseCode = "401", description = "Não autorizado"),
    @ApiResponse(responseCode = "403", description = "Proibido"),
    @ApiResponse(responseCode = "404", description = "Não encontrado"),
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),
	})
	public ResponseEntity<UsuarioResponseDto> obterPorId (@PathVariable Long id ){
		return  ResponseEntity.ok(usuarioService.obterPorId(id));
		
	}
	
	@PostMapping
	@Operation(summary ="Adiciona um novo usuário", description = "Adiciona um novo usuário", tags = {"Usuários"}, responses = {
    @ApiResponse(description = "Sucesso", responseCode="200", content = {
			@Content(
				mediaType = "application/json",
				array = @ArraySchema(schema = @Schema(implementation = UsuarioResponseDto.class))
			)}),
    @ApiResponse(responseCode = "201", description = "Requisição bem sucedida"),
    @ApiResponse(responseCode = "400", description = "Não autorizado"),
    @ApiResponse(responseCode = "401", description = "Proibido"),
    @ApiResponse(responseCode = "403", description = "Não encontrado"),
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),
	})
	public ResponseEntity<UsuarioResponseDto> adicionar(@RequestBody UsuarioRequestDto dto) {
		UsuarioResponseDto usuario = usuarioService.cadastrar(dto);
		return new ResponseEntity<>(usuario,HttpStatus.CREATED);
	}
	
	
	
	
	@PutMapping ("/{id}")
	@Operation(summary ="Atualiza dados de um usuário", description = "Atualiza dados de um usuário", tags = {"Usuários"}, responses = {
    @ApiResponse(description = "Sucesso", responseCode="200", content = {
			@Content(
				mediaType = "application/json",
				array = @ArraySchema(schema = @Schema(implementation = UsuarioResponseDto.class))
			)}),
    @ApiResponse(responseCode = "400", description = "Não autorizado"),
    @ApiResponse(responseCode = "401", description = "Proibido"),
    @ApiResponse(responseCode = "403", description = "Não encontrado"),
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),
	})
	public ResponseEntity<UsuarioResponseDto> atualizar(@PathVariable Long id, @RequestBody UsuarioRequestDto dto){
		UsuarioResponseDto usuario = usuarioService.atualizar(id,dto);
		return new ResponseEntity<>(usuario,HttpStatus.OK);
	}
	
	@DeleteMapping ("/{id}")
	@Operation(summary ="Deleta um usuário", description = "Deleta um usuário", tags = {"Usuários"}, responses = {
    @ApiResponse(description = "Sucesso", responseCode="200", content = {
			@Content(
				mediaType = "application/json",
				array = @ArraySchema(schema = @Schema(implementation = UsuarioResponseDto.class))
			)}),
    @ApiResponse(responseCode = "204", description = "Solicitação bem sucedida"),
    @ApiResponse(responseCode = "400", description = "Não autorizado"),
    @ApiResponse(responseCode = "401", description = "Proibido"),
    @ApiResponse(responseCode = "403", description = "Não encontrado"),
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),
	})
	public ResponseEntity<?> deletar(@PathVariable Long id){
	usuarioService.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
}



