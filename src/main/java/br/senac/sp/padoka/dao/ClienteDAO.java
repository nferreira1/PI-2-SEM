/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.sp.padoka.dao;

import br.senac.sp.padoka.model.Cliente;
import br.senac.sp.padoka.model.Endereco;
import br.senac.sp.padoka.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nathan Ferreira
 */
public class ClienteDAO {

    public void inserir(Cliente cliente) {
        String sql = "INSERT INTO clientes (nome, CPF, data_de_nascimento, celular, telefone, email, sexo, estado_civil, observacoes, status_conta) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, 1)";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCPF());
            stmt.setString(3, cliente.getData_de_nascimento());
            stmt.setString(4, cliente.getCelular());
            stmt.setString(5, cliente.getTelefone());
            stmt.setString(6, cliente.getEmail());
            stmt.setString(7, cliente.getSexo());
            stmt.setString(8, cliente.getEstado_civil());
            stmt.setString(9, cliente.getObservacoes());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    cliente.setId(rs.getInt(1));
                } else {
                    throw new SQLException("Erro ao inserir endereço. Nenhum ID obtido.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir cliente.", e);
        }
    }

    public void atualizar(Cliente cliente) {
        String sql = "UPDATE clientes SET nome = ?, CPF = ?, data_de_nascimento = ?, celular = ?, telefone = ?, email = ?, sexo = ?, estado_civil = ?, observacoes = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCPF());
            stmt.setString(3, cliente.getData_de_nascimento());
            stmt.setString(4, cliente.getCelular());
            stmt.setString(5, cliente.getTelefone());
            stmt.setString(6, cliente.getEmail());
            stmt.setString(7, cliente.getSexo());
            stmt.setString(8, cliente.getEstado_civil());
            stmt.setString(9, cliente.getObservacoes());
            stmt.setInt(10, cliente.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cliente.", e);
        }
    }

    public List<Cliente> listarClientes() {
        List<Cliente> listaClientes = new ArrayList<>();

        String sql = """
                     SELECT C.ID as cliente_ID,
                         C.nome,
                         C.CPF,
                         C.data_de_nascimento,
                         C.celular,
                         C.telefone,
                         C.email,
                         C.sexo,
                         C.estado_civil,
                         C.observacoes,
                         E.ID as endereco_ID,
                         E.CEP,
                         E.logradouro,
                         E.numero,
                         E.bairro,
                         E.complemento,
                         E.UF,
                         E.cidade FROM clientes AS C INNER JOIN enderecos AS E ON E.cliente_id = C.ID WHERE C.status_conta = 1""";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                Endereco endereco = new Endereco();

                endereco.setCep(rs.getString("CEP"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setLogradouro(rs.getString("logradouro"));
                endereco.setNumero(rs.getString("numero"));
                endereco.setComplemento(rs.getString("complemento"));
                endereco.setUf(rs.getString("UF"));
                endereco.setLocalidade(rs.getString("cidade"));
                endereco.setId(rs.getInt("endereco_ID"));

                cliente.setId(rs.getInt("cliente_ID"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCPF(rs.getString("CPF"));
                cliente.setData_de_nascimento(rs.getString("data_de_nascimento"));
                cliente.setCelular(rs.getString("celular"));
                cliente.setEndereco(endereco);
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setEstado_civil(rs.getString("estado_civil"));
                cliente.setObservacoes(rs.getString("observacoes"));

                listaClientes.add(cliente);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar clientes.", e);
        }

        return listaClientes;
    }

    public void deletar(int id) {
        String sql = "UPDATE clientes SET status_conta = 0 WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cliente.", e);
        }
    }
}
