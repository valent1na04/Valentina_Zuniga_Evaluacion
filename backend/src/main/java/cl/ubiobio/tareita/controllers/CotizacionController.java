package cl.ubiobio.tareita.controllers;

import cl.ubiobio.tareita.models.Cotizacion;
import cl.ubiobio.tareita.services.CotizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cotizaciones")
@CrossOrigin(origins = "*")
public class CotizacionController {

    @Autowired
    private CotizacionService cotizacionService;

    @GetMapping
    public ResponseEntity<List<Cotizacion>> getAllCotizaciones() {
        List<Cotizacion> cotizaciones = cotizacionService.getAllCotizaciones();
        return new ResponseEntity<>(cotizaciones, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cotizacion> getCotizacionById(@PathVariable Integer id) {
        return cotizacionService.getCotizacionById(id)
                .map(cotizacion -> new ResponseEntity<>(cotizacion, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/cliente/{cliente}")
    public ResponseEntity<List<Cotizacion>> getCotizacionesByCliente(@PathVariable String cliente) {
        List<Cotizacion> cotizaciones = cotizacionService.getCotizacionesByCliente(cliente);
        return new ResponseEntity<>(cotizaciones, HttpStatus.OK);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Cotizacion>> getCotizacionesByEstado(@PathVariable String estado) {
        List<Cotizacion> cotizaciones = cotizacionService.getCotizacionesByEstado(estado);
        return new ResponseEntity<>(cotizaciones, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cotizacion> createCotizacion(@RequestBody Cotizacion cotizacion) {
        Cotizacion newCotizacion = cotizacionService.createCotizacion(cotizacion);
        return new ResponseEntity<>(newCotizacion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cotizacion> updateCotizacion(@PathVariable Integer id, @RequestBody Cotizacion cotizacion) {
        try {
            Cotizacion updatedCotizacion = cotizacionService.updateCotizacion(id, cotizacion);
            return new ResponseEntity<>(updatedCotizacion, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCotizacion(@PathVariable Integer id) {
        try {
            cotizacionService.deleteCotizacion(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
