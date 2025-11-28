package com.main.gym_api.repository;

import com.main.gym_api.model.Inscripcion;
import com.main.gym_api.model.EstadoInscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    List<Inscripcion> findByMiembroId(Long miembroId);
    List<Inscripcion> findByClaseId(Long claseId);
    int countByClaseIdAndEstadoInscripcion(Long claseId, EstadoInscripcion estado);

    // Método requerido para validar asistencia
    List<Inscripcion> findByMiembroIdAndClaseId(Long miembroId, Long claseId);
}




