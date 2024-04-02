import java.util.ArrayList;
import java.util.Scanner;

public class Exercicio4 {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> userNumbers = new ArrayList<>();

        System.out.println("Digite a quantidade de números a serem digitados: ");
        int numbersQuantity = scanner.nextInt();
        
        for (int i = 0; i < numbersQuantity; i++) {
            userNumbers.add(scanner.nextInt());
        }

        String res = "";
        System.out.println("Sequência na ordem inversa:");
        for (int i = numbersQuantity-1; i >= 0; i--) {
            res += userNumbers.get(i) + " ";
        }

        System.out.println(res);
    }
}
