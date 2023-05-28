import java.util.*;

public class Trip {

  public Integer tripId;
  public User user;
  public Route route;


  public Trip(Integer tripId, User user, Route route) {
    this.tripId = tripId;
    this.user = user;
    this.route = route;
  }

  public Integer getTripId() {
    return tripId;
  }

  public void setTripId(Integer tripId) {
    this.tripId = tripId;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Route getRoute() {
    return route;
  }

  public void setRoute(Route route) {
    this.route = route;
  }
}