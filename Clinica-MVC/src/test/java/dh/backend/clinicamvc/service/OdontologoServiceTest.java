package dh.backend.clinicamvc.service;

import dh.backend.clinicamvc.entity.Odontologo;
import dh.backend.clinicamvc.service.impl.OdontologoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OdontologoServiceTest {

    private static Logger LOGGER = LoggerFactory.getLogger(OdontologoServiceTest.class);

    @Autowired
    private OdontologoService odontologoService;

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
    void testOdontologoGuardado(){
        Odontologo odontologoDB = odontologoService.registrarOdontologo(odontologo);
        assertNotNull(odontologoDB);
    }

    @Test
    @DisplayName("Testear busqueda odontologo por id")
    void testOdontologoPorId(){
        Integer id = 1;
        Optional<Odontologo> odontologoEncontrado = odontologoService.buscarPorId(id);
        assertTrue(odontologoEncontrado.isPresent());
        assertEquals(id, odontologoEncontrado.get().getId());
    }
}
