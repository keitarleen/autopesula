package ee.ttu.pesemiskabiinid.controller;

import ee.ttu.pesemiskabiinid.model.WashCabin;
import ee.ttu.pesemiskabiinid.service.WashCabinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/cabin")
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
}
