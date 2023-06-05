package ch.futnetsinglemaster.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PutRencontreDTO {
        private int rencontreId;
        private int score1;
        private int score2;
        private LocalDate date;
}
