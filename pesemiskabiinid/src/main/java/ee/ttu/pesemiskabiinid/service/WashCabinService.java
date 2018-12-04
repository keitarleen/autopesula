package ee.ttu.pesemiskabiinid.service;

import ee.ttu.pesemiskabiinid.model.*;
import ee.ttu.pesemiskabiinid.repository.WashCabinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

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

    public List<WashCabinDto> getAll() throws SQLException {
        return repository.getAll();
    }

    public List<WashCabinDto> getActiveInactiveCabins() throws SQLException {
        return repository.getActiveInactiveCabins();
    }

    public List<WashCabinStatementDto> getCabinStatement() throws SQLException {
        return repository.getWashCabinStatement();
    }

    public List<WashCabinCategoryDto> getCabinCategories() throws SQLException {
        return repository.getWashCabinCategory();
    }

    public List<WashCabinDetailDto> getCabinDetails(String id) throws SQLException {
        return repository.getCabinDetail(id);
    }
}
