package SoulCode.Services.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import SoulCode.Services.Models.UsuarioJWT;
import SoulCode.Services.Repository.UsuarioJWTRepository;
import SoulCode.Services.Servicos.UsuarioJWTServicos;



@CrossOrigin
@RestController
@RequestMapping("servico")
public class UsuarioJWTController {


	 @Autowired
	    UsuarioJWTServicos usuarioJWTServicos;

	    @Autowired
	    PasswordEncoder encoder;

	    @Autowired
	    UsuarioJWTRepository usuarioJWTRepository;
	    
	   

	    @GetMapping("/usuario")
	    public ResponseEntity<List<UsuarioJWT>> listarUsuarioJWT(){
	        return ResponseEntity.ok(usuarioJWTServicos.listarUsuarioJWT());
	    }

	    @PostMapping("/usuario")
	    public ResponseEntity<UsuarioJWT> inserirUsuario(@RequestBody UsuarioJWT usuarioJWT){
	        usuarioJWT.setPassword(encoder.encode(usuarioJWT.getPassword()));
	        return ResponseEntity.ok(usuarioJWTServicos.inserirUsuario(usuarioJWT));
	    }

	    @GetMapping("/usuario/id/{id}")
	    public ResponseEntity<UsuarioJWT> buscarIdUsuario(@PathVariable Integer id){
	        UsuarioJWT usuario = usuarioJWTServicos.buscarIdUsuario(id);
	        return ResponseEntity.ok().body(usuario);
	    }

	    @DeleteMapping("/usuario/id/{id}")
	    public ResponseEntity<Void> excluirUsuario(@PathVariable Integer id){
	    	usuarioJWTServicos.excluirUsuario(id);
	        return ResponseEntity.noContent().build();
	    }

	    @PutMapping("/usuario/id/{id}")
	    public ResponseEntity<UsuarioJWT> editarUsuario(@PathVariable Integer id, @RequestBody UsuarioJWT usuario) {
	        usuario.setId(id);
	        usuario = usuarioJWTServicos.editarUsuario(usuario);
	        return ResponseEntity.noContent().build();
	    }

	    @GetMapping("/usuario/login/{login}")
	    public ResponseEntity<UsuarioJWT> buscarLoginUsuario(@PathVariable String login) {
	        UsuarioJWT usuario = usuarioJWTServicos.findByLogin(login);
	        return ResponseEntity.ok().body(usuario);
	    }
}