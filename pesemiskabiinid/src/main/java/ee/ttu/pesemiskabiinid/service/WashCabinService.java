package ee.ttu.pesemiskabiinid.service;

import ee.ttu.pesemiskabiinid.model.WashCabin;
import ee.ttu.pesemiskabiinid.repository.WashCabinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class WashCabinService {

    private final WashCabinRepository repository;

    @Autowired
    public WashCabinService(WashCabinRepository repository) {
        this.repository = repository;
    }

    public void save(WashCabin cabin) throws SQLException {
        repository.save(cabin);
    }
}
