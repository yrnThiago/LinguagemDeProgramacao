package pom.xml.gui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import pom.xml.dao.HistoricoPesoDAO;
import pom.xml.modelo.Aluno;
import pom.xml.modelo.HistoricoPeso;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class HistoricoPesoGUI extends JFrame implements ActionListener {
    private JButton calcularButton, consultarButton, sairButton, atualizarButton;
    private JTable alunoTable;
    private DefaultTableModel tableModel;
    private Aluno aluno;
    private Double alunoPeso;
    private int historicoId;
    private String dataPeso;


    public HistoricoPesoGUI(Aluno aluno) {
        this.aluno = aluno; 

        setTitle("Histórico de peso de " + this.aluno.getNome() + ": ");
        setSize(500, 400);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        calcularButton = new JButton("Calcular IMC");
        atualizarButton = new JButton("Atualizar");
        sairButton = new JButton("Sair");

        calcularButton.addActionListener(this);
        sairButton.addActionListener(this);
        atualizarButton.addActionListener(this);

        buttonPanel.add(calcularButton);
        buttonPanel.add(atualizarButton);
        buttonPanel.add(sairButton);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Peso (Kg)", "Data"}, 0);
        alunoTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        alunoTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = alunoTable.getSelectedRow();
                    if (selectedRow != -1) {
                        int historicoID = (int) tableModel.getValueAt(selectedRow, 0);
                        Double alunoPeso = (Double) tableModel.getValueAt(selectedRow, 1);
                        String dataPeso = (String) tableModel.getValueAt(selectedRow, 2);

                        selecionaHistoricoAluno(alunoPeso, historicoID, dataPeso);
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(alunoTable);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);

        atualizarTabela();
    }

    public void selecionaHistoricoAluno(Double peso, int historicoID, String dataPeso) {
        this.alunoPeso = peso;
        this.historicoId = historicoID;
        this.dataPeso = dataPeso;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calcularButton) {
            if (this.alunoPeso != null) {
                HistoricoPeso historicoPeso = new HistoricoPeso(this.historicoId, this.aluno.getID(), this.dataPeso, this.alunoPeso);
                Double imc = historicoPeso.calculaIMC(this.alunoPeso, this.aluno.getAltura());
                String imcFormatado = historicoPeso.formataIMC(imc);
                JOptionPane.showMessageDialog(this, "IMC: " + imcFormatado, "Calculadora IMC", JOptionPane.INFORMATION_MESSAGE);
                historicoPeso.gravarDadosEmArquivo(this.aluno, imcFormatado);
            }
            else {
                JOptionPane.showMessageDialog(this, "Selecione um histórico para calcular o IMC!", "Calculadora IMC", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == consultarButton) {
            atualizarTabela();
        } else if (e.getSource() == atualizarButton) {
            atualizarTabela();
        } else if (e.getSource() == sairButton) {
            dispose();
        }
    }

    private void atualizarTabela() {
        HistoricoPesoDAO historioDAO = new HistoricoPesoDAO();
        List<HistoricoPeso> historicos = historioDAO.pegaHistoricoPorAlunoID(this.aluno.getID());

        tableModel.setRowCount(0);

        for (HistoricoPeso historico : historicos) {
            Object[] rowData = {historico.getId(), historico.getPeso(), historico.getDataPeso()};
            tableModel.addRow(rowData);
        }
    }

}
