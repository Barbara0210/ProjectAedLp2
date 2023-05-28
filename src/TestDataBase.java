import java.util.*;
import java.io.*;

public class TestDataBase {

    public static void main(String[] args) {
        DataBase dataBase = new DataBase();
        try {


            // Testar a função de remoção do usuário
            User user1 = new User(1, "User1");
            dataBase.insertUser(user1);
            System.out.println("User inserido: " + user1);
            dataBase.removeUser(user1.getUserId());
            System.out.println("Usuário removido.");

            // Testar a função de remoção da estação
            Station station1 = new Station(1,"estacao 1","autocarro",41.14961, -8.61099);
            Station station2 = new Station(2,"estacao 2","aviao",38.70775, -9.13659);

            dataBase.insertStation(station1);
            System.out.println("Estação inserida: " + station1);
           // dataBase.removeStation(station1.getStationId());
           // System.out.println("Estação removida.");

            Integer userId = 1;
            String startDate = "2023-01-01";
            String endDate = "2023-12-31";
            System.out.println("getUserRoutesInPeriod:");
            List<Route> userRoutes = dataBase.getUserRoutesInPeriod(userId, startDate, endDate);
            for (Route route : userRoutes) {
                System.out.println(route);
            }

            // Testar getUnusedStationsInPeriod
            System.out.println("getUnusedStationsInPeriod:");
            List<Station> unusedStations = dataBase.getUnusedStationsInPeriod(startDate, endDate);
            for (Station station : unusedStations) {
                System.out.println(station);
            }

            // Testar getUsersByStationsInPeriod
            List<Station> targetStations = new ArrayList<>();
          //  Station station1 = ...; // Busque ou crie a estação 1 de acordo com sua base de dados
           // Station station2 = ...; // Busque ou crie a estação 2 de acordo com sua base de dados
            targetStations.add(station1);
            targetStations.add(station2);
            System.out.println("getUsersByStationsInPeriod:");
            List<User> usersByStations = dataBase.getUsersByStationsInPeriod(targetStations, startDate, endDate);
            for (User user : usersByStations) {
                System.out.println(user);
            }

            // Testar getTopUsersByStationCountInPeriod
            System.out.println("getTopUsersByStationCountInPeriod:");
            List<User> topUsers = dataBase.getTopUsersByStationCountInPeriod(startDate, endDate);
            for (User user : topUsers) {
                System.out.println(user);
            }

            // Testar getRoutesBetweenStations
            Integer stationId1 =1;
            Integer stationId2 = 2;
            System.out.println("getRoutesBetweenStations:");
            List<Route> routesBetweenStations = dataBase.getRoutesBetweenStations(stationId1, stationId2);
            for (Route route : routesBetweenStations) {
                System.out.println(route);
            }

            dataBase.testPopulateStations();
            dataBase.testPopulateSchedules();

            // Test listSchedules
            System.out.println("List of schedulesSAA:");
            for (Schedule schedule : dataBase.listSchedules()) {
                System.out.println("Schedule ID: " + schedule.getScheduleId() + ", Start Time: " + schedule.getStartTime() + ", End Time: " + schedule.getEndTime());
            }

            // Test listStations
            System.out.println("List of stations:");
            for (Station station : dataBase.listStations()) {
                System.out.println(station.getStationName());
            }
 /*
            // Testar a função de remoção da viagem do usuário
            User user2 = new User(2, "User2");
            dataBase.insertUser(user2);
            System.out.println("Usuário inserido: " + user2);
            Trip trip1 = new Trip(1),;
            dataBase.insertUserTrip(user2.getUserId(), trip1);
            System.out.println("Viagem inserida para o usuário: " + trip1);
            dataBase.removeUserTrip(user2.getUserId(), trip1.getTripId());
            System.out.println("Viagem removida do usuário.");
         // Testar a função de remoção da rota
            Route route1 = new Route(1);
            dataBase.insertRoute(route1);
            System.out.println("Rota inserida: " + route1);
            dataBase.removeRoute(route1.getRouteId());
            System.out.println("Rota removida.");



            // Testar a função de remoção da conexão
            Connection connection1 = new Connection(1);
            dataBase.insertConnection(connection1);
            System.out.println("Conexão inserida: " + connection1);
            dataBase.removeConnection(connection1.getConnectionId());
            System.out.println("Conexão removida.");

            // Testar a função de remoção do horário
            Schedule schedule1 = new Schedule(1);
            dataBase.insertSchedule(schedule1);
            System.out.println("Horário inserido: " + schedule1);
            dataBase.removeSchedule(schedule1.getScheduleId());
            System.out.println("Horário removido.");

      */
        } catch (IOException e) {
            e.printStackTrace();
        }

/*
        // Test insertStation
        Station station1 = new Station(1, "Station 1", "autocarro");
        Station station2 = new Station(2, "Station 2", "aviao");
        Station station3 = new Station(3, "Station 3", "taxi");

        dataBase.insertStation(station1);
        dataBase.insertStation(station2);
        dataBase.insertStation(station3);

        // Test listStations
        System.out.println("List of stations:");
        for (Station station : dataBase.listStations()) {
            System.out.println(station.getStationName());
        }

        station1.setStationName("estacao teste edicao");
        dataBase.editStation(station1);
        // Test listStations
        System.out.println("List of stations:");
        for (Station station : dataBase.listStations()) {
            System.out.println(station.getStationName());
        }
        // Test removeStation
        dataBase.removeStation(station1.getStationId());
        System.out.println("Station 1 removed.");
        // Test listStations
        System.out.println("List of stations:");
        for (Station station : dataBase.listStations()) {
            System.out.println(station.getStationName());
        }

        // Test insertSchedule
        Schedule schedule1 = new Schedule(1, "12:00", "13:00");
        Schedule schedule2 = new Schedule(2, "13:00", "14:00");
        Schedule schedule3 = new Schedule(3, "14:00", "15:00");

        dataBase.insertSchedule(schedule1);
        dataBase.insertSchedule(schedule2);
        dataBase.insertSchedule(schedule3);

        // Test listSchedules
        System.out.println("List of schedules before removal:");
        for (Schedule schedule : dataBase.listSchedules()) {
            System.out.println("Schedule ID: " + schedule.getScheduleId() + ", Start Time: " + schedule.getStartTime() + ", End Time: " + schedule.getEndTime());
        }

        // Test editSchedule
        schedule1.setStartTime("14:00");
        schedule1.setEndTime("15:00");
        dataBase.editSchedule(schedule1);
        for (Schedule schedule : dataBase.listSchedules()) {
            System.out.println("Schedule ID: " + schedule.getScheduleId() + ", Start Time: " + schedule.getStartTime() + ", End Time: " + schedule.getEndTime());
        }
        // Test removeSchedule
        dataBase.removeSchedule(schedule1.getScheduleId());
        System.out.println("Schedule 1 removed.");

        // Test listSchedules after removal
        System.out.println("List of schedules after removal:");
        for (Schedule schedule : dataBase.listSchedules()) {
            System.out.println("Schedule ID: " + schedule.getScheduleId() + ", Start Time: " + schedule.getStartTime() + ", End Time: " + schedule.getEndTime());
        }

        // Test insertConnection
        Connection connection1 = new Connection(1, station1, station2, schedule1);
        Connection connection2 = new Connection(2, station2, station3, schedule2);
        Connection connection3 = new Connection(3, station3, station1, schedule3);

        dataBase.insertConnection(connection1);
        dataBase.insertConnection(connection2);
        dataBase.insertConnection(connection3);


        // Test listConnections
        System.out.println("List of connections:");
        for (Connection connection : dataBase.listConnections()) {
            System.out.println(connection.getConnectionId());
            System.out.println(connection.getStation1());
            System.out.println(connection.getStation2());
            System.out.println(connection.schedule);

        }

        // Test editConnection
        connection1.setStation1(station3);
        connection1.setStation2(station2);
        connection1.setSchedule(schedule2);
        dataBase.editConnection(connection1);
        System.out.println("Connection 1 edited.");
        System.out.println("List of connections:");
        for (Connection connection : dataBase.listConnections()) {
            System.out.println(connection.getConnectionId());
            System.out.println(connection.getStation1());
            System.out.println(connection.getStation2());
            System.out.println(connection.schedule);

        }
        // Test removeConnection
        dataBase.removeConnection(connection1.getConnectionId());
        System.out.println("Connection 1 removed.");
        System.out.println("List of connections:");
        for (Connection connection : dataBase.listConnections()) {
            System.out.println(connection.getConnectionId());
            System.out.println(connection.getStation1());
            System.out.println(connection.getStation2());
            System.out.println(connection.schedule);

        }

        // Test insertRoute
        Route route1 = new Route(1, new ArrayList<>(Arrays.asList(connection1)), 2.5f);
        Route route2 = new Route(2, new ArrayList<>(Arrays.asList(connection2)), 2.5f);
        Route route3 = new Route(3, new ArrayList<>(Arrays.asList(connection3)), 2.5f);

        route1.addConnection(connection2);
        dataBase.insertRoute(route1);
        System.out.println("Route 1 inserted.");

        // Test listRoutes
        System.out.println("List of routes:");
        for (Route route : dataBase.listRoutes()) {
            System.out.println(route);
        }

        // Test editRoute
        route1.addConnection(connection3);
        dataBase.editRoute(route1);
        System.out.println("Route 1 edited. Connection 3 added.");

        // Test listRoutes after editing
        System.out.println("List of routes after editing:");
        for (Route route : dataBase.listRoutes()) {
            System.out.println(route);
        }

        // Test removeRoute
        dataBase.removeRoute(route1.getRouteId());
        System.out.println("Route 1 removed.");

        // Test listRoutes after removing
        System.out.println("List of routes after removing:");
        for (Route route : dataBase.listRoutes()) {
            System.out.println(route);
        }
// Test insertUser
        User user1 = new User(1, "User 1");
        dataBase.insertUser(user1);
        System.out.println("User 1 inserted.");

// Test insertTrip
        Trip trip1 = new Trip(1, user1, route1);
        dataBase.insertUserTrip(user1.getUserId(), trip1);
        System.out.println("Trip 1 for User 1 inserted.");

// Test listUserTrips
        List<Trip> user1Trips = dataBase.listUserTrips(user1.getUserId());
        if (user1Trips != null) {
            System.out.println("List of User 1's trips:");
            for (Trip trip : user1Trips) {
                System.out.println(trip.getTripId());
                System.out.println(trip.route);
                System.out.println(trip.route.duration);


            }
        } else {
            System.out.println("User 1 has no trips.");
        }

// Test editUserTrip
        if (user1Trips != null) {
            int tripIndexToEdit = user1Trips.indexOf(trip1); // find the index of trip1 in the list
            Trip editedTrip1 = new Trip(1, user1, route3); // let's say we want to edit trip1 to use route2
            dataBase.editUserTrip(user1.getUserId(), tripIndexToEdit, editedTrip1);
            System.out.println("Trip 1 for User 1 edited.");
        }

        // Test removeUserTrip
        if (user1Trips != null) {
            dataBase.removeUserTrip(user1.getUserId(), trip1.getTripId());  // Remove the trip by its ID
            System.out.println("Trip 1 for User 1 removed.");

            // Test listTrips again
            System.out.println("List of User 1's trips after removal:");
            user1Trips = dataBase.listUserTrips(user1.getUserId());  // <-- Only update once
            if (user1Trips != null && !user1Trips.isEmpty()) {
                for (Trip trip : user1Trips) {
                    System.out.println(trip.getTripId());
                    System.out.println(trip.route);
                    System.out.println(trip.route.duration);
                }
            } else {
                System.out.println("User 1 has no trips.");
            }
        }



// Test listUsers
        System.out.println("List of users:");
        for(
    User user :dataBase.listUsers())

    {
        System.out.println(user.getUserName());
    }

// Test editUser
        user1.setUserName("User 1 edited");
        dataBase.editUser(user1.getUserId(),user1.getUserName());
        System.out.println("User 1 edited.");

// Test removeUser
        dataBase.removeUser(user1.getUserId());
        System.out.println("User 1 removed.");
*/

}
    }
