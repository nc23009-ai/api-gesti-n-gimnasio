

package com.main.gym_api.dto;

public class MembresiaDTO {
    private Long id;
    private String tipo;
    private String fechaInicio;
    private String fechaFin;
    private Double precio;
    private Boolean estadoActivo;

    // Constructor vacío
    public MembresiaDTO() {}

    // Constructor con parámetros
    public MembresiaDTO(Long id, String tipo, String fechaInicio, String fechaFin, Double precio, Boolean estadoActivo) {
        this.id = id;
        this.tipo = tipo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precio = precio;
        this.estadoActivo = estadoActivo;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    
    public String getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(String fechaInicio) { this.fechaInicio = fechaInicio; }
    
    public String getFechaFin() { return fechaFin; }
    public void setFechaFin(String fechaFin) { this.fechaFin = fechaFin; }
    
    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }
    
    public Boolean getEstadoActivo() { return estadoActivo; }
    public void setEstadoActivo(Boolean estadoActivo) { this.estadoActivo = estadoActivo; }
}