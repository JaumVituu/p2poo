import java.util.Random;
import javax.swing.JOptionPane;

class TesteJogo{
    public static void main(String[] args)throws Exception{
        Personagem p = new Personagem();
        p.nome = "Pelé";
        int op;
        String login = null;
        String senha = null;
        int codUsuario;
        var s = "1 - Realizar login\n2 - Efetuar cadastro";
        op = Integer.parseInt(JOptionPane.showInputDialog(s));
        switch(op){
            case 1:
                do{
                    login = JOptionPane.showInputDialog("Digite o seu login");
                    if(login == null) System.exit(0);
                    var senhaEsperada = DAO.buscarLogin(login);
                    if(senhaEsperada == null){
                        JOptionPane.showMessageDialog(null, "Login não encontrado.");
                    }
                    else{
                        senha = JOptionPane.showInputDialog("Digite sua senha");
                        if(senha == null) System.exit(0);
                        if(!senha.equals(senhaEsperada)){
                            JOptionPane.showMessageDialog(null, "Senha incorreta.");
                            continue;
                        }
                        break;
                    }
                }while(true);
                break;
            case 2:
                do{
                    login = JOptionPane.showInputDialog("Digite o seu login");
                    if(login == null) System.exit(0);
                    if(DAO.buscarLogin(login) != null){
                        JOptionPane.showMessageDialog(null, "Login já existe.");
                    }
                    else{
                        senha = JOptionPane.showInputDialog("Digite sua senha");
                        if(senha == null) System.exit(0);
                        if(senha.length() < 3){
                            JOptionPane.showMessageDialog(null, "Digite uma senha maior.");
                            continue;
                        }
                        DAO.cadastrarUsuario(login, senha);
                        break;
                    }
                    
                }while(true);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Valor inválido. Programa encerrando");
                System.exit(0);
                break;
        }
        codUsuario = DAO.getCodUsuario(login);
        s = "1 - Jogar\n2 - Consultar log";
        do{
            op = Integer.parseInt(JOptionPane.showInputDialog(null, s));
            switch(op){
                case 1:
                    int pontuacao = 0;
                    for(int i = 0; i < 10; i++){
                        pontuacao += jogar(p, codUsuario);
                    }
                    DAO.cadastrarResultado(codUsuario, pontuacao);
                    break;
                case 2:
                    var atividades = DAO.listarAtividades();
                    var sb = new StringBuilder("\n");
                    for(Atividade atv : atividades){
                        sb.append(atv).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, sb.toString());
                    break;
                case 0 :break;
                default:
                    JOptionPane.showMessageDialog(null, "Digite um valor válido.");
            }
        }while(op != 0);
    }

    public static int jogar(Personagem p, int codUsuario){
        int retorno = 0;
        var acao = new Random();
        Atividade atv = new Atividade();
        try{
            switch(acao.nextInt(3)){
                case 0:
                    p.cacar();
                    atv = new Atividade("caçar", codUsuario);
                    retorno = 2;
                    break;
                case 1: 
                    p.comer();
                    atv = new Atividade("comer", codUsuario);
                    break;
                case 2:
                    p.dormir();
                    atv = new Atividade("dormir", codUsuario);
                    retorno = -1;
                    break;
            }
            DAO.cadastrarAtividade(atv);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Não foi possível estabelecer conexão");
        }
        return retorno;
    }
}