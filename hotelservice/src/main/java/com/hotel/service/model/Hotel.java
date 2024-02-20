package com.hotel.service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_hotels")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String location;
    private String about;
}
