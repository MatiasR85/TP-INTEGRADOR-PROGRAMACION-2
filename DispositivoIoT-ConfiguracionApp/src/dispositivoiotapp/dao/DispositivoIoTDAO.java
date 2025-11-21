
package dispositivoiotapp.dao;

import DatabaseConnection.Conexion;
import dispositivoiotapp.model.DispositivoIoT;
import dispositivoiotapp.model.ConfiguracionRed;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DispositivoIoTDAO {
    
    private Connection con;
    private ConfiguracionRedDAO configDao;

    public DispositivoIoTDAO() {
        con = Conexion.getConexion();
        configDao = new ConfiguracionRedDAO();
    } 
    
 public int guardar(DispositivoIoT d, Connection conn) throws SQLException {
    String sql = "INSERT INTO dispositivoiot "
               + "(eliminado, serial, modelo, ubicacion, firmwareversion, configuracionred_id) "
               + "VALUES (?, ?, ?, ?, ?, ?)";

    PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
    ps.setBoolean(1, d.getEliminado() != null ? d.getEliminado() : false);
    ps.setString(2, d.getSerial());
    ps.setString(3, d.getModelo());
    ps.setString(4, d.getUbicacion());
    ps.setString(5, d.getFirmwareVersion());

    if (d.getConfiguracionRed() != null) {
        ps.setLong(6, d.getConfiguracionRed().getId());
    } else {
        ps.setNull(6, java.sql.Types.BIGINT);
    }

    ps.executeUpdate();

    ResultSet rs = ps.getGeneratedKeys();
    int id = -1;

    if (rs.next()) {
        id = rs.getInt(1);
    }

    rs.close();
    ps.close();

    return id;
}
    
  public DispositivoIoT buscarPorId(long id) {
    String sql = "SELECT * FROM dispositivoiot WHERE id = ? AND eliminado = 0";
    DispositivoIoT d = null;

    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            d = new DispositivoIoT();
            d.setId(rs.getLong("id"));
            d.setEliminado(rs.getBoolean("eliminado"));
            d.setSerial(rs.getString("serial"));
            d.setModelo(rs.getString("modelo"));
            d.setUbicacion(rs.getString("ubicacion"));
            d.setFirmwareVersion(rs.getString("firmwareversion"));

            long cfgId = rs.getLong("configuracionred_id");
            if (!rs.wasNull()) {
                ConfiguracionRed cfg = configDao.buscarPorId(cfgId);
                d.setConfiguracionRed(cfg);
            }
        }

        rs.close();
        ps.close();

    } catch (SQLException ex) {
        System.out.println("Error al buscar dispositivo: " + ex.getMessage());
    }

    return d;
}  
    
  public List<DispositivoIoT> listar() {
    List<DispositivoIoT> lista = new ArrayList<>();
    String sql = "SELECT * FROM dispositivoiot WHERE eliminado = 0";

    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            DispositivoIoT d = new DispositivoIoT();
            d.setId(rs.getLong("id"));
            d.setEliminado(rs.getBoolean("eliminado"));
            d.setSerial(rs.getString("serial"));
            d.setModelo(rs.getString("modelo"));
            d.setUbicacion(rs.getString("ubicacion"));
            d.setFirmwareVersion(rs.getString("firmwareversion"));

            long cfgId = rs.getLong("configuracionred_id");
            if (!rs.wasNull()) {
                d.setConfiguracionRed(configDao.buscarPorId(cfgId));
            }

            lista.add(d);
        }

        rs.close();
        ps.close();

    } catch (SQLException ex) {
        System.out.println("Error al listar dispositivos: " + ex.getMessage());
    }

    return lista;
}
  
  public void actualizar(DispositivoIoT d) {
    String sql = "UPDATE dispositivoiot SET serial=?, modelo=?, ubicacion=?, firmwareversion=?, configuracionred_id=? WHERE id=?";

    try {
        // 1) actualizar/guardar configuración
        ConfiguracionRed cfg = d.getConfiguracionRed();
        if (cfg != null) {
            if (cfg.getId() == null || cfg.getId() == 0L) {
                configDao.guardar(cfg, con);
            } else {
                configDao.actualizar(cfg);
            }
        }

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, d.getSerial());
        ps.setString(2, d.getModelo());
        ps.setString(3, d.getUbicacion());
        ps.setString(4, d.getFirmwareVersion());

        if (cfg != null && cfg.getId() != null) {
            ps.setLong(5, cfg.getId());
        } else {
            ps.setNull(5, Types.BIGINT);
        }

        ps.setLong(6, d.getId());

        ps.executeUpdate();
        ps.close();

        System.out.println("Dispositivo actualizado.");

    } catch (SQLException ex) {
        System.out.println("Error al actualizar dispositivo: " + ex.getMessage());
    }
}
  
  public void eliminar(long id) {
    String sql = "UPDATE dispositivoiot SET eliminado = 1 WHERE id = ?";

    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, id);
        ps.executeUpdate();
        ps.close();
        System.out.println("Dispositivo eliminado (baja lógica).");
    } catch (SQLException ex) {
        System.out.println("Error al eliminar dispositivo: " + ex.getMessage());
    }
}
  
  
}
