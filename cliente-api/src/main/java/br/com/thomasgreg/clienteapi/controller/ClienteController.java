package br.com.thomasgreg.clienteapi.controller;

import br.com.thomasgreg.clienteapi.dto.ClienteDTO;
import br.com.thomasgreg.clienteapi.dto.LogradouroDTO;
import br.com.thomasgreg.clienteapi.entity.Cliente;
import br.com.thomasgreg.clienteapi.entity.Logradouro;
import br.com.thomasgreg.clienteapi.service.ClienteService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.net.URLConnection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ClienteDTO> criar(
            @RequestPart("nome") String nome,
            @RequestPart("email") String email,
            @RequestPart("logotipo") MultipartFile logotipo,
            @RequestPart("logradouros") String logradourosJson
    ) {
        try {
            List<String> enderecos = objectMapper.readValue(logradourosJson, new TypeReference<List<String>>() {});
            Cliente cliente = clienteService.criar(nome, email, logotipo, enderecos);
            return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(cliente));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping
    public List<ClienteDTO> listarTodos() {
        return clienteService.listarTodos().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(toDTO(clienteService.buscarPorId(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/logotipo")
    public ResponseEntity<byte[]> obterLogotipo(@PathVariable Long id) {
        byte[] imagem = clienteService.obterLogotipo(id);
        if (imagem == null) {
            return ResponseEntity.notFound().build();
        }

        String mimeType;
        try {
            mimeType = URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(imagem));
        } catch (Exception e) {
            mimeType = "image/png";
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(mimeType));
        return new ResponseEntity<>(imagem, headers, HttpStatus.OK);
    }

    private ClienteDTO toDTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setEmail(cliente.getEmail());
        dto.setLogradouros(cliente.getLogradouros().stream().map(log -> {
            LogradouroDTO ldto = new LogradouroDTO();
            ldto.setId(log.getId());
            ldto.setEndereco(log.getEndereco());
            return ldto;
        }).collect(Collectors.toList()));
        return dto;
    }
}
