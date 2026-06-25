package repository;

import model.OrdemServico;
import util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrdemServicoRepository {

    public void salvar(OrdemServico os) {

        String sql =
                "INSERT INTO ordem_servico(id_veiculo, descricao, valor, status) VALUES (?, ?, ?, ?)";

        try (
                Connection conn = Conexao.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setLong(1, os.getIdVeiculo());
            stmt.setString(2, os.getDescricao());
            stmt.setBigDecimal(3, os.getValor());
            stmt.setString(4, os.getStatus());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}