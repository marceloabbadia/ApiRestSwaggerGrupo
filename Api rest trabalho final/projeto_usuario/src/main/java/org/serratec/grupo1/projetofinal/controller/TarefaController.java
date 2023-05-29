package org.serratec.grupo1.projetofinal.controller;


import java.util.List;

import org.serratec.grupo1.projetofinal.dto.tarefa.TarefaRequestDto;
import org.serratec.grupo1.projetofinal.dto.tarefa.TarefaResponseDto;
import org.serratec.grupo1.projetofinal.service.TarefaService;
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
@RequestMapping("/api/tarefas")
public class TarefaController {

	
	@Autowired
	private TarefaService tarefaService;
	
	@GetMapping
	@Operation(summary ="Retorna todas as tarefas", description = "Retorna todas as tarefas", tags = {"Tarefas"}, responses = {
    @ApiResponse(description = "Sucesso", responseCode="200", content = {
			@Content(
				mediaType = "application/json",
				array = @ArraySchema(schema = @Schema(implementation = TarefaResponseDto.class))
			)}),
    @ApiResponse(responseCode = "400", description = "Solicitação incorreta"),
    @ApiResponse(responseCode = "401", description = "Não autorizado"),
    @ApiResponse(responseCode = "403", description = "Proibido"),
    @ApiResponse(responseCode = "404", description = "Não encontrado"),
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),
    })
	public ResponseEntity<List<TarefaResponseDto>> obterTodos (){
		return ResponseEntity.ok(tarefaService.obterTodos());
	}
	
	@GetMapping ("/{id}")
	@Operation(summary ="Busca uma tarefa", description = "Busca uma tarefa", tags = {"Tarefas"}, responses = {
	 @ApiResponse(description = "Sucesso", responseCode="200", content = {
			@Content(
				mediaType = "application/json",
				array = @ArraySchema(schema = @Schema(implementation = TarefaResponseDto.class))
			)}),
    @ApiResponse(responseCode = "204", description = "Solicitação bem sucedida"),
    @ApiResponse(responseCode = "400", description = "Solicitação incorreta"),
    @ApiResponse(responseCode = "401", description = "Não autorizado"),
    @ApiResponse(responseCode = "403", description = "Proibido"),
    @ApiResponse(responseCode = "404", description = "Não encontrado"),
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),
	})
	public ResponseEntity<TarefaResponseDto> obterPorId (@PathVariable Long id ){
		return  ResponseEntity.ok(tarefaService.obterPorId(id));
		
	}
	
	@PostMapping
	@Operation(summary ="Adiciona uma nova tarefa", description = "Adiciona uma nova tarefa", tags = {"Tarefas"}, responses = {
    @ApiResponse(description = "Sucesso", responseCode="200", content = {
			@Content(
				mediaType = "application/json",
				array = @ArraySchema(schema = @Schema(implementation = TarefaResponseDto.class))
			)}),
    @ApiResponse(responseCode = "201", description = "Requisição bem sucedida"),
    @ApiResponse(responseCode = "400", description = "Não autorizado"),
    @ApiResponse(responseCode = "401", description = "Proibido"),
    @ApiResponse(responseCode = "403", description = "Não encontrado"),
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),
	})
	public ResponseEntity<TarefaResponseDto> adicionar(@RequestBody TarefaRequestDto dto) {
		TarefaResponseDto tarefa = tarefaService.cadastrar(dto);
		return new ResponseEntity<>(tarefa,HttpStatus.CREATED);
	}
	
	
	@PutMapping ("/{id}")
	@Operation(summary ="Atualiza dados de uma tarefa", description = "Atualiza dados de uma tarefa", tags = {"Tarefas"}, responses = {
    @ApiResponse(description = "Sucesso", responseCode="200", content = {
			@Content(
				mediaType = "application/json",
				array = @ArraySchema(schema = @Schema(implementation = TarefaResponseDto.class))
			)}),
    @ApiResponse(responseCode = "400", description = "Não autorizado"),
    @ApiResponse(responseCode = "401", description = "Proibido"),
    @ApiResponse(responseCode = "403", description = "Não encontrado"),
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),
	})
	public ResponseEntity<TarefaResponseDto> atualizar(@PathVariable Long id, @RequestBody TarefaRequestDto dto){
		TarefaResponseDto tarefa = tarefaService.atualizar(id,dto);
		return new ResponseEntity<>(tarefa,HttpStatus.OK);
	}
	
	@DeleteMapping ("/{id}")
	@Operation(summary ="Deleta uma tarefa", description = "Deleta uma tarefa", tags = {"Tarefas"}, responses = {
    @ApiResponse(description = "Sucesso", responseCode="200", content = {
			@Content(
				mediaType = "application/json",
				array = @ArraySchema(schema = @Schema(implementation = TarefaResponseDto.class))
			)}),
    @ApiResponse(responseCode = "204", description = "Solicitação bem sucedida"),
    @ApiResponse(responseCode = "400", description = "Não autorizado"),
    @ApiResponse(responseCode = "401", description = "Proibido"),
    @ApiResponse(responseCode = "403", description = "Não encontrado"),
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),
	})
	public ResponseEntity<?> deletar(@PathVariable Long id){
		tarefaService.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}


