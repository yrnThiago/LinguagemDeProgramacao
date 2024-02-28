import javax.swing.JOptionPane;

public class Exercicio_8 {

    public static void main(String[] args) {
        
        String text = JOptionPane.showInputDialog("Digite um número: ");
        String res = (Integer.parseInt(text) % 2 == 0)? "Par" : "Ímpar"; 

        JOptionPane.showMessageDialog(null, res);

    }
    
}
