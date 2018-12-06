package ee.ttu.pesemiskabiinid.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WashCabinDetailDto {

    @Max(3)
    String id;
    String name;
    String cabinType;
    String state;
    Integer carLength;
    String building;
    LocalDate dateCreated;
    String employee;
    String email;
}
