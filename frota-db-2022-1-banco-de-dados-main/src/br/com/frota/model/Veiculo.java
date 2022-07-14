package br.com.frota.model;
import br.com.frota.model.Marca;
import br.com.frota.DAO.MarcaDAO;

public class Veiculo     extends GenericModel {
    private String descricao;
    private int idMarca;
    public Veiculo(String descricao) {
        this.descricao = descricao;
    }
    public Veiculo(int idMarca) {
        this.idMarca = idMarca;
    }

    public Veiculo(Integer id, String descricao, int idMarca) {
        this.descricao = descricao;
        this.idMarca= idMarca;
        super.setId(id);
    }
    public int getIdMarca() { return idMarca ;}
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Marca {" +
                "id='" + this.getId() + "\'" +
                "descricao='" + descricao + "\'" +
                '}';
    }
}
