package model;

public class GestorClientes {

    private final gimnasio gym;

    public GestorClientes() {
        this.gym = gimnasio.getInstance();
    }

    public void registrar(Cliente cliente) throws Exception {
        if (buscarPorDocumento(cliente.getDocumento()) != null) {
            throw new Exception("El cliente con el documento dado ya está registrado.");
        }
        gym.getListaClientes().add(cliente);
    }

    public Cliente buscarPorDocumento(String documento) {
        for (Cliente c : gym.getListaClientes()) {
            if (c.getDocumento().equals(documento)) {
                return c;
            }
        }
        return null;
    }

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

    public boolean eliminar(String documento) {
        Cliente cliente = buscarPorDocumento(documento);
        if (cliente != null) {
            gym.getListaClientes().remove(cliente);
            return true;
        }
        return false;
    }

    public Cliente buscarPorTelefono(String telefono) {
        for (Cliente c : gym.getListaClientes()) {
            if (c.getTelefono().equals(telefono)) {
                return c;
            }
        }
        return null;
    }

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
