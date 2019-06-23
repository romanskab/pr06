package com.oktenweb.pr06.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.LocalDate;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int application_id;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private Course title;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private Client client;

    @Override
    public String toString() {
        return "Application{" +
                "application_id=" + application_id +
                ", date='" + date + '\'' +
                ", title='" + title + '\'' +
                ", client_name='" + client.getName() + '\'' +
                '}';
    }
}
