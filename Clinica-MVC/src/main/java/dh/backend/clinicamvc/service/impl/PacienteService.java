package dh.backend.clinicamvc.service.impl;

import dh.backend.clinicamvc.entity.Paciente;
import dh.backend.clinicamvc.exception.ResourceNotFoundException;
import dh.backend.clinicamvc.repository.IPacienteRepository;
import dh.backend.clinicamvc.service.IPacienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {
    private static Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);

    private IPacienteRepository pacienteRepository;

    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente registrarPaciente(Paciente paciente) {
        LOGGER.info("Registrando nuevo paciente: {}", paciente);
        Paciente result = pacienteRepository.save(paciente);
        LOGGER.info("Paciente registrado exitosamente: {}", result);
        return result;
    }

    public Optional<Paciente> buscarPorId(Integer id) {
        LOGGER.info("Buscando paciente con id: {}", id);
        Optional<Paciente> result = pacienteRepository.findById(id);
        if (result.isPresent()) {
            LOGGER.info("Paciente encontrado: {}", result.get());
        } else {
            LOGGER.warn("Paciente no encontrado con id: {}", id);
        }
        return result;
    }

    public List<Paciente> buscarTodos() {
        LOGGER.info("Buscando todos los pacientes");
        List<Paciente> result = pacienteRepository.findAll();
        LOGGER.info("Cantidad de pacientes encontrados: {}", result.size());
        return result;
    }

    @Override
    public void actualizarPaciente(Paciente paciente) {
        LOGGER.info("Actualizando paciente: {}", paciente);
        pacienteRepository.save(paciente);
        LOGGER.info("Paciente actualizado exitosamente: {}", paciente);
    }

    @Override
    public void eliminarPaciente(Integer id) throws ResourceNotFoundException {
        LOGGER.info("Eliminando paciente con id: {}", id);
        Optional<Paciente> pacienteOptional = buscarPorId(id);
        if (pacienteOptional.isPresent()) {
            pacienteRepository.deleteById(id);
            LOGGER.info("Paciente eliminado exitosamente con id: {}", id);
        } else {
            LOGGER.error("Error al eliminar paciente. No encontrado con id: {}", id);
            throw new ResourceNotFoundException("{\"message\": \"paciente no encontrado\"}");
        }
    }
}
