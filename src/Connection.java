import java.util.Vector;

/**
 * Representa uma conexão entre duas estações em um mapa de rede de transporte.
 */
public class Connection {

  /**
   * ID da conexão.
   */
  public Integer connectionId;

  /**
   * Primeira estação da conexão.
   */
  public Station station1;

  /**
   * Segunda estação da conexão.
   */
  public Station station2;

  /**
   * Horário de partida e chegada da conexão.
   */
  public Schedule schedule;

  /**
   * Construtor da classe Connection.
   *
   * @param connectionId ID da conexão
   * @param station1     Primeira estação da conexão
   * @param station2     Segunda estação da conexão
   * @param schedule     Horário de partida e chegada da conexão
   */
  public Connection(Integer connectionId, Station station1, Station station2, Schedule schedule) {
    this.connectionId = connectionId;
    this.station1 = station1;
    this.station2 = station2;
    this.schedule = schedule;
  }

  /**
   * Obtém o ID da conexão.
   *
   * @return ID da conexão
   */
  public Integer getConnectionId() {
    return connectionId;
  }

  /**
   * Define o ID da conexão.
   *
   * @param connectionId ID da conexão
   */
  public void setConnectionId(Integer connectionId) {
    this.connectionId = connectionId;
  }

  /**
   * Obtém a primeira estação da conexão.
   *
   * @return Primeira estação da conexão
   */
  public Station getStation1() {
    return station1;
  }

  /**
   * Define a primeira estação da conexão.
   *
   * @param station1 Primeira estação da conexão
   */
  public void setStation1(Station station1) {
    this.station1 = station1;
  }

  /**
   * Obtém a segunda estação da conexão.
   *
   * @return Segunda estação da conexão
   */
  public Station getStation2() {
    return station2;
  }

  /**
   * Define a segunda estação da conexão.
   *
   * @param station2 Segunda estação da conexão
   */
  public void setStation2(Station station2) {
    this.station2 = station2;
  }

  /**
   * Obtém o horário de partida e chegada da conexão.
   *
   * @return Horário de partida e chegada da conexão
   */
  public Schedule getSchedule() {
    return schedule;
  }

  /**
   * Define o horário de partida e chegada da conexão.
   *
   * @param schedule Horário de partida e chegada da conexão
   */
  public void setSchedule(Schedule schedule) {
    this.schedule = schedule;
  }

  /**
   * Retorna uma representação em String da conexão.
   *
   * @return Representação em String da conexão
   */
  @Override
  public String toString() {
    return "Connection ID: " + connectionId + ", Station 1: " + station1.getStationName() + ", Station 2: " + station2.getStationName() + ", Schedule: " + schedule.getScheduleId();
  }
}
