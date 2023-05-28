package ch.futnetsinglemaster.api.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResultJSON {

    private int responseCode;
    private String responseTitle;
    private String responseText;

}
