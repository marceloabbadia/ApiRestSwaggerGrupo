package org.serratec.grupo1.projetofinal.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.serratec.grupo1.projetofinal.domain.exception.ResourceBadRequestException;
import org.serratec.grupo1.projetofinal.domain.exception.ResourceNotFoundException;
import org.serratec.grupo1.projetofinal.dto.perfilusuario.PerfilUsuarioRequestDto;
import org.serratec.grupo1.projetofinal.dto.perfilusuario.PerfilUsuarioResponseDto;
import org.serratec.grupo1.projetofinal.model.PerfilUsuario;
import org.serratec.grupo1.projetofinal.repository.PerfilUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerfilUsuarioService implements ICRUDService<PerfilUsuarioRequestDto, PerfilUsuarioResponseDto> {

	@Autowired
	private PerfilUsuarioRepository perfilUsuarioRepository;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<PerfilUsuarioResponseDto> obterTodos() {
		return perfilUsuarioRepository.findAll().stream()
				.map(p -> mapper.map(p, PerfilUsuarioResponseDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public PerfilUsuarioResponseDto obterPorId(Long id) {
		return perfilUsuarioRepository.findById(id)
				.map(p -> mapper.map(p, PerfilUsuarioResponseDto.class))
				.orElseThrow(() -> new ResourceNotFoundException("Perfil usuário não encontrado"));
	}

	public void validacaoPerfilUsuario(Long idUsuario) {
		PerfilUsuario validUsuario = perfilUsuarioRepository.findByIdUsuario(idUsuario);
		if (validUsuario != null) {
			throw new ResourceBadRequestException("Usuário já cadastrado!");
		}
	}

	@Override
	public PerfilUsuarioResponseDto cadastrar(PerfilUsuarioRequestDto dto) {
		validacaoPerfilUsuario(dto.getIdUsuario());
		PerfilUsuario perfilUsuario = mapper.map(dto, PerfilUsuario.class);

		perfilUsuario.setId(null);
		perfilUsuario.setUsuario(usuarioService.findByIdModelUsuario(dto.getIdUsuario()));
		if (perfilUsuario.getNomeCompleto().isEmpty() || perfilUsuario.getDataNasc() == null
				|| perfilUsuario.getGenero().isEmpty() || perfilUsuario.getUsuario() == null) {
			throw new ResourceBadRequestException("Favor preencher todos os campos!");
		} else {
			perfilUsuario = perfilUsuarioRepository.save(perfilUsuario);
		}
		return mapper.map(perfilUsuario, PerfilUsuarioResponseDto.class);

	}

	@Override
	public PerfilUsuarioResponseDto atualizar(Long id, PerfilUsuarioRequestDto dto) {

		obterPorId(id);

		PerfilUsuario perfilUsuario = mapper.map(dto, PerfilUsuario.class);
		perfilUsuario.setId(id);
		if (perfilUsuario.getNomeCompleto().isEmpty() || perfilUsuario.getDataNasc() == null
				|| perfilUsuario.getGenero().isEmpty() || perfilUsuario.getUsuario() == null) {
			throw new ResourceBadRequestException("Favor preencher todos os campos!");
		} else {
			perfilUsuarioRepository.save(perfilUsuario);
		}
		return mapper.map(perfilUsuario, PerfilUsuarioResponseDto.class);

	}

	@Override
	public void deletar(Long id) {
		perfilUsuarioRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Perfil de usuário não encontrado"));

		perfilUsuarioRepository.deleteById(id);

	}

}
