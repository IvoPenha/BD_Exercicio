package br.com.frota.DAO;

import br.com.frota.model.MarcaPneu;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MarcaPneuDAO extends ConexaoDB {

    private static final String INSERT_MARCA_PNEU_SQL = "INSERT INTO marca_pneu (descricao) VALUES (?);";
    private static final String SELECT_MARCA_PNEU_BY_ID = "SELECT id, descricao FROM marca_pneu WHERE id = ?";
    private static final String SELECT_ALL_MARCA_PNEU = "SELECT * FROM marca_pneu;";
    private static final String DELETE_MARCA_PNEU_SQL = "DELETE FROM marca_pneu WHERE id = ?;";
    private static final String UPDATE_MARCA_PNEU_SQL = "UPDATE marca_pneu SET descricao = ? WHERE id = ?;";
    private static final String TOTAL = "SELECT count(1) FROM marca_pneu;";

    public Integer count() {
        Integer count = 0;
        try (PreparedStatement preparedStatement = prapararSQL(TOTAL)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    public void insertMarcaPneu(MarcaPneu entidade) {
        try (PreparedStatement preparedStatement = prapararSQL(INSERT_MARCA_PNEU_SQL)) {
            preparedStatement.setString(1, entidade.getDescricao());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public MarcaPneu selectMarcaPneu(int id) {
        MarcaPneu entidade = null;
        try (PreparedStatement preparedStatement = prapararSQL(SELECT_MARCA_PNEU_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String descricao = rs.getString("descricao");
                entidade = new MarcaPneu(id, descricao);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<MarcaPneu> selectAllMarcaPneus() {
        List<MarcaPneu> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prapararSQL(SELECT_ALL_MARCA_PNEU)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String descricao = rs.getString("descricao");
                entidades.add(new MarcaPneu(id, descricao));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteMarcaPneu(int id) throws SQLException {
        try (PreparedStatement statement = prapararSQL(DELETE_MARCA_PNEU_SQL)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateMarcaPneu(MarcaPneu entidade) throws SQLException {
        try (PreparedStatement statement = prapararSQL(UPDATE_MARCA_PNEU_SQL)) {
            statement.setString(1, entidade.getDescricao());
            statement.setInt(2, entidade.getId());

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
