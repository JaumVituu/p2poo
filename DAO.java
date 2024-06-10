import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.LinkedList;

public class DAO{
    public static void cadastrarAtividade(Atividade a) throws Exception{
        //COALESCE faz o seguinte:
        //caso o valor da esquerda resulta em NULL, ele envia o valor a direita
        //Ou seja, caso o valor maximo da tabela cod_atividade for NULL, ou seja, caso nao exista nenhum registro
        //ele coloca 0 no lugar do ID
        var s = "INSERT INTO tb_atividade(cod_atividade, descricao, data_de_ocorrencia) VALUES((SELECT COALESCE(MAX(cod_atividade), 0) FROM tb_atividade) + 1, ?, CURRENT_TIMESTAMP);";
        Connection c = ConnectionFactory.getConnection();
        PreparedStatement ps = c.prepareStatement(s);
        ps.setString(1,a.getDescricao());
        ps.execute();
        ps.close();
        c.close();
    }

    public static java.util.List<Atividade> listarAtividades() throws Exception{
        var sql = "SELECT * FROM tb_atividade ORDER BY data_de_ocorrencia ASC;";

        Connection c = ConnectionFactory.getConnection();
        PreparedStatement ps = c.prepareStatement(sql);

        java.sql.ResultSet rs = ps.executeQuery();

        var atividades = new LinkedList<Atividade>();
        while(rs.next()){
            var atv = new Atividade(
                rs.getString("descricao"),
                rs.getInt("cod_atividade"),
                rs.getString("data_de_ocorrencia")
            );
            atividades.add(atv);
        }
        ps.close();
        c.close();

        return atividades;
    }
}