package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class gimnasio {

    private static gimnasio instancia;

    private String nombreComercial;
    private String nit;
    private String direccion;
    private String telefono;
    private String correo;
    private String paginaWeb;
    private List<Cliente> listaClientes = new ArrayList<>();
    private List<Entrenador> listaEntrenadores = new ArrayList<>();
    private List<PlanEntrenamiento> listaPlanes = new ArrayList<>();
    private List<ServicioAdicional> listaServicios = new ArrayList<>();
    private List<Inscripcion> listaInscripciones = new ArrayList<>();

    private gimnasio() {

    }

    /**
     * implementacion del patron singleton
     * @return
     */
    public static gimnasio getInstance() {
        if (instancia == null) {
            instancia = new gimnasio();
        }
        return instancia;
    }


    /**
     * crea clientes
     * @param cliente
     */
    public void agregarCliente(Cliente cliente) {
        listaClientes.add(cliente);
    }

    /**
     * metodo que permite leer clientes
     * @param documento
     * @return
     */
    public Cliente buscarClientePorDocumento(String documento) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getDocumento().equals(documento)) {
                return cliente;
            }
        }
        return null;
    }

    /**
     * metodo que permite actualizar clientes
     * @param documento
     * @param nombre
     * @param telefono
     * @param correo
     * @param edad
     * @return
     */
    public boolean actualizarCliente(String documento, String nombre, String telefono, String correo, int edad) {
        Cliente cliente = buscarClientePorDocumento(documento);
        if (cliente != null) {
            cliente.setNombre(nombre);
            cliente.setTelefono(telefono);
            cliente.setCorreo(correo);
            cliente.setEdad(edad);
            return true;
        }
        return false;
    }

    /**
     * metodo que permite eliminar clientes
     * @param documento
     * @return
     */
    public boolean eliminarCliente(String documento) {
        Cliente cliente = buscarClientePorDocumento(documento);
        if (cliente != null) {
            listaClientes.remove(cliente);
            return true;
        }
        return false;
    }

    /**
     * metodo que permite crear entrenadores
     * @param entrenador
     */

    public void agregarEntrenador(Entrenador entrenador) {
        listaEntrenadores.add(entrenador);
    }

    /**
     * metodo que permite leer entrenadores
     * @param documento
     * @return
     */
    public Entrenador buscarEntrenadorPorDocumento(String documento) {
        for (Entrenador entrenador : listaEntrenadores) {
            if (entrenador.getDocumento().equals(documento)) {
                return entrenador;
            }
        }
        return null;
    }

    /**
     * metodo que permite actualizar entrenadores
     * @param documento
     * @param nombre
     * @param telefono
     * @param correo
     * @param especialidad
     * @param tarifaPorSesion
     * @return
     */
    public boolean actualizarEntrenador(String documento, String nombre, String telefono, String correo, String especialidad, double tarifaPorSesion) {
        Entrenador entrenador = buscarEntrenadorPorDocumento(documento);
        if (entrenador != null) {
            entrenador.setNombre(nombre);
            entrenador.setTelefono(telefono);
            entrenador.setCorreo(correo);
            entrenador.setEspecialidad(especialidad);
            entrenador.setTarifaPorSesion(tarifaPorSesion);
            return true;
        }
        return false;
    }

    /**
     * metodo que permite eliminar entrenadores
     * @param documento
     * @return
     */
    public boolean eliminarEntrenador(String documento) {
        Entrenador entrenador = buscarEntrenadorPorDocumento(documento);
        if (entrenador != null) {
            listaEntrenadores.remove(entrenador);
            return true;
        }
        return false;
    }

    /**
     * metodo que permite crear planes de entrenammiento
     * @param plan
     */

    public void agregarPlan(PlanEntrenamiento plan) {
        listaPlanes.add(plan);
    }

    public PlanEntrenamiento buscarPlanPorCodigo(String codigo) {
        for (PlanEntrenamiento plan : listaPlanes) {
            if (plan.getCodigo().equals(codigo)) {
                return plan;
            }
        }
        return null;
    }

    /**
     * Metodo que permite actualizar planes de entrenamiento
     * @param codigo
     * @param nombre
     * @param descripcion
     * @param duracionMeses
     * @param valorMensual
     * @return
     */
    public boolean actualizarPlan(String codigo, String nombre, String descripcion, int duracionMeses, double valorMensual) {
        PlanEntrenamiento plan = buscarPlanPorCodigo(codigo);
        if (plan != null) {
            plan.setNombre(nombre);
            plan.setDescripcion(descripcion);
            plan.setDuracionMeses(duracionMeses);
            plan.setValorMensual(valorMensual);
            return true;
        }
        return false;
    }

    /**
     * metodo que permite eliminar planes de entrenamiento
     * @param codigo
     * @return
     */
    public boolean eliminarPlan(String codigo) {
        PlanEntrenamiento plan = buscarPlanPorCodigo(codigo);
        if (plan != null) {
            listaPlanes.remove(plan);
            return true;
        }
        return false;
    }

    /**
     * metodo que permite agregarServicios adicionales
     * @param servicio
     */

    public void agregarServicio(ServicioAdicional servicio) {
        listaServicios.add(servicio);
    }

    /**
     * metodo que permite leer servicios adicionales
     * @param codigo
     * @return
     */
    public ServicioAdicional buscarServicioPorCodigo(String codigo) {
        for (ServicioAdicional servicio : listaServicios) {
            if (servicio.getCodigo().equals(codigo)) {
                return servicio;
            }
        }
        return null;
    }

    /**
     * metodo que permite actualizar servicios adicionales
     * @param codigo
     * @param nombre
     * @param descripcion
     * @param precio
     * @param disponible
     * @return
     */
    public boolean actualizarServicio(String codigo, String nombre, String descripcion, double precio, boolean disponible) {
        ServicioAdicional servicio = buscarServicioPorCodigo(codigo);
        if (servicio != null) {
            servicio.setNombre(nombre);
            servicio.setDescripcion(descripcion);
            servicio.setPrecio(precio);
            servicio.setDisponible(disponible);
            return true;
        }
        return false;
    }

    /**
     * metodo que permite eliminar servicios adicionales
     * @param codigo
     * @return
     */
    public boolean eliminarServicio(String codigo) {
        ServicioAdicional servicio = buscarServicioPorCodigo(codigo);
        if (servicio != null) {
            listaServicios.remove(servicio);
            return true;
        }
        return false;
    }

    /**
     * metodo que permite agregr inscripciones
     * @param inscripcion
     */

    public void agregarInscripcion(Inscripcion inscripcion) {
        listaInscripciones.add(inscripcion);
    }

    /**
     * metodo que permite leer inscripciones
     * @param id
     * @return
     */
    public Inscripcion buscarInscripcionPorId(String id) {
        for (Inscripcion inscripcion : listaInscripciones) {
            if (inscripcion.getId().equals(id)) {
                return inscripcion;
            }
        }
        return null;
    }

    /**
     * metodo que permite eliminar inscrpciones
     * @param id
     * @return
     */
    public boolean eliminarInscripcion(String id) {
        Inscripcion inscripcion = buscarInscripcionPorId(id);
        if (inscripcion != null) {
            listaInscripciones.remove(inscripcion);
            return true;
        }
        return false;
    }

    //LOGICA DE NEGOCIO

    /**
     * metodo que permite buscar cliente por telefono
     * @param telefono
     * @return
     */
    public Cliente buscarClientePorTelefono(String telefono) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getTelefono().equals(telefono)) {
                return cliente;
            }
        }
        return null;
    }

    /**
     * Metodo que verifica si el numero encontrado es un numero perfecto
     * @param numero
     * @return
     */
    public boolean esNumeroPerfecto(long numero) {
        if (numero <= 1) {
            return false;
        }
        long suma = 0;
        for (long i = 1; i <= numero / 2; i++) {
            if (numero % i == 0) {
                suma += i;
            }
        }
        return suma == numero;
    }


    // setters y getters

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

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public List<Entrenador> getListaEntrenadores() {
        return listaEntrenadores;
    }

    public void setListaEntrenadores(List<Entrenador> listaEntrenadores) {
        this.listaEntrenadores = listaEntrenadores;
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
//toString
    @Override
    public String toString() {
        return "SmartGym{" +
                "nombreComercial='" + nombreComercial + '\'' +
                ", nit='" + nit + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", paginaWeb='" + paginaWeb + '\'' +
                ", listaClientes=" + listaClientes +
                ", listaEntrenadores=" + listaEntrenadores +
                ", listaPlanes=" + listaPlanes +
                ", listaServicios=" + listaServicios +
                ", listaInscripciones=" + listaInscripciones +
                '}';
    }
}
