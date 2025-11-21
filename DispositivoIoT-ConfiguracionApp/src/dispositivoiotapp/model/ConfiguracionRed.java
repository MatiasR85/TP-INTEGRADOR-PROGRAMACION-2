
package dispositivoiotapp.model;


public class ConfiguracionRed {
    
    private Long id;
    private Boolean eliminado;
    private String ip;
    private String mascara;
    private String gateway;
    private String dnsPrimario;
    private Boolean dhcpHabilitado;

    public ConfiguracionRed() {
     
    }

    public ConfiguracionRed(Long id, Boolean eliminado, String ip, String mascara, String gateway, String dnsPrimario, Boolean dhcpHabilitado) {
        this.id = id;
        this.eliminado = eliminado;
        this.ip = ip;
        this.mascara = mascara;
        this.gateway = gateway;
        this.dnsPrimario = dnsPrimario;
        this.dhcpHabilitado = dhcpHabilitado;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }



    public String getMascara() {
        return mascara;
    }

    public void setMascara(String mascara) {
        this.mascara = mascara;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getDnsPrimario() {
        return dnsPrimario;
    }

    public void setDnsPrimario(String dnsPrimario) {
        this.dnsPrimario = dnsPrimario;
    }

    public Boolean getDhcpHabilitado() {
        return dhcpHabilitado;
    }

    public void setDhcpHabilitado(Boolean dhcpHabilitado) {
        this.dhcpHabilitado = dhcpHabilitado;
    }

    @Override
    public String toString() {
        return "ConfiguracionRed{" + "id=" + id + ", eliminado=" + eliminado + ", ip=" + ip + ", mascara=" + mascara + ", gateway=" + gateway + ", dnsPrimario=" + dnsPrimario + ", dhcpHabilitado=" + dhcpHabilitado + '}';
    }
    
    
}
