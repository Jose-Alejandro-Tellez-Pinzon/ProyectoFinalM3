package com.devsenior.atellez_jbenavides.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.devsenior.atellez_jbenavides.exception.NotFoundException;

public class UserServiceTest {

    private UserService service;

    @BeforeEach
    void setup() {
        service = new UserService();
    }

    @Test
    void testAddUsers() throws NotFoundException {

        // GIVEN - Preparar los datos de la prueba
        String name = "Juan Perez";
        String email = "juanP231@gmail.com";
        String id = "214354";
        LocalDate registerDate = LocalDate.of(2023, 10, 1);

        // WHEN - Ejecutar el metodo a probar
        service.addUsers(name, email, id, registerDate);

        // THEN - Verificaciones que el metodo se ejecutó bien
        var user = service.getUserById(id);
        assertNotNull(user);
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(id, user.getId());
    }

    @Test
    void testDeleteExistingUser() throws NotFoundException {
        // GIVEN
        String name = "Juan Perez";
        String email = "juanP231@gmail.com";
        String id = "214354";
        LocalDate registerDate = LocalDate.of(2023, 10, 1);
        service.addUsers(name, email, id, registerDate);

        // WHEN
        service.deleteUser(id);

        // THEN
        try {
            service.getUserById(id);
            fail();
        } catch (NotFoundException e) {
            assertTrue(true);
        }
    }

    @Test
    void testDeleteNonExistingUser() {
        // GIVEN
        var id = "123456789";

        // WHEN - THEN
        assertThrows(NotFoundException.class,
                () -> {
                    service.deleteUser(id);
                });
    }

    @Test
    void testDeleteWithExistingBooksButNotGivenIsbn() {
        // GIVEN
        service.addUsers("Alejandro Tellez", "A.tellez@gmai.com", "1034278586");
        var id = "1234567890";

        // WHEN - THEN
        assertThrows(NotFoundException.class,
                () -> service.deleteUser(id));
    }

    @Test
    void testGetAllUsers() {
        // GIVEN

        // WHEN
        var user = service.getAllUsers();

        // THEN
        assertNotNull(user);
        assertEquals(0, user.size());
    }

    @Test
    void testGetUserById() {

        // GIVEN
        service.addUsers("Alejandro Tellez", "A.tellez@gmai.com", "1034278586");
        var id = "1234567890";

        // WHEN - THEN
        assertThrows(NotFoundException.class,
                () -> service.getUserById(id));
    }

    @Test
    void testUpdateUserEmail() throws NotFoundException {

        // GIVEN
        String name = "Juan Perez";
        String email = "juanP231@gmail.com";
        String id = "214354";
        LocalDate registerDate = LocalDate.of(2023, 10, 1);
        service.addUsers(name, email, id, registerDate);

        // WHEN
        service.updateUserEmail(id, email);

        // THEN
        var user = service.getUserById(id);
        assertNotNull(user);
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(id, user.getId());


    }

    @Test
    void testUpdateUserName() throws NotFoundException {
        String name = "Juan Perez";
        String email = "juanP231@gmail.com";
        String id = "214354";
        LocalDate registerDate = LocalDate.of(2023, 10, 1);
        service.addUsers(name, email, id, registerDate);

        // WHEN
        service.updateUserName(id,  name);

        // THEN
        var user = service.getUserById(id);
        assertNotNull(user);
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(id, user.getId());
    }
}
