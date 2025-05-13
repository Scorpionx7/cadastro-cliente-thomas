package br.com.thomasgreg.clienteapi.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Lob
    private byte[] logotipo;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Logradouro> logradouros = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(String nome, String email, byte[] logotipo) {
        this.nome = nome;
        this.email = email;
        this.logotipo = logotipo;
    }


    public void addLogradouro(Logradouro logradouro){
        this.logradouros.add(logradouro);
        logradouro.setCliente(this);
    }
    public void removeLogradouro(Logradouro logradouro){
        this.logradouros.remove(logradouro);
        logradouro.setCliente(null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getLogotipo() {
        return logotipo;
    }

    public void setLogotipo(byte[] logotipo) {
        this.logotipo = logotipo;
    }

    public List<Logradouro> getLogradouros() {
        return logradouros;
    }

    public void setLogradouros(List<Logradouro> logradouros) {
        this.logradouros = logradouros;
    }
}
