package br.com.frota.util;

import br.com.frota.DAO.MarcaPneuDAO;
import br.com.frota.model.MarcaPneu;


import java.sql.SQLException;
import java.util.List;

public class addMarcaPneu {
    static MarcaPneuDAO marcaPneuDao = new MarcaPneuDAO();

    public static void main(String[] args) throws SQLException {

        //count
        System.out.println(marcaPneuDao.count());

        //salvar
        MarcaPneu marcaPneu = new MarcaPneu("Michelin");
        marcaPneuDao.insertMarcaPneu(marcaPneu);

        //buscar por ID
        marcaPneu = marcaPneuDao.selectMarcaPneu(2);
        System.out.println(marcaPneu);

        //Update
        marcaPneu.setDescricao("Bridgestone ");
        marcaPneuDao.updateMarcaPneu(marcaPneu);
        marcaPneu = MarcaPneuDAO.selectMarcaPneu(2);
        System.out.println(marcaPneu);

        //Select all
        List<MarcaPneu> marcaPneus = MarcaPneuDAO.selectAllMarcaPneus();
        marcaPneus.forEach(System.out::println);

        //Delete
        MarcaPneuDAO.deleteMarcaPneu(2);
        MarcaPneuDAO.selectAllMarcaPneus().forEach(System.out::println);
    }
}