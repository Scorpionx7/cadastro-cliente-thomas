package br.com.thomasgreg.clienteapi.repository.impl;

import br.com.thomasgreg.clienteapi.dto.ClienteResumoDTO;
import br.com.thomasgreg.clienteapi.dto.DominioEmailDTO;
import br.com.thomasgreg.clienteapi.dto.LogradouroDuplicadoDTO;
import br.com.thomasgreg.clienteapi.repository.RelatorioRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@SuppressWarnings("unchecked")
public class RelatorioRepositoryImpl implements RelatorioRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ClienteResumoDTO> listarClientesComQtdLogradouros() {
        List<Object[]> resultados = entityManager
                .createNativeQuery("EXEC sp_clientes_com_qtd_logradouros")
                .getResultList();

        return resultados.stream()
                .map(r -> new ClienteResumoDTO(
                        ((Number) r[0]).longValue(),  // id
                        (String) r[1],               // nome
                        (String) r[2],               // email
                        ((Number) r[3]).longValue()  // qtd_logradouros
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<DominioEmailDTO> listarDominiosMaisUsados() {
        List<Object[]> resultados = entityManager
                .createNativeQuery("EXEC sp_dominio_email_mais_usado")
                .getResultList();

        return resultados.stream()
                .map(r -> new DominioEmailDTO(
                        (String) r[0],                // dominio
                        ((Number) r[1]).longValue()   // qtd
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<LogradouroDuplicadoDTO> listarLogradouroDuplicados() {
        List<Object[]> resultados = entityManager
                .createNativeQuery("EXEC sp_logradouros_duplicados")
                .getResultList();

        return resultados.stream()
                .map(r -> new LogradouroDuplicadoDTO(
                        (String) r[0],                // endereco
                        ((Number) r[1]).longValue()   // qtd_ocorrencias
                ))
                .collect(Collectors.toList());
    }
}
