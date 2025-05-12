package br.com.thomasgreg.clienteapi.service;

import br.com.thomasgreg.clienteapi.entity.Cliente;
import br.com.thomasgreg.clienteapi.entity.Logradouro;
import br.com.thomasgreg.clienteapi.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public Cliente criarCliente(Cliente cliente){
        //Garantir que não haja duplicidade de email
        if(clienteRepository.findByEmail(cliente.getEmail()).isPresent()){
            throw new RuntimeException("Já existe um cliente com esse email");
        }

        for (Logradouro logradouro : cliente.getLogradouros()) {
            logradouro.setCliente(cliente);
        }


        return clienteRepository.save(cliente);
    }

    @Transactional
    public Cliente atualizar(Long id, Cliente novoCliente){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        cliente.setNome(novoCliente.getNome());
        cliente.setEmail(novoCliente.getEmail());
        cliente.setLogotipo(novoCliente.getLogotipo());

        return clienteRepository.save(cliente);
    }

    @Transactional
    public void deletar(Long id){
        clienteRepository.deleteById(id);
    }

    public List<Cliente> listarTodos(){
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Long id){
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

}

