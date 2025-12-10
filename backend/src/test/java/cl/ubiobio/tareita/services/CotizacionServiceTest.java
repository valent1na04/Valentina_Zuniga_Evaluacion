package cl.ubiobio.tareita.services;

import cl.ubiobio.tareita.models.Cotizacion;
import cl.ubiobio.tareita.repositories.CotizacionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("CotizacionService Unit Tests")
class CotizacionServiceTest {

    @Mock
    private CotizacionRepository cotizacionRepository;

    @InjectMocks
    private CotizacionService cotizacionService;

    private Cotizacion testCotizacion;

    @BeforeEach
    void setUp() {
        testCotizacion = new Cotizacion(LocalDateTime.now(), "Juan Perez", 150000, "Pendiente");
        testCotizacion.setId(1);
    }

    @Test
    @DisplayName("Should return all cotizaciones")
    void testGetAllCotizaciones() {
        // Arrange
        Cotizacion cotizacion2 = new Cotizacion(LocalDateTime.now(), "Maria Lopez", 200000, "Aprobada");
        List<Cotizacion> expectedCotizaciones = Arrays.asList(testCotizacion, cotizacion2);
        when(cotizacionRepository.findAll()).thenReturn(expectedCotizaciones);

        // Act
        List<Cotizacion> result = cotizacionService.getAllCotizaciones();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(cotizacionRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return cotizacion by id when exists")
    void testGetCotizacionById_WhenExists() {
        // Arrange
        when(cotizacionRepository.findById(1)).thenReturn(Optional.of(testCotizacion));

        // Act
        Optional<Cotizacion> result = cotizacionService.getCotizacionById(1);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Juan Perez", result.get().getCliente());
        verify(cotizacionRepository, times(1)).findById(1);
    }

    @Test
    @DisplayName("Should return empty when cotizacion does not exist")
    void testGetCotizacionById_WhenNotExists() {
        // Arrange
        when(cotizacionRepository.findById(999)).thenReturn(Optional.empty());

        // Act
        Optional<Cotizacion> result = cotizacionService.getCotizacionById(999);

        // Assert
        assertFalse(result.isPresent());
        verify(cotizacionRepository, times(1)).findById(999);
    }

    @Test
    @DisplayName("Should return cotizaciones filtered by cliente")
    void testGetCotizacionesByCliente() {
        // Arrange
        List<Cotizacion> clienteCotizaciones = Arrays.asList(testCotizacion);
        when(cotizacionRepository.findByCliente("Juan Perez")).thenReturn(clienteCotizaciones);

        // Act
        List<Cotizacion> result = cotizacionService.getCotizacionesByCliente("Juan Perez");

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Juan Perez", result.get(0).getCliente());
        verify(cotizacionRepository, times(1)).findByCliente("Juan Perez");
    }

    @Test
    @DisplayName("Should return cotizaciones filtered by estado")
    void testGetCotizacionesByEstado() {
        // Arrange
        List<Cotizacion> pendingCotizaciones = Arrays.asList(testCotizacion);
        when(cotizacionRepository.findByEstado("Pendiente")).thenReturn(pendingCotizaciones);

        // Act
        List<Cotizacion> result = cotizacionService.getCotizacionesByEstado("Pendiente");

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Pendiente", result.get(0).getEstado());
        verify(cotizacionRepository, times(1)).findByEstado("Pendiente");
    }

    @Test
    @DisplayName("Should create new cotizacion successfully")
    void testCreateCotizacion() {
        // Arrange
        Cotizacion newCotizacion = new Cotizacion(LocalDateTime.now(), "Pedro Garcia", 300000, "Pendiente");
        when(cotizacionRepository.save(any(Cotizacion.class))).thenReturn(newCotizacion);

        // Act
        Cotizacion result = cotizacionService.createCotizacion(newCotizacion);

        // Assert
        assertNotNull(result);
        assertEquals("Pedro Garcia", result.getCliente());
        verify(cotizacionRepository, times(1)).save(newCotizacion);
    }

    @Test
    @DisplayName("Should update existing cotizacion successfully")
    void testUpdateCotizacion_WhenExists() {
        // Arrange
        Cotizacion updatedDetails = new Cotizacion(LocalDateTime.now().plusDays(1), "Juan Perez", 180000, "Aprobada");
        when(cotizacionRepository.findById(1)).thenReturn(Optional.of(testCotizacion));
        when(cotizacionRepository.save(any(Cotizacion.class))).thenReturn(testCotizacion);

        // Act
        Cotizacion result = cotizacionService.updateCotizacion(1, updatedDetails);

        // Assert
        assertNotNull(result);
        assertEquals("Aprobada", result.getEstado());
        assertEquals(180000, result.getTotal());
        verify(cotizacionRepository, times(1)).findById(1);
        verify(cotizacionRepository, times(1)).save(testCotizacion);
    }

    @Test
    @DisplayName("Should throw exception when updating non-existent cotizacion")
    void testUpdateCotizacion_WhenNotExists() {
        // Arrange
        Cotizacion updatedDetails = new Cotizacion(LocalDateTime.now(), "Test", 100000, "Pendiente");
        when(cotizacionRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            cotizacionService.updateCotizacion(999, updatedDetails);
        });
        assertEquals("Cotizacion no encontrada con id: 999", exception.getMessage());
        verify(cotizacionRepository, times(1)).findById(999);
        verify(cotizacionRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should delete cotizacion successfully when exists")
    void testDeleteCotizacion_WhenExists() {
        // Arrange
        when(cotizacionRepository.findById(1)).thenReturn(Optional.of(testCotizacion));
        doNothing().when(cotizacionRepository).delete(testCotizacion);

        // Act
        cotizacionService.deleteCotizacion(1);

        // Assert
        verify(cotizacionRepository, times(1)).findById(1);
        verify(cotizacionRepository, times(1)).delete(testCotizacion);
    }

    @Test
    @DisplayName("Should throw exception when deleting non-existent cotizacion")
    void testDeleteCotizacion_WhenNotExists() {
        // Arrange
        when(cotizacionRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            cotizacionService.deleteCotizacion(999);
        });
        assertEquals("Cotizacion no encontrada con id: 999", exception.getMessage());
        verify(cotizacionRepository, times(1)).findById(999);
        verify(cotizacionRepository, never()).delete(any());
    }
}
