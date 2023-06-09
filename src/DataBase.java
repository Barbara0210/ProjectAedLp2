import java.util.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import edu.princeton.cs.algs4.RedBlackBST;
import java.util.stream.Collectors;

import edu.princeton.cs.algs4.EdgeWeightedDigraph;

/**
 * A classe DataBase representa um banco de dados para armazenar e gerenciar vários dados relacionados a um sistema de transporte.
 * Inclui funcionalidades para gerenciar usuários, viagens, rotas, estações, conexões e horários.
 */
public class DataBase {

  private RedBlackBST<Integer, User> users; // Armazena os usuários com seus IDs como chaves.
  private RedBlackBST<Integer, List<Trip>> userTrips; // Armazena as viagens dos usuários com seus IDs como chaves.

  private HashMap<Integer, Route> routes; // Armazena as rotas com seus IDs como chaves.
  private HashMap<Integer, Station> stations; // Armazena as estações com seus IDs como chaves.
  private HashMap<Integer, Connection> connections; // Armazena as conexões com seus IDs como chaves.
  private HashMap<Integer, Schedule> schedules; // Armazena os horários com seus IDs como chaves.

  /**
   * Construtor da classe DataBase. Inicializa as estruturas de dados utilizadas.
   */
  public DataBase() {
    this.users = new RedBlackBST<>();
    this.routes = new HashMap<>();
    this.stations = new HashMap<>();
    this.connections = new HashMap<>();
    this.schedules = new HashMap<>();
    this.userTrips = new RedBlackBST<>();
  }

  /**
   * Obtém a árvore binária de pesquisa vermelho-preto que armazena os usuários.
   * @return A árvore binária de pesquisa vermelho-preto dos usuários.
   */
  public RedBlackBST<Integer, User> getUsers() {
    return users;
  }

  /**
   * Obtém a árvore binária de pesquisa vermelho-preto que armazena as viagens dos usuários.
   * @return A árvore binária de pesquisa vermelho-preto das viagens dos usuários.
   */
  public RedBlackBST<Integer, List<Trip>> getUserTrips() {
    return this.userTrips;
  }

  /**
   * Define a árvore binária de pesquisa vermelho-preto que armazena os usuários.
   * @param users A árvore binária de pesquisa vermelho-preto dos usuários.
   */
  public void setUsers(RedBlackBST<Integer, User> users) {
    this.users = users;
  }

  /**
   * Define a árvore binária de pesquisa vermelho-preto que armazena as viagens dos usuários.
   * @param userTrips A árvore binária de pesquisa vermelho-preto das viagens dos usuários.
   */
  public void setUserTrips(RedBlackBST<Integer, List<Trip>> userTrips) {
    this.userTrips = userTrips;
  }

  /**
   * Obtém o mapa que armazena as rotas.
   * @return O mapa das rotas.
   */
  public HashMap<Integer, Route> getRoutes() {
    return routes;
  }

  /**
   * Define o mapa que armazena as rotas.
   * @param routes O mapa das rotas.
   */
  public void setRoutes(HashMap<Integer, Route> routes) {
    this.routes = routes;
  }

  /**
   * Obtém o mapa que armazena as estações.
   * @return O mapa das estações.
   */
  public HashMap<Integer, Station> getStations() {
    return stations;
  }

  /**
   * Define o mapa que armazena as estações.
   * @param stations O mapa das estações.
   */
  public void setStations(HashMap<Integer, Station> stations) {
    this.stations = stations;
  }

  /**
   * Obtém o mapa que armazena as conexões.
   * @return O mapa das conexões.
   */
  public HashMap<Integer, Connection> getConnections() {
    return connections;
  }

  /**
   * Define o mapa que armazena as conexões.
   * @param connections O mapa das conexões.
   */
  public void setConnections(HashMap<Integer, Connection> connections) {
    this.connections = connections;
  }

  /**
   * Obtém o mapa que armazena os horários.
   * @return O mapa dos horários.
   */
  public HashMap<Integer, Schedule> getSchedules() {
    return schedules;
  }

  /**
   * Define o mapa que armazena os horários.
   * @param schedules O mapa dos horários.
   */
  public void setSchedules(HashMap<Integer, Schedule> schedules) {
    this.schedules = schedules;
  }

  // Aqui devem ser inseridos, removidos, editados e listados métodos para cada classe...


  /**
   * Insere um usuário no banco de dados.
   * @param user O usuário a ser inserido.
   */
  public void insertUser(User user) {
    users.put(user.getUserId(), user);
  }

  /**
   * Edita o nome de um usuário.
   * @param userId O ID do usuário a ser editado.
   * @param newUserName O novo nome do usuário.
   * @return O usuário após a edição.
   */
  public User editUser(Integer userId, String newUserName) {
    User user = users.get(userId);
    if(user != null) {
      user.setUserName(newUserName);
    }
    return user;
  }

  /**
   * Remove um usuário do banco de dados.
   * @param userId O ID do usuário a ser removido.
   * @throws IOException Se ocorrer um erro de E/S durante a remoção.
   */
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

  /**
   * Lista todos os usuários do banco de dados.
   * @return Uma lista de usuários.
   */
  public ArrayList<User> listUsers() {
    ArrayList<User> userList = new ArrayList<>();
    for (Integer key : users.keys()) {
      userList.add(users.get(key));
    }
    return userList;
  }

  /**
   * Insere uma rota no banco de dados.
   * @param route A rota a ser inserida.
   */
  public void insertRoute(Route route) {
    routes.put(route.getRouteId(), route);
  }

  /**
   * Remove uma rota do banco de dados.
   * @param routeId O ID da rota a ser removida.
   * @throws IOException Se ocorrer um erro de E/S durante a remoção.
   */
  public void removeRoute(Integer routeId) throws IOException {
    Route route = routes.get(routeId);
    if (route != null) {
      archiveData("routeArchive.txt", route.toString());
      routes.remove(routeId);
    }
  }

  /**
   * Edita uma rota existente no banco de dados.
   * @param route A rota editada.
   */
  public void editRoute(Route route) {
    routes.put(route.getRouteId(), route);
  }

  /**
   * Lista todas as rotas do banco de dados.
   * @return Uma lista de rotas.
   */
  public List<Route> listRoutes() {
    return new ArrayList<>(routes.values());
  }

  /**
   * Insere uma estação no banco de dados.
   * @param station A estação a ser inserida.
   */
  public void insertStation(Station station) {
    stations.put(station.getStationId(), station);
  }

  /**
   * Remove uma estação do banco de dados.
   * @param stationId O ID da estação a ser removida.
   * @throws IOException Se ocorrer um erro de E/S durante a remoção.
   */
  public void removeStation(Integer stationId) throws IOException {
    Station station = stations.get(stationId);
    if (station != null) {
      archiveData("stationArchive.txt", station.toString());
      stations.remove(stationId);
    }
  }

  /**
   * Edita uma estação existente no banco de dados.
   * @param station A estação editada.
   */
  public void editStation(Station station) {
    stations.put(station.getStationId(), station);
  }

  /**
   * Lista todas as estações do banco de dados.
   * @return Uma lista de estações.
   */
  public List<Station> listStations() {
    return new ArrayList<>(stations.values());
  }

  /**
   * Insere uma conexão no banco de dados.
   * @param connection A conexão a ser inserida.
   */
  public void insertConnection(Connection connection) {
    connections.put(connection.getConnectionId(), connection);
  }

  /**
   * Remove uma conexão do banco de dados.
   * @param connectionId O ID da conexão a ser removida.
   * @throws IOException Se ocorrer um erro de E/S durante a remoção.
   */
  public void removeConnection(Integer connectionId) throws IOException {
    Connection connection = connections.get(connectionId);
    if (connection != null) {
      archiveData("connectionArchive.txt", connection.toString());
      connections.remove(connectionId);
    }
  }

  /**
   * Edita uma conexão existente no banco de dados.
   * @param connection A conexão editada.
   */
  public void editConnection(Connection connection) {
    connections.put(connection.getConnectionId(), connection);
  }

  /**
   * Lista todas as conexões do banco de dados.
   * @return Uma lista de conexões.
   */
  public List<Connection> listConnections() {
    return new ArrayList<>(connections.values());
  }

  /**
   * Insere um horário no banco de dados.
   * @param schedule O horário a ser inserido.
   */
  public void insertSchedule(Schedule schedule) {
    schedules.put(schedule.getScheduleId(), schedule);
  }

  /**
   * Remove um horário do banco de dados.
   * @param scheduleId O ID do horário a ser removido.
   * @throws IOException Se ocorrer um erro de E/S durante a remoção.
   */
  public void removeSchedule(Integer scheduleId) throws IOException {
    Schedule schedule = schedules.get(scheduleId);
    if (schedule != null) {
      archiveData("scheduleArchive.txt", schedule.toString());
      schedules.remove(scheduleId);
    }
  }

  /**
   * Edita um horário existente no banco de dados.
   * @param schedule O horário editado.
   */
  public void editSchedule(Schedule schedule) {
    schedules.put(schedule.getScheduleId(), schedule);
  }

  /**
   * Lista todos os horários do banco de dados.
   * @return Uma lista de horários.
   */
  public List<Schedule> listSchedules() {
    return new ArrayList<>(schedules.values());
  }

  /**
   * Insere uma viagem de um usuário no banco de dados.
   * @param userId O ID do usuário.
   * @param trip A viagem a ser inserida.
   * @param startDate A data de início da viagem.
   * @param endDate A data de fim da viagem.
   */
  public void insertUserTrip(Integer userId, Trip trip, String startDate, String endDate) {
    User user = users.get(userId);
    if (user != null) {
      trip.setStartDate(startDate);
      trip.setEndDate(endDate);
      user.addTrip(trip);
      userTrips.put(userId, user.getTrips());
    } else {
      System.out.println("User not found.");
    }
  }

  /**
   * Lista todas as viagens de um usuário.
   * @param userId O ID do usuário.
   * @return Uma lista de viagens do usuário.
   */
  public List<Trip> listUserTrips(Integer userId) {
    return this.userTrips.get(userId);
  }

  /**
   * Edita uma viagem de um usuário.
   * @param userId O ID do usuário.
   * @param tripIndex O índice da viagem a ser editada.
   * @param newTrip A nova viagem.
   */
  public void editUserTrip(Integer userId, Integer tripIndex, Trip newTrip) {
    List<Trip> trips = this.userTrips.get(userId);
    if (trips != null && tripIndex >= 0 && tripIndex < trips.size()) {
      trips.set(tripIndex, newTrip);
      this.userTrips.put(userId, trips);
    }
  }

  /**
   * Remove uma viagem de um usuário.
   * @param userId O ID do usuário.
   * @param tripId O ID da viagem a ser removida.
   * @throws IOException Se ocorrer um erro de E/S durante a remoção.
   */
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
    }
  }
  /**
   * Arquiva os dados fornecidos em um arquivo.
   *
   * @param filename O nome do arquivo onde os dados serão arquivados.
   * @param data     Os dados a serem arquivados.
   * @throws IOException Se ocorrer um erro de E/S durante a gravação do arquivo.
   */
  private void archiveData(String filename, String data) throws IOException {
    PrintWriter out = new PrintWriter(new FileWriter(filename, true));
    out.println(data);
    out.close();
  }
  /**
   * Obtém todas as rotas planejadas e usadas por um usuário em um determinado período.
   *
   * @param userId     O ID do usuário.
   * @param startDate  A data de início do período.
   * @param endDate    A data de término do período.
   * @return Uma lista de rotas usadas pelo usuário no período especificado.
   */
  // a) Todas as rotas planeadas e usadas por um User num dado período;
  public List<Route> getUserRoutesInPeriod(Integer userId, String startDate, String endDate) {
    List<Trip> trips = this.userTrips.get(userId);
    List<Route> result = new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate start = LocalDate.parse(startDate, formatter);
    LocalDate end = LocalDate.parse(endDate, formatter);
    if(trips != null) {
      for(Trip trip: trips){
        LocalDate tripStart = LocalDate.parse(trip.getStartDate(), formatter);
        LocalDate tripEnd = LocalDate.parse(trip.getEndDate(), formatter);
        if((tripStart.isAfter(start) || tripStart.isEqual(start)) &&
                (tripEnd.isBefore(end) || tripEnd.isEqual(end))) {
          result.add(trip.getRoute());
        }
      }
    }
    return result;
  }
  /**
   * Obtém todas as estações que não foram visitadas/usadas por usuários em um determinado período.
   *
   * @param startDate A data de início do período.
   * @param endDate   A data de término do período.
   * @return Uma lista de estações não utilizadas no período especificado.
   */
  // b) Todas as estações que não foram visitadas/usadas por usuários num dado período;
  public List<Station> getUnusedStationsInPeriod(String startDate, String endDate) {
    List<Station> usedStations = new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate start = LocalDate.parse(startDate, formatter);
    LocalDate end = LocalDate.parse(endDate, formatter);
    for (Integer userId : userTrips.keys()){
      List<Trip> trips = userTrips.get(userId);
      for(Trip trip: trips){
        LocalDate tripStart = LocalDate.parse(trip.getStartDate(), formatter);
        LocalDate tripEnd = LocalDate.parse(trip.getEndDate(), formatter);
        if((tripStart.isAfter(start) || tripStart.isEqual(start)) &&
                (tripEnd.isBefore(end) || tripEnd.isEqual(end))) {
          for(Connection connection: trip.getRoute().getConnections()){
            usedStations.add(connection.getStation1());
            usedStations.add(connection.getStation2());
          }
        }
      }
    }
    List<Station> result = new ArrayList<>(stations.values());
    result.removeAll(usedStations);
    return result;
  }
  /**
   * Obtém todos os usuários que já utilizaram uma rota que passa por determinadas estações em um dado período.
   *
   * @param targetStations As estações alvo.
   * @param startDate      A data de início do período.
   * @param endDate        A data de término do período.
   * @return Uma lista de usuários que utilizaram as rotas contendo as estações alvo no período especificado.
   */
  // c) Todos os usuários que já utilizaram uma rota que passa por determinadas estações num dado período;
  public List<User> getUsersByStationsInPeriod(List<Station> targetStations, String startDate, String endDate) {
    List<User> result = new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate start = LocalDate.parse(startDate, formatter);
    LocalDate end = LocalDate.parse(endDate, formatter);
    for (Integer userId : userTrips.keys()){
      List<Trip> trips = userTrips.get(userId);
      for(Trip trip: trips){
        LocalDate tripStart = LocalDate.parse(trip.getStartDate(), formatter);
        LocalDate tripEnd = LocalDate.parse(trip.getEndDate(), formatter);
        if((tripStart.isAfter(start) || tripStart.isEqual(start)) &&
                (tripEnd.isBefore(end) || tripEnd.isEqual(end))) {
          for(Connection connection: trip.getRoute().getConnections()){
            if(targetStations.contains(connection.getStation1()) || targetStations.contains(connection.getStation2())) {
              result.add(users.get(userId));
              break;
            }
          }
        }
      }
    }
    return result;
  }
  /**
   * Obtém os top-3 usuários que visitaram/usaram o maior número de estações em um dado período.
   *
   * @param startDate A data de início do período.
   * @param endDate   A data de término do período.
   * @return Uma lista dos top-3 usuários com o maior número de estações visitadas/usadas no período especificado.
   */
  // d) Top-3 dos usuários que visitaram/usaram o maior número de estações num dado período;
  public List<User> getTopUsersByStationCountInPeriod(String startDate, String endDate) {
    HashMap<User, Integer> userStationCount = new HashMap<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate start = LocalDate.parse(startDate, formatter);
    LocalDate end = LocalDate.parse(endDate, formatter);
    for (Integer userId : userTrips.keys()){
      List<Trip> trips = userTrips.get(userId);
      for(Trip trip: trips){
        LocalDate tripStart = LocalDate.parse(trip.getStartDate(), formatter);
        LocalDate tripEnd = LocalDate.parse(trip.getEndDate(), formatter);
        if((tripStart.isAfter(start) || tripStart.isEqual(start)) &&
                (tripEnd.isBefore(end) || tripEnd.isEqual(end))) {
          userStationCount.putIfAbsent(users.get(userId), 0);
          for(Connection connection: trip.getRoute().getConnections()){
            userStationCount.put(users.get(userId), userStationCount.get(users.get(userId)) + 2);
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
  /**
   * Obtém as rotas entre duas estações especificadas.
   *
   * @param station1 A primeira estação.
   * @param station2 A segunda estação.
   * @return Uma lista de rotas que conectam as duas estações especificadas.
   */
  // e) Os horários de ligação entre dois terminais de duas localizações indicadas (ex. Porto, Paris);
  public List<Route> getRoutesBetweenStations(Station station1, Station station2) {
    List<Route> result = new ArrayList<>();
    for (Route route : routes.values()){
      for(Connection connection: route.getConnections()){
        if((connection.getStation1().equals(station1) && connection.getStation2().equals(station2)) ||
                (connection.getStation1().equals(station2) && connection.getStation2().equals(station1))) {
          result.add(route);
          break; // para evitar adicionar a mesma rota mais de uma vez se houver várias conexões correspondentes
        }
      }
    }
    return result;
  }
  // Funções auxiliares
  /**
   * Verifica se uma conexão está dentro de um determinado período.
   *
   * @param connection A conexão a ser verificada.
   * @param startDate  A data de início do período.
   * @param endDate    A data de término do período.
   * @return true se a conexão estiver dentro do período, caso contrário, false.
   */
  private boolean isWithinPeriod(Connection connection, String startDate, String endDate) {
    String scheduleStartTime = connection.getSchedule().getStartTime();
    String scheduleEndTime = connection.getSchedule().getEndTime();
    return scheduleStartTime.compareTo(startDate) >= 0 && scheduleEndTime.compareTo(endDate) <= 0;
  }
  /**
   * Verifica se uma rota contém todas as estações alvo.
   *
   * @param route          A rota a ser verificada.
   * @param targetStations As estações alvo.
   * @return true se a rota contiver todas as estações alvo, caso contrário, false.
   */
  private boolean routeContainsStations(Route route, List<Station> targetStations) {
    List<Station> routeStations = new ArrayList<>();
    for(Connection connection: route.getConnections()){
      routeStations.add(connection.getStation1());
      routeStations.add(connection.getStation2());
    }
    return routeStations.containsAll(targetStations);
  }

  /**
   * Verifica se uma rota contém duas estações especificadas.
   *
   * @param route      A rota a ser verificada.
   * @param stationId1 O ID da primeira estação.
   * @param stationId2 O ID da segunda estação.
   * @return true se a rota contiver ambas as estações especificadas, caso contrário, false.
   */
  private boolean routeHasStations(Route route, Integer stationId1, Integer stationId2) {
    List<Station> routeStations = new ArrayList<>();
    for(Connection connection: route.getConnections()){
      routeStations.add(connection.getStation1());
      routeStations.add(connection.getStation2());
    }
    return routeStations.contains(stations.get(stationId1)) && routeStations.contains(stations.get(stationId2));
  }


  // Relatório Global
  /**
   * Gera um relatório global e o salva em um arquivo de texto.
   */
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

  /**
   * Preenche o banco de dados com dados iniciais.
   */

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
  /**
   * Popula o mapa da rede com as estações e conexões do banco de dados.
   *
   * @param networkMap O mapa da rede a ser populado.
   */
  public void populateNetworkMap(NetworkMap networkMap) throws IOException {
    populateStations("data/stations.txt");
    populateConnections("data/connections.txt");

    for (Station station : stations.values()) {
      networkMap.addStation(station);
    }

    for (Connection connection : connections.values()) {
      networkMap.addConnection(connection);
    }
  }
  /**
   * Testa o mapa da rede, exibindo o número total de estações e conexões.
   */
 public void testNetworkMap() {
    NetworkMap networkMap = new NetworkMap();
   // populateNetworkMap(networkMap);

    EdgeWeightedDigraph graph = networkMap.getTransportNetwork();
    System.out.println("Número total de vértices (estações): " + graph.V());
    System.out.println("Número total de arestas (conexões): " + graph.E());
    System.out.println("Número total de vértices (estações): " + networkMap.getNumberOfStations());

  }
  /**
   * Preenche as estações a partir de um arquivo de texto.
   *
   * @param filename O nome do arquivo de texto contendo os dados das estações.
   * @throws IOException Se ocorrer um erro de leitura do arquivo.
   */
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

  /**
   * Testa o método populateStations, imprimindo as estações populadas.
   */
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
  /**
   * Popula os horários com base em um arquivo de texto.
   *
   * @param filename o nome do arquivo de texto contendo as informações dos horários
   * @throws IOException se ocorrer um erro durante a leitura do arquivo
   */
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
  /**
   * Testa o método populateSchedules, imprimindo os horários populados.
   */
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
  /**
   * Popula as conexões com base em um arquivo de texto.
   *
   * @param filename o nome do arquivo de texto contendo as informações das conexões
   * @throws IOException se ocorrer um erro durante a leitura do arquivo
   */
  public void populateConnections(String filename) throws IOException {
    // Criando a estrutura HashMap (se já não existir)
    if (connections == null) {
      connections = new HashMap<>();
    }

    // Lendo o arquivo de texto
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = reader.readLine()) != null) {
        // Dividindo a linha em partes
        String[] parts = line.split(", ");

        // Verificando se os dados são válidos
        if (parts.length != 4) {
          System.out.println("Dados inválidos na linha: " + line);
          continue;
        }


        // Obtendo os valores
        Integer connectionId = Integer.parseInt(parts[0]);
        Station station1 = stations.get(Integer.parseInt(parts[1]));
        Station station2 = stations.get(Integer.parseInt(parts[2]));
        Schedule schedule = schedules.get(Integer.parseInt(parts[3]));

        // Criando uma nova conexão
        Connection connection = new Connection(connectionId, station1, station2, schedule);

        // Adicionando a conexão à HashMap
        connections.put(connectionId, connection);
      }
    }
  }
  /**
   * Testa o método populateConnections, imprimindo as conexões populadas.
   */
  public void testPopulateConnections() {
    try {
      // Chame a função para popular as conexões com o nome do arquivo de texto
      populateConnections("data/connections.txt");

      // Imprima as conexões para verificar se elas foram preenchidas corretamente
      for (Connection connection : connections.values()) {
        System.out.println(connection.toString());
      }
    } catch (IOException e) {
      // Trate qualquer exceção que possa ser lançada
      e.printStackTrace();
    }
  }
  /**
   * Popula as rotas com base em um arquivo de texto.
   *
   * @param filename o nome do arquivo de texto contendo as informações das rotas
   * @throws IOException se ocorrer um erro durante a leitura do arquivo
   */
  public void populateRoutes(String filename) throws IOException {
    if (routes == null) {
      routes = new HashMap<>();
    }
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split(", ");

        if (parts.length < 3) {
          System.out.println("Dados inválidos na linha: " + line);
          continue;
        }

        Integer routeId = Integer.parseInt(parts[0]);
        String[] connectionIds = parts[1].split("/");

        ArrayList<Connection> connections = new ArrayList<>();
        for (String id : connectionIds) {
          Integer connectionId = Integer.parseInt(id);
          Connection connection = this.connections.get(connectionId);
          if (connection != null) {
            connections.add(connection);
          }
        }

        float duration = Float.parseFloat(parts[2]);
        Route route = new Route(routeId, connections, duration);
        routes.put(routeId, route);
      }
    }
  }
  /**
   * Testa o método populateRoutes, imprimindo as rotas populadas.
   */
  public void testPopulateRoutes() {
    try {
      // Chame a função para popular as rotas com o nome do arquivo de texto
      populateRoutes("data/routes.txt");

      // Imprima as rotas para verificar se elas foram preenchidas corretamente
      for (Route route : routes.values()) {
        System.out.println(route.toString());
      }
    } catch (IOException e) {
      // Trate qualquer exceção que possa ser lançada
      e.printStackTrace();
    }
  }
  /**
   * Popula as viagens com base em um arquivo de texto.
   *
   * @param filename o nome do arquivo de texto contendo as informações das viagens
   * @throws IOException se ocorrer um erro durante a leitura do arquivo
   */
  public void populateTrips(String filename) throws IOException {
    if (userTrips == null) {
      userTrips = new RedBlackBST<>();
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split(", ");
        if (parts.length < 5) {
          System.out.println("Dados inválidos na linha: " + line);
          continue;
        }
        Integer tripId = Integer.parseInt(parts[0]);
        Integer userId = Integer.parseInt(parts[1]);
        Integer routeId = Integer.parseInt(parts[2]);
        String startDate = parts[3];  // Leitura da data de início da viagem
        String endDate = parts[4];  // Leitura da data de fim da viagem
        Route route = routes.get(routeId);
        User user = users.get(userId);
        if (route != null && user != null) {
          Trip trip = new Trip(tripId, user, route, startDate, endDate);  // Inclusão das datas na criação do objeto Trip
          if (userTrips.contains(userId)) {
            userTrips.get(userId).add(trip);
          } else {
            List<Trip> tripList = new ArrayList<>();
            tripList.add(trip);
            userTrips.put(userId, tripList);
          }
          // Aqui também atualizamos o usuário com as novas viagens
          user.setTrips(userTrips.get(userId));
        }
      }
    }
  }


  /**
   * Testa o método populateTrips, imprimindo as viagens populadas para cada usuário.
   */
  public void testPopulateTrips() {
    try {
      populateTrips("data/trips.txt");
      for (Integer userId : userTrips.keys()) {
        List<Trip> trips = userTrips.get(userId);
        System.out.println("Trips for user " + userId + ":");
        for (Trip trip : trips) {
          System.out.println(trip.toString());
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  /**
   * Popula os usuários com base em um arquivo de texto.
   *
   * @param filename o nome do arquivo de texto contendo as informações dos usuários
   * @throws IOException se ocorrer um erro durante a leitura do arquivo
   */
  public void populateUsers(String filename) throws IOException {
    if (users == null) {
      users = new RedBlackBST<>();
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split(", ");
        if (parts.length < 2) {
          System.out.println("Dados inválidos na linha: " + line);
          continue;
        }
        Integer userId = Integer.parseInt(parts[0]);
        String userName = parts[1];
        User user = new User(userId, userName);
        List<Trip> userTripList = userTrips.get(userId);
        // Verifique se a lista de viagens é null antes de definir as viagens do usuário
        if (userTripList != null) {
          user.setTrips(userTripList);
        } else {
          // Se a lista de viagens for null, defina as viagens do usuário como uma nova lista vazia
          user.setTrips(new ArrayList<>());
        }
        users.put(userId, user);
      }
    }
  }
  /**
   * Lista todos os usuários e suas viagens.
   */
  public void listAllUsersAndTrips() {
    for (Integer userId : users.keys()) {
      User user = users.get(userId);
      System.out.println("User ID: " + userId);
      System.out.println("Trips:");
      for (Trip trip : user.getTrips()) {
        System.out.println("Trip ID: " + trip.getTripId());
        System.out.println("Route ID: " + trip.getRoute().getRouteId());
        System.out.println("Start Date: " + trip.getStartDate());
        System.out.println("End Date: " + trip.getEndDate());
        System.out.println("-----------------------------");
      }
    }
  }
  /**
   * Testa o método populateUsers, imprimindo os usuários e suas viagens.
   */
  public void testPopulateUsers() {
    try {
      populateUsers("data/users.txt");
      for (Integer userId : users.keys()) {
        User user = users.get(userId);
        System.out.println(user.toString());
        List<Trip> userTripList = userTrips.get(userId);
        if(userTripList != null) {
          System.out.println("Trips: ");
          for (Trip trip : userTripList) {
            System.out.println(trip.toString());
          }
        } else {
          System.out.println("No trips for this user.");
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Preenche todos os dados do sistema chamando os métodos de população individual.
   */
  public void populateData() {
    try {
      // Primeiro, preencha os dados que não têm dependências
      populateStations("data/stations.txt");
      populateSchedules("data/schedules.txt");
      populateConnections("data/connections.txt");

      // Em seguida, preencha os dados de Routes, que dependem de Stations, Schedules e Connections
      populateRoutes("data/routes.txt");

      // Agora, preencha os dados de Users, que não dependem de outros dados no momento da criação
      populateUsers("data/users.txt");

      // Finalmente, preencha os dados de Trips, que dependem de Users e Routes
      populateTrips("data/trips.txt");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}






