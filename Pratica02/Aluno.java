public class Aluno {
    int ra;
    String nome;
    char sexo;
    String rg;
    String cpf;
    Data dataNasc;
    
    public Aluno(int ra, String nome, char sexo, String rg, String cpf, Data dataNasc) {
        this.ra = ra;
        this.nome = nome;
        this.sexo = sexo;
        this.rg = rg;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
    }
    
    public void imprimir() {
        System.out.println("RA:" + this.ra + "\n" + "Nome: " + this.nome + "\n" + "Sexo: " + this.sexo + "\n" + "RG: " + this.rg + "\n" + "CPF: " + this.cpf + "\n" + "Data de Nascimento: " + this.dataNasc.formatarData() + "\n");
    }

    public int getRa() {
        return this.ra;
    }

    public String getName() {
        return this.nome;
    }

    public char getSexo() { 
        return this.sexo;
    }

    public String getRg() {
        return this.rg;
    }

    public String getCpf() {
        return this.cpf;
    }

    public String getDataNasc() {
        return this.dataNasc.formatarData();
    }

    public void setRa(int newRa) {
        this.ra = newRa;
    }

    public void setName(String newName) {
        this.nome = newName;
    }

    public void setSexo(char newSexo) {
        this.sexo = newSexo;
    }

    public void setRg(String newRg) {
        this.rg = newRg;
    }

    public void setCpf(String newCpf) {
        this.cpf = newCpf;
    }

    public void setDataNasc(int newDia, int newMes, int newAno) {
        Data newData = new Data(newDia, newAno, newMes); 
        this.dataNasc = newData;
    }
    
}
