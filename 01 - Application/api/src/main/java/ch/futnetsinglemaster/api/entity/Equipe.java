package ch.futnetsinglemaster.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "equipe")
public class Equipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_Equipe", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "nomEquipe", nullable = false, length = 100)
    private String nomEquipe;

}