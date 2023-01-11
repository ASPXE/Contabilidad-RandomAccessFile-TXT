/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.libro_contabilidad.MenuPrincipal;

import com.mycompany.MenuLibroDiario.MenuLibroDiario;
import com.mycompany.libro_contabilidad.Modelo.Archivo;
import com.mycompany.libro_contabilidad.Modelo.LibroDiario;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aspxe
 */
public class MenuPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form MenuPrincipal
     */
    public MenuPrincipal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblLibroDiario = new javax.swing.JLabel();
        lblLibroMayor = new javax.swing.JLabel();
        cbLibroDiario = new javax.swing.JComboBox<>();
        cbLibroMayor = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Principal");
        setLocation(new java.awt.Point(600, 300));
        setMaximumSize(new java.awt.Dimension(600, 600));
        setMinimumSize(new java.awt.Dimension(10, 10));
        setPreferredSize(new java.awt.Dimension(600, 600));
        getContentPane().setLayout(null);

        lblLibroDiario.setText("Libro diario");
        getContentPane().add(lblLibroDiario);
        lblLibroDiario.setBounds(20, 50, 90, 18);

        lblLibroMayor.setText("Libro mayor");
        getContentPane().add(lblLibroMayor);
        lblLibroMayor.setBounds(20, 150, 77, 18);

        cbLibroDiario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Agregar libro diario", "Ver libro diario ", " " }));
        cbLibroDiario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLibroDiarioActionPerformed(evt);
            }
        });
        getContentPane().add(cbLibroDiario);
        cbLibroDiario.setBounds(20, 90, 170, 24);

        cbLibroMayor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ver libro mayor" }));
        cbLibroMayor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLibroMayorActionPerformed(evt);
            }
        });
        getContentPane().add(cbLibroMayor);
        cbLibroMayor.setBounds(20, 190, 170, 24);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(0, 260, 600, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbLibroDiarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLibroDiarioActionPerformed
        // TODO add your handling code here:
        int seleccion = cbLibroDiario.getSelectedIndex();
        switch (seleccion) {
            case 0:
                //Creamos una instancia de MenuLibroDiario
                MenuLibroDiario mld = new MenuLibroDiario();
                mld.setVisible(true);
                break;
            case 1:
                Archivo arch = new Archivo();

                try {
                    arch.reporteLibroDiario();
                } catch (IOException ex) {
                    Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;

            default:
                break;
        }
    }//GEN-LAST:event_cbLibroDiarioActionPerformed

    private void cbLibroMayorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLibroMayorActionPerformed
        // TODO add your handling code here:
        int seleccion = cbLibroMayor.getSelectedIndex();
        switch (seleccion) {
            case 0:
                Archivo arch = new Archivo();
                try{
                    arch.reporteLibroMayor();
                }catch(Exception e){
                    System.out.println("Ha ocurrido un error: "+e);
                }
                break;
            case 1:

                break;
            default:
                break;
        }
    }//GEN-LAST:event_cbLibroMayorActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbLibroDiario;
    private javax.swing.JComboBox<String> cbLibroMayor;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblLibroDiario;
    private javax.swing.JLabel lblLibroMayor;
    // End of variables declaration//GEN-END:variables
}