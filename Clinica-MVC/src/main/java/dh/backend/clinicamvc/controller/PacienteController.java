package dh.backend.clinicamvc.controller;

import dh.backend.clinicamvc.model.Paciente;
import dh.backend.clinicamvc.service.impl.PacienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PacienteController {
    private PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping("/buscarPaciente")
    public String buscarPorId(Model model, @RequestParam Integer id){
        Paciente paciente = pacienteService.buscarPorId(id);
        model.addAttribute("nombre",paciente.getNombre());
        model.addAttribute("apellido",paciente.getApellido());

        return "index";
    }
}
