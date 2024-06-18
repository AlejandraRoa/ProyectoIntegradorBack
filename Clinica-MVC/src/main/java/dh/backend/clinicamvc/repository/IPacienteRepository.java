package dh.backend.clinicamvc.repository;

import dh.backend.clinicamvc.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPacienteRepository extends JpaRepository<Paciente, Integer> {
}
