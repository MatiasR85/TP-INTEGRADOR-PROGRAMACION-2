
package dispositivoiotapp;

import dispositivoiotapp.dao.DispositivoIoTDAO;
import dispositivoiotapp.dao.ConfiguracionRedDAO;
import dispositivoiotapp.model.DispositivoIoT;
import dispositivoiotapp.model.ConfiguracionRed;
import java.sql.Connection;
import DatabaseConnection.Conexion;
import java.util.List;
import java.util.Scanner;

public class Menuapp {

    private static final DispositivoIoTDAO dispositivoDAO = new DispositivoIoTDAO();
    private static final ConfiguracionRedDAO configDAO = new ConfiguracionRedDAO();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int opcion;

        do {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1. Listar dispositivos");
            System.out.println("2. Crear dispositivo");
            System.out.println("3. Eliminar dispositivo");
            System.out.println("0. Salir");
            System.out.print("Opción: ");

            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {

                case 1:
                    listarDispositivos();
                    break;

                case 2:
                    crearDispositivo();
                    break;

                case 3:
                    eliminarDispositivo();
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida");
            }

        } while (opcion != 0);
    }

    private static void listarDispositivos() {
        List<DispositivoIoT> lista = dispositivoDAO.listar();

        System.out.println("\n===== LISTA DE DISPOSITIVOS =====");

        for (DispositivoIoT d : lista) {
            System.out.println("--------------------------------");
            System.out.println("ID: " + d.getId());
            System.out.println("Serial: " + d.getSerial());
            System.out.println("Modelo: " + d.getModelo());
            System.out.println("Ubicación: " + d.getUbicacion());
            System.out.println("Firmware: " + d.getFirmwareVersion());

            if (d.getConfiguracionRed() != null) {
                System.out.println("---- Configuración ----");
                System.out.println("IP: " + d.getConfiguracionRed().getIp());
            }
        }
    }
    private static void crearDispositivo() {
        
     try (Connection conn = Conexion.getConexion()) {

        conn.setAutoCommit(false);

        System.out.print("Serial: ");
        String serial = sc.nextLine();

        System.out.print("Modelo: ");
        String modelo = sc.nextLine();

        System.out.print("Ubicación: ");
        String ubicacion = sc.nextLine();

        System.out.print("Firmware: ");
        String firmware = sc.nextLine();

        System.out.print("¿Crear configuración de red? (s/n): ");
        String resp = sc.nextLine();

        ConfiguracionRed config = null;

        if (resp.equalsIgnoreCase("s")) {
            System.out.print("IP: ");
            String ip = sc.nextLine();

            System.out.print("Máscara: ");
            String mascara = sc.nextLine();

            System.out.print("Gateway: ");
            String gateway = sc.nextLine();

            System.out.print("DNS: ");
            String dns = sc.nextLine();

            config = new ConfiguracionRed(null, false, ip, mascara, gateway, dns, false);
            long configId = configDAO.guardar(config, conn);
            config.setId(configId);
        }

        DispositivoIoT nuevo = new DispositivoIoT(
                null, false, serial, modelo, ubicacion, firmware, config
        );

        dispositivoDAO.guardar(nuevo, conn);

        conn.commit();
        System.out.println("Dispositivo creado correctamente!");

    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Error al crear el dispositivo.");
    }
}

    private static void eliminarDispositivo() {
        System.out.print("Ingrese ID del dispositivo: ");
        long id = Long.parseLong(sc.nextLine());

        dispositivoDAO.eliminar(id);

        System.out.println("Dispositivo marcado como eliminado.");
    }
}

