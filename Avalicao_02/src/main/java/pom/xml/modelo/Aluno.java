package pom.xml.modelo;

public class Aluno {
    private int id;
    private String cpf;
    private String nome;
    private String dataNasc;
    private double peso;
    private double altura;

    public Aluno(int id, String cpf, String nome, String dataNasc, double peso, double altura) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.peso = peso;
        this.altura = altura;
    }

    public int getID() {
        return this.id;
    }

    public String getCPF() {
        return this.cpf;
    }

    public String getNome() {
        return this.nome;
    }

    public String getDataNasc() {
        return this.dataNasc;
    }

    public double getPeso() {
        return this.peso;
    }

    public double getAltura() {
        return this.altura;
    }

    public void setCPF(String novoCPF) {
        this.cpf = novoCPF;
    }

    public void setNome(String novoNome) {
        this.nome = novoNome;
    }

    public void setDataNasc(String novoDataNasc) {
        this.dataNasc = novoDataNasc;
    }

    public void setPeso(double novoPeso) {
        this.peso = novoPeso;
    }

    public void setAltura(double novoAltura) {
        this.altura = novoAltura;
    }

    public java.sql.Date convertDataToSQLData () {
        return java.sql.Date.valueOf(this.dataNasc);
    }

}
