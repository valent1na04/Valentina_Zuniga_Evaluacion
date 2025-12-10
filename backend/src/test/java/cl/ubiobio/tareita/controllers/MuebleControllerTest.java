package cl.ubiobio.tareita.controllers;

import cl.ubiobio.tareita.dto.MuebleDTO;
import cl.ubiobio.tareita.mappers.MuebleMapper;
import cl.ubiobio.tareita.models.Mueble;
import cl.ubiobio.tareita.services.MuebleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("MuebleController Unit Tests")
class MuebleControllerTest {

    @Mock
    private MuebleService muebleService;

    @Mock
    private MuebleMapper muebleMapper;

    @InjectMocks
    private MuebleController muebleController;

    private Mueble testMueble;
    private MuebleDTO testMuebleDTO;

    @BeforeEach
    void setUp() {
        testMueble = new Mueble("Silla Ejecutiva", "Silla", 50000, 10, true, "Grande", "Cuero");
        testMueble.setId(1);

        testMuebleDTO = MuebleDTO.builder()
                .id(1)
                .nombre("Silla Ejecutiva")
                .tipo("Silla")
                .precioBase(50000)
                .stock(10)
                .estado(true)
                .tamanio("Grande")
                .material("Cuero")
                .build();
    }

    @Test
    @DisplayName("Should return all muebles as DTOs")
    void testGetAllMuebles() {
        // Arrange
        Mueble mueble2 = new Mueble("Mesa", "Mesa", 80000, 5, true, "Mediano", "Madera");
        MuebleDTO dto2 = MuebleDTO.builder()
                .nombre("Mesa")
                .tipo("Mesa")
                .precioBase(80000)
                .build();

        when(muebleService.getAllMuebles()).thenReturn(Arrays.asList(testMueble, mueble2));
        when(muebleMapper.toDTO(testMueble)).thenReturn(testMuebleDTO);
        when(muebleMapper.toDTO(mueble2)).thenReturn(dto2);

        // Act
        ResponseEntity<List<MuebleDTO>> response = muebleController.getAllMuebles();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        verify(muebleService, times(1)).getAllMuebles();
        verify(muebleMapper, times(2)).toDTO(any(Mueble.class));
    }

    @Test
    @DisplayName("Should return mueble by id when exists")
    void testGetMuebleById_WhenExists() {
        // Arrange
        when(muebleService.getMuebleById(1)).thenReturn(Optional.of(testMueble));
        when(muebleMapper.toDTO(testMueble)).thenReturn(testMuebleDTO);

        // Act
        ResponseEntity<MuebleDTO> response = muebleController.getMuebleById(1);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Silla Ejecutiva", response.getBody().getNombre());
        verify(muebleService, times(1)).getMuebleById(1);
    }

    @Test
    @DisplayName("Should return NOT_FOUND when mueble does not exist")
    void testGetMuebleById_WhenNotExists() {
        // Arrange
        when(muebleService.getMuebleById(999)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<MuebleDTO> response = muebleController.getMuebleById(999);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(muebleService, times(1)).getMuebleById(999);
        verify(muebleMapper, never()).toDTO(any());
    }

    @Test
    @DisplayName("Should return muebles filtered by estado")
    void testGetMueblesByEstado() {
        // Arrange
        when(muebleService.getMueblesByEstado(true)).thenReturn(Arrays.asList(testMueble));
        when(muebleMapper.toDTO(testMueble)).thenReturn(testMuebleDTO);

        // Act
        ResponseEntity<List<MuebleDTO>> response = muebleController.getMueblesByEstado(true);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(muebleService, times(1)).getMueblesByEstado(true);
    }

    @Test
    @DisplayName("Should return muebles filtered by tipo")
    void testGetMueblesByTipo() {
        // Arrange
        when(muebleService.getMueblesByTipo("Silla")).thenReturn(Arrays.asList(testMueble));
        when(muebleMapper.toDTO(testMueble)).thenReturn(testMuebleDTO);

        // Act
        ResponseEntity<List<MuebleDTO>> response = muebleController.getMueblesByTipo("Silla");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(muebleService, times(1)).getMueblesByTipo("Silla");
    }

    @Test
    @DisplayName("Should return muebles filtered by material")
    void testGetMueblesByMaterial() {
        // Arrange
        when(muebleService.getMueblesByMaterial("Cuero")).thenReturn(Arrays.asList(testMueble));
        when(muebleMapper.toDTO(testMueble)).thenReturn(testMuebleDTO);

        // Act
        ResponseEntity<List<MuebleDTO>> response = muebleController.getMueblesByMaterial("Cuero");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(muebleService, times(1)).getMueblesByMaterial("Cuero");
    }

    @Test
    @DisplayName("Should create new mueble successfully")
    void testCreateMueble() {
        // Arrange
        when(muebleMapper.toEntity(testMuebleDTO)).thenReturn(testMueble);
        when(muebleService.createMueble(testMueble)).thenReturn(testMueble);
        when(muebleMapper.toDTO(testMueble)).thenReturn(testMuebleDTO);

        // Act
        ResponseEntity<MuebleDTO> response = muebleController.createMueble(testMuebleDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Silla Ejecutiva", response.getBody().getNombre());
        verify(muebleService, times(1)).createMueble(testMueble);
    }

    @Test
    @DisplayName("Should update mueble successfully when exists")
    void testUpdateMueble_WhenExists() {
        // Arrange
        MuebleDTO updatedDTO = MuebleDTO.builder()
                .id(1)
                .nombre("Silla Premium")
                .tipo("Silla")
                .precioBase(60000)
                .stock(15)
                .estado(true)
                .tamanio("Grande")
                .material("Cuero Premium")
                .build();

        Mueble updatedMueble = new Mueble("Silla Premium", "Silla", 60000, 15, true, "Grande", "Cuero Premium");
        updatedMueble.setId(1);

        when(muebleMapper.toEntity(updatedDTO)).thenReturn(updatedMueble);
        when(muebleService.updateMueble(1, updatedMueble)).thenReturn(updatedMueble);
        when(muebleMapper.toDTO(updatedMueble)).thenReturn(updatedDTO);

        // Act
        ResponseEntity<MuebleDTO> response = muebleController.updateMueble(1, updatedDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Silla Premium", response.getBody().getNombre());
        verify(muebleService, times(1)).updateMueble(1, updatedMueble);
    }

    @Test
    @DisplayName("Should return NOT_FOUND when updating non-existent mueble")
    void testUpdateMueble_WhenNotExists() {
        // Arrange
        when(muebleMapper.toEntity(testMuebleDTO)).thenReturn(testMueble);
        when(muebleService.updateMueble(999, testMueble)).thenThrow(new RuntimeException("Mueble no encontrado con id: 999"));

        // Act
        ResponseEntity<MuebleDTO> response = muebleController.updateMueble(999, testMuebleDTO);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(muebleService, times(1)).updateMueble(999, testMueble);
    }

    @Test
    @DisplayName("Should delete mueble successfully when exists")
    void testDeleteMueble_WhenExists() {
        // Arrange
        doNothing().when(muebleService).deleteMueble(1);

        // Act
        ResponseEntity<Void> response = muebleController.deleteMueble(1);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(muebleService, times(1)).deleteMueble(1);
    }

    @Test
    @DisplayName("Should return NOT_FOUND when deleting non-existent mueble")
    void testDeleteMueble_WhenNotExists() {
        // Arrange
        doThrow(new RuntimeException("Mueble no encontrado con id: 999")).when(muebleService).deleteMueble(999);

        // Act
        ResponseEntity<Void> response = muebleController.deleteMueble(999);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(muebleService, times(1)).deleteMueble(999);
    }
}
