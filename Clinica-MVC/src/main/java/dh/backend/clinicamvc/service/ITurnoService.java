package dh.backend.clinicamvc.service;
import dh.backend.clinicamvc.Dto.Request.TurnoRequestDto;
import dh.backend.clinicamvc.Dto.Response.TurnoResponseDto;
import dh.backend.clinicamvc.exception.ResourceNotFoundException;

import java.time.LocalDate;
import java.util.List;
public interface ITurnoService {
    TurnoResponseDto registrar(TurnoRequestDto turnoRequestDto);
    TurnoResponseDto buscarPorId(Integer id);
    List<TurnoResponseDto> buscarTodos();
    void actualizarTurno(Integer id, TurnoRequestDto turnoRequestDto);
    void eliminarTurno(Integer id) throws ResourceNotFoundException;

    List<TurnoResponseDto> buscarTurnoEntreFechas(LocalDate startDate, LocalDate endDate);
}
