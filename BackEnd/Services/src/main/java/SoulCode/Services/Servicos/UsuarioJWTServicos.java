package SoulCode.Services.Servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import SoulCode.Services.Models.UsuarioJWT;
import SoulCode.Services.Repository.UsuarioJWTRepository;

@Service
public class UsuarioJWTServicos {

	
	 @Bean
		BCryptPasswordEncoder bCryptPasswordEncoder() {
			return new BCryptPasswordEncoder();
		}
	
	 @Autowired
	    UsuarioJWTRepository usuarioJWTRepository;


	    public List<UsuarioJWT> listarUsuarioJWT(){
	        return usuarioJWTRepository.findAll();
	    }

	    public UsuarioJWT buscarIdUsuario(Integer idUsuario){
	        Optional<UsuarioJWT> usuario = usuarioJWTRepository.findById(idUsuario);
	        return usuario.orElseThrow();
	    }

	    public UsuarioJWT findByLogin(String login) {
	        Optional<UsuarioJWT> usuarioJWT = usuarioJWTRepository.findByLogin(login);
	        return usuarioJWT.orElseThrow();
	    }

	    public UsuarioJWT inserirUsuario(UsuarioJWT usuarioJWT) {
	        return usuarioJWTRepository.save(usuarioJWT);
	    }

	    public UsuarioJWT editarUsuario(UsuarioJWT usuario) {
	        buscarIdUsuario(usuario.getId());
	        return usuarioJWTRepository.save(usuario);
	    }

	    public void excluirUsuario(Integer idUsuario) {
	        buscarIdUsuario(idUsuario);
	        usuarioJWTRepository.deleteById(idUsuario);
	    }

}
