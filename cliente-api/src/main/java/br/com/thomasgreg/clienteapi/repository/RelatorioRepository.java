package br.com.thomasgreg.clienteapi.repository;

import br.com.thomasgreg.clienteapi.dto.ClienteResumoDTO;
import br.com.thomasgreg.clienteapi.dto.DominioEmailDTO;
import br.com.thomasgreg.clienteapi.dto.LogradouroDuplicadoDTO;

import java.util.List;

public interface RelatorioRepository {
    List<ClienteResumoDTO> listarClientesComQtdLogradouros();
    List<DominioEmailDTO> listarDominiosMaisUsados();
    List<LogradouroDuplicadoDTO> listarLogradouroDuplicados();
}
