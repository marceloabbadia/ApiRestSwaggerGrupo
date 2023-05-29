package org.serratec.grupo1.projetofinal.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.serratec.grupo1.projetofinal.domain.exception.ResourceBadRequestException;
import org.serratec.grupo1.projetofinal.domain.exception.ResourceNotFoundException;
import org.serratec.grupo1.projetofinal.dto.usuario.UsuarioRequestDto;
import org.serratec.grupo1.projetofinal.dto.usuario.UsuarioResponseDto;
import org.serratec.grupo1.projetofinal.model.Usuario;
import org.serratec.grupo1.projetofinal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements ICRUDService<UsuarioRequestDto, UsuarioResponseDto>, UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public List<UsuarioResponseDto> obterTodos() {
		return usuarioRepository.findAll().stream()
				.map(p -> mapper.map(p, UsuarioResponseDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public UsuarioResponseDto obterPorId(Long id) {
		return usuarioRepository.findById(id)
				.map(p -> mapper.map(p, UsuarioResponseDto.class))
				.orElseThrow(() -> new ResourceNotFoundException("Vovó delaide!"));
	}

	@Override
	public UsuarioResponseDto cadastrar(UsuarioRequestDto dto) {
		validarEmailExistente(dto.getEmail());
		validarUsuario(dto);

		Usuario usuario = mapper.map(dto, Usuario.class);
		usuario.setId(null);
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		usuario.setDataCadastro(usuario.getDataCadastro());
		usuario = usuarioRepository.save(usuario);
		return mapper.map(usuario, UsuarioResponseDto.class);

	}

	@Override
	public UsuarioResponseDto atualizar(Long id, UsuarioRequestDto dto) {

		obterPorId(id);
		validarUsuario(dto);
		Usuario usuario = mapper.map(dto, Usuario.class);
		usuario.setId(id);
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		usuario.setDataCadastro(usuario.getDataCadastro());
		usuario = usuarioRepository.save(usuario);
		return mapper.map(usuario, UsuarioResponseDto.class);
	}

	@Override
	public void deletar(Long id) {
		usuarioRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado!"));

		usuarioRepository.deleteById(id);
	}

	private void validarUsuario(UsuarioRequestDto dto) {

		if (dto.getEmail() == null || dto.getSenha() == null) {

			throw new ResourceBadRequestException("E-mail e senha sao obrigatorios");
		}
	}

	public UsuarioResponseDto obterPorEmail(String email) {
		Optional<Usuario> optUsuario = usuarioRepository.findByEmail(email);
		if (optUsuario.isEmpty()) {

			throw new ResourceNotFoundException("Nao foi possivel encontrar o  usuario atraves do e-mail " + email);
		}

		return mapper.map(optUsuario.get(), UsuarioResponseDto.class);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> optUsuario = usuarioRepository.findByEmail(username);
		if (optUsuario.isEmpty()) {
			throw new ResourceNotFoundException("E-mail ou senha invalidos!" + username);
		}
		return optUsuario.get();
	}

	public void validarEmailExistente(String email) {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
		if (usuario != null) {
			throw new ResourceBadRequestException("Email já cadastrado!");
		}
	}

	public Usuario findByIdModelUsuario(Long idProjeto) {
		return usuarioRepository.findById(idProjeto)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado!"));
	}

	public Usuario usuarioTarefa(Long idUsuario) {
		return usuarioRepository.usuarioTarefas(idUsuario);

	}

}
