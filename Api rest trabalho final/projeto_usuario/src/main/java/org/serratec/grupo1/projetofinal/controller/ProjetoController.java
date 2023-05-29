package org.serratec.grupo1.projetofinal.controller;

import java.util.List;

import org.serratec.grupo1.projetofinal.dto.perfilusuario.PerfilUsuarioResponseDto;
import org.serratec.grupo1.projetofinal.dto.projeto.ProjetoRequestDto;
import org.serratec.grupo1.projetofinal.dto.projeto.ProjetoResponseDto;
import org.serratec.grupo1.projetofinal.model.Projeto;
import org.serratec.grupo1.projetofinal.service.ProjetoService;
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
@RequestMapping("/api/projetos")
public class ProjetoController {

	@Autowired
	private ProjetoService projetoService;

	@GetMapping
	@Operation(summary = "Retornar projetos", description = "Retorna todos os projetos inseridos.", tags = {"Projetos" }, responses = {
			@ApiResponse(description = "Sucesso", responseCode = "200", content = {
					@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PerfilUsuarioResponseDto.class))) }),
			@ApiResponse(responseCode = "400", description = "Solicitação incorreta"),
			@ApiResponse(responseCode = "401", description = "Não autorizado"),
			@ApiResponse(responseCode = "403", description = "Proibido"),
			@ApiResponse(responseCode = "404", description = "Não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor"), })
	public ResponseEntity<List<ProjetoResponseDto>> obterTodos() {
		return ResponseEntity.ok(projetoService.obterTodos());
	}

	@GetMapping("/{id}")
	@Operation(summary = "Buscar projeto", description = "Busca um projeto por id", tags = { "Projetos" }, responses = {
			@ApiResponse(description = "Sucesso", responseCode = "200", content = {
					@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PerfilUsuarioResponseDto.class))) }),
			@ApiResponse(responseCode = "204", description = "Solicitação bem sucedida"),
			@ApiResponse(responseCode = "400", description = "Solicitação incorreta"),
			@ApiResponse(responseCode = "401", description = "Não autorizado"),
			@ApiResponse(responseCode = "403", description = "Proibido"),
			@ApiResponse(responseCode = "404", description = "Não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor"), })
	public ResponseEntity<ProjetoResponseDto> obterPorId(@PathVariable Long id) {
		return ResponseEntity.ok(projetoService.obterPorId(id));

	}

	@GetMapping("/relatório/{id}")
	@Operation(summary = "Retornar tarefas do projeto", description = "Retorna as tarefas atribuídas ao projeto", tags = {"Projetos" }, responses = {
			@ApiResponse(description = "Sucesso", responseCode = "200", content = {
					@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PerfilUsuarioResponseDto.class))) }),
			@ApiResponse(responseCode = "400", description = "Solicitação incorreta"),
			@ApiResponse(responseCode = "401", description = "Não autorizado"),
			@ApiResponse(responseCode = "403", description = "Proibido"),
			@ApiResponse(responseCode = "404", description = "Não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor"), })
	public ResponseEntity<Projeto> relatorio(@PathVariable Long idProjeto) {
		return ResponseEntity.ok(projetoService.relatorioTarefas(idProjeto));
	}

	@PostMapping
	@Operation(summary = "Adicionar perfil de usuário", description = "Adiciona um novo perfil de usuário", tags = {"Projetos" }, responses = {
			@ApiResponse(description = "Sucesso", responseCode = "200", content = {
					@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PerfilUsuarioResponseDto.class))) }),
			@ApiResponse(responseCode = "201", description = "Requisição bem sucedida"),
			@ApiResponse(responseCode = "400", description = "Não autorizado"),
			@ApiResponse(responseCode = "401", description = "Proibido"),
			@ApiResponse(responseCode = "403", description = "Não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor"), })
	public ResponseEntity<ProjetoResponseDto> adicionar(@RequestBody ProjetoRequestDto dto) {
		ProjetoResponseDto projeto = projetoService.cadastrar(dto);
		return new ResponseEntity<>(projeto, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualizar um perfil de usuário", description = "Atualiza dados de um perfil de usuário", tags = {"Projetos" }, responses = {
			@ApiResponse(description = "Sucesso", responseCode = "200", content = {
					@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PerfilUsuarioResponseDto.class))) }),
			@ApiResponse(responseCode = "400", description = "Não autorizado"),
			@ApiResponse(responseCode = "401", description = "Proibido"),
			@ApiResponse(responseCode = "403", description = "Não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor"), })
	public ResponseEntity<ProjetoResponseDto> atualizar(@PathVariable Long id, @RequestBody ProjetoRequestDto dto) {
		ProjetoResponseDto projeto = projetoService.atualizar(id, dto);
		return new ResponseEntity<>(projeto, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deletar perfil de usuário", description = "Deleta um perfil de usuário", tags = {"Projetos" }, responses = {
			@ApiResponse(description = "Sucesso", responseCode = "200", content = {
					@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PerfilUsuarioResponseDto.class))) }),
			@ApiResponse(responseCode = "204", description = "Solicitação bem sucedida"),
			@ApiResponse(responseCode = "400", description = "Não autorizado"),
			@ApiResponse(responseCode = "401", description = "Proibido"),
			@ApiResponse(responseCode = "403", description = "Não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor"), })
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		projetoService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
