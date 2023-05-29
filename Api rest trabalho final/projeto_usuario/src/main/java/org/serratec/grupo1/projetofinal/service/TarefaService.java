package org.serratec.grupo1.projetofinal.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.serratec.grupo1.projetofinal.domain.exception.ResourceBadRequestException;
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
		return tarefaRepository.findAll().stream()
				.map(p -> mapper.map(p, TarefaResponseDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public TarefaResponseDto obterPorId(Long id) {
		return tarefaRepository.findById(id)
				.map(p -> mapper.map(p, TarefaResponseDto.class))
				.orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada!"));
	}

	@Override
	public TarefaResponseDto cadastrar(TarefaRequestDto dto) {
		Tarefa tarefa = mapper.map(dto, Tarefa.class);
		tarefa.setId(null);
		tarefa.setProjeto(projetoService.findByIdModelProjeto(dto.getIdProjeto()));
		tarefa.setUsuarioResp(usuarioService.findByIdModelUsuario(dto.getIdUsuario()));
		if (tarefa.getDataCriacao() == null || tarefa.getDescricao().isEmpty() || tarefa.getTitulo().isEmpty()) {
			throw new ResourceBadRequestException("Favor preencher todos os campos!");
		} else {
			tarefaRepository.save(tarefa);
		}

		return mapper.map(tarefa, TarefaResponseDto.class);

	}

	@Override
	public TarefaResponseDto atualizar(Long id, TarefaRequestDto dto) {

		obterPorId(id);

		Tarefa tarefa = mapper.map(dto, Tarefa.class);
		tarefa.setId(id);
		if (tarefa.getDataCriacao() == null || tarefa.getDescricao().isEmpty() || tarefa.getTitulo().isEmpty()) {
			throw new ResourceBadRequestException("Favor preencher todos os campos!");
		} else {
			tarefaRepository.save(tarefa);
		}

		return mapper.map(tarefa, TarefaResponseDto.class);
	}

	@Override
	public void deletar(Long id) {
		tarefaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada!"));

		tarefaRepository.deleteById(id);

	}

}
