public class Resultado {
    private String usuario;
    private int pontuacao;
    private String dataOcorrencia;

    public Resultado(String usuario, int pontuacao, String dataOcorrencia){
        this.usuario = usuario;
        this.pontuacao = pontuacao;
        this.dataOcorrencia = dataOcorrencia;
    }

    public String toString(){
        return String.format("Usuário: %15s | Pontos = %2d | Data: %s", usuario.length() > 10 ? usuario.substring(0, 9) : usuario, pontuacao, dataOcorrencia);
    }
}
