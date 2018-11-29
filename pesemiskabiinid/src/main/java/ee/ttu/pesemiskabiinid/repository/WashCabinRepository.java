package ee.ttu.pesemiskabiinid.repository;

import ee.ttu.pesemiskabiinid.model.WashCabin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class WashCabinRepository {

    private final DataSource ds;

    @Autowired
    public WashCabinRepository(DataSource ds) {
        this.ds = ds;
    }

    public void save(WashCabin cabin) throws SQLException {
        String sql = "INSERT INTO pesemiskabiin (pesemiskabiini_kood, registreerija_id, pesemiskabiini_seisundi_liik_kood, pesemiskabiini_tyyp_kood, nimetus, hoone_kood, " +
                "max_auto_pikkus, reg_aeg, kommentaar) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = ds.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, cabin.getId());
        preparedStatement.setInt(2, cabin.getRegistrator().getId());
        preparedStatement.setString(3, cabin.getState());
        preparedStatement.setString(4, cabin.getType());
        preparedStatement.setString(5, cabin.getName());
        preparedStatement.setString(6, cabin.getBuilding().getId());
        preparedStatement.setInt(7, cabin.getCarSize());
        preparedStatement.setDate(8, Date.valueOf(cabin.getDateCreated()));
        preparedStatement.setString(9, cabin.getComment());
        preparedStatement.executeUpdate();
    }
}
