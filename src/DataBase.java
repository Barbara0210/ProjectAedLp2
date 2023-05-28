import java.util.*;
import java.io.*;

import edu.princeton.cs.algs4.RedBlackBST;
import java.util.stream.Collectors;

import edu.princeton.cs.algs4.EdgeWeightedDigraph;

public class DataBase {
  private RedBlackBST<Integer, User> users;
  private RedBlackBST<Integer, List<Trip>> userTrips;

  private HashMap<Integer, Route> routes; 
  private HashMap<Integer, Station> stations;
  private HashMap<Integer, Connection> connections;
  private HashMap<Integer, Schedule> schedules;

  public DataBase() {
    this.users = new RedBlackBST<>();
    this.routes = new HashMap<>();
    this.stations = new HashMap<>();
    this.connections = new HashMap<>();
    this.schedules = new HashMap<>();
    this.userTrips = new RedBlackBST<>();

  }

  // Inserir, remover, editar e listar métodos para cada classe...


  public void insertUser(User user) {
    users.put(user.getUserId(), user);
  }

  public User editUser(Integer userId, String newUserName) {
    User user = users.get(userId);
    if(user != null) {
      user.setUserName(newUserName);
    }
    return user;
  }

  public void removeUser(Integer userId) throws IOException {
    User user = users.get(userId);
    if (user != null) {
      archiveData("userArchive.txt", user.toString());
      List<Trip> trips = userTrips.get(userId);
      if (trips != null) {
        for (Trip trip : trips) {
          archiveData("tripArchive.txt", trip.toString());
        }
        userTrips.delete(userId);
      }
      users.delete(userId);
    }
  }
  public ArrayList<User> listUsers() {
    ArrayList<User> userList = new ArrayList<>();
    for (Integer key : users.keys()) {
      userList.add(users.get(key));
    }
    return userList;
  }





  public void insertRoute(Route route) {
    routes.put(route.getRouteId(), route);
  }


  public void removeRoute(Integer routeId) throws IOException {
    Route route = routes.get(routeId);
    if (route != null) {
      archiveData("routeArchive.txt", route.toString());
      routes.remove(routeId);
    }
  }
  public void editRoute(Route route) {
    routes.put(route.getRouteId(), route);
  }

  public List<Route> listRoutes() {
    return new ArrayList<>(routes.values());
  }

  public void insertStation(Station station) {
    stations.put(station.getStationId(), station);
  }

  public void removeStation(Integer stationId) throws IOException {
    Station station = stations.get(stationId);
    if (station != null) {
      archiveData("stationArchive.txt", station.toString());
      stations.remove(stationId);
    }
  }
  public void editStation(Station station) {
    stations.put(station.getStationId(), station);
  }

  public List<Station> listStations() {
    return new ArrayList<>(stations.values());
  }

  public void insertConnection(Connection connection) {
    connections.put(connection.getConnectionId(), connection);
  }

  public void removeConnection(Integer connectionId) throws IOException {
    Connection connection = connections.get(connectionId);
    if (connection != null) {
      archiveData("connectionArchive.txt", connection.toString());
      connections.remove(connectionId);
    }
  }
  public void editConnection(Connection connection) {
    connections.put(connection.getConnectionId(), connection);
  }

  public List<Connection> listConnections() {
    return new ArrayList<>(connections.values());
  }

  public void insertSchedule(Schedule schedule) {
    schedules.put(schedule.getScheduleId(), schedule);
  }

  public void removeSchedule(Integer scheduleId) throws IOException {
    Schedule schedule = schedules.get(scheduleId);
    if (schedule != null) {
      archiveData("scheduleArchive.txt", schedule.toString());
      schedules.remove(scheduleId);
    }
  }
  public void editSchedule(Schedule schedule) {
    schedules.put(schedule.getScheduleId(), schedule);
  }

  public List<Schedule> listSchedules() {
    return new ArrayList<>(schedules.values());
  }
  public void insertUserTrip(Integer userId, Trip trip) {
    User user = users.get(userId);
    if (user != null) {
      user.addTrip(trip);
      userTrips.put(userId, user.getTrips());  // Supondo que userTrips seja a estrutura de dados que guarda todas as viagens por userId.
    } else {
      System.out.println("User not found.");
    }
  }




  public List<Trip> listUserTrips(Integer userId) {
    return this.userTrips.get(userId);
  }

  public void editUserTrip(Integer userId, Integer tripIndex, Trip newTrip) {
    List<Trip> trips = this.userTrips.get(userId);
    if (trips != null && tripIndex >= 0 && tripIndex < trips.size()) {
      trips.set(tripIndex, newTrip);
      this.userTrips.put(userId, trips);
    }
  }

  public void removeUserTrip(Integer userId, Integer tripId) throws IOException {
    User user = users.get(userId);
    if (user != null) {
      List<Trip> trips = user.getTrips();
      for (Trip trip : trips) {
        if (trip.getTripId().equals(tripId)) {
          archiveData("tripArchive.txt", trip.toString());
          trips.remove(trip);
          break;
        }
      }
      userTrips.put(userId, trips);
    }}
  private void archiveData(String filename, String data) throws IOException {
    PrintWriter out = new PrintWriter(new FileWriter(filename, true));
    out.println(data);
    out.close();
  }

  // a) Todas as rotas planeadas e usadas por um User num dado período:
  public List<Route> getUserRoutesInPeriod(Integer userId, String startDate, String endDate) {
    List<Trip> trips = this.userTrips.get(userId);
    List<Route> result = new ArrayList<>();
    if(trips != null) {
      for(Trip trip: trips){
        for(Connection connection: trip.getRoute().getConnections()){
          if(isWithinPeriod(connection, startDate, endDate)){
            result.add(trip.getRoute());
          }
        }
      }
    }
    return result;
  }

  // b) Todas as Stations que não foram visitados/usados por Users, num dado período:
  public List<Station> getUnusedStationsInPeriod(String startDate, String endDate) {
    ArrayList<Station> usedStations = new ArrayList<>();
    for (Integer userId : userTrips.keys()){
      List<Trip> trips = userTrips.get(userId);
      for(Trip trip: trips){
        for(Connection connection: trip.getRoute().getConnections()){
          if(isWithinPeriod(connection, startDate, endDate)){
            usedStations.add(connection.getStation1());
            usedStations.add(connection.getStation2());
          }
        }
      }
    }
    ArrayList<Station> result = new ArrayList<>(stations.values());
    result.removeAll(usedStations);
    return result;
  }

  // c) Todos os User que já usaram uma rota que passa por determinadas Stations num dado período:
  public List<User> getUsersByStationsInPeriod(List<Station> targetStations, String startDate, String endDate) {
    List<User> result = new ArrayList<>();
    for(Integer userId: userTrips.keys()){
      List<Trip> trips = userTrips.get(userId);
      for(Trip trip: trips){
        for(Connection connection: trip.getRoute().getConnections()){
          if(isWithinPeriod(connection, startDate, endDate) &&
                  routeContainsStations(trip.getRoute(), targetStations)){
            result.add(trip.getUser());
          }
        }
      }
    }
    return result;
  }

  // d) Top-3 dos Users que visitaram/usaram o maior número de stations num dado período:
  public List<User> getTopUsersByStationCountInPeriod(String startDate, String endDate) {
    HashMap<User, Integer> userStationCount = new HashMap<>();
    for(Integer userId: userTrips.keys()){
      List<Trip> trips = userTrips.get(userId);
      for(Trip trip: trips){
        for(Connection connection: trip.getRoute().getConnections()){
          if(isWithinPeriod(connection, startDate, endDate)){
            userStationCount.put(trip.getUser(), userStationCount.getOrDefault(trip.getUser(), 0) + trip.getRoute().getConnections().size());
          }
        }
      }
    }
    return userStationCount.entrySet().stream()
            .sorted(Map.Entry.<User, Integer>comparingByValue().reversed())
            .limit(3)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
  }

  // e) Todas as rotas usadas entre dois terminais (Estações):
  public List<Route> getRoutesBetweenStations(Integer stationId1, Integer stationId2) {
    List<Route> result = new ArrayList<>();
    for(Route route: routes.values()){
      if(routeHasStations(route, stationId1, stationId2)){
        result.add(route);
      }
    }
    return result;
  }

  // Funções auxiliares
  private boolean isWithinPeriod(Connection connection, String startDate, String endDate) {
    String scheduleStartTime = connection.getSchedule().getStartTime();
    String scheduleEndTime = connection.getSchedule().getEndTime();
    return scheduleStartTime.compareTo(startDate) >= 0 && scheduleEndTime.compareTo(endDate) <= 0;
  }

  private boolean routeContainsStations(Route route, List<Station> targetStations) {
    List<Station> routeStations = new ArrayList<>();
    for(Connection connection: route.getConnections()){
      routeStations.add(connection.getStation1());
      routeStations.add(connection.getStation2());
    }
    return routeStations.containsAll(targetStations);
  }

  private boolean routeHasStations(Route route, Integer stationId1, Integer stationId2) {
    List<Station> routeStations = new ArrayList<>();
    for(Connection connection: route.getConnections()){
      routeStations.add(connection.getStation1());
      routeStations.add(connection.getStation2());
    }
    return routeStations.contains(stations.get(stationId1)) && routeStations.contains(stations.get(stationId2));
  }


  // Relatório Global
  // Relatório Global
  public void generateGlobalReport() {
    try (PrintWriter writer = new PrintWriter("report.txt", "UTF-8")) {
      writer.println("=== Relatório Global ===\n");

      writer.println("--- Lista de Usuários ---");
      for (Integer userId : users.keys()) {
        User user = users.get(userId);
        if (user != null) {
          writer.println(user.toString());
        }
      }

      writer.println("\n--- Lista de Estações ---");
      for (Integer stationId : stations.keySet()) {
        Station station = stations.get(stationId);
        if (station != null) {
          writer.println(station.toString());
        }
      }

      writer.println("\n--- Lista de Conexões ---");
      for (Integer connectionId : connections.keySet()) {
        Connection connection = connections.get(connectionId);
        if (connection != null) {
          writer.println(connection.toString());
        }
      }
    } catch (IOException e) {
      System.out.println("Erro ao escrever o relatório: " + e.getMessage());
    }
  }

  // No arquivo Database.java
  public void populateDatabase() {
    User user1 = new User(1, "João");
    User user2 = new User(2, "Maria");
    users.put(1, user1);
    users.put(2, user2);

    Station station1 = new Station(1, "Estação 1", "Carro",41.14961, -8.61099);
    Station station2 = new Station(2, "Estação 2", "Bicicleta",38.70775, -9.13659);
    stations.put(1, station1);
    stations.put(2, station2);

    Schedule schedule1 = new Schedule(1, "08:00", "12:00");
    Connection connection1 = new Connection(1, station1, station2, schedule1);
    connections.put(1, connection1);
  }
  public void populateNetworkMap(NetworkMap networkMap) {
    // Adiciona todas as estações ao mapa da rede
    for (Station station : this.stations.values()) {
      networkMap.addStation(station);
    }

    // Adiciona todas as conexões ao mapa da rede
    for (Connection connection : this.connections.values()) {
      networkMap.addConnection(connection);
    }
  }

  public void testNetworkMap() {
    NetworkMap networkMap = new NetworkMap();
    populateNetworkMap(networkMap);

    EdgeWeightedDigraph graph = networkMap.getTransportNetwork();
    System.out.println("Número total de vértices (estações): " + graph.V());
    System.out.println("Número total de arestas (conexões): " + graph.E());
    System.out.println("Número total de vértices (estações): " + networkMap.getNumberOfStations());

  }
/*
  public void populateUsers(String filename) throws IOException {
    // Criando a estrutura RedBlackBST (se já não existir)
    if (users == null) {
      users = new RedBlackBST<>();
    }

    // Lendo o arquivo de texto
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = reader.readLine()) != null) {
        // Dividindo a linha em partes
        String[] parts = line.split(", ");

        // Obtendo os valores
        Integer userId = Integer.parseInt(parts[0]);
        String userName = parts[1];

        // Criando um novo usuário
        User user = new User(userId, userName);

        // Inicializando a lista de viagens do usuário como uma nova lista vazia
        user.setTrips(new ArrayList<>());

        // Adicionando o usuário à RedBlackBST
        users.put(userId, user);
      }
    }
  }*/

  public void populateStations(String filename) throws IOException {
    if (stations == null) {
      stations = new HashMap<>();
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split(", ");
        if (parts.length >= 5 && !parts[0].isEmpty() && !parts[3].isEmpty() && !parts[4].isEmpty()) {
          Integer stationId = Integer.parseInt(parts[0]);
          String stationName = parts[1];
          String vehicle = parts[2];
          double latitude = Double.parseDouble(parts[3]);
          double longitude = Double.parseDouble(parts[4]);

          Station station = new Station(stationId, stationName, vehicle, latitude, longitude);
          stations.put(stationId, station);
        } else {
          System.err.println("Dados inválidos na linha: " + line);
        }
      }
    }
  }

  public void testPopulateStations() {
    try {
      populateStations("data/stations.txt");
      for (Station station : stations.values()) {
        System.out.println(station.toString());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void populateSchedules(String filename) throws IOException {
    if (schedules == null) {
      schedules = new HashMap<>();
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split(", ");
        if (parts.length >= 3 && !parts[0].isEmpty()) {
          Integer scheduleId = Integer.parseInt(parts[0]);
          String startTime = parts[1];
          String endTime = parts[2];

          Schedule schedule = new Schedule(scheduleId, startTime, endTime);
          schedules.put(scheduleId, schedule);
        } else {
          System.err.println("Dados inválidos na linha: " + line);
        }
      }
    }
  }

  public void testPopulateSchedules() {
    try {
      populateSchedules("data/schedules.txt");
      for (Schedule schedule : schedules.values()) {
        System.out.println(schedule.toString());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}






