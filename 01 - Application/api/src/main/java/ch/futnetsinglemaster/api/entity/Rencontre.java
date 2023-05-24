package ch.futnetsinglemaster.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "rencontre")
public class Rencontre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_Rencontre", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "Valide", nullable = false)
    private Byte valide;

    @NotNull
    @Column(name = "Date", nullable = false)
    private LocalDate date;

}