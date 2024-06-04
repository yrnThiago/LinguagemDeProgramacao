package pom.xml.gui;

import javax.swing.*;

import pom.xml.dao.AlunoDAO;
import pom.xml.dao.HistoricoPesoDAO;
import pom.xml.modelo.Aluno;
import pom.xml.modelo.HistoricoPeso;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AlunoDetalhesGUI extends JFrame implements ActionListener {
    private JTextField nomeTextField, idTextField, cpfTextField, dataNascTextField, pesoTextField, alturaTextField;
    private JButton adicionarButton, fecharButton, alterarButton, excluirButton, historicoButton;
    private JLabel idLabel, cpfLabel, dataNascLabel, pesoLabel, alturaLabel;
    private int alunoId = 0;
    private Aluno aluno = null;

    public void cadastrarAluno() {
        setTitle("Detalhes do Aluno");
        setSize(400, 260);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridLayout(7, 2));

        JLabel nomeLabel = new JLabel("Nome:");
        panel.add(nomeLabel);
        nomeTextField = new JTextField();
        panel.add(nomeTextField);

        cpfLabel = new JLabel("CPF:");
        panel.add(cpfLabel);
        cpfTextField = new JTextField();
        panel.add(cpfTextField);

        dataNascLabel = new JLabel("Data de Nascimento:");
        panel.add(dataNascLabel);
        dataNascTextField = new JTextField();
        panel.add(dataNascTextField);

        pesoLabel = new JLabel("Peso:");
        panel.add(pesoLabel);
        pesoTextField = new JTextField();
        panel.add(pesoTextField);

        alturaLabel = new JLabel("Altura:");
        panel.add(alturaLabel);
        alturaTextField = new JTextField();
        panel.add(alturaTextField);

        adicionarButton = new JButton("Cadastrar");
        fecharButton = new JButton("Fechar");

        adicionarButton.addActionListener(this);
        fecharButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(adicionarButton);
        buttonPanel.add(fecharButton);

        panel.add(buttonPanel);
        add(panel);
    }

    public void detalhesAlunoPorID(Aluno aluno, int id) {
        this.aluno = aluno;
        this.alunoId = id;

        setTitle("Detalhes do Aluno");
        setSize(550, 270);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        JPanel panel = new JPanel(new GridLayout(7, 2));

        JLabel nomeLabel = new JLabel("Nome:");
        panel.add(nomeLabel);
        nomeTextField = new JTextField(aluno.getNome());
        panel.add(nomeTextField);

        cpfLabel = new JLabel("CPF:");
        panel.add(cpfLabel);
        cpfTextField = new JTextField(aluno.getCPF());
        panel.add(cpfTextField);

        dataNascLabel = new JLabel("Data de Nascimento:");
        panel.add(dataNascLabel);
        dataNascTextField = new JTextField(aluno.getDataNasc());
        panel.add(dataNascTextField);

        pesoLabel = new JLabel("Peso:");
        panel.add(pesoLabel);
        pesoTextField = new JTextField(Double.toString(aluno.getPeso()));
        panel.add(pesoTextField);

        alturaLabel = new JLabel("Altura:");
        panel.add(alturaLabel);
        alturaTextField = new JTextField(Double.toString(aluno.getAltura()));
        panel.add(alturaTextField);

        historicoButton = new JButton("Histórico");
        historicoButton.addActionListener(this);
        alterarButton = new JButton("Alterar");
        alterarButton.addActionListener(this);
        excluirButton = new JButton("Excluir");
        excluirButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(historicoButton);
        buttonPanel.add(alterarButton);
        buttonPanel.add(excluirButton);

        panel.add(buttonPanel);
        add(panel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fecharButton) {
            dispose();
            return;
        }

        String nome = nomeTextField.getText();
        String cpf = cpfTextField.getText();
        String dataNasc = dataNascTextField.getText();
        double peso = Double.parseDouble(pesoTextField.getText());
        double altura = Double.parseDouble(alturaTextField.getText());
        String dataAtual = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if (e.getSource() == adicionarButton) {
            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, insira um nome.", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                Aluno aluno = new Aluno(0, cpf, nome, dataNasc, peso, altura);
                AlunoDAO dao = new AlunoDAO();
                Aluno alunoAdicionado = dao.adiciona(aluno);

                HistoricoPeso historico = new HistoricoPeso(0, alunoAdicionado.getID(), dataAtual, peso);
                HistoricoPesoDAO histDao = new HistoricoPesoDAO();
                histDao.adiciona(historico);

                JOptionPane.showMessageDialog(this, "Aluno cadastrado com sucesso: " + nome, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        }
        else if (e.getSource() == alterarButton) {
            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, insira um nome.", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                Aluno alunoAtualizado = new Aluno(0, cpf, nome, dataNasc, peso, altura);
                HistoricoPeso novoHistorico = new HistoricoPeso(0, alunoId, dataAtual, peso);
                
                AlunoDAO dao = new AlunoDAO();
                HistoricoPesoDAO histDao = new HistoricoPesoDAO();

                histDao.adiciona(novoHistorico);
                dao.atualizaAlunoPorID(alunoAtualizado, this.alunoId);
                JOptionPane.showMessageDialog(this, "Aluno ID: " + this.alunoId + " alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        }

        else if (e.getSource() == historicoButton) {
            HistoricoPesoGUI historico = new HistoricoPesoGUI(this.aluno);
            historico.setVisible(true);
        }

        else if (e.getSource() == excluirButton) {
            AlunoDAO dao = new AlunoDAO();
            HistoricoPesoDAO histDao = new HistoricoPesoDAO();
            HistoricoPeso historicoPeso = new HistoricoPeso(0, this.aluno.getID(), "", 0);

            boolean alunoFoiExcluido = dao.excluiAlunoPorID(this.alunoId);
            boolean historicoAlunoFoiExcluido = histDao.excluiHistoricoPorAlunoID(this.alunoId);

            File diretorio = new File("data/historicosAlunos/" + this.aluno.getNome());
            boolean arquivoAlunoFoiExcluido = historicoPeso.excluirDiretorioRecursivamente(diretorio);
            
            if (alunoFoiExcluido && historicoAlunoFoiExcluido && arquivoAlunoFoiExcluido) {
                JOptionPane.showMessageDialog(this, "Aluno ID: " + this.alunoId + " excluído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
            else {
                JOptionPane.showMessageDialog(this, "Não foi possível excluir, tente novamente!", "ERRO", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
