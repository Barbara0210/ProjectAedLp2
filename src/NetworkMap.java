import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;

import java.util.HashMap;
import java.util.Map;

public class NetworkMap {
    private EdgeWeightedDigraph transportNetwork;
    private Map<Integer, Station> stationMap;

    public NetworkMap() {
        this.transportNetwork = new EdgeWeightedDigraph(1000); // Ajuste o número de vértices de acordo com a quantidade de estações
        this.stationMap = new HashMap<>();
    }

    public void addStation(Station station) {
        stationMap.put(station.getStationId(), station);
    }

    public void addConnection(Connection connection) {
        int v = connection.getStation1().getStationId();
        int w = connection.getStation2().getStationId();
        double weight = connection.getSchedule().getDuration();
        DirectedEdge edge = new DirectedEdge(v, w, weight);
        transportNetwork.addEdge(edge);
    }

    public EdgeWeightedDigraph getTransportNetwork() {
        return transportNetwork;
    }

    public int getNumberOfStations() {
        return stationMap.size();
    }

    public void listStationsByType(String type1, String type2) {
        for (Station station : stationMap.values()) {
            if (station.getVehicle().equals(type1) || station.getVehicle().equals(type2)) {
                System.out.println(station);
            }
        }
    }

    public void listStationsNearCoordinate(double latitude, double longitude, double distance, String type) {
        for (Station station : stationMap.values()) {
            if (station.getVehicle().equals(type) && haversineDistance(latitude, longitude, station.getLatitude(), station.getLongitude()) <= distance) {
                System.out.println(station);
            }
        }
    }

    public static double haversineDistance(double lat1, double lon1, double lat2, double lon2) {
        int earthRadius = 6371; // Raio da Terra em km
        double latDiff = Math.toRadians(lat2 - lat1);
        double lonDiff = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDiff / 2) * Math.sin(latDiff / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDiff / 2) * Math.sin(lonDiff / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = earthRadius * c;
        return distance;
    }
}
