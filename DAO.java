import java.sql.Connection;
import java.sql.PreparedStatement;

public class DAO{
    public static void cadastrarAtividade(Personagem p, String acao) throws Exception{
        //COALESCE faz o seguinte:
        //caso o valor da esquerda resulta em NULL, ele envia o valor a direita
        //Ou seja, caso o valor maximo da tabela cod_atividade for NULL, ou seja, caso nao exista nenhum registro
        //ele coloca 0 no lugar do ID
        var s = "INSERT INTO tb_atividade(cod_atividade, descricao, data_de_ocorrencia) VALUES((SELECT COALESCE(MAX(cod_atividade), 0) FROM tb_atividade) + 1, ?, CURRENT_TIMESTAMP);";
        Connection c = ConnectionFactory.getConnection();
        PreparedStatement ps = c.prepareStatement(s);
        ps.setString(1,acao);
        ps.execute();
        ps.close();
        c.close();
    }
}