package com.mycompany.MenuLibroDiario;

import com.mycompany.libro_contabilidad.Modelo.Archivo;
import com.mycompany.libro_contabilidad.Modelo.LibroDiario;
import com.mycompany.libro_contabilidad.Modelo.Transacciones;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author aspxe
 */
public class MenuLibroDiario extends javax.swing.JFrame {

    public String nombreArchivo;
    /**
     * Creates new form MenuLibroDiario
     */
    public MenuLibroDiario() {
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

        btnBuscarTxt = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtNombreArchivo = new javax.swing.JTextField();
        lblGenerar = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu LIbro Diario");
        setLocation(new java.awt.Point(600, 300));
        setMaximumSize(new java.awt.Dimension(600, 600));
        setMinimumSize(new java.awt.Dimension(10, 10));
        setPreferredSize(new java.awt.Dimension(600, 600));
        getContentPane().setLayout(null);

        btnBuscarTxt.setText("Buscar txt");
        btnBuscarTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarTxtActionPerformed(evt);
            }
        });
        getContentPane().add(btnBuscarTxt);
        btnBuscarTxt.setBounds(30, 160, 150, 24);

        jLabel1.setText("Nombre del archivo");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 70, 140, 18);
        getContentPane().add(txtNombreArchivo);
        txtNombreArchivo.setBounds(30, 110, 140, 24);

        lblGenerar.setFont(new java.awt.Font("Liberation Sans", 0, 20)); // NOI18N
        lblGenerar.setText("Generar archivos de libro diario");
        getContentPane().add(lblGenerar);
        lblGenerar.setBounds(30, 20, 310, 24);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(0, 200, 600, 20);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarTxtActionPerformed
        
        //Creamos una instancia de la clase Archivo
        Archivo arch = new Archivo();
        String ruta = arch.buscarArchivoTxt();
        //Creamos una instancia de la clase LibroDiario
        LibroDiario ld = new LibroDiario();
        //Creamos una instancia de la clase Transacciones
        Transacciones t = new Transacciones();
        this.nombreArchivo = txtNombreArchivo.getText();
        MenuLibroDiario mld = new MenuLibroDiario();
        if(this.nombreArchivo.equals("")){
            JOptionPane.showMessageDialog(rootPane, "No se ha ingresado como se llamara el archivo", "Nombre de archivo no ingresado", JOptionPane.ERROR_MESSAGE);
        }else{
            mld.setNombreArchivo(this.nombreArchivo);
        }
        /*
         Estamos recuperando los siguientes datos con la finalidad
         de ingresarlos a la lista de transacciones
         */
        int[] nums = arch.obtenerNumeroDeCuenta(ruta); //Contiene 10 numeros de cuenta
        int[] cheques = arch.obtenerNumeroDeCheque(ruta); //Contiene 10 numeros de cheque
        String[] fechas = arch.obtenerFechas(ruta); //Contiene 10 fechas
        String[] descripciones = arch.obtenerDescripcion(ruta); //Contiene 10 descripciones
        double[] cargosOAbonos = arch.obtenerCargoOAbono(ruta); //Contiene 10 cargos o abonos
        try {
            //Ingresamos los datos al libro diario
            arch.agregarLibroDiario(mld, ld, nums, cheques, fechas, descripciones, cargosOAbonos);
            //Ingresamos los datos al historial de transacciones
            arch.agregarTransacciones(mld, t, cheques, cheques, fechas, descripciones, cargosOAbonos);
            //Ingresamos los datos del libro diario al libro mayor gracias a las transacciones
            
        } catch (IOException ex) {
            Logger.getLogger(MenuLibroDiario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnBuscarTxtActionPerformed

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
            java.util.logging.Logger.getLogger(MenuLibroDiario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuLibroDiario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuLibroDiario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuLibroDiario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuLibroDiario().setVisible(true);
            }
        });
    }

    public String getNombreArchivo() {
        return this.nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblGenerar;
    private javax.swing.JTextField txtNombreArchivo;
    // End of variables declaration//GEN-END:variables
}