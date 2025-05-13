package br.com.thomasgreg.clienteapi.controller;

import br.com.thomasgreg.clienteapi.dto.ClienteResumoDTO;
import br.com.thomasgreg.clienteapi.dto.DominioEmailDTO;
import br.com.thomasgreg.clienteapi.dto.LogradouroDuplicadoDTO;
import br.com.thomasgreg.clienteapi.repository.RelatorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/relatorio")
public class RelatorioController {

    @Autowired
    private RelatorioRepository relatorioRepository;

    @GetMapping("/clientes-logradouros")
    public List<ClienteResumoDTO> listarClientesComQtdLogradouros() {
        return relatorioRepository.listarClientesComQtdLogradouros();
    }

    @GetMapping("/dominios-email")
    public List<DominioEmailDTO> dominiosMaisUsados() {
        return relatorioRepository.listarDominiosMaisUsados();
    }

    @GetMapping("/logradouros-duplicados")
    public List<LogradouroDuplicadoDTO> listarLogradouroDuplicados() {
        return relatorioRepository.listarLogradouroDuplicados();
    }

}
