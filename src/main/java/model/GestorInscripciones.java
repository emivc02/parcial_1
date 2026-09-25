package model;

import java.time.LocalDate;

public class GestorInscripciones {

    private final gimnasio gym;

    public GestorInscripciones() {
        this.gym = gimnasio.getInstance();
    }

    public Inscripcion buscarInscripcionPorId(String id) {
        for (Inscripcion i : gym.getListaInscripciones()) {
            if (i.getId().equals(id)) {
                return i;
            }
        }
        return null;
    }

    public boolean eliminarInscripcion(String id) {
        Inscripcion inscripcion = buscarInscripcionPorId(id);
        if (inscripcion != null) {
            gym.getListaInscripciones().remove(inscripcion);
            return true;
        }
        return false;
    }

    public void registrarInscripcion(Inscripcion inscripcion) throws Exception {
        if (inscripcion != null) {
            for (Inscripcion i : gym.getListaInscripciones()) {
                if (i.getId().equals(inscripcion.getId())) {
                    throw new Exception("La inscripción con el ID dado ya existe.");
                }
            }

            // F6: Asignar un entrenador específico a la inscripción (si el plan es personalizado).
            boolean isPersonalizado = inscripcion.getPlanEntrenamiento() instanceof PlanPersonalizado;
            if (inscripcion.getEntrenador() != null && !isPersonalizado) {
                throw new Exception("Solo se puede asignar un entrenador si el plan es Personalizado.");
            }
            if (isPersonalizado && inscripcion.getEntrenador() == null) {
                throw new Exception("Debe asignar un entrenador para planes personalizados.");
            }

            gym.getListaInscripciones().add(inscripcion);
        }
    }

    public double calcularIngresosPorPeriodo(LocalDate fechaInicio, LocalDate fechaFin) {
        double totalIngresos = 0.0;
        for (Inscripcion inscripcion : gym.getListaInscripciones()) {
            LocalDate fechaInscripcion = inscripcion.getFecha();
            if (fechaInscripcion != null) {
                boolean isAfterOrEqual = fechaInscripcion.isEqual(fechaInicio) || fechaInscripcion.isAfter(fechaInicio);
                boolean isBeforeOrEqual = fechaInscripcion.isEqual(fechaFin) || fechaInscripcion.isBefore(fechaFin);
                
                if (isAfterOrEqual && isBeforeOrEqual) {
                    totalIngresos += inscripcion.getValorTotal();
                }
            }
        }
        return totalIngresos;
    }
}
