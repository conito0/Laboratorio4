package Laboratorio4com.example.demo.controller;

import Laboratorio4com.example.demo.entities.Usuario;
import Laboratorio4com.example.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/usuario")
@RestController
@Controller
public class UsuarioController {

    private UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("login/{usuario}/{clave}")
    @CrossOrigin
    public Usuario login(@PathVariable String usuario, @PathVariable String clave) {
        return this.usuarioService.login(usuario, clave);
    }

    @PostMapping("registro")
    @CrossOrigin
    public Usuario registro(@RequestBody Usuario usuario) {
        return this.usuarioService.registro(usuario);
    }

}
