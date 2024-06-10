import java.util.Random;
import java.util.LinkedList;

import javax.swing.JOptionPane;
class TesteJogo{
    public static void main(String[] args) throws Exception{
        Personagem p = new Personagem();
        p.nome = "Pelé";
        var s = "0 - Sair\n1 - Jogar\n2 - Consultar log";
        int op;
        do{
            op = Integer.parseInt(JOptionPane.showInputDialog(null, s));
            switch(op){
                case 1:
                    for(int i = 0; i < 10; i++){
                        jogar(p);
                    }
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

    public static void jogar(Personagem p){
        var acao = new Random();
        var atv = new Atividade();
        try{
            switch(acao.nextInt(3)){
                case 0:
                    p.cacar();
                    atv.setDescricao("caçar");
                    break;
                case 1: 
                    p.comer();
                    atv.setDescricao("comer");;
                    break;
                case 2:
                    p.dormir();
                    atv.setDescricao("dormir");;
                    break;
            }
            DAO.cadastrarAtividade(atv);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Não foi possível estabelecer conexão");
        }
    }
}