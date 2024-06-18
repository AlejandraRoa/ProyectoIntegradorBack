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
import java.util.Optional;

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
    @DisplayName("Testear que un odontólogo fue guardado")
    void testPacienteGuardado(){
        Odontologo odontologoDB = odontologoService.registrarOdontologo(odontologo);
        assertNotNull(odontologoDB);
    }


    @Test
    @DisplayName("Testear busqueda odontologo por id")
    void testPacientePorId(){
        Integer id = 1;
        Optional<Odontologo> odontologoEncontrado = odontologoService.buscarPorId(id);
        Odontologo paciente1 = odontologoEncontrado.get();

        assertEquals(id, paciente1.getId());
    }


}