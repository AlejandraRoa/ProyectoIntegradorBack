package dh.backend.clinicamvc.service;


import dh.backend.clinicamvc.dao.impl.OdontologoDaoH2;
import dh.backend.clinicamvc.model.Odontologo;
import dh.backend.clinicamvc.service.impl.OdontologoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceTest {
    private static Logger LOGGER = LoggerFactory.getLogger(OdontologoServiceTest.class);
    @Autowired
    private static OdontologoService odontologoService;
    private Odontologo odontologo;

    @BeforeEach
    void setUp(){
        odontologo = new Odontologo();
        odontologo.setNombre("Menganito");
        odontologo.setApellido("Cosme");
        odontologo.setNumeroMatricula("1502448765");
    }

    @Test
    @DisplayName("Testear busqueda todos los odontologos")
    void testBusquedaTodos() {

        Odontologo odontologo = new Odontologo("Urrego","Maria","16858361");
        Odontologo odontologo2 = new Odontologo("Sabando","Sergio","16857863");
        odontologoService.registrarOdontologo(odontologo);
        odontologoService.registrarOdontologo(odontologo2);

        List<Odontologo> odontologos = odontologoService.buscarTodos();
        assertEquals(2, odontologos.size());

    }

    @Test
    @DisplayName("Testear busqueda odontologo por id")
    void testPacientePorId(){
        Integer id = 1;
        Odontologo odontologoEncontrado = odontologoService.buscarPorId(id);
        assertEquals(id, odontologoEncontrado.getId());
    }


}