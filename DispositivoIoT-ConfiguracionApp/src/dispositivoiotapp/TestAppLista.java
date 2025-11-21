
package dispositivoiotapp;


import dispositivoiotapp.dao.ConfiguracionRedDAO;
import dispositivoiotapp.dao.DispositivoIoTDAO;
import java.util.List;

public class TestAppLista {

    public static void main(String[] args) {

        DispositivoIoTDAO dispositivoDAO = new DispositivoIoTDAO();
        ConfiguracionRedDAO configDAO = new ConfiguracionRedDAO();

        System.out.println("=== LISTA DE CONFIGURACIONES ===");
        configDAO.listar().forEach(System.out::println);

        System.out.println("\n=== LISTA DE DISPOSITIVOS ===");
        dispositivoDAO.listar().forEach(System.out::println);
    }
}
