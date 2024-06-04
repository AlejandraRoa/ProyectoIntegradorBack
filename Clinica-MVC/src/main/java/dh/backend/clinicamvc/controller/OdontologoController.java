package dh.backend.clinicamvc.controller;

import dh.backend.clinicamvc.model.Odontologo;
import dh.backend.clinicamvc.model.Paciente;
import dh.backend.clinicamvc.service.impl.OdontologoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private OdontologoService odontologoService;

    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }
/*
    @GetMapping("/buscarOdontologo")
    public String buscarPorId(Model model, @RequestParam Integer id){
        Odontologo odontologo = odontologoService.buscarPorId(id);
        model.addAttribute("Matricula",odontologo.getNumeroMatricula());

        return "index1";
    }*/
    @GetMapping
    public List<Odontologo> ListarOdontologos(){
        return odontologoService.buscarTodos();
    }

    @GetMapping("/{id}")
    public Odontologo ConsultarOdontologos(@PathVariable Integer id){
        return odontologoService.buscarPorId(id);
    }

    @PostMapping
    public Odontologo CrearOdontologos(@RequestBody Odontologo odontologo){
        Odontologo odont= odontologoService.registrarOdontologo(odontologo);
        return odont;
    }
}
