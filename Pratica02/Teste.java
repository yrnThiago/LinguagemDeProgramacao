public class Teste {
    public static void main(String[] args) {
        Data dataNasc = new Data(27, 05, 2002);
        Aluno alunoTeste = new Aluno(1234, "Thiago", 'M', "123", "321", dataNasc);
        
        alunoTeste.imprimir();
    }
}
