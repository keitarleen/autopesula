package ee.ttu.pesemiskabiinid.repository;

import ee.ttu.pesemiskabiinid.model.*;
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
        return getCabinDto(sql);
    }

    public List<WashCabinDto> getActiveInactiveCabins() throws SQLException {
        String sql = "SELECT p.pesemiskabiini_kood, p.nimetus AS pesemiskabiini_nimetus, pt.nimetus AS tyyp, ps.nimetus AS seisund " +
                "FROM pesemiskabiin AS p " +
                "LEFT JOIN isik AS i ON i.isik_id=p.registreerija_id " +
                "LEFT JOIN pesemiskabiini_tyyp AS pt ON pt.pesemiskabiini_tyyp_kood=p.pesemiskabiini_tyyp_kood " +
                "LEFT JOIN pesemiskabiini_seisundi_liik AS ps ON ps.pesemiskabiini_seisundi_liik_kood=p.pesemiskabiini_seisundi_liik_kood " +
                "WHERE ps.pesemiskabiini_seisundi_liik_kood IN ('AKT', 'EBA')";
        return getCabinDto(sql);
    }

    public List<WashCabinStatementDto> getWashCabinStatement() throws SQLException {
        String sql = "SELECT psl.pesemiskabiini_seisundi_liik_kood AS kood, upper(psl.nimetus) AS pesemiskabiini_seisund, count(p.pesemiskabiini_kood) AS kokku " +
                "FROM pesemiskabiin AS p " +
                "LEFT JOIN pesemiskabiini_seisundi_liik psl ON p.pesemiskabiini_seisundi_liik_kood = psl.pesemiskabiini_seisundi_liik_kood " +
                "GROUP BY psl.pesemiskabiini_seisundi_liik_kood, p.nimetus, psl.nimetus " +
                "ORDER BY COUNT(p.pesemiskabiini_kood) DESC, psl.nimetus";
        ResultSet rs = ds.getConnection().createStatement().executeQuery(sql);
        List<WashCabinStatementDto> cabins = new ArrayList<>();
        while (rs.next()) {
            cabins.add(
                    new WashCabinStatementDto(
                            rs.getString("kood"),
                            rs.getString("pesemiskabiini_seisund"),
                            rs.getInt("kokku")
                    )
            );
        }
        return cabins;
    }

    public List<WashCabinCategoryDto> getWashCabinCategory() throws SQLException {
        String sql = "SELECT pko.pesemiskabiini_kood, pesemiskabiini_kategooria.nimetus || '(' || pesemiskabiini_kategooria_tyyp.nimetus || ')' AS kategooria " +
                "FROM pesemiskabiini_kategooria_tyyp " +
                "INNER JOIN pesemiskabiini_kategooria " +
                "ON pesemiskabiini_kategooria_tyyp.pesemiskabiini_kategooria_tyyp_kood = pesemiskabiini_kategooria.pesemiskabiini_kategooria_tyyp_kood " +
                "INNER JOIN pesemiskabiini_kategooria_omamine pko ON pesemiskabiini_kategooria.pesemiskabiini_kategooria_kood = pko.pesemiskabiini_kategooria_kood";
        ResultSet rs = ds.getConnection().createStatement().executeQuery(sql);
        List<WashCabinCategoryDto> categories = new ArrayList<>();
        while (rs.next()) {
            categories.add(
                    new WashCabinCategoryDto(
                            rs.getString("pesemiskabiini_kood"),
                            rs.getString("kategooria")
                    )
            );
        }
        return categories;
    }

    public List<WashCabinDetailDto> getCabinDetail(String id) throws SQLException {
        String sql = "SELECT p.pesemiskabiini_kood," +
                "       p.nimetus," +
                "       pt.nimetus                   AS kabiini_tyyp, " +
                "       psl.nimetus                  AS seisundi_liik, " +
                "       p.max_auto_pikkus," +
                "       p.hoone_kood," +
                "       p.reg_aeg," +
                "       i.eesnimi & ' ' & i.perenimi AS tootaja," +
                "       i.e_meil " +
                "FROM pesemiskabiin AS p " +
                "       LEFT JOIN isik AS i ON i.isik_id = p.registreerija_id " +
                "       LEFT JOIN hoone h ON p.hoone_kood = h.hoone_kood " +
                "       LEFT JOIN pesemiskabiini_tyyp pt ON p.pesemiskabiini_tyyp_kood = pt.pesemiskabiini_tyyp_kood " +
                "       LEFT JOIN pesemiskabiini_seisundi_liik psl ON p.pesemiskabiini_seisundi_liik_kood = psl.pesemiskabiini_seisundi_liik_kood " +
                "WHERE p.pesemiskabiini_kood=?";
        PreparedStatement ps = ds.getConnection().prepareStatement(sql);
        String preparedStatement = ps.toString();
        // ResultSet rs = ds.getConnection().createStatement().executeQuery(sql);
        ps.setString(1, id);
        ResultSet rs = ds.getConnection().createStatement().executeQuery(preparedStatement);
        List<WashCabinDetailDto> cabins = new ArrayList<>();
        while (rs.next()) {
            cabins.add(
                    new WashCabinDetailDto(
                            rs.getString("pesemiskabiini_kood"),
                            rs.getString("nimetus"),
                            rs.getString("kabiini_tyyp"),
                            rs.getString("seisundi_liik"),
                            rs.getInt("max_auto_pikkus"),
                            rs.getString("hoone_kood"),
                            rs.getDate("reg_aeg").toLocalDate(),
                            rs.getString("tootaja"),
                            rs.getString("e_meil")
                    )
            );
        }

        return cabins;
    }

    private List<WashCabinDto> getCabinDto(String sql) throws SQLException {
        ResultSet rs = ds.getConnection().createStatement().executeQuery(sql);
        List<WashCabinDto> cabins = new ArrayList<>();
        while (rs.next()) {
            cabins.add(
                    new WashCabinDto(
                            rs.getString("pesemiskabiini_kood"),
                            rs.getString("pesemiskabiini_nimetus"),
                            rs.getString("tyyp"),
                            rs.getString("seisund")
                            )
            );
        }
        return cabins;
    }
}
