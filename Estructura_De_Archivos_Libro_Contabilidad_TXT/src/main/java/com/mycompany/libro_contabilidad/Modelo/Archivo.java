package com.mycompany.libro_contabilidad.Modelo;

import com.mycompany.MenuLibroDiario.MenuLibroDiario;
import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author aspxe
 */
public class Archivo extends javax.swing.JFrame {

    RandomAccessFile file;

    public String buscarArchivoTxt() {
        int seleccion;
        String rutaArchivoLibroDiario = "";

        JFileChooser archivo = new JFileChooser();
        archivo.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        FileNameExtensionFilter archivoTxt = new FileNameExtensionFilter("Archivos de texto del tipo .txt", "txt");
        archivo.setFileFilter(archivoTxt);

        seleccion = archivo.showOpenDialog(this);
        if (seleccion != JFileChooser.CANCEL_OPTION) {
            File file = archivo.getSelectedFile();

            if (file == null || file.getName().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Carga de archivo cancelada...", "En espera...", JOptionPane.ERROR_MESSAGE);
            } else {
                rutaArchivoLibroDiario = file.getAbsolutePath();
                JOptionPane.showMessageDialog(this, "Su archivo se encuentra en la siguiente ruta: " + file.getAbsolutePath() + "\n", "Ruta del archivo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        return rutaArchivoLibroDiario;
    }

    public int[] obtenerNumeroDeCuenta(String ruta) {
        //Leemos el archivo, ingresamos los numeros de cuenta a un arreglo y regresamos el arreglo
        //Guarda hasta 10 numeros de cuenta diferentes

        String contenido = "";
        String numeroDeCuenta = "";
        int numerosDeCuenta[] = new int[10];
        int posicion = 0;

        try {
            File file = new File(ruta);
            if (file.exists()) {
                file.createNewFile();
            }
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while (br.ready()) {
                contenido += br.readLine();
                numeroDeCuenta = contenido.substring(0, 3);
                numerosDeCuenta[posicion] = Integer.parseInt(numeroDeCuenta);
                if (posicion <= 10) {
                    posicion += 1;
                }
                contenido = "";
            }
            br.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e, "Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
        }
        return numerosDeCuenta;
    }

    public int[] obtenerNumeroDeCheque(String ruta) {
        String contenido = "";
        String numeroDeCheque = "";
        int numerosDeCheque[] = new int[10];
        int posicion = 0;
        try {
            File file = new File(ruta);
            if (file.exists()) {
                file.createNewFile();
            }
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while (br.ready()) {
                contenido += br.readLine();
                numeroDeCheque = contenido.substring(7, 11);
                numerosDeCheque[posicion] = Integer.parseInt(numeroDeCheque);
                if (posicion <= 10) {
                    posicion += 1;
                }
                contenido = "";
            }
            br.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e, "Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
        }
        return numerosDeCheque;
    }

    public String[] obtenerFechas(String ruta) {
        String contenido = "";
        String fecha = "";
        String[] fechas = new String[10];
        int posicion = 0;
        try {
            File file = new File(ruta);
            if (file.exists()) {
                file.createNewFile();
            }
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while (br.ready()) {
                contenido += br.readLine();
                fecha = contenido.substring(13, 21);
                fechas[posicion] = fecha;
                if (posicion <= 10) {
                    posicion += 1;
                }
                contenido = "";
            }
            br.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e, "Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
        }
        return fechas;
    }

    public String[] obtenerDescripcion(String ruta) {
        String contenido = "";
        String descripcion = "";
        String[] descripciones = new String[10];
        int posicion = 0;
        try {
            File file = new File(ruta);
            if (file.exists()) {
                file.createNewFile();
            }
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while (br.ready()) {
                contenido += br.readLine();
                descripcion = contenido.substring(24, 53);
                descripciones[posicion] = descripcion;
                if (posicion <= 10) {
                    posicion += 1;
                }
                contenido = "";
            }
            br.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e, "Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
        }
        return descripciones;
    }

    public double[] obtenerCargoOAbono(String ruta) {
        String contenido = "";
        String cargoOAbono = "";
        double[] cargosOAbonos = new double[10];
        int posicion = 0;
        try {
            File file = new File(ruta);
            if (file.exists()) {
                file.createNewFile();
            }
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while (br.ready()) {
                contenido += br.readLine();
                cargoOAbono = contenido.substring(53, 64);
                if (cargoOAbono.contains("")) {
                    cargoOAbono.replace("", "0");
                }
                cargosOAbonos[posicion] = Double.parseDouble(cargoOAbono);
                if (posicion <= 10) {
                    posicion += 1;
                }
                contenido = "";
            }
            br.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e, "Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
        }
        return cargosOAbonos;
    }

    //Metodo que genera un archivo con los datos del libro diario
    public void agregarLibroDiario(MenuLibroDiario mld, LibroDiario ld, int[] cuentasNums, int[] chequesNums, String[] fechas, String[] descripciones, double[] cargosOAbonos) throws IOException {
        try {
            for (int i = 0; i < 10; i++) {
                //Creamos una instancia de RAF
                this.file = new RandomAccessFile(mld.getNombreArchivo() + "-" + i + "-" + "LD" + ".dat", "rw");
                //Posicionamos el puntero al final del archivo
                this.file.seek(this.file.length());
                //Todos los arreglos contienen 10 datos, por lo que haremos 10 ciclos para generar los 10 registros
                ld.setCuentaNum(cuentasNums[i]);
                ld.setChequeNum(chequesNums[i]);
                ld.setFecha(fechas[i]);
                ld.setDescripcion(descripciones[i]);
                ld.setCargoOAbono(cargosOAbonos[i]);
                ld.grabarLibroDiario(this.file);
                this.file.close();
            }
            JOptionPane.showMessageDialog(rootPane, "Registros agregados con exito", "Registros", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e, "Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Metodo que genera un archivo con los datos del libro diario, y los ingresa al historial de transacciones
    public void agregarTransacciones(MenuLibroDiario mld, Transacciones t, int[] cuentasNums, int[] chequesNums, String[] fechas, String[] descripciones, double[] cargosOAbonos) throws IOException {
        try {
            for (int i = 0; i < 10; i++) {
                //Creamos una instancia de RAF
                this.file = new RandomAccessFile(mld.getNombreArchivo() + "-" + i + "-" + "T"+".dat", "rw");
                //Posicionamos el puntero al final del archivo
                this.file.seek(this.file.length());
                //Todos los arreglos contienen 10 datos, por lo que haremos 10 ciclos para generar los 10 registros
                t.setCuentaNum(cuentasNums[i]);
                t.setChequeNum(chequesNums[i]);
                t.setFecha(fechas[i]);
                t.setDescripcion(descripciones[i]);
                t.setCargoOAbono(cargosOAbonos[i]);
                t.grabarTransacciones(this.file);
                this.file.close();
            }
            JOptionPane.showMessageDialog(rootPane, "Registros agregados con exito", "Registros", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e, "Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //Leer informacion de un archivo .dat, el usuario puede buscar los archivos
    public void reporte() throws IOException {
        
        int seleccion;
        String nombreArchivo = "";

        JFileChooser archivo = new JFileChooser();
        archivo.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        FileNameExtensionFilter archivoTxt = new FileNameExtensionFilter("Archivos de texto del tipo .dat", "dat");
        archivo.setFileFilter(archivoTxt);

        seleccion = archivo.showOpenDialog(this);
        if (seleccion != JFileChooser.CANCEL_OPTION) {
            File file = archivo.getSelectedFile();

            if (file == null || file.getName().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Carga de archivo cancelada...", "En espera...", JOptionPane.ERROR_MESSAGE);
            } else {
                nombreArchivo = file.getName();
                JOptionPane.showMessageDialog(this, "Su archivo se encuentra en la siguiente ruta: " + file.getAbsolutePath() + "\n", "Ruta del archivo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
        this.file = new RandomAccessFile(nombreArchivo, "r");
        LibroDiario ld = new LibroDiario();

        String mensaje = "";

        //Recorrer el archivo
        while (this.file.getFilePointer() < this.file.length()) {//mientras no llegue al fineal
            ld.leerLibroDiario(this.file);
            mensaje = mensaje + " " + ld.getCuentaNum() + " " + ld.getChequeNum() + " " + ld.getFecha() + " " + ld.getDescripcion() + " " + ld.getCargoOAbono() + "\n";
        }

        JOptionPane.showMessageDialog(null, mensaje);
        this.file.close();
    }
    
    /*
    Leyendo los archivos .dat generado por la clase de transacciones, recuperamos la informacion de 
    numero de cuenta, fecha, y los cargos y abonos, de manera que asi se generara el total 
    del mes para el libro mayor.
    */
    

}
