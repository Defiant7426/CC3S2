package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TowerTest {

    @Test
    void testCreateTowerWithB() {
        Tower t = new Tower('B');
        assertEquals(t.getSymbol(),'B');
    }

    @Test
    void testCreateTowerWithT(){
        Tower t = new Tower('T');
        assertEquals(t.getSymbol(), 'T');
    }

    @Test
    void testCreateTowerWithInvalidParamether(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Tower('X'));
        assertEquals("El simbolo de la torre no es valido", exception.getMessage());
    }
}
