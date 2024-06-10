public class Atividade {
    private String descricao;
    public int id;
    public String dataOcorrencia;

    public Atividade(String acao){
        this.descricao = acao;
    }
    public Atividade(){
        this.descricao = null;
        this.dataOcorrencia = null;
        this.id = 0;
    }

    public Atividade(String descricao, int id, String dataOcorrencia){
        this.descricao = descricao;
        this.id = id;
        this.dataOcorrencia = dataOcorrencia;
    }

    public void setDescricao(String acao){
        this.descricao = acao;
    }

    public String getDescricao(){
        return descricao;
    }

    public String toString(){
        var s = String.format("Desc: %6s | Cod: %3d | Data: %29s", descricao, id, dataOcorrencia);
        return s;
    }
}
