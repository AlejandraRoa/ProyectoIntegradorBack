package dh.backend.clinicamvc.service;

import dh.backend.clinicamvc.Dto.Request.TurnoRequestDto;
import dh.backend.clinicamvc.Dto.Response.TurnoResponseDto;
import dh.backend.clinicamvc.entity.Domicilio;
import dh.backend.clinicamvc.entity.Odontologo;
import dh.backend.clinicamvc.entity.Paciente;
import dh.backend.clinicamvc.entity.Turno;
import dh.backend.clinicamvc.repository.IOdontologoRepository;
import dh.backend.clinicamvc.repository.IPacienteRepository;
import dh.backend.clinicamvc.service.impl.TurnoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TurnoServiceTest {
    private static Logger LOGGER = LoggerFactory.getLogger(TurnoServiceTest.class);

    @Autowired
    private TurnoService turnoService;

    @Autowired
    private IOdontologoRepository odontologoRepository;

    @Autowired
    private IPacienteRepository pacienteRepository;

    @Autowired
    private ModelMapper modelMapper;

    private TurnoRequestDto turnoRequestDto;
    private Turno turno;
    private Odontologo odontologo;
    private Paciente paciente;

    @BeforeEach
    void setUp() {
        // Crear y guardar un odontólogo de prueba
        odontologo = new Odontologo();
        odontologo.setNombre("Menganito");
        odontologo.setApellido("Cosme");
        odontologo.setNumeroMatricula("1502448765");
        odontologoRepository.save(odontologo);

        // Crear y guardar un paciente de prueba

        paciente = new Paciente();
        paciente.setNombre("Menganito");
        paciente.setApellido("Cosme");
        paciente.setDni("464646");
        paciente.setFechaIngreso(LocalDate.of(2024,06,20));
        pacienteRepository.save(paciente);

        // Inicializar TurnoRequestDto
        turnoRequestDto = new TurnoRequestDto();
        turnoRequestDto.setPaciente_id(paciente.getId());
        turnoRequestDto.setOdontologo_id(odontologo.getId());
        turnoRequestDto.setFecha("2024-06-20");

        // Crear un turno de prueba
        turno = new Turno();
        turno.setOdontologo(odontologo);
        turno.setPaciente(paciente);
        turno.setFecha(LocalDate.parse("2024-06-20"));
    }

    @Test
    @DisplayName("Testear que un turno fue guardado")
    void testTurnoGuardado() {
        TurnoResponseDto turnoGuardado = turnoService.registrar(turnoRequestDto);

        assertNotNull(turnoGuardado);
        assertEquals(turno.getFecha(), LocalDate.parse(turnoGuardado.getFecha()));
    }

    @Test
    @DisplayName("Testear búsqueda de todos los turnos")
    void testBuscarTodosLosTurnos() {
        turnoService.registrar(turnoRequestDto);

        List<TurnoResponseDto> turnos = turnoService.buscarTodos();

        assertNotNull(turnos);
        assertTrue(turnos.size() > 0);
    }
}
