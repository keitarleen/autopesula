package ee.ttu.pesemiskabiinid.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WashCabinDetailViewDto {

    String id;
    String name;
    String type;
    String state;
    Integer carLength;
    String building;
    LocalDate registration;
    String employee;
    String email;
    String categoryName;
    String categoryType;
}
