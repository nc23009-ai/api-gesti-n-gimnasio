package com.main.gym_api.repository;

import com.main.gym_api.model.Membresia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MembresiaRepository extends JpaRepository<Membresia, Long> {
    List<Membresia> findByMiembroId(Long miembroId);
    List<Membresia> findByMiembroIdAndActivaTrue(Long miembroId);
}
