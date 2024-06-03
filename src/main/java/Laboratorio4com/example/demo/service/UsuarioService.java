package Laboratorio4com.example.demo.service;

import Laboratorio4com.example.demo.entities.Usuario;
import Laboratorio4com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario login(String usuario, String clave) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        Usuario usuarioDB = this.usuarioRepository.findByNombreUsuario(usuario);

        if(bCryptPasswordEncoder.matches(clave, usuarioDB.getClave())) {
            return usuarioDB;
        }

        throw new RuntimeException("La password o el usuario no coinciden");
    }

    public Usuario registro(Usuario usuario) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        Usuario usuarioDB = this.usuarioRepository.findByNombreUsuario(usuario.getNombreUsuario());

        if(usuarioDB != null) {
            throw new RuntimeException("El usuario ya existe");
        }

        usuario.setClave(bCryptPasswordEncoder.encode(usuario.getClave()));

        return this.usuarioRepository.save(usuario);
    }

}
