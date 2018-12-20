package ee.ttu.pesemiskabiinid.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

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
    List<String> categoryType;

    public WashCabinDetailViewDto(String id, String name, String type, String state, Integer carLength, String building, LocalDate registration, String employee, String email) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.state = state;
        this.carLength = carLength;
        this.building = building;
        this.registration = registration;
        this.employee = employee;
        this.email = email;
    }
}
