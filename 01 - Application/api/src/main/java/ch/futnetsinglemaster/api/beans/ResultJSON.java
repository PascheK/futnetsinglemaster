package ch.futnetsinglemaster.api.beans;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultJSON {

    private int responseCode;
    private String responseTitle;
    private String responseText;

}
