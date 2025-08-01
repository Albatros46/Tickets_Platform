package com.tickets.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
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
@Table(name="users")
public class User {
    @Id
    @Column(name = "id",updatable = false,nullable = false)
    private UUID id;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "email",nullable = false)
    private String email;
    @CreatedDate
    @Column(name = "created_at",updatable = false,nullable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "organizer",cascade = CascadeType.ALL)
    private List<Event> organizedEvents=new ArrayList<>(); //Organized events

    @ManyToMany
    @JoinTable(
            name = "user_attending_events",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name="event_id")
    )
    private List<Event> attendingEvents=new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "user_staffing_events",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name="event_id")
    )
    private List<Event> staffingEvents=new ArrayList<>();
    /*
    * TODO
    *  1-Organized events
    *  2-Attending events
    *  3-Staffing events
    * */

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(createdAt, user.createdAt) && Objects.equals(updatedAt, user.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, createdAt, updatedAt);
    }
}
