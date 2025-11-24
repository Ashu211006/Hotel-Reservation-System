package com.example.hotel;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository repo;

    public ReservationService(ReservationRepository repo) {
        this.repo = repo;
    }

    public List<Reservation> getAll() {
        return repo.findAll();
    }

    public Reservation create(Reservation r) {
        r.setReservationId(null); // ensure insert
        return repo.save(r);
    }

    public Optional<Reservation> getByIdAndGuest(Integer id, String guest) {
        return repo.findByReservationIdAndGuestName(id, guest);
    }

    public Optional<Reservation> getById(Integer id) {
        return repo.findById(id);
    }

    public Reservation update(Integer id, Reservation updates) {
        return repo.findById(id).map(existing -> {
            if (updates.getGuestName() != null) existing.setGuestName(updates.getGuestName());
            if (updates.getRoomNumber() != null) existing.setRoomNumber(updates.getRoomNumber());
            if (updates.getContactNumber() != null) existing.setContactNumber(updates.getContactNumber());
            return repo.save(existing);
        }).orElseThrow(() -> new RuntimeException("Reservation not found: " + id));
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
