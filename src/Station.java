import java.util.Vector;

/**
 * Representa uma estação de transporte.
 */
public class Station {

  public Integer stationId; // Identificador da estação
  public String stationName; // Nome da estação
  public String vehicle; // Veículo associado à estação
  public double latitude; // Latitude da estação
  public double longitude; // Longitude da estação

  /**
   * Construtor da classe Station.
   *
   * @param stationId   Identificador da estação
   * @param stationName Nome da estação
   * @param vehicle     Veículo associado à estação
   * @param latitude    Latitude da estação
   * @param longitude   Longitude da estação
   */
  public Station(Integer stationId, String stationName, String vehicle, double latitude, double longitude) {
    this.stationId = stationId;
    this.stationName = stationName;
    this.vehicle = vehicle;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  /**
   * Obtém o identificador da estação.
   *
   * @return Identificador da estação
   */
  public Integer getStationId() {
    return this.stationId;
  }

  /**
   * Define o identificador da estação.
   *
   * @param stationId Identificador da estação
   */
  public void setStationId(Integer stationId) {
    this.stationId = stationId;
  }

  /**
   * Obtém o nome da estação.
   *
   * @return Nome da estação
   */
  public String getStationName() {
    return this.stationName;
  }

  /**
   * Define o nome da estação.
   *
   * @param stationName Nome da estação
   */
  public void setStationName(String stationName) {
    this.stationName = stationName;
  }

  /**
   * Obtém o veículo associado à estação.
   *
   * @return Veículo associado à estação
   */
  public String getVehicle() {
    return this.vehicle;
  }

  /**
   * Define o veículo associado à estação.
   *
   * @param vehicle Veículo associado à estação
   */
  public void setVehicle(String vehicle) {
    this.vehicle = vehicle;
  }

  /**
   * Obtém a latitude da estação.
   *
   * @return Latitude da estação
   */
  public double getLatitude() {
    return this.latitude;
  }

  /**
   * Define a latitude da estação.
   *
   * @param latitude Latitude da estação
   */
  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  /**
   * Obtém a longitude da estação.
   *
   * @return Longitude da estação
   */
  public double getLongitude() {
    return this.longitude;
  }

  /**
   * Define a longitude da estação.
   *
   * @param longitude Longitude da estação
   */
  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  /**
   * Retorna uma representação em formato de string da estação, incluindo o identificador e o nome da estação.
   *
   * @return Representação em formato de string da estação
   */
  @Override
  public String toString() {
    return "Station Id: " + this.stationId + ", Station Name: " + this.stationName;
  }
}
