import java.util.ArrayList;

/**
 * Representa uma rota que consiste em uma sequência de conexões.
 */
public class Route {

  public Integer routeId; // Identificador da rota
  public ArrayList<Connection> connections; // Lista de conexões da rota
  public float duration; // Duração total da rota

  /**
   * Construtor da classe Route.
   *
   * @param routeId     Identificador da rota
   * @param connections Lista de conexões da rota
   * @param duration    Duração total da rota
   */
  public Route(Integer routeId, ArrayList<Connection> connections, float duration) {
    this.routeId = routeId;
    this.connections = connections;
    this.duration = duration;
  }

  /**
   * Obtém o identificador da rota.
   *
   * @return Identificador da rota
   */
  public Integer getRouteId() {
    return routeId;
  }

  /**
   * Define o identificador da rota.
   *
   * @param routeId Identificador da rota
   */
  public void setRouteId(Integer routeId) {
    this.routeId = routeId;
  }

  /**
   * Obtém a lista de conexões da rota.
   *
   * @return Lista de conexões da rota
   */
  public ArrayList<Connection> getConnections() {
    return connections;
  }

  /**
   * Define a lista de conexões da rota.
   *
   * @param connections Lista de conexões da rota
   */
  public void setConnections(ArrayList<Connection> connections) {
    this.connections = connections;
  }

  /**
   * Obtém a duração total da rota.
   *
   * @return Duração total da rota
   */
  public float getDuration() {
    return duration;
  }

  /**
   * Define a duração total da rota.
   *
   * @param duration Duração total da rota
   */
  public void setDuration(float duration) {
    this.duration = duration;
  }

  /**
   * Adiciona uma conexão à rota.
   *
   * @param connection Conexão a ser adicionada
   */
  public void addConnection(Connection connection) {
    this.connections.add(connection);
  }

  /**
   * Retorna uma representação em formato de string da rota, incluindo o identificador da rota e todas as conexões.
   *
   * @return Representação em formato de string da rota
   */
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
