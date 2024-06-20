package dh.backend.clinicamvc.service;

import dh.backend.clinicamvc.exception.ResourceNotFoundException;
import dh.backend.clinicamvc.entity.Odontologo;

import java.util.List;
import java.util.Optional;

public interface IOdoltologoService {
    Optional<Odontologo> buscarPorId(Integer id);
    Odontologo registrarOdontologo(Odontologo odontologo);
    List<Odontologo> buscarTodos();

    void actualizarOdontologo(Odontologo odontologo);
    void eliminarOdontologo(Integer id) throws ResourceNotFoundException;
    List<Odontologo> buscarPorApellido(String apellido);
    List<Odontologo> buscarPorNombre(String nombre);
}
