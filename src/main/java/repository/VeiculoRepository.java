package repository;

import model.Veiculo;
import util.Conexao;

import java.sql.*;

public class VeiculoRepository {

    public void salvar(Veiculo veiculo) {

        String sql =
                "INSERT INTO veiculo(placa, modelo, ano, id_cliente) VALUES (?, ?, ?, ?)";

        try (
                Connection conn = Conexao.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getModelo());
            stmt.setInt(3, veiculo.getAno());
            stmt.setLong(4, veiculo.getIdCliente());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean existe(Long id) {

        String sql =
                "SELECT * FROM veiculo WHERE id = ?";

        try (
                Connection conn = Conexao.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}