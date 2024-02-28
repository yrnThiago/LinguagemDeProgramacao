import javax.swing.JOptionPane;

public class Exercicio_7 {
    
    public static void main(String[] args) {

        while (true){
            String nome = JOptionPane.showInputDialog("Qual o seu nome: ");

            if (nome != null && nome.equals("") == false) {
                JOptionPane.showMessageDialog(null, nome);
                break;
            }

            JOptionPane.showMessageDialog(null, "Nome não encontrado, digite novamente...");
        }

    }
}
