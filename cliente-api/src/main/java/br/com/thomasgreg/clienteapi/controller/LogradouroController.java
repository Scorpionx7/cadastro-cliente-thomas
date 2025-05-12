package br.com.thomasgreg.clienteapi.controller;

import br.com.thomasgreg.clienteapi.entity.Logradouro;
import br.com.thomasgreg.clienteapi.service.LogradouroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logradouros")
public class LogradouroController {

    @Autowired
    private LogradouroService logradouroService;

    @GetMapping
    public List<Logradouro> listarTodos(){
        return logradouroService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Logradouro> buscarPorId(@PathVariable Long id){
        Logradouro logradouro = logradouroService.buscarPorId(id);
        return ResponseEntity.ok(logradouro);
    }

    @PostMapping
    public ResponseEntity<Logradouro> criar(@RequestBody Logradouro logradouro){
        Logradouro novoLogradouro = logradouroService.criarLogradouro(logradouro);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoLogradouro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Logradouro> atualizar(@PathVariable Long id, @RequestBody Logradouro logradouro){
        Logradouro logradouroAtualizado = logradouroService.atualizar(id, logradouro);
        return ResponseEntity.ok(logradouroAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        logradouroService.deletar(id);
        return ResponseEntity.noContent().build();
    }


}
