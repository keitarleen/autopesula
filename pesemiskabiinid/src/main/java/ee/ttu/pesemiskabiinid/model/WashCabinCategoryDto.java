package ee.ttu.pesemiskabiinid.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;

@Data
@AllArgsConstructor
public class WashCabinCategoryDto {

    @Max(3)
    private String id;
    private String category;
}
