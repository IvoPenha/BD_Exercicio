package br.com.frota.servico;

import br.com.frota.DAO.MarcaPneuDAO;
import br.com.frota.model.MarcaPneu;

public class ServicoMarcaPneu extends MarcaPneuDAO{

    //TODO: Aqui é um exemplo de regra de negócio. possivelmente quando estivermos implementando
    // as regras de vistoria do pneu tenham regras nesse modelo de padrão de projeto.
    public void insertMarcaPneu(MarcaPneu entidade) {
        if (count() <= 20) {
            super.insertMarcaPneu(entidade);
        }
    }
}
