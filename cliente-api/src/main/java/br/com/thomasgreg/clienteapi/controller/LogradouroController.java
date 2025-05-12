package br.com.thomasgreg.clienteapi.controller;

import br.com.thomasgreg.clienteapi.dto.LogradouroDTO;
import br.com.thomasgreg.clienteapi.entity.Logradouro;
import br.com.thomasgreg.clienteapi.service.LogradouroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/logradouros")
public class LogradouroController {

    @Autowired
    private LogradouroService logradouroService;

    @GetMapping
    public List<LogradouroDTO> listarTodos(){
        return logradouroService.listarTodos().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LogradouroDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(toDTO(logradouroService.buscarPorId(id)));
    }

    @PostMapping
    public ResponseEntity<LogradouroDTO> criar(@RequestBody Logradouro logradouro){
        Logradouro novo = logradouroService.criarLogradouro(logradouro);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(novo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LogradouroDTO> atualizar(@PathVariable Long id, @RequestBody Logradouro logradouro){
        Logradouro atualizado = logradouroService.atualizar(id, logradouro);
        return ResponseEntity.ok(toDTO(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        logradouroService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    private LogradouroDTO toDTO(Logradouro logradouro) {
        LogradouroDTO dto = new LogradouroDTO();
        dto.setId(logradouro.getId());
        dto.setEndereco(logradouro.getEndereco());
        return dto;
    }
}
