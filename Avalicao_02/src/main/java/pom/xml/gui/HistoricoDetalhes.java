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

public class HistoricoDetalhes extends JFrame implements ActionListener {
    private JTextField idTextField, pesoTextField, dataTextField;
    private JButton adicionarButton, fecharButton, alterarButton, excluirButton;
    private JLabel cpfLabel, dataLabel, pesoLabel;
    private int historicoId = 0;
    private HistoricoPeso historico = null;

    public void detalhesHistoricoPorID(HistoricoPeso historico, int id) {
        this.historico = historico;
        this.historicoId = id;

        setTitle("Detalhes do Histórico");
        setSize(250, 260);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        JPanel panel = new JPanel(new GridLayout(7, 2));

        pesoLabel = new JLabel("Peso (Kg):");
        panel.add(pesoLabel);
        pesoTextField = new JTextField(String.valueOf(this.historico.getPeso()));
        panel.add(pesoTextField);

        dataLabel = new JLabel("Data:");
        panel.add(dataLabel);
        dataTextField = new JTextField(this.historico.getDataPeso());
        panel.add(dataTextField);

        alterarButton = new JButton("Alterar");
        alterarButton.addActionListener(this);
        excluirButton = new JButton("Excluir");
        excluirButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
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

        double peso = Double.parseDouble(pesoTextField.getText());
        String data = dataTextField.getText();
        if (e.getSource() == alterarButton) {
            HistoricoPeso novoHistorico = new HistoricoPeso(this.historicoId, this.historico.getAlunoID(), data, peso);
            
            HistoricoPesoDAO histDao = new HistoricoPesoDAO();

            histDao.atualizaHistoricoPorID(novoHistorico, this.historicoId);
            JOptionPane.showMessageDialog(this, "Histórico ID: " + this.historicoId + " alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }

        else if (e.getSource() == excluirButton) {
            HistoricoPesoDAO histDao = new HistoricoPesoDAO();
            boolean historicoAlunoFoiExcluido = histDao.excluiHistoricoPorID(this.historicoId);
            
            if (historicoAlunoFoiExcluido) {
                JOptionPane.showMessageDialog(this, "Histórico ID: " + this.historicoId + " excluído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
            else {
                JOptionPane.showMessageDialog(this, "Não foi possível excluir, tente novamente!", "ERRO", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
