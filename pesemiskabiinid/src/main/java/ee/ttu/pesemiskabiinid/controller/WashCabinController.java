package ee.ttu.pesemiskabiinid.controller;

import ee.ttu.pesemiskabiinid.model.WashCabin;
import ee.ttu.pesemiskabiinid.model.WashCabinDto;
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

   @GetMapping("/getAll")
   public List<WashCabinDto> getAll() throws SQLException {
        return service.getAll();
   }
}
