
package Service;

import dispositivoiotapp.model.DispositivoIoT;
import dispositivoiotapp.model.ConfiguracionRed;

import dispositivoiotapp.dao.DispositivoIoTDAO;
import dispositivoiotapp.dao.ConfiguracionRedDAO;

import DatabaseConnection.Conexion;

import java.sql.Connection;

public class DispositivoIoTService {
    
     private DispositivoIoTDAO dispositivoDao = new DispositivoIoTDAO();
    private ConfiguracionRedDAO configDao = new ConfiguracionRedDAO();

    public DispositivoIoT crearDispositivoConConfiguracion(DispositivoIoT disp, ConfiguracionRed conf) {

        Connection conn = null;

        try {
            conn = Conexion.getConexion();
            conn.setAutoCommit(false);

            // 1️⃣ Crear configuración y obtener ID
            long idConfig = configDao.guardar(conf, conn);
            conf.setId(idConfig);
            disp.setConfiguracionRed(conf);

            // 2️⃣ Crear dispositivo
            dispositivoDao.guardar(disp, conn);

            // 3️⃣ Confirmar
            conn.commit();
            System.out.println("✔ Commit realizado");

            return disp;

        } catch (Exception e) {

            try { 
                if (conn != null) {
                    conn.rollback();
                    System.out.println("❌ Error → Rollback ejecutado");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();
            return null;

        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}