package com.main.gym_api.service;

import com.main.gym_api.dto.EmpleadoDTO;
import java.util.List;

public interface EmpleadoService {
    EmpleadoDTO crearEmpleado(EmpleadoDTO dto);
    List<EmpleadoDTO> listarEmpleados();
    EmpleadoDTO obtenerPorId(Long id);
    EmpleadoDTO actualizarEmpleado(Long id, EmpleadoDTO dto);
    void eliminarEmpleado(Long id);
}