import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

public class DAO{
    public static void cadastrarAtividade(Atividade a) throws Exception{
        //COALESCE faz o seguinte:
        //caso o valor da esquerda resulta em NULL, ele envia o valor a direita
        //Ou seja, caso o valor maximo da tabela cod_atividade for NULL, ou seja, caso nao exista nenhum registro
        //ele coloca 0 no lugar do ID
        var s = "INSERT INTO tb_atividade(cod_atividade, descricao, data_de_ocorrencia, fk_usuario) VALUES((SELECT COALESCE(MAX(cod_atividade), 0) FROM tb_atividade) + 1, ?, CURRENT_TIMESTAMP, ?);";
        Connection c = ConnectionFactory.getConnection();
        PreparedStatement ps = c.prepareStatement(s);
        ps.setString(1,a.getDescricao());
        ps.setInt(2, a.getFkUsuario());
        ps.execute();
        ps.close();
        c.close();
    }

    public static java.util.List<Atividade> listarAtividades() throws Exception{

        var sql = "SELECT t1.*, t2.login FROM tb_atividade t1 LEFT JOIN tb_usuario t2 ON t1.fk_usuario = t2.cod_usuario ORDER BY data_de_ocorrencia ASC;";

        Connection c = ConnectionFactory.getConnection();
        PreparedStatement ps = c.prepareStatement(sql);

        java.sql.ResultSet rs = ps.executeQuery();

        var atividades = new LinkedList<Atividade>();
        while(rs.next()){
            int fk = rs.getInt("fk_usuario");
            sql = String.format("SELECT login FROM tb_atividade WHERE cod_usuario = %d;", fk);
            var atv = new Atividade(
                rs.getString("descricao"),
                rs.getInt("cod_atividade"),
                rs.getString("data_de_ocorrencia"),
                rs.getInt("fk_usuario"),
                rs.getString("login")
            );
            atividades.add(atv);
        }
        ps.close();
        c.close();

        return atividades;
    }

    public static String buscarLogin(String l) throws Exception{
        var sql = "SELECT * FROM tb_usuario WHERE login = ?;";
        Connection c = ConnectionFactory.getConnection();
        PreparedStatement ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ps.setString(1, l);
        java.sql.ResultSet rs = ps.executeQuery();
        if(rs.first()){
            var senha = rs.getString("senha");
            ps.close();
            c.close();
            return senha;
        }
        ps.close();
        c.close();
        return null;
    }

    public static void cadastrarUsuario(String login, String senha) throws Exception{
        var sql = "INSERT INTO tb_usuario(login, senha) VALUES(?,?);";
        Connection c = ConnectionFactory.getConnection();
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, login);
        ps.setString(2, senha);
        ps.execute();
        ps.close();
        c.close();
    }

    public static int getCodUsuario(String login) throws Exception{
        var sql = "SELECT cod_usuario FROM tb_usuario WHERE login = ?;";
        Connection c = ConnectionFactory.getConnection();
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, login);
        java.sql.ResultSet rs = ps.executeQuery();
        rs.next();
        var cod = rs.getInt("cod_usuario");
        ps.close();
        c.close();
        return cod;
    }

    public static void cadastrarResultado(int codUsuario, int pontuacao) throws Exception{
        String sql = "INSERT INTO tb_resultado(fk_usuario, pontuacao, data_de_ocorrencia) VALUES(?,?,CURRENT_TIMESTAMP);";
        Connection c = ConnectionFactory.getConnection();
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, codUsuario);
        ps.setInt(2, pontuacao);
        ps.execute();
        ps.close();
        c.close();
    }

    public static java.util.List<Resultado> listarResultados() throws Exception{
        var sql = "SELECT tr.*, tu.login FROM tb_resultado AS tr INNER JOIN tb_usuario as tu ON tr.fk_usuario = tu.cod_usuario ORDER BY tr.pontuacao DESC, tr.data_de_ocorrencia ASC;";
        Connection c = ConnectionFactory.getConnection();
        PreparedStatement ps = c.prepareStatement(sql);
        java.sql.ResultSet rs = ps.executeQuery();
        LinkedList lista = new LinkedList<Resultado>();
        while(rs.next()){
            Resultado r = new Resultado(
                rs.getString("login"),
                rs.getInt("pontuacao"),
                rs.getString("data_de_ocorrencia")
            );
            lista.add(r);
        }
        rs.close();
        ps.close();
        c.close();
        return lista;
    }
}