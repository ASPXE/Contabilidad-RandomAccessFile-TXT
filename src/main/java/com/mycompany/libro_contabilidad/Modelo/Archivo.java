package com.mycompany.libro_contabilidad.Modelo;

import com.mycompany.MenuLibroDiario.MenuLibroDiario;
import com.mycompany.MenuLibroMayor.MenuLibroMayor;
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
        //Agregamos la informacion del txt a registros dat con informacion del libro diario 
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

        /*
        En base al contenido del archivo txt, revisamos cuales son los meses 
        registrados con movimientos para poder hacer los totales de cada mes
        y agregarlos al libro mayor junto con su numero de cuenta y titulo
        de cuenta.
         */
    }
    
    //Metodo para agregar libro mayor
    public void agregarLibroMayor(LibroMayor lm, String tituloCuenta, int cuenta, double totalMesEnero, double totalMesFebrero, double totalMesMarzo, double totalMesAbril, double totalMesMayo, double totalMesJunio, double totalMesJulio, double totalMesAgosto, double totalMesSeptiembre, double totalMesOctubre, double totalMesNoviembre, double totalMesDiciembre) throws IOException{
        try{
            this.file = new RandomAccessFile(tituloCuenta, "rw");
            this.file.seek(this.file.length());
            lm.setNumeroCuenta(cuenta);
            lm.setTituloCuenta(tituloCuenta);
            lm.setTotalEnero(totalMesEnero);
            lm.setTotalFebrero(totalMesFebrero);
            lm.setTotalMarzo(totalMesMarzo);
            lm.setTotalAbril(totalMesAbril);
            lm.setTotalMayo(totalMesMayo);
            lm.setTotalJunio(totalMesJunio);
            lm.setTotalJulio(totalMesJulio);
            lm.setTotalAgosto(totalMesAgosto);
            lm.setTotalSeptiembre(totalMesSeptiembre);
            lm.setTotalOctubre(totalMesOctubre);
            lm.setTotalNoviembre(totalMesNoviembre);
            lm.setTotalDiciembre(totalMesDiciembre);
            lm.grabarLibroMayor(this.file);
            this.file.close();
            JOptionPane.showMessageDialog(rootPane, "Libro mayor creado", "Libro mayor", JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            System.out.println("Ha ocurrido un error "+e);
        }
    }

    //Metodo que genera un archivo con los datos del libro diario, y los ingresa al historial de transacciones
    public void agregarTransacciones(MenuLibroDiario mld, Transacciones t, int[] cuentasNums, int[] chequesNums, String[] fechas, String[] descripciones, double[] cargosOAbonos) throws IOException {
        try {
            for (int i = 0; i < 10; i++) {
                //Creamos una instancia de RAF
                this.file = new RandomAccessFile(mld.getNombreArchivo() + "-" + i + "-" + "T" + ".dat", "rw");
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
    public void reporteLibroDiario() throws IOException {

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
    
    public void reporteLibroMayor() throws IOException{
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
        LibroMayor lm = new LibroMayor();

        String mensaje = "";

        //Recorrer el archivo
        while (this.file.getFilePointer() < this.file.length()) {//mientras no llegue al fineal
            lm.leerLibroMayor(this.file);
            mensaje = mensaje + " " + lm.getNumeroCuenta() + " " + lm.getTituloCuenta()+ " " + lm.getTotalEnero() + " " + lm.getTotalFebrero() + " " + lm.getTotalMarzo() + " " + lm.getTotalAbril() + " " + lm.getTotalMayo() + " " + lm.getTotalJunio() + " " + lm.getTotalJulio() + " " + lm.getTotalAgosto() + " " + lm.getTotalSeptiembre() + " " + lm.getTotalOctubre() + " " + lm.getTotalNoviembre() + " " + lm.getTotalDiciembre() + "\n";
        }

        JOptionPane.showMessageDialog(null, mensaje);
        this.file.close();
    }

    /*
    Leyendo los archivos .dat generado por la clase de transacciones, recuperamos la informacion de 
    numero de cuenta, fecha, y los cargos y abonos, de manera que asi se generara el total 
    del mes para el libro mayor.
     */
    //Metodo que valida si en el arreglo de fechas se encuentra un mes en especifico y regresa el monto del mes
    public double totalMes(String[] fechas, int numeroMes, double[] cargoOAbono) {
        int[] indices = new int[10]; //Arreglo para guardar en que indice del arreglo de fechas se encontro el mes solicitado
        int r = 0;
        double totalMes = 0;
        switch (numeroMes) {
            case 1:
                //Recorremos el arreglo de fechas
                for (r = 0; r < 10; r++) {
                    if (fechas[r].substring(3, 5).contains("01")) {
                        indices[r] = r; //Guardamos la posicion del arreglo en la que aparece
                        if (cargoOAbono[r] < 0) {
                            //No hacemos nada, en este caso no nos interesan las cantidades menores a cero
                        } else if (cargoOAbono[r] >= 0) {
                            //Tomaremos en cuenta las cantidades mayores o iguales a cero
                            totalMes += cargoOAbono[r];
                        }
                    } else {
                        /*
                        ***Esta parte del codigo la borrare despues***
                        Si en cualquier posicion del arreglo aparece el numero 10, significa que 
                        en esa posicion no se encontro el mes de enero.
                         */
                        indices[r] = 10;
                        totalMes += 0;
                    }
                }
                break;
            case 2:
                for (r = 0; r < 10; r++) {
                    if (fechas[r].substring(3, 5).contains("02")) {
                        indices[r] = r;
                        if (cargoOAbono[r] < 0) {
                        } else if (cargoOAbono[r] >= 0) {
                            totalMes += cargoOAbono[r];
                        }
                    } else {
                        indices[r] = 10;
                        totalMes += 0;
                    }
                }
                break;
            case 3:
                for (r = 0; r < 10; r++) {
                    if (fechas[r].substring(3, 5).contains("03")) {
                        indices[r] = r;
                        if (cargoOAbono[r] < 0) {
                        } else if (cargoOAbono[r] >= 0) {
                            totalMes += cargoOAbono[r];
                        }
                    } else {
                        indices[r] = 10;
                        totalMes += 0;
                    }
                }
                break;
            case 4:
                for (r = 0; r < 10; r++) {
                    if (fechas[r].substring(3, 5).contains("04")) {
                        indices[r] = r;
                        if (cargoOAbono[r] < 0) {
                        } else if (cargoOAbono[r] >= 0) {
                            totalMes += cargoOAbono[r];
                        }
                    } else {
                        indices[r] = 10;
                        totalMes += 0;
                    }
                }
                break;
            case 5:
                for (r = 0; r < 10; r++) {
                    if (fechas[r].substring(3, 5).contains("05")) {
                        indices[r] = r;
                        if (cargoOAbono[r] < 0) {
                        } else if (cargoOAbono[r] >= 0) {
                            totalMes += cargoOAbono[r];
                        }
                    } else {
                        indices[r] = 10;
                        totalMes += 0;
                    }
                }
                break;
            case 6:
                for (r = 0; r < 10; r++) {
                    if (fechas[r].substring(3, 5).contains("06")) {
                        indices[r] = r;
                        if (cargoOAbono[r] < 0) {
                        } else if (cargoOAbono[r] >= 0) {
                            totalMes += cargoOAbono[r];
                        }
                    } else {
                        indices[r] = 10;
                        totalMes += 0;
                    }
                }
                break;
            case 7:
                for (r = 0; r < 10; r++) {
                    if (fechas[r].substring(3, 5).contains("07")) {
                        indices[r] = r;
                        if (cargoOAbono[r] < 0) {
                        } else if (cargoOAbono[r] >= 0) {
                            totalMes += cargoOAbono[r];
                        }
                    } else {
                        indices[r] = 10;
                        totalMes += 0;
                    }
                }
                break;
            case 8:
                for (r = 0; r < 10; r++) {
                    if (fechas[r].substring(3, 5).contains("08")) {
                        indices[r] = r;
                        if (cargoOAbono[r] < 0) {
                        } else if (cargoOAbono[r] >= 0) {
                            totalMes += cargoOAbono[r];
                        }
                    } else {
                        indices[r] = 10;
                        totalMes += 0;
                    }
                }
                break;
            case 9:
                for (r = 0; r < 10; r++) {
                    if (fechas[r].substring(3, 5).contains("09")) {
                        indices[r] = r;
                        if (cargoOAbono[r] < 0) {
                        } else if (cargoOAbono[r] >= 0) {
                            totalMes += cargoOAbono[r];
                        }
                    } else {
                        indices[r] = 10;
                        totalMes += 0;
                    }
                }
                break;
            case 10:
                for (r = 0; r < 10; r++) {
                    if (fechas[r].substring(3, 5).contains("10")) {
                        indices[r] = r;
                        if (cargoOAbono[r] < 0) {
                        } else if (cargoOAbono[r] >= 0) {
                            totalMes += cargoOAbono[r];
                        }
                    } else {
                        indices[r] = 10;
                        totalMes += 0;
                    }
                }
                break;
            case 11:
                for (r = 0; r < 10; r++) {
                    if (fechas[r].substring(3, 5).contains("11")) {
                        indices[r] = r;
                        if (cargoOAbono[r] < 0) {
                        } else if (cargoOAbono[r] >= 0) {
                            totalMes += cargoOAbono[r];
                        }
                    } else {
                        indices[r] = 10;
                        totalMes += 0;
                    }
                }
                break;
            case 12:
                for (r = 0; r < 10; r++) {
                    if (fechas[r].substring(3, 5).contains("12")) {
                        indices[r] = r;
                        if (cargoOAbono[r] < 0) {
                        } else if (cargoOAbono[r] >= 0) {
                            totalMes += cargoOAbono[r];
                        }
                    } else {
                        indices[r] = 10;
                        totalMes += 0;
                    }
                }
                break;
            default:
                JOptionPane.showMessageDialog(rootPane, "No existe el mes " + numeroMes + " en el calendario", "Numero de mes no valido", JOptionPane.ERROR_MESSAGE);
                break;
        }
        /*
        ***ESTO LO BORRARE DESPUES***
        Imprimimos en que indices en los que se encontro el mes y en cuales no
        */
        for(r = 0; r < 10; r ++){
            System.out.println(indices[r]);
        }
        return totalMes;
    }
    
    //Metodo que valida si el numero de cuenta ingresado existe en el txt
    public int numeroDeCuenta(int[] nums, int numeroCuenta){
        int r = 0;
        int cuenta = 0;
        for(r = 0; r < 10; r ++){
            if(nums[r] == numeroCuenta){
                cuenta = nums[r];
                break;
            }
        }
        return cuenta;
    }
    
    //Metodo que solicita al usuario que ingrese el titulo de la cuenta
    public String tituloCuenta(MenuLibroMayor mlm){
        return mlm.getTituloCuenta();
    }

}
