package pe.edu.vallegrande.vg_ms_casas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("Casa")
public class Casa {
    @Id
    private Integer id_casa;
    private String nombre;
    private String direccion;
    private String estado; // "A" = Activo, "I" = Inactivo
}