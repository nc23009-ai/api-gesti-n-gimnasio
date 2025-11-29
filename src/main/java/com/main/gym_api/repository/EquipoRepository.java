package com.main.gym_api.repository;

import com.main.gym_api.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {
    List<Equipo> findByEstadoEquipo(String estado); //buscar por estado (string)
}

