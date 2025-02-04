package entities;

import com.ucab.cmcapp.common.entities.Coordenada;
import com.ucab.cmcapp.common.entities.Zona_Segura;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class CoordenadaTest {

    @Test
    public void testGetAndSetMethods() {
        // Arrange
        Zona_Segura zonaSeguraMock = mock(Zona_Segura.class);

        Coordenada coordenada = new Coordenada();
        coordenada.set_id(1);
        coordenada.set_latitud(10.0f);
        coordenada.set_longitud(20.0f);
        coordenada.set_zona_segura(zonaSeguraMock);

        // Act & Assert
        assertEquals(1, coordenada.get_id());
        assertEquals(10.0f, coordenada.get_latitud());
        assertEquals(20.0f, coordenada.get_longitud());
        assertEquals(zonaSeguraMock, coordenada.get_zona_segura());
    }

    @Test
    public void testConstructor1() {
        // Arrange
        Coordenada coordenadaOriginal = new Coordenada();
        coordenadaOriginal.set_latitud(30.0f);
        coordenadaOriginal.set_longitud(40.0f);

        // Act
        Coordenada coordenadaCopia = new Coordenada(coordenadaOriginal);

        // Assert
        assertEquals(30.0f, coordenadaCopia.get_latitud());
        assertEquals(40.0f, coordenadaCopia.get_longitud());
    }

    @Test
    public void testIdConstructor2() {
        // Arrange & Act
        Coordenada coordenada = new Coordenada(5);

        // Assert
        assertEquals(5, coordenada.get_id());
    }
}