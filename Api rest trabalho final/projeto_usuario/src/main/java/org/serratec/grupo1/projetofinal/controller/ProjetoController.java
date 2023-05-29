package org.serratec.grupo1.projetofinal.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

	
@RestController
@RequestMapping("/api/projetos")
public class ProjetoController {

	
	@Autowired
	private ProjetoService projetoService;
	 
	@GetMapping
	public ResponseEntity<List<ProjetoResponseDto>> obterTodos (){
		return ResponseEntity.ok(projetoService.obterTodos());
	}
	/* 
	@GetMapping
	public ResponseEntity<List<ProjetoResponseDto>> findAll (){
		return ResponseEntity.ok(projetoService.findAll());
	}
	*/
	
	@GetMapping ("/{id}")
	public ResponseEntity<ProjetoResponseDto> obterPorId (@PathVariable Long id ){
		return  ResponseEntity.ok(projetoService.obterPorId(id));
		
	}

	@GetMapping ("/relatorio")
	public ResponseEntity<Projeto> relatorio(@RequestParam Long idProjeto) {
		return ResponseEntity.ok(projetoService.relatorioTarefas(idProjeto));
	}
	/* 
	@GetMapping ("/{id}")
	public ResponseEntity<ProjetoResponseDto> findById (@PathVariable Long id ){
		return  ResponseEntity.ok(projetoService.findById(id));
		
	}
	*/
	@PostMapping
	public ResponseEntity<ProjetoResponseDto> adicionar(@RequestBody ProjetoRequestDto dto) {
		ProjetoResponseDto projeto = projetoService.cadastrar(dto);
		return new ResponseEntity<>(projeto,HttpStatus.CREATED);
	}
	
	
	@PutMapping ("/{id}")
	public ResponseEntity<ProjetoResponseDto> atualizar(@PathVariable Long id, @RequestBody ProjetoRequestDto dto){
		ProjetoResponseDto projeto = projetoService.atualizar(id,dto);
		return new ResponseEntity<>(projeto,HttpStatus.OK);
	}
	
	@DeleteMapping ("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id){
		projetoService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}



