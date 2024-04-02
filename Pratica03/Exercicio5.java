import java.util.ArrayList;
import java.util.Scanner;

public class Exercicio5 {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Integer> userFirstNumbers = new ArrayList<>();
        ArrayList<Integer> userNumbers = new ArrayList<>();
        
        for (int i = 0; i < 8; i++) {
            System.out.println("Digite um número: ");
            int numberInput =  scanner.nextInt();
            if (i <= 3) {
                userFirstNumbers.add(numberInput);
            } else {
                userNumbers.add(numberInput);
            }
        }

        userNumbers.addAll(userFirstNumbers);

        StringBuilder res = new StringBuilder();
        for (Integer num : userNumbers) {
            res.append(num);
        }
        System.out.println(res.toString());

    }
}
