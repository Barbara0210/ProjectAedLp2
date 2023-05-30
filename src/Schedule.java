import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Representa um horário de partida e chegada.
 */
public class Schedule {
  private Integer scheduleId; // Identificador do horário
  private String startTime; // Horário de partida
  private String endTime; // Horário de chegada

  /**
   * Construtor da classe Schedule.
   *
   * @param scheduleId Identificador do horário
   * @param startTime  Horário de partida
   * @param endTime    Horário de chegada
   */
  public Schedule(Integer scheduleId, String startTime, String endTime) {
    this.scheduleId = scheduleId;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  /**
   * Obtém o identificador do horário.
   *
   * @return Identificador do horário
   */
  public Integer getScheduleId() {
    return scheduleId;
  }

  /**
   * Define o identificador do horário.
   *
   * @param scheduleId Identificador do horário
   */
  public void setScheduleId(Integer scheduleId) {
    this.scheduleId = scheduleId;
  }

  /**
   * Obtém o horário de partida.
   *
   * @return Horário de partida
   */
  public String getStartTime() {
    return startTime;
  }

  /**
   * Define o horário de partida.
   *
   * @param startTime Horário de partida
   */
  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  /**
   * Obtém o horário de chegada.
   *
   * @return Horário de chegada
   */
  public String getEndTime() {
    return endTime;
  }

  /**
   * Define o horário de chegada.
   *
   * @param endTime Horário de chegada
   */
  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  /**
   * Calcula a duração do horário, em minutos.
   *
   * @return Duração do horário em minutos
   */
  public long getDuration() {
    SimpleDateFormat format = new SimpleDateFormat("HH:mm");
    try {
      Date start = format.parse(startTime);
      Date end = format.parse(endTime);
      long diffInMillies = Math.abs(end.getTime() - start.getTime());
      return TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
    } catch (ParseException e) {
      e.printStackTrace();
      return -1;
    }
  }

  /**
   * Retorna uma representação em formato de string do horário, incluindo o identificador, horário de partida e horário de chegada.
   *
   * @return Representação em formato de string do horário
   */
  @Override
  public String toString() {
    return "Schedule Id: " + this.scheduleId + ", Start Time: " + this.startTime + ", End Time: " + this.endTime;
  }
}
