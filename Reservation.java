package com.example.hotel;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    @JsonProperty("reservation_id")
    private Integer reservationId;

    @Column(name = "guest_name", nullable = false)
    @JsonProperty("guest_name")
    private String guestName;

    @Column(name = "room_number", nullable = false)
    @JsonProperty("room_number")
    private Integer roomNumber;

    @Column(name = "contact_number")
    @JsonProperty("contact_number")
    private String contactNumber;

    @Column(name = "reservation_date", columnDefinition = "TIMESTAMP")
    @JsonProperty("reservation_date")
    private LocalDateTime reservationDate;

    public Reservation() {}

    @PrePersist
    public void prePersist() {
        if (reservationDate == null) reservationDate = LocalDateTime.now();
    }

    public Integer getReservationId() { return reservationId; }
    public void setReservationId(Integer reservationId) { this.reservationId = reservationId; }

    public String getGuestName() { return guestName; }
    public void setGuestName(String guestName) { this.guestName = guestName; }

    public Integer getRoomNumber() { return roomNumber; }
    public void setRoomNumber(Integer roomNumber) { this.roomNumber = roomNumber; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    public LocalDateTime getReservationDate() { return reservationDate; }
    public void setReservationDate(LocalDateTime reservationDate) { this.reservationDate = reservationDate; }
}
