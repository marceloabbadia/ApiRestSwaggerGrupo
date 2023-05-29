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
		/* 
	List<PerfilUsuario> perfilUsuarios = perfilUsuarioRepository.findAll();
	
	List<PerfilUsuarioResponseDto> perfilUsuariosDto = new ArrayList<>();
	
	for(PerfilUsuario perfilUsuario: perfilUsuarios) {
		var dto = mapper.map(perfilUsuario,PerfilUsuarioResponseDto.class);	
		perfilUsuariosDto.add(dto);
		}
		return perfilUsuariosDto;
	}
	*/
	@Override
	public PerfilUsuarioResponseDto obterPorId(Long id) {
		return perfilUsuarioRepository.findById(id)
		.map(p -> mapper.map(p, PerfilUsuarioResponseDto.class))
		.orElseThrow(() -> new ResourceNotFoundException("Vovó delaide!"));
	}

		/* 
    Optional<PerfilUsuario> optPerfilUsuario = perfilUsuarioRepository.findById(id);
	if (optPerfilUsuario.isEmpty()) {	
		throw new ResourceNotFoundException("PerfilUsuario nao encontrada" + id);
		}	
		return mapper.map(optPerfilUsuario.get(), PerfilUsuarioResponseDto.class);
		}
		*/


	@Override
	public PerfilUsuarioResponseDto cadastrar(PerfilUsuarioRequestDto dto) {

	PerfilUsuario perfilUsuario = mapper.map(dto, PerfilUsuario.class);
	
	perfilUsuario.setId(null);
	perfilUsuario.setUsuario(usuarioService.findByIdModelUsuario(dto.getIdUsuario()));
	if (perfilUsuario.getNomeCompleto() == "" || perfilUsuario.getDataNasc() == null || perfilUsuario.getGenero() == "" || perfilUsuario.getUsuario() == null) {
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
	perfilUsuario = perfilUsuarioRepository.save(perfilUsuario);
	return mapper.map(perfilUsuario, PerfilUsuarioResponseDto.class);
	}

	
	@Override
	public void deletar(Long id) {
	obterPorId(id);
	
	perfilUsuarioRepository.deleteById(id);
		
	}

}

