package br.com.thomasgreg.clienteapi.dto;

public class DominioEmailDTO {
    private String dominio;
    private Long qtd;

    public DominioEmailDTO(String dominio, Long qtd) {
        this.dominio = dominio;
        this.qtd = qtd;
    }

    public Long getQtd() {
        return qtd;
    }

    public void setQtd(Long qtd) {
        this.qtd = qtd;
    }

    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }
}
