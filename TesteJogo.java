import javax.swing.JOptionPane;

class TesteJogo{
    public static void main(String[] args) throws Exception{
        Personagem p = new Personagem();
        p.nome = "Pelé";
        try{
            DAO.cadastrarAtividade(p,"comer");
            JOptionPane.showMessageDialog(null, "Atividade cadastrada com sucesso!");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Não foi possível estabelecer conexão.");
        }
    }
}