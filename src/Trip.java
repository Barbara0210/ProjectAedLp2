import java.util.*;

/**
 * Represents a trip taken by a user on a specific route.
 */
public class Trip {
  private Integer tripId;
  private User user;
  private Route route;
  private String startDate;  // campo para a data de início
  private String endDate;  // campo para a data de fim

  /**
   * Constructs a Trip object with the given trip ID, user, route, start date, and end date.
   *
   * @param tripId    the ID of the trip
   * @param user      the user who took the trip
   * @param route     the route taken in the trip
   * @param startDate the start date of the trip
   * @param endDate   the end date of the trip
   */
  public Trip(Integer tripId, User user, Route route, String startDate, String endDate) {
    this.tripId = tripId;
    this.user = user;
    this.route = route;
    this.startDate = startDate;  // inicializa o campo da data de início
    this.endDate = endDate;  // inicializa o campo da data de fim
  }

  /**
   * Retrieves the start date of the trip.
   *
   * @return the start date of the trip
   */
  public String getStartDate() {
    return startDate;
  }

  /**
   * Sets the start date of the trip.
   *
   * @param startDate the start date to set
   */
  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  /**
   * Retrieves the end date of the trip.
   *
   * @return the end date of the trip
   */
  public String getEndDate() {
    return endDate;
  }

  /**
   * Sets the end date of the trip.
   *
   * @param endDate the end date to set
   */
  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  /**
   * Retrieves the ID of the trip.
   *
   * @return the trip ID
   */
  public Integer getTripId() {
    return tripId;
  }

  /**
   * Sets the ID of the trip.
   *
   * @param tripId the trip ID to set
   */
  public void setTripId(Integer tripId) {
    this.tripId = tripId;
  }

  /**
   * Retrieves the user who took the trip.
   *
   * @return the user of the trip
   */
  public User getUser() {
    return user;
  }

  /**
   * Sets the user who took the trip.
   *
   * @param user the user to set
   */
  public void setUser(User user) {
    this.user = user;
  }

  /**
   * Retrieves the route taken in the trip.
   *
   * @return the route of the trip
   */
  public Route getRoute() {
    return route;
  }

  /**
   * Sets the route taken in the trip.
   *
   * @param route the route to set
   */
  public void setRoute(Route route) {
    this.route = route;
  }

  @Override
  public String toString() {
    return "Trip ID: " + tripId + ", User: " + user.getUserName() + ", Route: " + route.getRouteId();
  }
}
