package br.com.thomasgreg.clienteapi.controller;

import br.com.thomasgreg.clienteapi.dto.AuthRequest;
import br.com.thomasgreg.clienteapi.dto.AuthResponse;
import br.com.thomasgreg.clienteapi.dto.RegisterRequest;
import br.com.thomasgreg.clienteapi.entity.Usuario;
import br.com.thomasgreg.clienteapi.repository.UsuarioRepository;
import br.com.thomasgreg.clienteapi.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegisterRequest request) {
        Usuario usuario = new Usuario();
        usuario.setUsername(request.getUsername());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuarioRepository.save(usuario);

        Map<String, String> resposta = new HashMap<>();
        resposta.put("message", "Usuário registrado com sucesso");
        return ResponseEntity.ok(resposta);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        Usuario usuario = usuarioRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new BadCredentialsException("Credenciais inválidas");
        }

        String token = jwtService.generateToken(usuario.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
