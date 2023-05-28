import java.util.Vector;

public class Station {

  public Integer stationId;
  public String stationName;
  public String vehicle;
  public double latitude;
  public double longitude;


  public Station(Integer stationId, String stationName, String vehicle, double latitude,double longitude) {
    this.stationId = stationId;
    this.stationName = stationName;
    this.vehicle=vehicle;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public Integer getStationId() {

    return this.stationId;
  }

  public void setStationId(Integer stationId) {
    this.stationId=stationId;
  }


  public void setStationName(String stationName) {
    this.stationName=stationName;
  }
  public String getVehicle() {
    return vehicle;
  }

  public void setVehicle(String vehicle) {
    this.vehicle = vehicle;
  }

  public String getStationName() {
    return stationName;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  @Override
  public String toString() {
    return "Station Id: " + this.stationId + ", Station Name: " + this.stationName;
  }
}