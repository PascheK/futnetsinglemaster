package ch.futnetsinglemaster.api.dto;


import lombok.*;


/**
 * A DTO for the {@link ch.futnetsinglemaster.api.entity.Utilisateur} entity
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PutUserDTO {
    private int id_user;
    private String nom;
    private String prenom;
    private String mail;
    private int role;
    private int nomEquipe;
}
