package ee.ttu.pesemiskabiinid.controller;

import ee.ttu.pesemiskabiinid.model.*;
import ee.ttu.pesemiskabiinid.service.WashCabinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/cabin")
@CrossOrigin
public class WashCabinController {

    private WashCabinService service;

    @Autowired
    public WashCabinController(WashCabinService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public WashCabin save(WashCabin cabin) throws SQLException {
        service.save(cabin);
        return cabin;
    }

    @GetMapping("/getActiveInactive")
    public List<WashCabinDto> getActiveInactiveCabins() throws SQLException {
        return service.getActiveInactiveCabins();
    }

    @GetMapping("/getStatement")
    public List<WashCabinStatementDto> getCabinStatements() throws SQLException {
        return service.getCabinStatement();
    }

    @GetMapping("/getAll")
    public List<WashCabinDetailDto> getAllCabins() throws SQLException {
        return service.getAllCabins();
    }

    @GetMapping("/endCabin/{id}")
    public String endCabin(@PathVariable("id") String id) throws SQLException {
        return service.endCabin(id);
    }

    @GetMapping("getDetails/{id}")
    public WashCabinDetailViewDto getCabinDetails(@PathVariable("id") String id) throws SQLException {
        return service.getCabinDetails(id);
    }

    @GetMapping("manager/{email}/{password}")
    public ManagerDto isUserManager(@PathVariable("email") String email, @PathVariable("password") String password) throws SQLException {
        return service.isUserManager(email, password);
    }
}
