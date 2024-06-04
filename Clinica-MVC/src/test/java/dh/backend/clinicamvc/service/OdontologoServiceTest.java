package dh.backend.clinicamvc.service;


import dh.backend.clinicamvc.dao.impl.OdontologoDaoH2;
import dh.backend.clinicamvc.model.Odontologo;
import dh.backend.clinicamvc.service.impl.OdontologoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceTest {
    private static Logger LOGGER = LoggerFactory.getLogger(OdontologoServiceTest.class);
    private static OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());

    @BeforeAll
    static void crearTablas(){
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/clinicaMVC2805;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
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