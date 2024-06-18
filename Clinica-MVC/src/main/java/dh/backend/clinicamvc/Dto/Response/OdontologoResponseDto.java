package dh.backend.clinicamvc.Dto.Response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OdontologoResponseDto {
    private Integer id;
    private String numeroMatricula;
    private String nombre;
    private String apellido;
}
