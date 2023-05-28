package ch.futnetsinglemaster.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A DTO for the {@link ch.futnetsinglemaster.api.entity.Utilisateur} entity
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostUserDTO {
    private String nom;
    private String prenom;
    private String mail;
    private int role;
    private int nomEquipe;

}
