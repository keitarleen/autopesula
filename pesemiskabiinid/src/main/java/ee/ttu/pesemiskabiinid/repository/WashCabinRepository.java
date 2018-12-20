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
        String sql = "SELECT f_lisa_pesemiskabiin(?, ?, ?, ?, ?, ?, ?, ?, ?)";
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

    public List<WashCabinDto> getActiveInactiveCabins() throws SQLException {
        String sql = "SELECT * FROM aktiivsed_mitteaktiivsed_kabiinid";
        return getCabinDto(sql);
    }

    public List<WashCabinStatementDto> getWashCabinStatement() throws SQLException {
        String sql = "SELECT * FROM pesemiskabiinide_koondaruanne";
        ResultSet rs = ds.getConnection().createStatement().executeQuery(sql);
        List<WashCabinStatementDto> cabins = new ArrayList<>();
        while (rs.next()) {
            cabins.add(
                    new WashCabinStatementDto(
                            rs.getString("pesemiskabiini_kood"),
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

    public List<WashCabinDetailDto> getAllCabins() throws SQLException {
        String sql = "SELECT * FROM koik_pesemiskabiinid";
        ResultSet rs = ds.getConnection().createStatement().executeQuery(sql);
        List<WashCabinDetailDto> cabins = new ArrayList<>();
        while (rs.next()) {
            cabins.add(new WashCabinDetailDto(
                    rs.getString("pesemiskabiini_kood"),
                    rs.getString("nimetus"),
                    rs.getString("kabiini_tyyp"),
                    rs.getString("seisundi_liik"),
                    rs.getInt("max_auto_pikkus"),
                    rs.getString("hoone_kood"),
                    rs.getDate("reg_aeg").toLocalDate(),
                    rs.getString("tootaja"),
                    rs.getString("e_meil")
            ));
        }
        return cabins;
    }

    public String endCabin(String id) throws SQLException {
        String sql = "SELECT f_lopeta_pesemiskabiin(?)";
        PreparedStatement ps = ds.getConnection().prepareStatement(sql);
        ps.setString(1, id);
        ps.executeUpdate();
        return id;
    }

    public WashCabinDetailViewDto getCabinDetails(String id) throws SQLException {
        String sql = "SELECT * FROM pesemiskabiini_detailid('" + id + "')";
        ResultSet rs = ds.getConnection().createStatement().executeQuery(sql);
        WashCabinDetailViewDto cabin = null;
        while (rs.next()) {
            cabin = new WashCabinDetailViewDto(
                    rs.getString("pesemiskabiini_kood"),
                    rs.getString("nimetus"),
                    rs.getString("pesemiskabiini_tyyp"),
                    rs.getString("seisund"),
                    rs.getInt("max_auto_pikkus"),
                    rs.getString("hoone_kood"),
                    rs.getDate("reg_aeg").toLocalDate(),
                    rs.getString("tootaja"),
                    rs.getString("e_meil"),
                    rs.getString("kategooria_nimetus"),
                    rs.getString("kategooria_tyyp")
                    );
        }

        return cabin;
    }

    private List<WashCabinDto> getCabinDto(String sql) throws SQLException {
        ResultSet rs = ds.getConnection().createStatement().executeQuery(sql);
        List<WashCabinDto> cabins = new ArrayList<>();
        while (rs.next()) {
            cabins.add(
                    new WashCabinDto(
                            rs.getString("pesemiskabiini_kood"),
                            rs.getString("pesemiskabiini_nimetus"),
                            rs.getString("pesemiskabiini_tyyp"),
                            rs.getString("seisund")
                    )
            );
        }
        return cabins;
    }
}
