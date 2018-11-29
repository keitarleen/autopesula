package ee.ttu.pesemiskabiinid.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WashCabin {

    private String id;
    private Employee registrator;
    private String state;
    private String type;
    private String name;
    private Building building;
    private Integer carSize;
    private LocalDate dateCreated;
    private String comment;

}
