package org.serratec.grupo1.projetofinal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.serratec.grupo1.projetofinal.domain.exception.ResourceNotFoundException;
import org.serratec.grupo1.projetofinal.dto.tarefa.TarefaRequestDto;
import org.serratec.grupo1.projetofinal.dto.tarefa.TarefaResponseDto;
import org.serratec.grupo1.projetofinal.model.Tarefa;
import org.serratec.grupo1.projetofinal.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TarefaService implements ICRUDService<TarefaRequestDto, TarefaResponseDto> { 
	
		
	@Autowired
	private TarefaRepository tarefaRepository;

	@Autowired
	private ProjetoService projetoService;

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public List<TarefaResponseDto> obterTodos() {

	List<Tarefa> tarefas = tarefaRepository.findAll();
	
	List<TarefaResponseDto> tarefasDto = new ArrayList<>();
	
	for(Tarefa tarefa: tarefas) {
		var dto = mapper.map(tarefa,TarefaResponseDto.class);	
		tarefasDto.add(dto);
		}
		return tarefasDto;
	}

	@Override
	public TarefaResponseDto obterPorId(Long id) {
    Optional<Tarefa> optTarefa = tarefaRepository.findById(id);
	if (optTarefa.isEmpty()) {	
		throw new ResourceNotFoundException("Tarefa nao encontrada" + id);
	}	
	return mapper.map(optTarefa.get(), TarefaResponseDto.class);
	}

	@Override
	public TarefaResponseDto cadastrar(TarefaRequestDto dto) {
			
	Tarefa tarefa = mapper.map(dto, Tarefa.class);
	tarefa.setId(null);
	tarefa.setProjeto(projetoService.findByIdModelProjeto(dto.getIdProjeto()));
	tarefa.setUsuarioResp(usuarioService.findByIdModelUsuario(dto.getIdUsuario()));
	tarefa = tarefaRepository.save(tarefa);
	return mapper.map(tarefa, TarefaResponseDto.class);
	
	}

	@Override
	public TarefaResponseDto atualizar(Long id, TarefaRequestDto dto) {
		
	obterPorId(id);

	Tarefa tarefa = mapper.map(dto, Tarefa.class);
	tarefa.setId(id);
	tarefa = tarefaRepository.save(tarefa);
	return mapper.map(tarefa, TarefaResponseDto.class);
	}

	
	@Override
	public void deletar(Long id) {
	obterPorId(id);
	
	tarefaRepository.deleteById(id);
		
	}

}

