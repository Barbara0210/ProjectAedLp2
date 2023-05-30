import java.util.*;

/**
 * Represents a user who takes trips.
 */
public class User {
  private Integer userId;
  private String userName;
  private List<Trip> trips;

  /**
   * Constructs a User object with the given user ID and username.
   *
   * @param userId   the ID of the user
   * @param userName the username of the user
   */
  public User(Integer userId, String userName) {
    this.userId = userId;
    this.userName = userName;
    this.trips = new ArrayList<>();
  }

  /**
   * Sets the list of trips for the user.
   *
   * @param trips the list of trips to set
   */
  public void setTrips(List<Trip> trips) {
    this.trips = trips;
  }

  /**
   * Retrieves the ID of the user.
   *
   * @return the user ID
   */
  public Integer getUserId() {
    return userId;
  }

  /**
   * Sets the ID of the user.
   *
   * @param userId the user ID to set
   */
  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  /**
   * Retrieves the username of the user.
   *
   * @return the username
   */
  public String getUserName() {
    return userName;
  }

  /**
   * Sets the username of the user.
   *
   * @param userName the username to set
   */
  public void setUserName(String userName) {
    this.userName = userName;
  }

  /**
   * Retrieves the list of trips taken by the user.
   *
   * @return the list of trips
   */
  public List<Trip> getTrips() {
    return trips;
  }

  /**
   * Adds a trip to the user's list of trips.
   *
   * @param trip the trip to add
   */
  public void addTrip(Trip trip) {
    this.trips.add(trip);
  }

  /**
   * Edits a trip in the user's list of trips at the specified index.
   *
   * @param index   the index of the trip to edit
   * @param newTrip the new trip to replace the existing trip
   */
  public void editTrip(int index, Trip newTrip) {
    this.trips.set(index, newTrip);
  }

  /**
   * Removes a trip from the user's list of trips at the specified index.
   *
   * @param index the index of the trip to remove
   */
  public void removeTrip(int index) {
    this.trips.remove(index);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("User ID: ").append(userId).append("\n");
    builder.append("Username: ").append(userName).append("\n");
    builder.append("Trips:\n");
    for (Trip trip : trips) {
      builder.append(trip.toString()).append("\n");
    }
    return builder.toString();
  }
}
