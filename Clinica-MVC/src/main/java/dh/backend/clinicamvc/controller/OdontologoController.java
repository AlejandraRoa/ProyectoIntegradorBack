package dh.backend.clinicamvc.controller;

import dh.backend.clinicamvc.model.Odontologo;
import dh.backend.clinicamvc.model.Paciente;
import dh.backend.clinicamvc.service.impl.OdontologoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public ResponseEntity<List<Odontologo>> ListarOdontologos(){
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> ConsultarOdontologos(@PathVariable Integer id){
        Odontologo odontologo = odontologoService.buscarPorId(id);
        if(odontologo != null){
            return ResponseEntity.ok(odontologo);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Odontologo> CrearOdontologos(@RequestBody Odontologo odontologo){
        Odontologo odont= odontologoService.registrarOdontologo(odontologo);
        return ResponseEntity.status(HttpStatus.CREATED).body(odont);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarOdontologo(@PathVariable Integer id, @RequestBody Odontologo odontologo) {
        odontologo.setId(id);  // Asigna el ID al objeto odontólogo
        odontologoService.actualizarOdontologo(odontologo);
        return ResponseEntity.ok("Odontologo actualizado");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Integer id){
         odontologoService.eliminarOdontologo(id);
        return ResponseEntity.ok("Odontologo eliminado");
    }
}
