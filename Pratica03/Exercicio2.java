import java.util.Scanner;

public class Exercicio2 {
    public static void main(String[] args) {
        int userNumbers[];
        userNumbers = new int[10];

        Scanner scanner = new Scanner(System.in);

        for (int i=0; i<10; i++) {
            userNumbers[i] = scanner.nextInt();
        }

        for (int i=0; i< userNumbers.length; i++) {
            if (userNumbers[i] >= 20){
                System.out.format("Vetor[%d] = %d \n", i, userNumbers[i]);
            }
        }

        scanner.close();
    }
}
