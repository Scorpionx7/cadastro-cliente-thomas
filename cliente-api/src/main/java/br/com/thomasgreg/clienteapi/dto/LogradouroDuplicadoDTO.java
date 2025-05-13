package br.com.thomasgreg.clienteapi.dto;

public class LogradouroDuplicadoDTO {
    private String logradouro;
    private Long qtdOcorrencias;

    public LogradouroDuplicadoDTO(String logradouro, Long qtdOcorrencias) {
        this.logradouro = logradouro;
        this.qtdOcorrencias = qtdOcorrencias;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Long getQtdOcorrencias() {
        return qtdOcorrencias;
    }

    public void setQtdOcorrencias(Long qtdOcorrencias) {
        this.qtdOcorrencias = qtdOcorrencias;
    }
}
