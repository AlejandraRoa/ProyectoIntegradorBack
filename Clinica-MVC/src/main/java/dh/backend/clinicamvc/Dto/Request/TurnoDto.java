package dh.backend.clinicamvc.Dto.Request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class TurnoDto {
    private Integer paciente_id;
    private Integer odontologo_id;
    private String fecha;


}
