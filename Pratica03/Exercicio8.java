import java.util.ArrayList;
import java.util.Arrays;

public class Exercicio8 {
    
    public static void main(String[] args) {
        ArrayList<Integer> M = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
        ArrayList<Integer> N = new ArrayList<>(Arrays.asList(1,1,1,1,1,1,1,1,1,1));
        int total = 0;

        for (int i = 0; i < M.size(); i++) {
            total += M.get(i) * N.get(i);
        }

        System.out.printf("O produto escalar de M por N é: %d", total);
    }
}
