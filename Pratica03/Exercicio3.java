import java.util.Scanner;
import java.util.ArrayList;

public class Exercicio3 {
    public static void main(String[] args) {
        ArrayList<Integer> userNumbers = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        int userIndex = 0, greater = 0;
        double med = 0.0;
        for (int i=0; i< 10; i++) {
            int inputNumber = scanner.nextInt();
            if (inputNumber % 2 == 0) {
                userNumbers.add(inputNumber);
                userIndex++;

                if (inputNumber > greater) greater = inputNumber;
                med += inputNumber;
            }
        }

        int userNumbersLength = userNumbers.size();
        med = (double) med / userNumbersLength;

        for (int i=0; i< userNumbersLength; i++) {
            System.out.printf("Vetor[%d] = %d\n", i, userNumbers.get(i));
        }
        System.out.printf("Quant de valores armazenados: %d \nO maior valor encontrado: %d\nMédia: %.1f \n", userNumbersLength, greater, med);
        scanner.close();
    }
}
