package org.serratec.grupo1.projetofinal.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.serratec.grupo1.projetofinal.domain.exception.ResourceBadRequestException;
import org.serratec.grupo1.projetofinal.domain.exception.ResourceNotFoundException;
import org.serratec.grupo1.projetofinal.dto.projeto.ProjetoRequestDto;
import org.serratec.grupo1.projetofinal.dto.projeto.ProjetoResponseDto;
import org.serratec.grupo1.projetofinal.model.Projeto;
import org.serratec.grupo1.projetofinal.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjetoService implements ICRUDService<ProjetoRequestDto, ProjetoResponseDto> {

	@Autowired
	private ProjetoRepository projetoRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<ProjetoResponseDto> obterTodos() {
		return projetoRepository.findAll().stream()
				.map(p -> mapper.map(p, ProjetoResponseDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public ProjetoResponseDto obterPorId(Long id) {
		return projetoRepository.findById(id)
				.map(p -> mapper.map(p, ProjetoResponseDto.class))
				.orElseThrow(() -> new ResourceNotFoundException("Vovó delaide!"));
	}

	public void ValidarNomeProjetoExistente(String nomeProjeto) {
		Projeto projeto = projetoRepository.findByNomeProjeto(nomeProjeto);
		if (projeto != null) {
			throw new ResourceBadRequestException("Nome de projeto já cadastrado!");
		}
	}

	@Override
	public ProjetoResponseDto cadastrar(ProjetoRequestDto dto) {
		ValidarNomeProjetoExistente(dto.getNomeProjeto());
		Projeto projeto = mapper.map(dto, Projeto.class);
		projeto.setId(null);

		if (projeto.getNomeProjeto().isEmpty() || projeto.getDescProjeto().isEmpty()) {
			throw new ResourceBadRequestException("Favor preencher todos os campos!");
		} else {
			projetoRepository.save(projeto);
		}
		return mapper.map(projeto, ProjetoResponseDto.class);

	}

	@Override
	public ProjetoResponseDto atualizar(Long id, ProjetoRequestDto dto) {
		ValidarNomeProjetoExistente(dto.getNomeProjeto());
		obterPorId(id);
		Projeto projeto = mapper.map(dto, Projeto.class);
		projeto.setId(id);

		if (projeto.getNomeProjeto().isEmpty() || projeto.getDescProjeto().isEmpty()) {
			throw new ResourceBadRequestException("Favor preencher todos os campos!");
		} else {
			projetoRepository.save(projeto);
		}
		return mapper.map(projeto, ProjetoResponseDto.class);
	}

	@Override
	public void deletar(Long id) {
		Optional<Projeto> projetoOptional = projetoRepository.findById(id);
		if (projetoOptional.isPresent()) {
			projetoRepository.deleteById(id);
		} else {
			throw new ResourceNotFoundException("Projeto não encontrado");
		}

	}

	public Projeto findByIdModelProjeto(Long idProjeto) {
		return projetoRepository.findById(idProjeto)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Não foi possivel salvar a tarefa, o projeto vinculado não existe!"));

	}

	public Projeto relatorioTarefas(Long idProjeto) {
		return projetoRepository.relatorioTarefas(idProjeto);
	}

}
