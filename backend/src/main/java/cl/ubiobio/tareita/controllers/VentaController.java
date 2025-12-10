package cl.ubiobio.tareita.controllers;

import cl.ubiobio.tareita.models.Venta;
import cl.ubiobio.tareita.services.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
@CrossOrigin(origins = "*")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping
    public ResponseEntity<List<Venta>> getAllVentas() {
        List<Venta> ventas = ventaService.getAllVentas();
        return new ResponseEntity<>(ventas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> getVentaById(@PathVariable Integer id) {
        return ventaService.getVentaById(id)
                .map(venta -> new ResponseEntity<>(venta, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Venta>> getVentasByEstado(@PathVariable String estado) {
        List<Venta> ventas = ventaService.getVentasByEstado(estado);
        return new ResponseEntity<>(ventas, HttpStatus.OK);
    }

    @GetMapping("/metodo-pago/{metodoPago}")
    public ResponseEntity<List<Venta>> getVentasByMetodoPago(@PathVariable String metodoPago) {
        List<Venta> ventas = ventaService.getVentasByMetodoPago(metodoPago);
        return new ResponseEntity<>(ventas, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Venta> createVenta(@RequestBody Venta venta) {
        Venta newVenta = ventaService.createVenta(venta);
        return new ResponseEntity<>(newVenta, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venta> updateVenta(@PathVariable Integer id, @RequestBody Venta venta) {
        try {
            Venta updatedVenta = ventaService.updateVenta(id, venta);
            return new ResponseEntity<>(updatedVenta, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenta(@PathVariable Integer id) {
        try {
            ventaService.deleteVenta(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
