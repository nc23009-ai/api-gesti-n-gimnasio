package com.main.gym_api.repository;

import com.main.gym_api.model.Clase;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface ClaseRepository extends JpaRepository<Clase, Long> {
    List<Clase> findByFechaAfter(LocalDateTime fecha);
}