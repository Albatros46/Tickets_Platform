package com.tickets.model;

import com.tickets.domain.EventStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id",updatable = false)
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "start_date")
    private LocalDateTime start;
    @Column(name = "end_date")
    private LocalDateTime end;
    @Column(name = "venue",nullable = true)
    private String venue;
    @Column(name = "sales_start")
    private LocalDateTime salesStart;
    @Column(name = "sales_end")
    private LocalDateTime salesEnd;
    @Column(name = "status",nullable = false)
    @Enumerated(EnumType.STRING)
    private EventStatusEnum status;
    //Relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id")
    private User organizer;
    @ManyToMany(mappedBy = "attendingEvents")
    private List<User> attendees=new ArrayList<>();
    @ManyToMany(mappedBy = "staffingEvents")
    private List<User> staff=new ArrayList<>();

    @OneToMany(mappedBy = "event",cascade = CascadeType.ALL)
    private List<TicketType> ticketTypes=new ArrayList<>();
    //-------------
    @CreatedDate
    @Column(name = "created_at",updatable = false,nullable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updatedAt;
    //-----------------


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id) && Objects.equals(name, event.name) && Objects.equals(start, event.start) && Objects.equals(end, event.end) && Objects.equals(venue, event.venue) && Objects.equals(salesStart, event.salesStart) && Objects.equals(salesEnd, event.salesEnd) && status == event.status && Objects.equals(createdAt, event.createdAt) && Objects.equals(updatedAt, event.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, start, end, venue, salesStart, salesEnd, status, createdAt, updatedAt);
    }
}
