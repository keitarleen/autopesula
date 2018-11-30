package ee.ttu.pesemiskabiinid.repository;

import ee.ttu.pesemiskabiinid.model.WashCabin;
import ee.ttu.pesemiskabiinid.model.WashCabinDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        preparedStatement.setDate(8, Date.valueOf(LocalDate.now()));
        preparedStatement.setString(9, cabin.getComment());
        preparedStatement.executeUpdate();
    }

    public List<WashCabinDto> getAll() throws SQLException {
        String sql = "SELECT p.pesemiskabiini_kood, p.nimetus AS pesemiskabiini_nimetus, pt.nimetus AS tyyp, ps.nimetus AS seisund " +
                "FROM pesemiskabiin AS p " +
                "LEFT JOIN isik AS i ON i.isik_id=p.registreerija_id " +
                "LEFT JOIN pesemiskabiini_tyyp AS pt ON pt.pesemiskabiini_tyyp_kood=p.pesemiskabiini_tyyp_kood " +
                "LEFT JOIN pesemiskabiini_seisundi_liik AS ps ON ps.pesemiskabiini_seisundi_liik_kood=p.pesemiskabiini_seisundi_liik_kood";
        ResultSet rs = ds.getConnection().createStatement().executeQuery(sql);
        List<WashCabinDto> cabins = new ArrayList<>();
        while (rs.next()) {
            cabins.add(
                    new WashCabinDto(
                            rs.getString("pesemiskabiini_kood"),
                            rs.getString("nimetus"),
                            rs.getString("tyyp"),
                            rs.getString("seisund")
                            )
            );
        }
        return cabins;
    }
}
