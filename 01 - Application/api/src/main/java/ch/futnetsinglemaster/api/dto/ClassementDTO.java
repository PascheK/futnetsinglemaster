package ch.futnetsinglemaster.api.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClassementDTO {
    private String nom;
    private String prenom;
    private String nomEquipe;

    private int Score;
}
