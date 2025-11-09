package com.main.gym_api.repository;

import com.main.gym_api.model.Miembro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MiembroRepository extends JpaRepository<Miembro, Long> {
    List<Miembro> findByMiembroActivoTrue();
}

