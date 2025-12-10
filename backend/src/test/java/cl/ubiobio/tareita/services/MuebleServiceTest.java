package cl.ubiobio.tareita.services;

import cl.ubiobio.tareita.models.Mueble;
import cl.ubiobio.tareita.repositories.MuebleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("MuebleService Unit Tests")
class MuebleServiceTest {

    @Mock
    private MuebleRepository muebleRepository;

    @InjectMocks
    private MuebleService muebleService;

    private Mueble testMueble;

    @BeforeEach
    void setUp() {
        testMueble = new Mueble("Silla Ejecutiva", "Silla", 50000, 10, true, "Grande", "Cuero");
        testMueble.setId(1);
    }

    @Test
    @DisplayName("Should return all muebles")
    void testGetAllMuebles() {
        // Arrange
        Mueble mueble2 = new Mueble("Mesa Escritorio", "Mesa", 80000, 5, true, "Mediano", "Madera");
        List<Mueble> expectedMuebles = Arrays.asList(testMueble, mueble2);
        when(muebleRepository.findAll()).thenReturn(expectedMuebles);

        // Act
        List<Mueble> result = muebleService.getAllMuebles();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(testMueble.getNombre(), result.get(0).getNombre());
        verify(muebleRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return mueble by id when exists")
    void testGetMuebleById_WhenExists() {
        // Arrange
        when(muebleRepository.findById(1)).thenReturn(Optional.of(testMueble));

        // Act
        Optional<Mueble> result = muebleService.getMuebleById(1);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(testMueble.getNombre(), result.get().getNombre());
        verify(muebleRepository, times(1)).findById(1);
    }

    @Test
    @DisplayName("Should return empty when mueble does not exist")
    void testGetMuebleById_WhenNotExists() {
        // Arrange
        when(muebleRepository.findById(999)).thenReturn(Optional.empty());

        // Act
        Optional<Mueble> result = muebleService.getMuebleById(999);

        // Assert
        assertFalse(result.isPresent());
        verify(muebleRepository, times(1)).findById(999);
    }

    @Test
    @DisplayName("Should return muebles filtered by estado")
    void testGetMueblesByEstado() {
        // Arrange
        List<Mueble> activeMuebles = Arrays.asList(testMueble);
        when(muebleRepository.findByEstado(true)).thenReturn(activeMuebles);

        // Act
        List<Mueble> result = muebleService.getMueblesByEstado(true);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.get(0).getEstado());
        verify(muebleRepository, times(1)).findByEstado(true);
    }

    @Test
    @DisplayName("Should return muebles filtered by tipo")
    void testGetMueblesByTipo() {
        // Arrange
        List<Mueble> sillas = Arrays.asList(testMueble);
        when(muebleRepository.findByTipo("Silla")).thenReturn(sillas);

        // Act
        List<Mueble> result = muebleService.getMueblesByTipo("Silla");

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Silla", result.get(0).getTipo());
        verify(muebleRepository, times(1)).findByTipo("Silla");
    }

    @Test
    @DisplayName("Should return muebles filtered by material")
    void testGetMueblesByMaterial() {
        // Arrange
        List<Mueble> cueroMuebles = Arrays.asList(testMueble);
        when(muebleRepository.findByMaterial("Cuero")).thenReturn(cueroMuebles);

        // Act
        List<Mueble> result = muebleService.getMueblesByMaterial("Cuero");

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Cuero", result.get(0).getMaterial());
        verify(muebleRepository, times(1)).findByMaterial("Cuero");
    }

    @Test
    @DisplayName("Should create new mueble successfully")
    void testCreateMueble() {
        // Arrange
        Mueble newMueble = new Mueble("Sofa", "Sofa", 120000, 3, true, "Grande", "Tela");
        when(muebleRepository.save(any(Mueble.class))).thenReturn(newMueble);

        // Act
        Mueble result = muebleService.createMueble(newMueble);

        // Assert
        assertNotNull(result);
        assertEquals("Sofa", result.getNombre());
        verify(muebleRepository, times(1)).save(newMueble);
    }

    @Test
    @DisplayName("Should update existing mueble successfully")
    void testUpdateMueble_WhenExists() {
        // Arrange
        Mueble updatedDetails = new Mueble("Silla Ejecutiva Premium", "Silla", 60000, 15, true, "Grande", "Cuero Premium");
        when(muebleRepository.findById(1)).thenReturn(Optional.of(testMueble));
        when(muebleRepository.save(any(Mueble.class))).thenReturn(testMueble);

        // Act
        Mueble result = muebleService.updateMueble(1, updatedDetails);

        // Assert
        assertNotNull(result);
        assertEquals("Silla Ejecutiva Premium", result.getNombre());
        assertEquals(60000, result.getPrecioBase());
        verify(muebleRepository, times(1)).findById(1);
        verify(muebleRepository, times(1)).save(testMueble);
    }

    @Test
    @DisplayName("Should throw exception when updating non-existent mueble")
    void testUpdateMueble_WhenNotExists() {
        // Arrange
        Mueble updatedDetails = new Mueble("Silla", "Silla", 60000, 15, true, "Grande", "Cuero");
        when(muebleRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            muebleService.updateMueble(999, updatedDetails);
        });
        assertEquals("Mueble no encontrado con id: 999", exception.getMessage());
        verify(muebleRepository, times(1)).findById(999);
        verify(muebleRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should delete mueble successfully when exists")
    void testDeleteMueble_WhenExists() {
        // Arrange
        when(muebleRepository.findById(1)).thenReturn(Optional.of(testMueble));
        doNothing().when(muebleRepository).delete(testMueble);

        // Act
        muebleService.deleteMueble(1);

        // Assert
        verify(muebleRepository, times(1)).findById(1);
        verify(muebleRepository, times(1)).delete(testMueble);
    }

    @Test
    @DisplayName("Should throw exception when deleting non-existent mueble")
    void testDeleteMueble_WhenNotExists() {
        // Arrange
        when(muebleRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            muebleService.deleteMueble(999);
        });
        assertEquals("Mueble no encontrado con id: 999", exception.getMessage());
        verify(muebleRepository, times(1)).findById(999);
        verify(muebleRepository, never()).delete(any());
    }
}
