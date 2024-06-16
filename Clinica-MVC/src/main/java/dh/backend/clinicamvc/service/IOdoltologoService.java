package dh.backend.clinicamvc.service;

import dh.backend.clinicamvc.dao.IDao;
import dh.backend.clinicamvc.model.Odontologo;

import java.util.List;

public interface IOdoltologoService {
    Odontologo buscarPorId(Integer id);
    Odontologo registrarOdontologo(Odontologo odontologo);
    List<Odontologo> buscarTodos();

    void actualizarOdontologo(Odontologo odontologo);
    void eliminarOdontologo(Integer id);

}
