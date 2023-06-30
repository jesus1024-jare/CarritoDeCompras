/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carritodecompras;

/**
 *
 * @author jesus
 */
public class nodo {

    private String modelo, marca, matricula;
    private float precio;
    private int unidades;

    nodo sig, ant;

    public nodo(String modelo, String marca, String matricula, float precio, int unidades) {
        this.modelo = modelo;
        this.marca = marca;
        this.matricula = matricula;
        this.precio = precio;
        this.unidades = unidades;
        sig = null;
        ant = null;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public float getPrecio() {
        return precio;
    }

    public nodo getAnt() {
        return ant;
    }

    public void setAnt(nodo ant) {
        this.ant = ant;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public nodo getSig() {
        return sig;
    }

    public void setSig(nodo sig) {
        this.sig = sig;
    }
}
