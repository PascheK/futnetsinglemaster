package ch.futnetsinglemaster.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RencontresDTO {
        private int idRencontre;
        private LocalDate date;
        private String joueur1;

        private String joueur2;

        private int score1;
        private int score2;
        private boolean valide;
    }

