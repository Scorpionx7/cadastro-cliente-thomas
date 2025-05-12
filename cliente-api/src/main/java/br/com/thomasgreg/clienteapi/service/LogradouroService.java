package br.com.thomasgreg.clienteapi.service;

import br.com.thomasgreg.clienteapi.entity.Cliente;
import br.com.thomasgreg.clienteapi.entity.Logradouro;
import br.com.thomasgreg.clienteapi.exception.RecursoNaoEncontradoException;
import br.com.thomasgreg.clienteapi.repository.ClienteRepository;
import br.com.thomasgreg.clienteapi.repository.LogradouroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LogradouroService {

    @Autowired
    private LogradouroRepository logradouroRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public Logradouro criarLogradouro(Logradouro logradouro) {
        if (logradouro.getCliente() == null || logradouro.getCliente().getId() == null) {
            throw new IllegalArgumentException("Cliente do logradouro deve ser informado.");
        }

        Cliente cliente = clienteRepository.findById(logradouro.getCliente().getId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente vinculado não encontrado"));

        logradouro.setCliente(cliente);
        return logradouroRepository.save(logradouro);
    }

    @Transactional
    public Logradouro atualizar(Long id, Logradouro atualizarLogradouro){
        Logradouro logradouro = logradouroRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Logradouro não encontrado"));

        logradouro.setEndereco(atualizarLogradouro.getEndereco());
        return logradouroRepository.save(logradouro);
    }

    @Transactional
    public void deletar(Long id){
        Logradouro logradouro = logradouroRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Logradouro não encontrado"));

        Cliente cliente = logradouro.getCliente();
        if(cliente != null && cliente.getLogradouros().contains(logradouro)){
            cliente.removeLogradouro(logradouro);
            clienteRepository.save(cliente);
        } else {
            logradouroRepository.delete(logradouro);
        }
    }

    public List<Logradouro> listarTodos(){
        return logradouroRepository.findAll();
    }

    public Logradouro buscarPorId(Long id){
        return logradouroRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Logradouro não encontrado"));
    }
}
