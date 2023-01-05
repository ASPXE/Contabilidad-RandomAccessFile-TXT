package com.mycompany.libro_contabilidad.Modelo;

import java.io.*;

/**
 *
 * @author aspxe
 */
public class Transacciones {
    
    private int cuentaNum;
    private int chequeNum;
    private String fecha;
    private String descripcion;
    private double cargoOAbono;
    
    public Transacciones(){
        
    }
    
    public Transacciones(int cuentaNum, int chequeNum, String fecha, String descripcion, double cargoOAbono){
        this.cuentaNum = cuentaNum;
        this.chequeNum = chequeNum;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.cargoOAbono = cargoOAbono;
    }
    
    //Metodo que graba la informacion en el archivo proporcionado
    public void grabarTransacciones(RandomAccessFile file) throws IOException{
        /**
         * El orden para grabar el registro es el mismo orden que estan
         * declarados. Registro de longitud fija. Campos de longitud fija
         *
         */
        //Escribimos cuentaNum
        file.writeInt(this.cuentaNum);
        
        //Escribimos chequeNum
        file.writeInt(this.chequeNum);
        
        //Escribimos la fecha. Convertimos el string a bytes
        byte fechaBytes[] = new byte[8]; //8 será la longitud fija para la fecha
        this.fecha.getBytes(0, this.fecha.length(), fechaBytes, 0);
        file.write(fechaBytes);
        
        //Escribimos la descripcion. Convertimos el string a bytes
        byte descripcionBytes[] = new byte[29]; //29 será la longitud fija para la descripcion
        this.descripcion.getBytes(0, this.descripcion.length(), descripcionBytes, 0);
        file.write(descripcionBytes);
        
        //Escribimos cargoOAbono
        file.writeDouble(this.cargoOAbono);
    }
    
    //Metodo que lee la informacion del archivo proporcionado
    public void leerTransacciones(RandomAccessFile file) throws IOException{
        /*
        Es importante leer el archivo en el mismo orden que declaramos las variables de la clase
        */
        
        //Leemos cuentaNum
        this.cuentaNum = file.readInt();
        
        //Leemos chequeNum
        this.chequeNum = file.readInt();
        
        //Leemos fecha, reconstruimos el string a partir de los bytes
        byte fechaByte[] = new byte[8]; //Importante que sea de la misma longitud fija, en este caso 8
        file.readFully(fechaByte);
        this.fecha = new String(fechaByte);
        
        //Leemos descripcion
        byte descripcionByte[] = new byte[29]; //Importante que sea de la misma longitud fija, en este caso 29
        file.readFully(descripcionByte);
        this.descripcion = new String(descripcionByte);
        
        //Leemos cargoOAbono
        this.cargoOAbono = file.readDouble();
    }

    public int getCuentaNum() {
        return this.cuentaNum;
    }

    public void setCuentaNum(int cuentaNum) {
        this.cuentaNum = cuentaNum;
    }

    public int getChequeNum() {
        return this.chequeNum;
    }

    public void setChequeNum(int chequeNum) {
        this.chequeNum = chequeNum;
    }

    public String getFecha() {
        return this.fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCargoOAbono() {
        return this.cargoOAbono;
    }

    public void setCargoOAbono(double cargoOAbono) {
        this.cargoOAbono = cargoOAbono;
    }
    
}
