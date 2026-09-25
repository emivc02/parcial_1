package co.edu.uniquindio.poo.parcial.model;

import java.util.List;

public class GestorClientes {

    private final gimnasio gym;

    public GestorClientes() {
        this.gym = gimnasio.getInstance();
    }

    /**
     * metodo que retorna la lista de clientes
     * @return
     */
    public List<Cliente> listarClientes() {
        return gym.getListaClientes();
    }

    /**
     * metodo que permite registrar clientes
     * @param cliente
     * @throws Exception
     */
    public void registrar(Cliente cliente) throws Exception {
        if (buscarPorDocumento(cliente.getDocumento()) != null) {
            throw new Exception("El cliente con el documento dado ya está registrado.");
        }
        gym.getListaClientes().add(cliente);
    }

    /**
     * metodo que permite buscar cliente en base a documento
     * @param documento
     * @return
     */
    public Cliente buscarPorDocumento(String documento) {
        for (Cliente c : gym.getListaClientes()) {
            if (c.getDocumento().equals(documento)) {
                return c;
            }
        }
        return null;
    }

    /**
     * metodo que te permite actualizar clientes
     * @param documento
     * @param nombre
     * @param telefono
     * @param correo
     * @param edad
     * @return
     */
    public boolean actualizar(String documento, String nombre, String telefono, String correo, int edad) {
        Cliente cliente = buscarPorDocumento(documento);
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
     * metodo que permite eliminar cliente en base a documento
     * @param documento
     * @return
     */
    public boolean eliminar(String documento) {
        Cliente cliente = buscarPorDocumento(documento);
        if (cliente != null) {
            gym.getListaClientes().remove(cliente);
            return true;
        }
        return false;
    }

    /**
     * metodo que permite buscar cliente en base a telefono
     * @param telefono
     * @return
     */
    public Cliente buscarPorTelefono(String telefono) {
        for (Cliente c : gym.getListaClientes()) {
            if (c.getTelefono().equals(telefono)) {
                return c;
            }
        }
        return null;
    }

    /**
     * metodo para saber si un numero es perfecto
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
}
