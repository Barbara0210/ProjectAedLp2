import java.util.*;
public class User {
  private Integer userId;
  private String userName;
  private ArrayList<Trip> trips;

  public void setTrips(ArrayList<Trip> trips) {
    this.trips = trips;
  }

  public User(Integer userId, String userName) {
    this.userId = userId;
    this.userName = userName;
    this.trips = new ArrayList<>();
  }

  // seus outros métodos get e set permanecem inalterados

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public ArrayList<Trip> getTrips() {
    return trips;
  }


  public void addTrip(Trip trip) {
    this.trips.add(trip);
  }

  // Método para editar uma viagem
  public void editTrip(int index, Trip newTrip) {
    this.trips.set(index, newTrip);
  }

  // Método para remover uma viagem
  public void removeTrip(int index) {
    this.trips.remove(index);
  }
  @Override
  public String toString() {
    return "User ID: " + userId + ", User Name: " + userName;
    // Adicione outras propriedades que você quer mostrar
  }
}