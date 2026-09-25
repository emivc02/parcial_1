package model;

import java.util.List;

public class ClienteLogica {

    private gimnasio gimnasio;


    public ClienteLogica(gimnasio gimnasio) {
        this.gimnasio = gimnasio;
    }
    /** Agrega un cliente. */
    public void agregarCliente(Cliente cliente) {
        gimnasio.getListaClientes().add(cliente);
    }

    /** Busca un cliente por documento; devuelve null si no existe. */
    public Cliente buscarCliente(String documento) {
        for (Cliente cliente : gimnasio.getListaClientes()) {
            if (cliente.getDocumento().equals(documento)) {
                return cliente;
            }
        }
        return null;
    }

    /** Actualiza los datos de un cliente. */
    public void actualizarCliente(String documento, String nombre, String telefono, String correo, int edad) {
        Cliente cliente = buscarCliente(documento);
        cliente.setNombre(nombre);
        cliente.setTelefono(telefono);
        cliente.setCorreo(correo);
        cliente.setEdad(edad);
    }

    /** Elimina un cliente por documento. */
    public void eliminarCliente(String documento) {
        gimnasio.getListaClientes().removeIf(cliente -> cliente.getDocumento().equals(documento));
    }

    /** Devuelve todos los clientes. */
    public List<Cliente> listarClientes() {
        return gimnasio.getListaClientes();
    }

    /** Busca un cliente por su número de teléfono; devuelve null si no existe. */
    public Cliente buscarClientePorTelefono(String telefono) {
        for (Cliente cliente : gimnasio.getListaClientes()) {
            if (cliente.getTelefono().equals(telefono)) {
                return cliente;
            }
        }
        return null;
    }

    /** Indica si un número es perfecto (la suma de sus divisores es igual al número). */
    public boolean esNumeroPerfecto(long numero) {
        long suma = 1;
        for (long i = 2; i * i <= numero; i++) {
            if (numero % i == 0) {
                suma += i + (numero / i == i ? 0 : numero / i);
            }
        }
        return numero > 1 && suma == numero;
    }
}
