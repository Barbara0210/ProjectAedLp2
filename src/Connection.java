import java.util.Vector;

public class Connection {

  public Integer connectionId;

  public Station station1;

  public Station station2;

  public Schedule schedule;

  public Connection(Integer connectionId, Station station1, Station station2, Schedule schedule) {
    this.connectionId = connectionId;
    this.station1 = station1;
    this.station2 = station2;
    this.schedule = schedule;
  }

  public Integer getConnectionId() {
    return connectionId;
  }

  public void setConnectionId(Integer connectionId) {
    this.connectionId = connectionId;
  }

  public Station getStation1() {
    return station1;
  }

  public void setStation1(Station station1) {
    this.station1 = station1;
  }

  public Station getStation2() {
    return station2;
  }

  public void setStation2(Station station2) {
    this.station2 = station2;
  }

  public Schedule getSchedule() {
    return schedule;
  }

  public void setSchedule(Schedule schedule) {
    this.schedule = schedule;
  }
  @Override
  public String toString() {
    return "Connection ID: " + connectionId + ", Station 1: " + station1.getStationName() + ", Station 2: " + station2.getStationName() + ", Schedule: " + schedule.getScheduleId();
  }
}