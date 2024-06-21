package dh.backend.clinicamvc.service.impl;

import dh.backend.clinicamvc.exception.ResourceNotFoundException;
import dh.backend.clinicamvc.entity.Odontologo;
import dh.backend.clinicamvc.repository.IOdontologoRepository;
import dh.backend.clinicamvc.service.IOdoltologoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdoltologoService {
    private static Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);

    private IOdontologoRepository odontologoRepository;

    public OdontologoService(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    public Optional<Odontologo> buscarPorId(Integer id) {
        LOGGER.info("Buscando odontólogo con id: {}", id);
        Optional<Odontologo> result = odontologoRepository.findById(id);
        if (result.isPresent()) {
            LOGGER.info("Odontólogo encontrado: {}", result.get());
        } else {
            LOGGER.warn("Odontólogo no encontrado con id: {}", id);
        }
        return result;
    }

    public Odontologo registrarOdontologo(Odontologo odontologo) {
        LOGGER.info("Registrando nuevo odontólogo: {}", odontologo);
        Odontologo result = odontologoRepository.save(odontologo);
        LOGGER.info("Odontólogo registrado exitosamente: {}", result);
        return result;
    }

    public List<Odontologo> buscarTodos() {
        LOGGER.info("Buscando todos los odontólogos");
        List<Odontologo> result = odontologoRepository.findAll();
        LOGGER.info("Cantidad de odontólogos encontrados: {}", result.size());
        return result;
    }

    @Override
    public void actualizarOdontologo(Odontologo odontologo) {
        LOGGER.info("Actualizando odontólogo: {}", odontologo);
        odontologoRepository.save(odontologo);
        LOGGER.info("Odontólogo actualizado exitosamente: {}", odontologo);
    }

    @Override
    public void eliminarOdontologo(Integer id) throws ResourceNotFoundException {
        LOGGER.info("Eliminando odontólogo con id: {}", id);
        Optional<Odontologo> odontologoOptional = buscarPorId(id);
        if (odontologoOptional.isPresent()) {
            odontologoRepository.deleteById(id);
            LOGGER.info("Odontólogo eliminado exitosamente con id: {}", id);
        } else {
            LOGGER.error("Error al eliminar odontólogo. No encontrado con id: {}", id);
            throw new ResourceNotFoundException("{\"message\": \"odontologo no encontrado\"}");
        }
    }

    @Override
    public List<Odontologo> buscarPorApellido(String apellido) {
        LOGGER.info("Buscando odontólogos por apellido: {}", apellido);
        List<Odontologo> result = odontologoRepository.buscarPorApellido(apellido);
        LOGGER.info("Cantidad de odontólogos encontrados con apellido {}: {}", apellido, result.size());
        return result;
    }

    @Override
    public List<Odontologo> buscarPorNombre(String nombre) {
        LOGGER.info("Buscando odontólogos por nombre: {}", nombre);
        List<Odontologo> result = odontologoRepository.findByNombreLike(nombre);
        LOGGER.info("Cantidad de odontólogos encontrados con nombre {}: {}", nombre, result.size());
        return result;
    }
}
