package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;


class TowerDefenseGameTest {
    @Mock
    private Map mockMap;

    @Mock
    private Player mockPlayer;

    @InjectMocks
    private TowerDefenseGame game;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPlaceTower() {
        Tower mockTower = mock(Tower.class);
        game.placeTower(mockTower, 2, 2);
        verify(mockMap).placeTower(mockTower, 2, 2);
    }
}
