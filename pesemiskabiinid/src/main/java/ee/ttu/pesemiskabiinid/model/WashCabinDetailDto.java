package ee.ttu.pesemiskabiinid.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class WashCabinDetailDto {

    @Max(3)
    public String id;
    public String name;
    public String cabinType;
    public String state;
    public Integer carLength;
    public String building;
    public LocalDate dateCreated;
    public String employee;
    public String email;
}
