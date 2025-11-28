package com.main.gym_api.repository;

import com.main.gym_api.model.Rutina;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RutinaRepository extends JpaRepository<Rutina, Long> {
    List<Rutina> findByMiembroId(Long miembroId);
    List<Rutina> findByEquipoId(Long equipoId);
}
