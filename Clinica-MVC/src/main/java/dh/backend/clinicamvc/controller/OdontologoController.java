package dh.backend.clinicamvc.controller;

import dh.backend.clinicamvc.model.Odontologo;
import dh.backend.clinicamvc.service.impl.OdontologoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OdontologoController {
    private OdontologoService odontologoService;

    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @GetMapping("/buscarOdontologo")
    public String buscarPorId(Model model, @RequestParam Integer id){
        Odontologo odontologo = odontologoService.buscarPorId(id);
        model.addAttribute("Matricula",odontologo.getNumeroMatricula());

        return "index1";
    }
}
