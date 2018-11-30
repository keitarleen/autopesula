package ee.ttu.pesemiskabiinid.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WashCabinDto {

    private String id;
    private String name;
    private String type;
    private String state;
}
