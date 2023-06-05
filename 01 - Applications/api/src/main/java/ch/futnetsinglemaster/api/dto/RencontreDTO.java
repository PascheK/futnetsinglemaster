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
public class RencontreDTO {
    private int rencontreID;
    private Byte valide;
    private LocalDate date;
}
