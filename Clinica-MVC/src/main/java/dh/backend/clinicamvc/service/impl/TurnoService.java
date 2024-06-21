package dh.backend.clinicamvc.service.impl;

import dh.backend.clinicamvc.Dto.Request.TurnoRequestDto;
import dh.backend.clinicamvc.Dto.Response.OdontologoResponseDto;
import dh.backend.clinicamvc.Dto.Response.PacienteResponseDto;
import dh.backend.clinicamvc.Dto.Response.TurnoResponseDto;
import dh.backend.clinicamvc.exception.ResourceNotFoundException;
import dh.backend.clinicamvc.entity.Odontologo;
import dh.backend.clinicamvc.entity.Paciente;
import dh.backend.clinicamvc.entity.Turno;
import dh.backend.clinicamvc.repository.IOdontologoRepository;
import dh.backend.clinicamvc.repository.IPacienteRepository;
import dh.backend.clinicamvc.repository.ITurnoRepository;
import dh.backend.clinicamvc.service.ITurnoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);

    private final IOdontologoRepository odontologoRepository;
    private final IPacienteRepository pacienteRepository;
    private final ITurnoRepository turnoRepository;
    private final ModelMapper modelMapper;

    public TurnoService(IOdontologoRepository odontologoRepository, IPacienteRepository pacienteRepository, ITurnoRepository turnoRepository, ModelMapper modelMapper) {
        this.odontologoRepository = odontologoRepository;
        this.pacienteRepository = pacienteRepository;
        this.turnoRepository = turnoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TurnoResponseDto registrar(TurnoRequestDto turnoRequestDto) {
        LOGGER.info("Registrando nuevo turno con datos: {}", turnoRequestDto);

        Optional<Paciente> paciente = pacienteRepository.findById(turnoRequestDto.getPaciente_id());
        Optional<Odontologo> odontologo = odontologoRepository.findById(turnoRequestDto.getOdontologo_id());

        Turno turnoARegistrar = new Turno();
        TurnoResponseDto turnoADevolver = null;

        if (paciente.isPresent() && odontologo.isPresent()) {
            turnoARegistrar.setOdontologo(odontologo.get());
            turnoARegistrar.setPaciente(paciente.get());
            turnoARegistrar.setFecha(LocalDate.parse(turnoRequestDto.getFecha()));

            Turno turnoGuardado = turnoRepository.save(turnoARegistrar);
            turnoADevolver = mapToResponseDto(turnoGuardado);

            LOGGER.info("Turno registrado exitosamente: {}", turnoADevolver);
        } else {
            LOGGER.warn("No se pudo registrar el turno. Paciente u odontólogo no encontrado.");
        }

        return turnoADevolver;
    }

    @Override
    public TurnoResponseDto buscarPorId(Integer id) {
        LOGGER.info("Buscando turno con id: {}", id);

        Optional<Turno> turnoOptional = turnoRepository.findById(id);

        if (turnoOptional.isPresent()) {
            Turno turnoEncontrado = turnoOptional.get();
            TurnoResponseDto turnoADevolver = mapToResponseDto(turnoEncontrado);

            LOGGER.info("Turno encontrado: {}", turnoADevolver);
            return turnoADevolver;
        } else {
            LOGGER.warn("Turno no encontrado con id: {}", id);
            return null;
        }
    }

    @Override
    public List<TurnoResponseDto> buscarTodos() {
        LOGGER.info("Buscando todos los turnos");

        List<Turno> turnos = turnoRepository.findAll();
        List<TurnoResponseDto> turnosADevolver = new ArrayList<>();

        for (Turno turno : turnos) {
            TurnoResponseDto turnoAuxiliar = mapToResponseDto(turno);
            turnosADevolver.add(turnoAuxiliar);
        }

        LOGGER.info("Cantidad de turnos encontrados: {}", turnosADevolver.size());
        return turnosADevolver;
    }

    @Override
    public void actualizarTurno(Integer id, TurnoRequestDto turnoRequestDto) {
        LOGGER.info("Actualizando turno con id: {}", id);

        Optional<Paciente> paciente = pacienteRepository.findById(turnoRequestDto.getPaciente_id());
        Optional<Odontologo> odontologo = odontologoRepository.findById(turnoRequestDto.getOdontologo_id());
        Optional<Turno> turno = turnoRepository.findById(id);

        if (paciente.isPresent() && odontologo.isPresent() && turno.isPresent()) {
            Turno turnoAModificar = turno.get();
            turnoAModificar.setOdontologo(odontologo.get());
            turnoAModificar.setPaciente(paciente.get());
            turnoAModificar.setFecha(LocalDate.parse(turnoRequestDto.getFecha()));

            turnoRepository.save(turnoAModificar);

            LOGGER.info("Turno actualizado exitosamente: {}", turnoAModificar);
        } else {
            LOGGER.warn("No se pudo actualizar el turno. Datos incompletos o turno no encontrado.");
        }
    }

    @Override
    public void eliminarTurno(Integer id) throws ResourceNotFoundException {
        LOGGER.info("Eliminando turno con id: {}", id);

        Optional<Turno> turnoOptional = turnoRepository.findById(id);

        if (turnoOptional.isPresent()) {
            turnoRepository.deleteById(id);
            LOGGER.info("Turno eliminado exitosamente con id: {}", id);
        } else {
            LOGGER.error("Error al eliminar turno. Turno no encontrado con id: {}", id);
            throw new ResourceNotFoundException("{\"message\": \"turno no encontrado\"}");
        }
    }

    @Override
    public List<TurnoResponseDto> buscarTurnoEntreFechas(LocalDate startDate, LocalDate endDate) {
        LOGGER.info("Buscando turnos entre fechas: {} y {}", startDate, endDate);

        List<Turno> listadoTurnos = turnoRepository.buscarTurnoEntreFechas(startDate, endDate);
        List<TurnoResponseDto> listadoARetornar = new ArrayList<>();

        for (Turno turno : listadoTurnos) {
            TurnoResponseDto turnoAuxiliar = mapToResponseDto(turno);
            listadoARetornar.add(turnoAuxiliar);
        }

        LOGGER.info("Cantidad de turnos encontrados entre las fechas: {}", listadoARetornar.size());
        return listadoARetornar;
    }

    private TurnoResponseDto mapToResponseDto(Turno turno) {
        TurnoResponseDto turnoResponseDto = modelMapper.map(turno, TurnoResponseDto.class);
        turnoResponseDto.setOdontologo(modelMapper.map(turno.getOdontologo(), OdontologoResponseDto.class));
        turnoResponseDto.setPaciente(modelMapper.map(turno.getPaciente(), PacienteResponseDto.class));
        return turnoResponseDto;
    }
}
