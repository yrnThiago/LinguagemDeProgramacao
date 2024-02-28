public class Exercicio_6 {
    

    public static void main(String[] args) {
        
        int soma = 0;
        int cont = 0;
        for (int i = 0; i <= 100; i++) {
            if (i % 2 != 0) {
                soma += i;
                cont++;
            }
        }

        double media = soma / cont;

        System.out.println(media);

    }
}
