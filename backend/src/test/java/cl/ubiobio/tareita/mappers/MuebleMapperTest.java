package cl.ubiobio.tareita.mappers;

import cl.ubiobio.tareita.dto.MuebleDTO;
import cl.ubiobio.tareita.models.Mueble;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("MuebleMapper Unit Tests")
class MuebleMapperTest {

    private MuebleMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new MuebleMapper();
    }

    @Test
    @DisplayName("Should convert entity to DTO successfully")
    void testToDTO_Success() {
        // Arrange
        Mueble mueble = new Mueble("Silla Ejecutiva", "Silla", 50000, 10, true, "Grande", "Cuero");
        mueble.setId(1);

        // Act
        MuebleDTO dto = mapper.toDTO(mueble);

        // Assert
        assertNotNull(dto);
        assertEquals(1, dto.getId());
        assertEquals("Silla Ejecutiva", dto.getNombre());
        assertEquals("Silla", dto.getTipo());
        assertEquals(50000, dto.getPrecioBase());
        assertEquals(10, dto.getStock());
        assertTrue(dto.getEstado());
        assertEquals("Grande", dto.getTamanio());
        assertEquals("Cuero", dto.getMaterial());
    }

    @Test
    @DisplayName("Should return null when converting null entity to DTO")
    void testToDTO_NullEntity() {
        // Act
        MuebleDTO dto = mapper.toDTO(null);

        // Assert
        assertNull(dto);
    }

    @Test
    @DisplayName("Should handle entity with null values")
    void testToDTO_EntityWithNullValues() {
        // Arrange
        Mueble mueble = new Mueble();
        mueble.setId(1);
        mueble.setNombre("Mesa");

        // Act
        MuebleDTO dto = mapper.toDTO(mueble);

        // Assert
        assertNotNull(dto);
        assertEquals(1, dto.getId());
        assertEquals("Mesa", dto.getNombre());
        assertNull(dto.getTipo());
        assertNull(dto.getPrecioBase());
        assertNull(dto.getStock());
        assertNull(dto.getEstado());
        assertNull(dto.getTamanio());
        assertNull(dto.getMaterial());
    }

    @Test
    @DisplayName("Should convert DTO to entity successfully")
    void testToEntity_Success() {
        // Arrange
        MuebleDTO dto = MuebleDTO.builder()
                .id(2)
                .nombre("Mesa Escritorio")
                .tipo("Mesa")
                .precioBase(80000)
                .stock(5)
                .estado(false)
                .tamanio("Mediano")
                .material("Madera")
                .build();

        // Act
        Mueble entity = mapper.toEntity(dto);

        // Assert
        assertNotNull(entity);
        assertEquals(2, entity.getId());
        assertEquals("Mesa Escritorio", entity.getNombre());
        assertEquals("Mesa", entity.getTipo());
        assertEquals(80000, entity.getPrecioBase());
        assertEquals(5, entity.getStock());
        assertFalse(entity.getEstado());
        assertEquals("Mediano", entity.getTamanio());
        assertEquals("Madera", entity.getMaterial());
    }

    @Test
    @DisplayName("Should return null when converting null DTO to entity")
    void testToEntity_NullDTO() {
        // Act
        Mueble entity = mapper.toEntity(null);

        // Assert
        assertNull(entity);
    }

    @Test
    @DisplayName("Should handle DTO with null values")
    void testToEntity_DTOWithNullValues() {
        // Arrange
        MuebleDTO dto = MuebleDTO.builder()
                .id(3)
                .nombre("Sofa")
                .build();

        // Act
        Mueble entity = mapper.toEntity(dto);

        // Assert
        assertNotNull(entity);
        assertEquals(3, entity.getId());
        assertEquals("Sofa", entity.getNombre());
        assertNull(entity.getTipo());
        assertNull(entity.getPrecioBase());
        assertNull(entity.getStock());
        assertNull(entity.getEstado());
        assertNull(entity.getTamanio());
        assertNull(entity.getMaterial());
    }

    @Test
    @DisplayName("Should maintain data integrity in bidirectional conversion")
    void testBidirectionalConversion() {
        // Arrange
        Mueble originalMueble = new Mueble("Estante", "Estantería", 120000, 8, true, "Alto", "Metal");
        originalMueble.setId(5);

        // Act
        MuebleDTO dto = mapper.toDTO(originalMueble);
        Mueble convertedMueble = mapper.toEntity(dto);

        // Assert
        assertNotNull(convertedMueble);
        assertEquals(originalMueble.getId(), convertedMueble.getId());
        assertEquals(originalMueble.getNombre(), convertedMueble.getNombre());
        assertEquals(originalMueble.getTipo(), convertedMueble.getTipo());
        assertEquals(originalMueble.getPrecioBase(), convertedMueble.getPrecioBase());
        assertEquals(originalMueble.getStock(), convertedMueble.getStock());
        assertEquals(originalMueble.getEstado(), convertedMueble.getEstado());
        assertEquals(originalMueble.getTamanio(), convertedMueble.getTamanio());
        assertEquals(originalMueble.getMaterial(), convertedMueble.getMaterial());
    }

    @Test
    @DisplayName("Should use DTO builder pattern correctly")
    void testDTOBuilderPattern() {
        // Arrange
        Mueble mueble = new Mueble("Cama", "Cama", 200000, 3, true, "King", "Madera Roble");
        mueble.setId(10);

        // Act
        MuebleDTO dto = mapper.toDTO(mueble);

        // Assert - Verify all builder fields were set correctly
        assertNotNull(dto);
        assertEquals(10, dto.getId());
        assertEquals("Cama", dto.getNombre());
        assertEquals("Cama", dto.getTipo());
        assertEquals(200000, dto.getPrecioBase());
        assertEquals(3, dto.getStock());
        assertTrue(dto.getEstado());
        assertEquals("King", dto.getTamanio());
        assertEquals("Madera Roble", dto.getMaterial());
    }
}
