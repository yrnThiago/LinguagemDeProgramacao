import java.util.Scanner;

public class Exercicio6 {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean arrayHasNumber = false;
        int arrayValuePos = 0;

        int userNumbers[];
        userNumbers = new int[]{2,5,4,54,43,22,5,9,30,15};

        System.out.println("Digite um valor para encontrar no vetor: ");
        int numberInput = scanner.nextInt(); 

        for (int i = 0; i < userNumbers.length; i++) {
            if (numberInput == userNumbers[i]) {
                arrayHasNumber = true;
                arrayValuePos = i;
                break;
            }
        }

        if (arrayHasNumber) {
            System.out.printf("O número foi encontrado na posição [%d] do vetor", arrayValuePos);
        } else {
            System.out.println("O número não foi encontrado no vetor");
        }
    }
}
