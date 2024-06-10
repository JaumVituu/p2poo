public class Atividade {
    private String acao;

    public Atividade(String acao){
        this.acao = acao;
    }
    public Atividade(){
        this.acao = null;
    }

    public void setAcao(String acao){
        this.acao = acao;
    }

    public String getAcao(){
        return acao;
    }
}
