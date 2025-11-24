package com.example.hotel;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Optional<Reservation> findByReservationIdAndGuestName(Integer reservationId, String guestName);
}
