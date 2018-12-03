package ee.ttu.pesemiskabiinid.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WashCabinStatementDto {

    private String id;
    private String state;
    private int total;

}
