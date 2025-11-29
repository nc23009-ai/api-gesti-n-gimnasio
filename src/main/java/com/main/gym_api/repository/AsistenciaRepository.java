package com.main.gym_api.repository;

import com.main.gym_api.model.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {
    List<Asistencia> findByMiembroId(Long miembroId);
    List<Asistencia> findByClaseId(Long claseId);
}

