
package dispositivoiotapp.model;


public class DispositivoIoT {
    
    private Long id;
    private Boolean eliminado;
    private String serial;
    private String modelo;
    private String ubicacion;
    private String firmwareVersion;
    private ConfiguracionRed configuracionRed;

    public DispositivoIoT() {
    }

    public DispositivoIoT(Long id, Boolean eliminado, String serial, String modelo, String ubicacion, String firmwareVersion, ConfiguracionRed configuracionRed) {
        this.id = id;
        this.eliminado = eliminado;
        this.serial = serial;
        this.modelo = modelo;
        this.ubicacion = ubicacion;
        this.firmwareVersion = firmwareVersion;
        this.configuracionRed = configuracionRed;
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

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public ConfiguracionRed getConfiguracionRed() {
        return configuracionRed;
    }

    public void setConfiguracionRed(ConfiguracionRed configuracionRed) {
        this.configuracionRed = configuracionRed;
    }

    @Override
    public String toString() {
        return "DispositivoIoT{" + "id=" + id + ", eliminado=" + eliminado + ", serial=" + serial + ", modelo=" + modelo + ", ubicacion=" + ubicacion + ", firmwareVersion=" + firmwareVersion + ", configuracionRed=" + configuracionRed + '}';
    }
    
    
}
