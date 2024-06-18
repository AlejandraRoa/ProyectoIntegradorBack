package dh.backend.clinicamvc.controller;

import dh.backend.clinicamvc.Dto.Request.TurnoRequestDto;
import dh.backend.clinicamvc.Dto.Response.TurnoResponseDto;
import dh.backend.clinicamvc.model.Turno;
import dh.backend.clinicamvc.service.ITurnoService;
import dh.backend.clinicamvc.service.impl.TurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Turno")
public class TurnoController {
    private ITurnoService turnoService;

    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }
    @PostMapping
    public ResponseEntity<TurnoResponseDto> agregarTurno(@RequestBody TurnoRequestDto turno){
        TurnoResponseDto turnoADevolver = turnoService.registrar(turno);
        if(turnoADevolver==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(turnoADevolver);
        }
    }

    @GetMapping
    public ResponseEntity<List<TurnoResponseDto>> buscarTodosTurnos(){

        return ResponseEntity.ok(turnoService.buscarTodos());
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> modificarTurno(@PathVariable Integer id, @RequestBody TurnoRequestDto turno){
        turnoService.actualizarTurno(id, turno);
        return ResponseEntity.ok("Turno modificado");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Integer id){
        turnoService.eliminarTurno(id);
        return ResponseEntity.ok("Turno eliminado");
    }

}
