package com.example.hotel;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin(origins = "*")
public class ReservationController {

    private final ReservationService service;

    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @GetMapping
    public List<Reservation> list() { return service.getAll(); }

    @PostMapping
    public ResponseEntity<Reservation> create(@RequestBody Reservation r) {
        Reservation saved = service.create(r);
        return ResponseEntity.ok(saved);
    }

   @GetMapping("/{id}")
public ResponseEntity<?> getRoomNumber(@PathVariable Integer id, @RequestParam String guest) {
    // Retrieve the optional reservation
    var opt = service.getByIdAndGuest(id, guest);

    if (opt.isPresent()) {
        // Successful branch returns a Reservation
        return ResponseEntity.ok(opt.get());
    } else {
        // Not-found branch returns a String message
        return ResponseEntity.status(404).body("Reservation not found");
    }
}


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Reservation updates) {
        try {
            Reservation updated = service.update(id, updates);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        if (!service.getById(id).isPresent()) {
            return ResponseEntity.status(404).body("Reservation not found");
        }
        service.delete(id);
        return ResponseEntity.ok("Deleted");
    }
}
