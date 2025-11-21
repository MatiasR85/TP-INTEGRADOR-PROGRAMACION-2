package dispositivoiotapp;

import DatabaseConnection.Conexion;
import dispositivoiotapp.dao.ConfiguracionRedDAO;
import dispositivoiotapp.dao.DispositivoIoTDAO;
import dispositivoiotapp.model.ConfiguracionRed;
import dispositivoiotapp.model.DispositivoIoT;

import java.sql.Connection;

public class TestApp {

  public static void main(String[] args) {

       
        try (Connection conn = Conexion.getConexion()) {

            conn.setAutoCommit(false);

            ConfiguracionRedDAO configDAO = new ConfiguracionRedDAO();
            DispositivoIoTDAO dispositivoDAO = new DispositivoIoTDAO();

            // 1) Crear configuración de red
            ConfiguracionRed config = new ConfiguracionRed(
                    null,
                    false,
                    "192.168.0.01",
                    "255.255.255.1",
                    "192.168.0.0",
                    "8.8.8.8",
                    true
            );

            long idConfig = configDAO.guardar(config, conn);
            config.setId(idConfig);

            System.out.println("Configuración de Red guardada con ID: " + idConfig);

            // 2) Crear dispositivo IoT asociado a esa configuración
            DispositivoIoT dispositivo = new DispositivoIoT(
                    null,
                    false,
                    "BOG210",   
                    "Sensor De Luz",
                    "Planta",
                    "v10",
                    config
            );

            dispositivoDAO.guardar(dispositivo, conn);

            System.out.println("Dispositivo IoT guardado correctamente.");

            
            conn.commit();
            System.out.println("=== COMMIT REALIZADO ===");

        } catch (Exception e) {
            e.printStackTrace();

            
            try (Connection conn = Conexion.getConexion()) {
                conn.rollback();
                System.out.println("=== ROLLBACK REALIZADO ===");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

