-- ==========================================
-- Script de criação de Stored Procedures
-- Projeto: Cliente API
-- Banco: SQL Server
-- Autor:Esther Rezende
-- ==========================================

-- ==========================================
-- SP 1: Relatório de clientes com quantidade de logradouros
-- ==========================================
CREATE OR ALTER PROCEDURE sp_clientes_com_qtd_logradouros
    AS
BEGIN
SELECT
    c.id,
    c.nome,
    c.email,
    COUNT(l.id) AS qtd_logradouros
FROM Cliente c
         LEFT JOIN Logradouro l ON l.cliente_id = c.id
GROUP BY c.id, c.nome, c.email
ORDER BY qtd_logradouros DESC;
END;
GO

-- ==========================================
-- SP 2: Domínio de e-mail mais usado
-- ==========================================
CREATE OR ALTER PROCEDURE sp_dominio_email_mais_usado
    AS
BEGIN
SELECT
    RIGHT(email, LEN(email) - CHARINDEX('@', email)) AS dominio,
    COUNT(*) AS qtd
FROM Cliente
GROUP BY RIGHT(email, LEN(email) - CHARINDEX('@', email))
ORDER BY qtd DESC;
END;
GO

-- ==========================================
-- SP 3: Logradouros duplicados
-- ==========================================
CREATE OR ALTER PROCEDURE sp_logradouros_duplicados
    AS
BEGIN
SELECT
    endereco,
    COUNT(*) AS qtd_ocorrencias
FROM Logradouro
GROUP BY endereco
HAVING COUNT(*) > 1
ORDER BY qtd_ocorrencias DESC;
END;
GO
