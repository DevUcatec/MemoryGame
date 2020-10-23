package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmMemoria extends JFrame implements ActionListener {
    private JPanel panel1;
    private JLabel jLabel1;
    private JLabel cronometro;
    private int filas;
    private int columnas;
    JButton[][] cuadro;
    javax.swing.Timer timer = new javax.swing.Timer(1000, this);
    private int segundos;

    public FrmMemoria(int columnas, int filas) {
        super("Juego de Memoria");
        this.columnas = columnas;
        this.filas = filas;
        this.segundos = filas * 10;
        panel1 = new JPanel();
        cronometro = new JLabel();
        jLabel1 = new JLabel("TIEMPO: ");
        cuadro = new JButton[filas][columnas];
        setMatriz();
        panel1.add(jLabel1);
        panel1.add(cronometro);
        setContentPane(panel1);
        timer.start();  //Iniciar cronometro
        setSize(columnas * 100, (filas * 90) + 20);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void setMatriz() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                cuadro[i][j] = new JButton();
                //cuadro[i][j].setText(i+" " +j);
                cuadro[i][j].setIcon(new ImageIcon("src/img/" + 100 + ".png"));
                panel1.add(cuadro[i][j]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        cronometro.setText("" + segundos--);
        if (segundos == -1) {
            timer.stop();
            JOptionPane.showMessageDialog(null, "PERDISTE!!!");
            System.exit(0);
        }
    }
}
