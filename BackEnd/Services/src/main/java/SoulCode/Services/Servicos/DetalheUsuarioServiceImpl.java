package SoulCode.Services.Servicos;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import SoulCode.Services.Data.DetalheUsuarioData;
import SoulCode.Services.Models.UsuarioJWT;
import SoulCode.Services.Repository.UsuarioJWTRepository;

import java.util.Optional;

@Component
public class DetalheUsuarioServiceImpl implements UserDetailsService{

    private final UsuarioJWTRepository usuarioJWTRepository;

    public DetalheUsuarioServiceImpl(UsuarioJWTRepository usuarioJWTRepository) {
        this.usuarioJWTRepository = usuarioJWTRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsuarioJWT> usuario = usuarioJWTRepository.findByLogin(username);
        if(usuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuario não cadastrado");
        }
        return new DetalheUsuarioData(usuario);
    }

}