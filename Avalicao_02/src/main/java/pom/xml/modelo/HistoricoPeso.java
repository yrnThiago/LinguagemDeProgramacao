package pom.xml.modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HistoricoPeso {
    private int id;
    private int alunoID;
    private String dataPeso;
    private double peso;

    public HistoricoPeso(int id, int alunoID, String dataPeso, double peso) {
        this.id = id;
        this.alunoID = alunoID;
        this.dataPeso = dataPeso;
        this.peso = peso;
    }

    public String formataIMC(Double imc) {
        String interpretacao = "";
        if (imc < 18.5) {
           interpretacao = "Abaixo do peso";
        } else if (imc >= 18.5 && imc <= 24.9) {
           interpretacao = "Peso normal";
        } else if (imc >= 25.0 && imc <= 29.9) {
           interpretacao = "Sobrepeso";
        } else if (imc >= 30.0 && imc <= 34.9) {
           interpretacao = "Obesidade grau 1";
        } else if (imc >= 35.0 && imc <= 39.9) {
           interpretacao = "Obesidade grau 2";
        } else {
           interpretacao = "Obesidade grau 3 (obesidade mórbida)";
        }
        return imc + " - " + interpretacao;
    }

    public Double calculaIMC(double peso, double altura){
        BigDecimal bd = BigDecimal.valueOf(peso / Math.pow(altura, 2));
        return bd.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public static void gravarDadosEmArquivo(Aluno aluno, String imc) {
        LocalDateTime dataHoraCalculo = LocalDateTime.now();
        DateTimeFormatter formatterDataHora = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
        String dataHoraFormatada = dataHoraCalculo.format(formatterDataHora);

        String caminhoDiretorioPrincipal = "data/historicosAlunos";
        File diretorioPrincipal = new File(caminhoDiretorioPrincipal);
        
        String nomeDiretorioAluno = aluno.getNome().replaceAll("\\s+", "_");
        File diretorioAluno = new File(diretorioPrincipal, nomeDiretorioAluno);

        if (!diretorioAluno.exists()) {
            diretorioAluno.mkdirs();
        }

        String nomeArquivo = "historico_" + dataHoraFormatada + ".txt";
        File arquivo = new File(diretorioAluno, nomeArquivo);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo, true))) {
            writer.write("Data do Calculo: " + dataHoraFormatada.replace('_', ' '));
            writer.newLine();
            writer.write("CPF: " + aluno.getCPF());
            writer.newLine();
            writer.write("Nome do Aluno: " + aluno.getNome());
            writer.newLine();
            writer.write("Indice IMC: " + imc);
            writer.newLine();
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao gravar dados no arquivo: " + e.getMessage());
        }
    }

    public static boolean excluirDiretorioRecursivamente(File diretorio) {
        if (diretorio.isDirectory()) {
            File[] arquivos = diretorio.listFiles();
            if (arquivos != null) {
                for (File arquivo : arquivos) {
                    excluirDiretorioRecursivamente(arquivo);
                }
            }
        }
        return diretorio.delete();
    }

    public String getDataPeso() {
        return dataPeso;
    }

    public void setDataPeso(String dataPeso) {
        this.dataPeso = dataPeso;
    }   

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getAlunoID() {
        return alunoID;
    }

    public void setAlunoID(int alunoID) {
        this.alunoID = alunoID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
