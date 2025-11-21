
package dispositivoiotapp.dao;

import DatabaseConnection.Conexion;
import dispositivoiotapp.model.ConfiguracionRed;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConfiguracionRedDAO {
    
    private Connection con;

    public ConfiguracionRedDAO() {
        con = Conexion.getConexion();
}
    
    public long guardar(ConfiguracionRed conf, Connection conn) throws SQLException {
    String sql = "INSERT INTO configuracionred (ip, mascara, gateway, dnsPrimario, dhcpHabilitado, eliminado) "
               + "VALUES (?, ?, ?, ?, ?, 0)";

    PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
    ps.setString(1, conf.getIp());
    ps.setString(2, conf.getMascara());
    ps.setString(3, conf.getGateway());
    ps.setString(4, conf.getDnsPrimario());
    ps.setBoolean(5, conf.getDhcpHabilitado());

    ps.executeUpdate();

    ResultSet rs = ps.getGeneratedKeys();
    long id = -1;

    if (rs.next()) {
        id = rs.getLong(1);
       
    }

    rs.close();
    ps.close();
        

    return id;
    }
    

    // BUSCAR POR ID
    public ConfiguracionRed buscarPorId(long id) {
        String sql = "SELECT * FROM configuracionred WHERE id = ? AND eliminado = 0";
        ConfiguracionRed config = null;

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                config = new ConfiguracionRed(
                    rs.getLong("id"),
                    rs.getBoolean("eliminado"),
                    rs.getString("ip"),
                    rs.getString("mascara"),
                    rs.getString("gateway"),
                    rs.getString("dnsPrimario"),
                    rs.getBoolean("dhcpHabilitado")

                );
            }

            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.println("Error al buscar configuración: " + ex.getMessage());
        }

        return config;
    }

    // ACTUALIZAR DATOS
    public void actualizar(ConfiguracionRed config) {
        String sql = "UPDATE configuracionred SET ip=?, mascara=?, gateway=?, dnsPrimario=?, dhcpHabilitado=? "
                   + "WHERE id=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, config.getIp());
            ps.setString(2, config.getMascara());
            ps.setString(3, config.getGateway());
            ps.setString(4, config.getDnsPrimario());
            ps.setBoolean(5, config.getDhcpHabilitado());
            ps.setLong(6, config.getId());

            ps.executeUpdate();
            ps.close();
            System.out.println("Configuración actualizada.");

        } catch (SQLException ex) {
            System.out.println("Error al actualizar configuración: " + ex.getMessage());
        }
    }

    // BAJA LÓGICA
    public void eliminar(long id) {
        String sql = "UPDATE configuracionred SET eliminado = 1 WHERE id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
            ps.close();

            System.out.println("Configuración eliminada (baja lógica).");

        } catch (SQLException ex) {
            System.out.println("Error al eliminar configuración: " + ex.getMessage());
        }
    }

    // LISTAR TODAS LAS CONFIGURACIONES
    public List<ConfiguracionRed> listar() {
        List<ConfiguracionRed> lista = new ArrayList<>();
        String sql = "SELECT * FROM configuracionred WHERE eliminado = 0";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ConfiguracionRed config = new ConfiguracionRed(
                    rs.getLong("id"),
                    rs.getBoolean("eliminado"),
                    rs.getString("ip"),
                    rs.getString("mascara"),
                    rs.getString("gateway"),
                    rs.getString("dnsPrimario"),
                    rs.getBoolean("dhcpHabilitado")
                  
                );

                lista.add(config);
            }

            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.println("Error al listar configuraciones: " + ex.getMessage());
        }

        return lista;
    }
}
