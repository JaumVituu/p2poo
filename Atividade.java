public class Atividade {
    private String descricao;
    public int id;
    public String dataOcorrencia;
    public int fkUsuario;
    public String usuario;

    public Atividade(String acao){
        this.descricao = acao;
    }
    public Atividade(){}

    public Atividade(String descricao, int id, String dataOcorrencia, int fkUsuario, String usuario){
        this.descricao = descricao;
        this.id = id;
        this.dataOcorrencia = dataOcorrencia;
        this.fkUsuario = fkUsuario;
        this.usuario = usuario;
    }

    public Atividade(String descricao, int fkUsuario){
        this.descricao = descricao;
        this.fkUsuario = fkUsuario;
    }

    public String getDescricao(){
        return descricao;
    }

    public int getFkUsuario(){
        return fkUsuario;
    }

    public String toString(){
        var s = String.format("Desc: %7s| Cod: %3d | Usuário: %15s | Data: %s", descricao, id, usuario.length() > 10 ? usuario.substring(0, 9) : usuario, dataOcorrencia);
        return s;
    }
}
