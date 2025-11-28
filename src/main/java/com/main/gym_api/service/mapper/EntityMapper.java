package com.main.gym_api.service.mapper;

import com.main.gym_api.dto.*;
import com.main.gym_api.model.*;
import java.util.Optional;

public class EntityMapper {

    private EntityMapper() {}

    // Empleado
    public static EmpleadoDTO toEmpleadoDTO(Empleado e) {
        if (e == null) return null;
        return EmpleadoDTO.builder()
                .id(e.getId())
                .nombre(e.getNombre())
                .apellido(e.getApellido())
                .email(e.getEmail())
                .telefono(e.getTelefono())
                .role(e.getRole())
                .area(e.getArea())
                .build();
    }

    public static Empleado toEmpleadoEntity(EmpleadoDTO dto) {
        if (dto == null) return null;
        Empleado e = new Empleado();
        e.setId(dto.getId());
        e.setNombre(dto.getNombre());
        e.setApellido(dto.getApellido());
        e.setEmail(dto.getEmail());
        e.setTelefono(dto.getTelefono());
        e.setRole(dto.getRole());
        e.setArea(dto.getArea());
        return e;
    }

    // Clase
    public static ClaseDTO toClaseDTO(Clase c) {
        if (c == null) return null;
        return ClaseDTO.builder()
                .id(c.getId())
                .nombre(c.getNombre())
                .fecha(c.getFecha())
                .cupoMaximo(c.getCupoMaximo())
                .empleadoId(c.getEmpleado() != null ? c.getEmpleado().getId() : null)
                .build();
    }

    public static Clase toClaseEntity(ClaseDTO dto) {
        if (dto == null) return null;
        Clase c = new Clase();
        c.setId(dto.getId());
        c.setNombre(dto.getNombre());
        c.setFecha(dto.getFecha());
        c.setCupoMaximo(dto.getCupoMaximo());
        // empleado must be set by service when provided (fetch/empleadoRepo)
        return c;
    }

    // Inscripcion
    public static InscripcionDTO toInscripcionDTO(Inscripcion i) {
        if (i == null) return null;
        return InscripcionDTO.builder()
                .id(i.getId())
                .fechaInscripcion(i.getFechaInscripcion())
                .estadoInscripcion(i.getEstadoInscripcion() != null ? i.getEstadoInscripcion().name() : null)
                .miembroId(i.getMiembro() != null ? i.getMiembro().getId() : null)
                .claseId(i.getClase() != null ? i.getClase().getId() : null)
                .build();
    }

    // Asistencia
    public static AsistenciaDTO toAsistenciaDTO(Asistencia a) {
        if (a == null) return null;
        return AsistenciaDTO.builder()
                .id(a.getId())
                .fechaHora(a.getFechaHora())
                .presente(a.isPresente())
                .miembroId(a.getMiembro() != null ? a.getMiembro().getId() : null)
                .claseId(a.getClase() != null ? a.getClase().getId() : null)
                .build();
    }

    // Pago
    public static PagoDTO toPagoDTO(Pago p) {
        if (p == null) return null;
        return PagoDTO.builder()
                .id(p.getId())
                .monto(p.getMonto())
                .fechaPago(p.getFechaPago())
                .metodo(p.getMetodo())
                .miembroId(p.getMiembro() != null ? p.getMiembro().getId() : null)
                .membresiaId(p.getMembresia() != null ? p.getMembresia().getId() : null)
                .build();
    }

    // Equipo
    public static EquipoDTO toEquipoDTO(Equipo e) {
        if (e == null) return null;
        return EquipoDTO.builder()
                .id(e.getId())
                .nombre(e.getNombre())
                .estadoEquipo(e.getEstadoEquipo() != null ? e.getEstadoEquipo().name() : null)
                .build();
    }

    // Rutina
    public static RutinaDTO toRutinaDTO(Rutina r) {
        if (r == null) return null;
        return RutinaDTO.builder()
                .id(r.getId())
                .descripcion(r.getDescripcion())
                .duracionSemanas(r.getDuracionSemanas())
                .repsEstandar(r.getRepsEstandar())
                .fechaInicio(r.getFechaInicio())
                .miembroId(r.getMiembro() != null ? r.getMiembro().getId() : null)
                .equipoId(r.getEquipo() != null ? r.getEquipo().getId() : null)
                .build();
    }

    // Miembro
    public static MiembroDTO toMiembroDTO(Miembro m) {
        if (m == null) return null;
        MiembroDTO dto = MiembroDTO.builder()
                .id(m.getId())
                .nombre(m.getNombre())
                .apellido(m.getApellido())
                .email(m.getEmail())
                .telefono(m.getTelefono())
                .fechaRegistro(m.getFechaRegistro())
                .miembroActivo(m.isMiembroActivo())
                .build();
        if (m.getMembresia() != null) {
            dto.setMembresia(toMembresiaDTO(m.getMembresia()));
        }
        return dto;
    }

    public static Miembro toMiembroEntity(MiembroDTO dto) {
        if (dto == null) return null;
        Miembro m = new Miembro();
        m.setId(dto.getId());
        m.setNombre(dto.getNombre());
        m.setApellido(dto.getApellido());
        m.setEmail(dto.getEmail());
        m.setTelefono(dto.getTelefono());
        m.setFechaRegistro(dto.getFechaRegistro());
        m.setMiembroActivo(dto.isMiembroActivo());
        // membresia mapping se maneja por servicio
        return m;
    }

    // Membresia
    public static MembresiaDTO toMembresiaDTO(Membresia e) {
        if (e == null) return null;
        return MembresiaDTO.builder()
                .id(e.getId())
                .tipo(e.getTipo())
                .fechaInicio(e.getFechaInicio())
                .fechaFin(e.getFechaFin())
                .costo(e.getCosto())
                .activa(e.isActiva())
                .miembroId(e.getMiembro() != null ? e.getMiembro().getId() : null)
                .build();
    }

    public static Membresia toMembresiaEntity(MembresiaDTO dto) {
        if (dto == null) return null;
        Membresia e = new Membresia();
        e.setId(dto.getId());
        e.setTipo(dto.getTipo());
        e.setFechaInicio(dto.getFechaInicio());
        e.setFechaFin(dto.getFechaFin());
        e.setCosto(dto.getCosto());
        e.setActiva(dto.isActiva());
        // miembro se asigna en service cuando llega miembroId
        return e;
    }
}
