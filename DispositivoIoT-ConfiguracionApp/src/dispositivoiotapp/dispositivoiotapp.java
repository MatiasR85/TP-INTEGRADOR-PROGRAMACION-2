
package dispositivoiotapp;

import dispositivoiotapp.dao.DispositivoIoTDAO;
import dispositivoiotapp.model.DispositivoIoT;
import java.util.List;


public class dispositivoiotapp {

   
    public static void main(String[] args) {
    
                DispositivoIoTDAO dao = new DispositivoIoTDAO();

        System.out.println("===== LISTADO DE DISPOSITIVOS =====");

        List<DispositivoIoT> lista = dao.listar();

        for (DispositivoIoT d : lista) {
            System.out.println("-----------------------------------");
            System.out.println("ID: " + d.getId());
            System.out.println("Serial: " + d.getSerial());
            System.out.println("Modelo: " + d.getModelo());
            System.out.println("Ubicación: " + d.getUbicacion());
            System.out.println("Firmware: " + d.getFirmwareVersion());

            if (d.getConfiguracionRed() != null) {
                System.out.println("---- Configuración Red ----");
                System.out.println("IP: " + d.getConfiguracionRed().getIp());
                System.out.println("Máscara: " + d.getConfiguracionRed().getMascara());
                System.out.println("Gateway: " + d.getConfiguracionRed().getGateway());
                System.out.println("DNS: " + d.getConfiguracionRed().getDnsPrimario());
                System.out.println("DHCP: " + d.getConfiguracionRed().getDhcpHabilitado());
            } else {
                System.out.println("Sin configuración de red asociada.");
            }
        }

        System.out.println("-----------------------------------");
        System.out.println("Total dispositivos: " + lista.size());
    }
}

        
    
    

