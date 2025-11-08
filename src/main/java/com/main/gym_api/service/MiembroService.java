/*
package com.main.gym_api.service;
import com.main.gym_api.dto.MiembroDTO;
import com.main.gym_api.model.Miembro;
import com.main.gym_api.repository.MiembroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class MiembroService {
    private final MiembroRepository miembroRepository;

    public MiembroService(MiembroRepository miembroRepository) {
        this.miembroRepository = miembroRepository;
    }

    */
/*
     * Método: listar()
     * Devuelve una lista de TODOS los miembros convertidos a DTO.
     *//*

    @Transactional(readOnly = true)
    public List<MiembroDTO> membersList() {
        return miembroRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    */
/*
     * Método: buscarPorId
     * Devuelve un miembro específico (DTO) o lanza una excepción si no lo encuentra.
     *//*

    @Transactional(readOnly = true)
    public MiembroDTO findByIdMember(Long id) {
        // findById devuelve un Optional<Miembro>
        Miembro member = miembroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Miembro no encontrado con id: " + id)); // Manejo de error
        return convertToDto(member);
    }

    */
/*
     * Método: guardar
     * Crea o actualiza un miembro.
     *//*

    @Transactional
    public MiembroDTO saveMember(MiembroDTO miembroDTO) {
        Miembro member = convertToEntity(miembroDTO);
        Miembro miembroGuardado = miembroRepository.save(member);
        return convertToDto(miembroGuardado);
    }

    */
/*
     * Método: eliminar
     * Elimina un miembro por su ID.
     *//*

    @Transactional
    public void deleteMember(Long id) {
        Miembro member = miembroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Miembro no encontrado con id: " + id));
        miembroRepository.delete(miembro); // O deleteById(id)
    }

    */
/*
     * Método: activar
     * Cambia el estado 'miembroActivo' a true.
     *//*

    @Transactional
    public MiembroDTO setActiveMember(Long id) {
        Miembro member = miembroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Miembro no encontrado con id: " + id));
        member.setMiembroActivo(true);
        Miembro miembroActualizado = miembroRepository.save(member);
        return convertToDto(miembroActualizado);
    }

    */
/*
     * Método: desactivar
     * Cambia el estado 'miembroActivo' a false.
     *//*

    @Transactional
    public MiembroDTO setInactiveMember(Long id) {
        Miembro miembro = miembroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Miembro no encontrado con id: " + id));
        miembro.setMiembroActivo(false);

        Miembro miembroActualizado = miembroRepository.save(miembro);
        return convertToDto(miembroActualizado);
    }

    private MiembroDTO convertToDto(Miembro miembro) {
        MiembroDTO dto = new MiembroDTO();
        dto.setId(miembro.getId());
        dto.setNombres(miembro.getName());
        dto.setApellidos(miembro.getLastname());
        dto.setEmail(miembro.getEmail());
        dto.setTelefono(miembro.getPhone());
        dto.setFechaRegistro(miembro.getRecordDate());
        dto.setEstadoActivo(miembro.getActiveState());
        return dto;
    }

    private Miembro convertToEntity(MiembroDTO dto) {
        Miembro miembro = new Miembro();
        if (dto.getId() != null) {
            miembro.setId(dto.getId());
        }
        miembro.setName(dto.getNombres());
        miembro.setLastname(dto.getApellidos());
        miembro.setEmail(dto.getEmail());
        miembro.setRecordDate(dto.getFechaRegistro());
        miembro.setActiveState(dto.getEstadoActivo());
        miembro.setPhone(dto.getTelefono());
        return miembro;
    }

}
*/
