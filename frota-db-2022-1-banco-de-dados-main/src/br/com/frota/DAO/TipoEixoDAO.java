package br.com.frota.DAO;

import br.com.frota.model.TipoEixo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoEixoDAO extends ConexaoDB {

    private static final String INSERT_TIPO_EIXO_SQL = "INSERT INTO tipo_eixo (descricao) VALUES (?);";
    private static final String SELECT_TIPO_EIXO_BY_ID = "SELECT id, descricao FROM tipo_eixo WHERE id = ?";
    private static final String SELECT_ALL_TIPO_EIXO = "SELECT * FROM tipo_eixo;";
    private static final String DELETE_TIPO_EIXO_SQL = "DELETE FROM tipo_eixo WHERE id = ?;";
    private static final String UPDATE_TIPO_EIXO_SQL = "UPDATE tipo_eixo SET descricao = ? WHERE id = ?;";

    public void insertTipoEixo(TipoEixo entidade) {
        try (PreparedStatement preparedStatement = prapararSQL(INSERT_TIPO_EIXO_SQL)) {
            preparedStatement.setString(1, entidade.getDescricao());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public TipoEixo selectTipoEixo(int id) {
        TipoEixo entidade = null;
        try (PreparedStatement preparedStatement = prapararSQL(SELECT_TIPO_EIXO_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String descricao = rs.getString("descricao");
                entidade = new TipoEixo(id, descricao);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<TipoEixo> selectAllTipoEixo() {
        List<TipoEixo> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prapararSQL(SELECT_ALL_TIPO_EIXO)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String descricao = rs.getString("descricao");
                entidades.add(new TipoEixo(id, descricao));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteTipoEixo(int id) throws SQLException {
        try (PreparedStatement statement = prapararSQL(DELETE_TIPO_EIXO_SQL)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateTipoEixo(TipoEixo entidade) throws SQLException {
        try (PreparedStatement statement = prapararSQL(UPDATE_TIPO_EIXO_SQL)) {
            statement.setString(1, entidade.getDescricao());
            statement.setInt(2, entidade.getId());

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
