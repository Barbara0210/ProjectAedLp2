import java.util.*;
import java.io.*;

/**
 * Classe de teste para a classe DataBase.
 */
public class TestDataBase {

    /**
     * Testa o método listConnections da classe DataBase.
     * Imprime uma lista de conexões no console.
     * @param dataBase O objeto DataBase a ser testado.
     */
    public static void testListConnections(DataBase dataBase) {
        System.out.println("List of connections:");
        for (Connection connection : dataBase.listConnections()) {
            System.out.println(connection.getConnectionId());
            System.out.println(connection.getStation1());
            System.out.println(connection.getStation2());
            System.out.println(connection.getSchedule());
        }
    }

    /**
     * Testa o método listRoutes da classe DataBase.
     * Imprime uma lista de rotas no console.
     * @param dataBase O objeto DataBase a ser testado.
     */
    public static void testListRoutes(DataBase dataBase) {
        System.out.println("List of routes :");
        for (Route route : dataBase.listRoutes()) {
            System.out.println("Route ID: " + route.getRouteId());
            System.out.println("Route Duration: " + route.getDuration());
            List<Connection> connections = route.getConnections();
            System.out.println("Connections in this route:");
            for (Connection connection : connections) {
                System.out.println(connection);
            }
        }
    }

    /**
     * Testa o método listStations da classe DataBase.
     * Imprime uma lista de estações no console.
     * @param dataBase O objeto DataBase a ser testado.
     */
    public static void testListStations(DataBase dataBase) {
        System.out.println("List of stations :");
        for (Station station : dataBase.listStations()) {
            System.out.println("Station ID: " + station.getStationId());
            System.out.println("Station Name: " + station.getStationName());
            System.out.println("Station Type: " + station.getVehicle());
            System.out.println("Station Latitude: " + station.getLatitude());
            System.out.println("Station Longitude " + station.getLongitude());
            System.out.println("-------------------------");
        }
    }

    /**
     * Testa o método listUsers da classe DataBase.
     * Imprime uma lista de usuários no console.
     * @param dataBase O objeto DataBase a ser testado.
     */
    public static void testListUsers(DataBase dataBase) {
        System.out.println("List of users:");
        for (Integer userKey : dataBase.getUsers().keys()) {
            User user = dataBase.getUsers().get(userKey);
            System.out.println("User ID: " + user.getUserId() + "\nUsername: " + user.getUserName());
            if (dataBase.getUserTrips().contains(userKey)) {
                List<Trip> tripsList = dataBase.getUserTrips().get(userKey);
                if (!tripsList.isEmpty()) {
                    System.out.println("Trips:");
                    for (Trip trip : tripsList) {
                        System.out.println("Trip ID: " + trip.getTripId() + ", User: " + user.getUserName() + ", Route: " + trip.getRoute().getRouteId());
                    }
                }
            }
            System.out.println();
        }
    }

    /**
     * Testa o método listAllTrips da classe DataBase.
     * Imprime uma lista de todas as viagens no console.
     * @param dataBase O objeto DataBase a ser testado.
     */
    public static void testListAllTrips(DataBase dataBase) {
        for (Integer userId : dataBase.getUserTrips().keys()) {
            List<Trip> trips = dataBase.getUserTrips().get(userId);
            System.out.println("User ID: " + userId);
            for (Trip trip : trips) {
                System.out.println("Trip ID: " + trip.getTripId());
                System.out.println("Route ID: " + trip.getRoute().getRouteId());
                System.out.println("Start Date: " + trip.getStartDate());
                System.out.println("End Date: " + trip.getEndDate());
                System.out.println("-----------------------------");
            }
        }
    }

    /**
     * Testa o método getUserRoutesInPeriod da classe DataBase.
     * Imprime as rotas de um usuário em um determinado período no console.
     * @param dataBase O objeto DataBase a ser testado.
     * @param userId O ID do usuário.
     * @param startDate A data de início do período.
     * @param endDate A data de fim do período.
     */
    public static void testGetUserRoutesInPeriod(DataBase dataBase, Integer userId, String startDate, String endDate) {
        System.out.println("getUserRoutesInPeriod:");
        List<Route> userRoutes = dataBase.getUserRoutesInPeriod(userId, startDate, endDate);
        for (Route route : userRoutes) {
            System.out.println(route);
        }
    }

    /**
     * Testa o método getUnusedStationsInPeriod da classe DataBase.
     * Imprime as estações não utilizadas em um determinado período no console.
     * @param dataBase O objeto DataBase a ser testado.
     * @param startDate A data de início do período.
     * @param endDate A data de fim do período.
     */
    public static void testGetUnusedStationsInPeriod(DataBase dataBase, String startDate, String endDate) {
        System.out.println("getUnusedStationsInPeriod:");
        List<Station> unusedStations = dataBase.getUnusedStationsInPeriod(startDate, endDate);
        if (unusedStations.isEmpty()) {
            System.out.println("Não existem estações não utilizadas no período fornecido.");
        } else {
            for (Station station : unusedStations) {
                System.out.println(station);
            }
        }
    }

    /**
     * Testa o método getUsersByStationsInPeriod da classe DataBase.
     * Imprime os usuários que utilizaram determinadas estações em um período no console.
     * @param dataBase O objeto DataBase a ser testado.
     * @param targetStations As estações de interesse.
     * @param startDate A data de início do período.
     * @param endDate A data de fim do período.
     */
    public static void testGetUsersByStationsInPeriod(DataBase dataBase, List<Station> targetStations, String startDate, String endDate) {
        System.out.println("getUsersByStationsInPeriod:");
        List<User> usersByStations = dataBase.getUsersByStationsInPeriod(targetStations, startDate, endDate);
        for (User user : usersByStations) {
            System.out.println(user);
        }
    }

    /**
     * Testa o método getTopUsersByStationCountInPeriod da classe DataBase.
     * Imprime os principais usuários com base na contagem de estações utilizadas em um período no console.
     * @param dataBase O objeto DataBase a ser testado.
     * @param startDate A data de início do período.
     * @param endDate A data de fim do período.
     */
    public static void testGetTopUsersByStationCountInPeriod(DataBase dataBase, String startDate, String endDate) {
        System.out.println("getTopUsersByStationCountInPeriod:");
        List<User> topUsers = dataBase.getTopUsersByStationCountInPeriod(startDate, endDate);
        for (User user : topUsers) {
            System.out.println(user);
        }
    }

    /**
     * Testa o método getRoutesBetweenStations da classe DataBase.
     * Imprime as rotas entre duas estações no console.
     * @param dataBase O objeto DataBase a ser testado.
     * @param stationId1 O ID da primeira estação.
     * @param stationId2 O ID da segunda estação.
     */
    public static void testGetRoutesBetweenStations(DataBase dataBase, Integer stationId1, Integer stationId2) {
        System.out.println("getRoutesBetweenStations:");
        Station station1 = dataBase.getStations().get(stationId1);
        Station station2 = dataBase.getStations().get(stationId2);
        List<Route> routesBetweenStations = dataBase.getRoutesBetweenStations(station1, station2);
        for (Route route : routesBetweenStations) {
            System.out.println(route);
        }
    }

    public static void main(String[] args) {
        DataBase dataBase = new DataBase();
        dataBase.populateData();
        dataBase.listAllUsersAndTrips();

        Integer userId = 1;
        String startDate = "2023-01-01";
        String endDate = "2023-12-31";

        testGetUserRoutesInPeriod(dataBase, userId, startDate, endDate);
        testGetUnusedStationsInPeriod(dataBase, startDate, endDate);

        List<Station> targetStations = new ArrayList<>();
        Station station1 = dataBase.getStations().get(1); // Substitua 1 pelo ID da estação que você quer testar
        Station station2 = dataBase.getStations().get(2); // Substitua 2 pelo ID da outra estação que você quer testar

        targetStations.add(station1);
        targetStations.add(station2);

        testGetUsersByStationsInPeriod(dataBase, targetStations, startDate, endDate);
        testGetTopUsersByStationCountInPeriod(dataBase, startDate, endDate);
        testGetRoutesBetweenStations(dataBase, 1, 2); // Substitua 1 e 2 pelos IDs das estações que você quer testar
        testListStations(dataBase);

        try {
            dataBase.removeStation(6);
        } catch (IOException e) {
            e.printStackTrace();
        }

        testListStations(dataBase);
        testListConnections(dataBase);
        dataBase.generateGlobalReport();
    }
}
