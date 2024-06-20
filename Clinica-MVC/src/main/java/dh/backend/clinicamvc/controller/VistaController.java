package dh.backend.clinicamvc.controller;

import dh.backend.clinicamvc.entity.Odontologo;
import dh.backend.clinicamvc.entity.Paciente;
import dh.backend.clinicamvc.service.IOdoltologoService;
import dh.backend.clinicamvc.service.IPacienteService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public class VistaController {
    private IPacienteService pacienteService;
    private IOdoltologoService odontologoService;

    public VistaController(IPacienteService pacienteService, IOdoltologoService odontologoService) {
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @GetMapping
    public String buscarPacientePorId(Model model, @RequestParam Integer id) {
        Optional<Paciente> pacienteOptional = pacienteService.buscarPorId(id);
        Paciente paciente = pacienteOptional.get();
        model.addAttribute("especialidad", "Paciente");
        model.addAttribute("nombre", paciente.getNombre());
        model.addAttribute("apellido", paciente.getApellido());
        return "index";
    }
    @GetMapping("/buscarOdontologo")
    public String buscarOdontologoPorId(Model model, @RequestParam Integer id){
        Optional<Odontologo> odontologoOptional = odontologoService.buscarPorId(id);
        Odontologo odontologo = odontologoOptional.get();
        model.addAttribute("especialidad", "odontologo");
        model.addAttribute("nombre", odontologo.getNombre());
        model.addAttribute("apellido", odontologo.getApellido());
        return "index";
    }

}