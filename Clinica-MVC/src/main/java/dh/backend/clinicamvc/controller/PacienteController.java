package dh.backend.clinicamvc.controller;

import dh.backend.clinicamvc.model.Odontologo;
import dh.backend.clinicamvc.model.Paciente;
import dh.backend.clinicamvc.service.impl.PacienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }
/*
    @GetMapping("/buscarPaciente")
    public String buscarPorId(Model model, @RequestParam Integer id){
        Paciente paciente = pacienteService.buscarPorId(id);
        model.addAttribute("nombre",paciente.getNombre());
        model.addAttribute("apellido",paciente.getApellido());

        return "index";
    }*/
    @GetMapping
    public List<Paciente> ListarPacientes(){
        return pacienteService.buscarTodos();
    }

    @GetMapping("/{id}")
    public Paciente ConsultarPacientes(@PathVariable Integer id){
        return pacienteService.buscarPorId(id);
    }

    @PostMapping
    public Paciente CrearPacientes(@RequestBody Paciente paciente){
        Paciente pac= pacienteService.registrarPaciente(paciente);
        return pac;
    }

    @PutMapping
    public String actualizarOdontologo(@RequestBody Paciente paciente){
        pacienteService.actualizarPaciente(paciente);
        return "Paciente Actualizado";
    }

    @DeleteMapping("/{id}")
    public String eliminarOdontologo(@PathVariable Integer id){
        pacienteService.eliminarPaciente(id);
        return "Paciente Eliminado";
    }

}
