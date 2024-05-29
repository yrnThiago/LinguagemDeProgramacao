package pom.xml.gui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import pom.xml.dao.AlunoDAO;
import pom.xml.modelo.Aluno;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class AlunoGUI extends JFrame implements ActionListener {
    private JButton cadastrarButton, consultarButton, sairButton, atualizarButton;
    private JTable alunoTable;
    private DefaultTableModel tableModel;
    private int alunoIdSelecionado;

    public AlunoGUI() {
        this.alunoIdSelecionado = 0;
        
        setTitle("Cadastro de Aluno");
        setSize(500, 400);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        cadastrarButton = new JButton("Cadastrar novo aluno");
        atualizarButton = new JButton("Atualizar");
        sairButton = new JButton("Sair");

        cadastrarButton.addActionListener(this);
        sairButton.addActionListener(this);
        atualizarButton.addActionListener(this);

        buttonPanel.add(cadastrarButton);
        buttonPanel.add(atualizarButton);
        buttonPanel.add(sairButton);

        tableModel = new DefaultTableModel(new Object[]{"ID", "CPF", "Nome", "Data de Nascimento", "Peso", "Altura"}, 0);
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
                        int id = (int) tableModel.getValueAt(selectedRow, 0);
                        selecionaAluno(id);
                        consultar(id);
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(alunoTable);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cadastrarButton) {
            cadastrar();
        } else if (e.getSource() == consultarButton) {
            consultar(this.alunoIdSelecionado);
        } else if (e.getSource() == atualizarButton) {
            atualizarTabela();
        } else if (e.getSource() == sairButton) {
            sair();
        }
    }

    private void selecionaAluno(int id) {
        this.alunoIdSelecionado = id;
    }

    private void cadastrar() {
        this.adicionarDetalhesAluno();
    }

    private void adicionarDetalhesAluno() {
        AlunoDetalhesGUI detalhesAluno = new AlunoDetalhesGUI();
        detalhesAluno.cadastrarAluno();
        detalhesAluno.setVisible(true);
    }

    private void consultar(int id) {
        AlunoDAO dao = new AlunoDAO();
        Aluno aluno = dao.pegaAlunoPorID(this.alunoIdSelecionado);
        AlunoDetalhesGUI detalhesAluno = new AlunoDetalhesGUI();
        detalhesAluno.detalhesAlunoPorID(aluno, this.alunoIdSelecionado);
        detalhesAluno.setVisible(true);
    }

    private void sair() {
        System.exit(0);
    }

    private void atualizarTabela() {
        AlunoDAO dao = new AlunoDAO();
        List<Aluno> alunos = dao.pegaAlunos();

        tableModel.setRowCount(0);

        for (Aluno aluno : alunos) {
            Object[] rowData = {aluno.getID(), aluno.getCPF(), aluno.getNome(), aluno.getDataNasc(), aluno.getPeso(), aluno.getAltura()};
            tableModel.addRow(rowData);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AlunoGUI cadastroAluno = new AlunoGUI();
            cadastroAluno.setVisible(true);
            cadastroAluno.atualizarTabela();
        });
    }
}
