package com.main.gym_api.repository;

import com.main.gym_api.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PagoRepository extends JpaRepository<Pago, Long> {
    List<Pago> findByMiembroId(Long miembroId);
    List<Pago> findByMembresiaId(Long membresiaId);
}
