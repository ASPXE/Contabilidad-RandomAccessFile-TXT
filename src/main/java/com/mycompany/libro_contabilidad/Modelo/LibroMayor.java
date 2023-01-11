package com.mycompany.libro_contabilidad.Modelo;

import java.io.*;

/**
 *
 * @author aspxe
 */
public class LibroMayor {
    
    private int numeroCuenta;
    private String tituloCuenta;
    private double totalEnero;
    private double totalFebrero;
    private double totalMarzo;
    private double totalAbril;
    private double totalMayo;
    private double totalJunio;
    private double totalJulio;
    private double totalAgosto;
    private double totalSeptiembre;
    private double totalOctubre;
    private double totalNoviembre;
    private double totalDiciembre;
    
    public LibroMayor(){
        
    }

    public LibroMayor(int numeroCuenta, String tituloCuenta, double totalEnero, double totalFebrero, double totalMarzo, double totalAbril, double totalMayo, double totalJunio, double totalJulio, double totalAgosto, double totalSeptiembre, double totalOctubre, double totalNoviembre, double totalDiciembre) {
        this.numeroCuenta = numeroCuenta;
        this.tituloCuenta = tituloCuenta;
        this.totalEnero = totalEnero;
        this.totalFebrero = totalFebrero;
        this.totalMarzo = totalMarzo;
        this.totalAbril = totalAbril;
        this.totalMayo = totalMayo;
        this.totalJunio = totalJunio;
        this.totalJulio = totalJulio;
        this.totalAgosto = totalAgosto;
        this.totalSeptiembre = totalSeptiembre;
        this.totalOctubre = totalOctubre;
        this.totalNoviembre = totalNoviembre;
        this.totalDiciembre = totalDiciembre;
    }
    
    //Metodo que recibe el archivo en el que se va a grabar la informacion
    public void grabarLibroMayor(RandomAccessFile file) throws IOException{
        /**
         * El orden para grabar el registro es el mismo orden que estan
         * declarados. Registro de longitud fija. Campos de longitud fija
         *
         */
        
        //Grabamos numeroCuenta
        file.writeInt(this.numeroCuenta);
        //Grabamos tituloCuenta, convertimos el string a bytes
        byte tituloCuentaBytes[] = new byte[30]; //Longitud fija de 30
        this.tituloCuenta.getBytes(0, this.tituloCuenta.length(), tituloCuentaBytes, 0);
        file.write(tituloCuentaBytes);
        //Grabamos totalEnero
        file.writeDouble(this.totalEnero);
        //Grabamos totalFebrero
        file.writeDouble(this.totalFebrero);
        //Grabamos totalMarzo
        file.writeDouble(this.totalMarzo);
        //Grabamos totalAbril
        file.writeDouble(this.totalAbril);
        //Grabamos totalMayo
        file.writeDouble(this.totalMayo);
        //Grabamos totalJunio
        file.writeDouble(this.totalJunio);
        //Grabamos totalJulio
        file.writeDouble(this.totalJulio);
        //Grabamos totalAgosto
        file.writeDouble(this.totalAgosto);
        //Grabamos totalSeptiembre
        file.writeDouble(this.totalSeptiembre);
        //Grabamos totalOctubre
        file.writeDouble(this.totalOctubre);
        //Grabamos totalNoviembre
        file.writeDouble(this.totalNoviembre);
        //Grabamos totalDiciembre
        file.writeDouble(this.totalDiciembre);
        
    }
    
    //Metodo para recuperar la informaciom grabada en el archivo 
    public void leerLibroMayor(RandomAccessFile file) throws IOException{
        
        //Leemos numeroCuenta
        this.numeroCuenta = file.readInt();
        //Leemos tituloCuenta, recuperamos los bytes y reconstruimos en un string
        byte tituloCuentaBytes[] = new byte[30];
        file.readFully(tituloCuentaBytes);
        this.tituloCuenta = new String(tituloCuentaBytes);
        //Leemos totalEnero
        this.totalEnero = file.readDouble();
        //Leemos totalFebrero
        this.totalFebrero = file.readDouble();
        //Leemos totalMarzo
        this.totalMarzo = file.readDouble();
        //Leemos totalAbril
        this.totalAbril = file.readDouble();
        //Leemos totalMayo
        this.totalMayo = file.readDouble();
        //Leemos totalJunio
        this.totalJunio = file.readDouble();
        //Leemos totalJulio
        this.totalJulio = file.readDouble();
        //Leemos totalAgosto
        this.totalAgosto = file.readDouble();
        //Leemos totalSeptiembre
        this.totalSeptiembre = file.readDouble();
        //Leemos totalOctubre
        this.totalOctubre = file.readDouble();
        //Leemos totalNoviembre
        this.totalNoviembre = file.readDouble();
        //Leemos totalDiciembre
        this.totalDiciembre = file.readDouble();
        
    }

    public int getNumeroCuenta() {
        return this.numeroCuenta;
    }

    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTituloCuenta() {
        return this.tituloCuenta;
    }

    public void setTituloCuenta(String tituloCuenta) {
        this.tituloCuenta = tituloCuenta;
    }

    public double getTotalEnero() {
        return this.totalEnero;
    }

    public void setTotalEnero(double totalEnero) {
        this.totalEnero = totalEnero;
    }

    public double getTotalFebrero() {
        return this.totalFebrero;
    }

    public void setTotalFebrero(double totalFebrero) {
        this.totalFebrero = totalFebrero;
    }

    public double getTotalMarzo() {
        return this.totalMarzo;
    }

    public void setTotalMarzo(double totalMarzo) {
        this.totalMarzo = totalMarzo;
    }

    public double getTotalAbril() {
        return this.totalAbril;
    }

    public void setTotalAbril(double totalAbril) {
        this.totalAbril = totalAbril;
    }

    public double getTotalMayo() {
        return this.totalMayo;
    }

    public void setTotalMayo(double totalMayo) {
        this.totalMayo = totalMayo;
    }

    public double getTotalJunio() {
        return this.totalJunio;
    }

    public void setTotalJunio(double totalJunio) {
        this.totalJunio = totalJunio;
    }

    public double getTotalJulio() {
        return this.totalJulio;
    }

    public void setTotalJulio(double totalJulio) {
        this.totalJulio = totalJulio;
    }

    public double getTotalAgosto() {
        return this.totalAgosto;
    }

    public void setTotalAgosto(double totalAgosto) {
        this.totalAgosto = totalAgosto;
    }

    public double getTotalSeptiembre() {
        return this.totalSeptiembre;
    }

    public void setTotalSeptiembre(double totalSeptiembre) {
        this.totalSeptiembre = totalSeptiembre;
    }

    public double getTotalOctubre() {
        return this.totalOctubre;
    }

    public void setTotalOctubre(double totalOctubre) {
        this.totalOctubre = totalOctubre;
    }

    public double getTotalNoviembre() {
        return this.totalNoviembre;
    }

    public void setTotalNoviembre(double totalNoviembre) {
        this.totalNoviembre = totalNoviembre;
    }

    public double getTotalDiciembre() {
        return this.totalDiciembre;
    }

    public void setTotalDiciembre(double totalDiciembre) {
        this.totalDiciembre = totalDiciembre;
    }
    

}
