package cl.ubiobio.tareita.controllers;

import cl.ubiobio.tareita.models.DetalleCotizacion;
import cl.ubiobio.tareita.services.DetalleCotizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalle-cotizacion")
@CrossOrigin(origins = "*")
public class DetalleCotizacionController {

    @Autowired
    private DetalleCotizacionService detalleCotizacionService;

    @GetMapping
    public ResponseEntity<List<DetalleCotizacion>> getAllDetalles() {
        List<DetalleCotizacion> detalles = detalleCotizacionService.getAllDetalles();
        return new ResponseEntity<>(detalles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleCotizacion> getDetalleById(@PathVariable Integer id) {
        return detalleCotizacionService.getDetalleById(id)
                .map(detalle -> new ResponseEntity<>(detalle, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/cotizacion/{cotizacionId}")
    public ResponseEntity<List<DetalleCotizacion>> getDetallesByCotizacionId(@PathVariable Integer cotizacionId) {
        List<DetalleCotizacion> detalles = detalleCotizacionService.getDetallesByCotizacionId(cotizacionId);
        return new ResponseEntity<>(detalles, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DetalleCotizacion> createDetalle(@RequestBody DetalleCotizacion detalle) {
        DetalleCotizacion newDetalle = detalleCotizacionService.createDetalle(detalle);
        return new ResponseEntity<>(newDetalle, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleCotizacion> updateDetalle(@PathVariable Integer id, @RequestBody DetalleCotizacion detalle) {
        try {
            DetalleCotizacion updatedDetalle = detalleCotizacionService.updateDetalle(id, detalle);
            return new ResponseEntity<>(updatedDetalle, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetalle(@PathVariable Integer id) {
        try {
            detalleCotizacionService.deleteDetalle(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
