package br.com.frota.DAO;

import br.com.frota.model.Banda;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BandaDAO extends ConexaoDB {

    private static final String INSERT_BANDA_SQL = "INSERT INTO banda (descricao, posicao) VALUES (?, ?);";
    private static final String SELECT_BANDA_BY_ID = "SELECT id, descricao FROM banda WHERE id = ?";
    private static final String SELECT_ALL_BANDA = "SELECT * FROM banda;";
    private static final String DELETE_BANDA_SQL = "DELETE FROM banda WHERE id = ?;";
    private static final String UPDATE_BANDA_SQL = "UPDATE banda SET descricao, posicao = ?,? WHERE id = ?;";

    public void insertBanda(Banda entidade) {
        try (PreparedStatement preparedStatement = prapararSQL(INSERT_BANDA_SQL)) {
            preparedStatement.setString(1, entidade.getDescricao());
            preparedStatement.setString(2, entidade.getPosicao());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Banda selectBanda(int id) {
        Banda entidade = null;
        try (PreparedStatement preparedStatement = prapararSQL(SELECT_BANDA_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String descricao = rs.getString("descricao");
                String posicao = rs.getString("posicao");
                entidade = new Banda(id, descricao,posicao);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<Banda> selectAllBanda() {
        List<Banda> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prapararSQL(SELECT_ALL_BANDA)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String descricao = rs.getString("descricao");
                String posicao = rs.getString("posicao");
                entidades.add(new Banda(id, descricao,posicao));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteBanda(int id) throws SQLException {
        try (PreparedStatement statement = prapararSQL(DELETE_BANDA_SQL)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateBanda(Banda entidade) throws SQLException {
        try (PreparedStatement statement = prapararSQL(UPDATE_BANDA_SQL)) {
            statement.setString(1, entidade.getDescricao());
            statement.setString(2, entidade.getPosicao());
            statement.setInt(3, entidade.getId());

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
