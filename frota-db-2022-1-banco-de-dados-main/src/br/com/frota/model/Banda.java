package br.com.frota.model;

public class Banda extends GenericModel {
    private String posicao;
    private String descricao;

    public Banda(String descricao, String posicao) {
        this.descricao = descricao;
        this.posicao = posicao;
    }
    public Banda(Integer id, String descricao, String posicao) {
        this.descricao = descricao;
        this.posicao = posicao;
        super.setId(id);
    }

    public String getDescricao() {
        return descricao;
    }
    public String getPosicao() {
        return posicao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void setPosicao(String posicao) {
        this.descricao = posicao;
    }
    @Override
    public String toString() {
        return "Marca {" +
                "id='" + this.getId() + "\'" +
                "descricao='" + descricao + "\'" +
                "posicao= ' " + posicao + "\'" +
                '}';
    }
}
