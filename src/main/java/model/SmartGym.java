package model;

import java.util.ArrayList;
import java.util.List;

public class SmartGym {

    private static SmartGym instancia;

    private String nombreComercial;
    private String nit;
    private String direccion;
    private String telefono;
    private String correo;
    private String paginaWeb;

    private List<Persona> listaPersonas = new ArrayList<>();
    private List<PlanEntrenamiento> listaPlanes = new ArrayList<>();
    private List<ServicioAdicional> listaServicios = new ArrayList<>();
    private List<Inscripcion> listaInscripciones = new ArrayList<>();

    public SmartGym(String nombreComercial, String nit, String direccion, String telefono, String correo, String paginaWeb, List<Persona> listaClientes, List<PlanEntrenamiento> listaPlanes, List<ServicioAdicional> listaServicios, List<Inscripcion> listaInscripciones) {
        this.nombreComercial = nombreComercial;
        this.nit = nit;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.paginaWeb = paginaWeb;
        this.listaPersonas = listaPersonas;
        this.listaPlanes = listaPlanes;
        this.listaServicios = listaServicios;
        this.listaInscripciones = listaInscripciones;
    }

   //faalta el get de instancia

    public static void setInstancia(SmartGym instancia) {
        SmartGym.instancia = instancia;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public List<Persona> getListaPersonas() {
        return listaPersonas;
    }

    public void setListaPersonas(List<Persona> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    public List<PlanEntrenamiento> getListaPlanes() {
        return listaPlanes;
    }

    public void setListaPlanes(List<PlanEntrenamiento> listaPlanes) {
        this.listaPlanes = listaPlanes;
    }

    public List<ServicioAdicional> getListaServicios() {
        return listaServicios;
    }

    public void setListaServicios(List<ServicioAdicional> listaServicios) {
        this.listaServicios = listaServicios;
    }

    public List<Inscripcion> getListaInscripciones() {
        return listaInscripciones;
    }

    public void setListaInscripciones(List<Inscripcion> listaInscripciones) {
        this.listaInscripciones = listaInscripciones;
    }

    @Override
    public String toString() {
        return "SmartGym{" +
                "nombreComercial='" + nombreComercial + '\'' +
                ", nit='" + nit + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", paginaWeb='" + paginaWeb + '\'' +
                ", listaPersonas=" + listaPersonas +
                ", listaPlanes=" + listaPlanes +
                ", listaServicios=" + listaServicios +
                ", listaInscripciones=" + listaInscripciones +
                '}';
    }



}
