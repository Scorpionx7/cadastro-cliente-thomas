package br.com.thomasgreg.clienteapi.service;

import br.com.thomasgreg.clienteapi.entity.Cliente;
import br.com.thomasgreg.clienteapi.entity.Logradouro;
import br.com.thomasgreg.clienteapi.exception.RecursoNaoEncontradoException;
import br.com.thomasgreg.clienteapi.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public Cliente criar(String nome, String email, MultipartFile logotipo, List<String> enderecos) {
        if (clienteRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Já existe um cliente com esse email.");
        }

        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setEmail(email);

        try {
            cliente.setLogotipo(logotipo.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Erro ao processar o logotipo", e);
        }

        List<Logradouro> logradouros = enderecos.stream()
                .map(end -> new Logradouro(null, end, cliente))
                .collect(Collectors.toList());

        cliente.setLogradouros(logradouros);

        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado"));
    }

    @Transactional
    public void deletar(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Cliente não encontrado");
        }
        clienteRepository.deleteById(id);
    }

    public byte[] obterLogotipo(Long id) {
        return clienteRepository.findById(id)
                .map(Cliente::getLogotipo)
                .orElse(null);
    }
}
