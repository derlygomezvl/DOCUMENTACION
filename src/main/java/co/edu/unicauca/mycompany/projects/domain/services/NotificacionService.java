package co.edu.unicauca.mycompany.projects.domain.services;

import co.edu.unicauca.mycompany.projects.domain.entities.AccionHistorial;
import co.edu.unicauca.mycompany.projects.domain.services.HistorialService;

public class NotificacionService {

    private final HistorialService historialService;

    public NotificacionService(HistorialService historialService) {
        this.historialService = historialService;
    }

    public void notificar(int usuarioId, String mensaje) {
        historialService.registrarAccion(usuarioId, "Notificación", mensaje);
    }
}