package dh.backend.clinicamvc.service.impl;

import dh.backend.clinicamvc.Dto.Request.TurnoRequestDto;
import dh.backend.clinicamvc.Dto.Response.OdontologoResponseDto;
import dh.backend.clinicamvc.Dto.Response.PacienteResponseDto;
import dh.backend.clinicamvc.Dto.Response.TurnoResponseDto;
import dh.backend.clinicamvc.dao.IDao;
import dh.backend.clinicamvc.model.Odontologo;
import dh.backend.clinicamvc.model.Paciente;
import dh.backend.clinicamvc.model.Turno;
import dh.backend.clinicamvc.service.ITurnoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TurnoService implements ITurnoService {
    private IDao<Turno> turnoIDao;
    private IDao<Paciente> pacienteIDao;
    private IDao<Odontologo> odontologoIDao;
    private ModelMapper modelMapper;

    public TurnoService(IDao<Turno> turnoIDao, IDao<Paciente> pacienteIDao, IDao<Odontologo> odontologoIDao, ModelMapper modelMapper) {
        this.turnoIDao = turnoIDao;
        this.pacienteIDao = pacienteIDao;
        this.odontologoIDao = odontologoIDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public TurnoResponseDto registrar(TurnoRequestDto turnoRequestDto) {
        Paciente paciente = pacienteIDao.buscarPorId(turnoRequestDto.getPaciente_id());
        Odontologo odontologo = odontologoIDao.buscarPorId(turnoRequestDto.getOdontologo_id());
        Turno turnoARegistrar = new Turno();
        Turno turnoGuardado = null;
        TurnoResponseDto turnoADevolver = null;
        if(paciente!=null && odontologo!=null){
            turnoARegistrar.setOdontologo(odontologo);
            turnoARegistrar.setPaciente(paciente);
            turnoARegistrar.setFecha(LocalDate.parse(turnoRequestDto.getFecha()));
            turnoGuardado = turnoIDao.registrar(turnoARegistrar);
            turnoADevolver = mapToResponseDto(turnoGuardado);
        }
        return turnoADevolver;
    }

    @Override
    public TurnoResponseDto buscarPorId(Integer id) {
        Turno turnoEncontrado = turnoIDao.buscarPorId(id);
        TurnoResponseDto turnoADevolver = mapToResponseDto(turnoEncontrado);
        return turnoADevolver;
    }

    @Override
    public List<TurnoResponseDto> buscarTodos() {
        List<Turno> turnos = turnoIDao.buscarTodos();
        List<TurnoResponseDto> turnosADevolver = new ArrayList<>();
        TurnoResponseDto turnoAuxiliar = null;
        for(Turno turno: turnos){
            turnoAuxiliar = mapToResponseDto(turno);
            turnosADevolver.add(turnoAuxiliar);
        }
        return turnosADevolver;
    }

    @Override
    public void actualizarTurno(Integer id, TurnoRequestDto turnoRequestDto) {
        Paciente paciente = pacienteIDao.buscarPorId(turnoRequestDto.getPaciente_id());
        Odontologo odontologo = odontologoIDao.buscarPorId(turnoRequestDto.getOdontologo_id());
        Turno turno = turnoIDao.buscarPorId(id);
        Turno turnoAModificar = new Turno();
        if(paciente!=null && odontologo!=null && turno != null){
            turnoAModificar.setId(id);
            turnoAModificar.setOdontologo(odontologo);
            turnoAModificar.setPaciente(paciente);
            turnoAModificar.setFecha(LocalDate.parse(turnoRequestDto.getFecha()));
            turnoIDao.actualizar(turnoAModificar);
        }
    }

    @Override
    public void eliminarTurno(Integer id) {
        turnoIDao.eliminar(id);
    }
    // metodo que mapea turno en turnoResponseDto
    private TurnoResponseDto mapToResponseDto(Turno turno){
        TurnoResponseDto turnoResponseDto = modelMapper.map(turno, TurnoResponseDto.class);
        turnoResponseDto.setOdontologo(modelMapper.map(turno.getOdontologo(), OdontologoResponseDto.class));
        turnoResponseDto.setPaciente(modelMapper.map(turno.getPaciente(), PacienteResponseDto.class));
        return  turnoResponseDto;
    }
}
