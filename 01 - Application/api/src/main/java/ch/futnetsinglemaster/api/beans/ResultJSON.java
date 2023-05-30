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
    private Object responseObject;

    public ResultJSON(int responseCode, String responseTitle, String responseText)
    {
        this.responseCode = responseCode;
        this.responseTitle = responseTitle;
        this.responseText = responseText;
    }

}
