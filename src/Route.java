import java.util.ArrayList;
import java.util.Vector;

public class Route {

  public Integer routeId;

  public ArrayList<Connection> connections;

  public float duration;

  public Route(Integer routeId, ArrayList<Connection> connections,float duration) {
    this.routeId = routeId;
    this.connections = new ArrayList<>();
    this.duration = duration;
  }

  public Integer getRouteId() {
    return routeId;
  }

  public void setRouteId(Integer routeId) {
    this.routeId = routeId;
  }

  public ArrayList<Connection> getConnections() {
    return connections;
  }

  public void setConnections(ArrayList<Connection> connections) {
    this.connections = connections;
  }

  public float getDuration() {
    return duration;
  }

  public void setDuration(float duration) {
    this.duration = duration;
  }
  public void addConnection(Connection connection) {
    this.connections.add(connection);
  }
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Route ID: ").append(routeId).append("\n");
    builder.append("Connections:\n");
    for (Connection connection : connections) {
      builder.append(connection.toString()).append("\n");
    }
    return builder.toString();
  }
}