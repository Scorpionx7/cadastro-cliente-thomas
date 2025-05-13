package br.com.thomasgreg.clienteapi.dto;

public class ClienteResumoDTO {
    private Long id;
    private String nome;
    private String email;
    private Long qtdLogradouros;

    public ClienteResumoDTO(Long id, String nome, String email, Long qtdLogradouros) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.qtdLogradouros = qtdLogradouros;
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

    public Long getQtdLogradouros() {
        return qtdLogradouros;
    }

    public void setQtdLogradouros(Long qtdLogradouros) {
        this.qtdLogradouros = qtdLogradouros;
    }
}
