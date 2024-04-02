import java.util.ArrayList;
import java.util.Arrays;

public class Exercicio7 {
    
    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(1, 2, 4, 6, 21));
        ArrayList<Integer> B = new ArrayList<>(Arrays.asList(2,3,6,7,9,11,15,20));
        String res = "";

        for ( int num : A ) {
            if (B.contains(num)){
                res += num + " ";
            }
        }

        System.out.println(res);
    }
}
