import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Dimension;

class TesteJogo{
    public static void main(String[] args)throws Exception{
        Personagem p = new Personagem();
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
        p.nome = login;
        StringBuilder sbLinha = new StringBuilder("");
        for(int i = 0;  i < 15; i++){
            sbLinha.append("--------"); // tem 8 traços - 8 * 15 = 120 traços no total
        }
        s = "1 - Jogar\n2 - Consultar log\n3 - Exibir Ranking";
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

                case 2:{
                        var atividades = DAO.listarAtividades();
                        var sb = new StringBuilder("\n");
                        for(Atividade atv : atividades){
                            sb.append(atv).append("\n");
                            sb.append(sbLinha.toString()).append("\n");
                        }

                        JTextArea textArea = new JTextArea(sb.toString()); // Quadro que exibe texto da sb dentro de uma janela do Jframe
                        JScrollPane scrollPane = new JScrollPane(textArea);  //Anexa o component textarea a um painel com barra de rolamento
                        scrollPane.setPreferredSize( new Dimension( 500, 800 ) ); // Escolhe o tamanho do scrollpane
                        JOptionPane.showMessageDialog(null, scrollPane, "Logs de Atividade", JOptionPane.OK_OPTION); 
                        //cria uma JOptionPane com o componente de textarea atrelado ao scrollpane com um botao de OK.
                        break;
                    }

                case 3:{
                        var resultados = DAO.listarResultados();
                        var sb = new StringBuilder("\n");
                        for(Resultado r : resultados){
                            sb.append(r).append("\n");
                            sb.append(sbLinha.toString().substring(0,99)).append("\n");
                        }
                        JTextArea textArea = new JTextArea(sb.toString()); // Quadro que exibe texto da sb dentro de uma janela do Jframe
                        JScrollPane scrollPane = new JScrollPane(textArea);  //Anexa o component textarea a um painel com barra de rolamento
                        scrollPane.setPreferredSize( new Dimension( 400, 400 ) ); // Escolhe o tamanho do scrollpane
                        JOptionPane.showMessageDialog(null, scrollPane, "Resultados", JOptionPane.OK_OPTION); 
                        //cria uma JOptionPane com o componente de textarea atrelado ao scrollpane com um botao de OK.
                        break;
                    }

                default:
                    JOptionPane.showMessageDialog(null, "Digite um valor válido.");
            }
        }while(true);
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