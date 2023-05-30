import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;

import java.util.HashMap;
import java.util.Map;

/**
 * Representa um mapa de rede de transporte.
 */
public class NetworkMap {
    private EdgeWeightedDigraph transportNetwork; // Rede de transporte ponderada por arestas
    private Map<Integer, Station> stationMap; // Mapeamento de ID de estação para objeto Station

    /**
     * Construtor da classe NetworkMap.
     * Inicializa a rede de transporte e o mapeamento de estações.
     */
    public NetworkMap() {
        this.transportNetwork = new EdgeWeightedDigraph(1000); // Ajuste o número de vértices de acordo com a quantidade de estações
        this.stationMap = new HashMap<>();
    }

    /**
     * Adiciona uma estação ao mapa de rede de transporte.
     *
     * @param station Estação a ser adicionada
     */
    public void addStation(Station station) {
        stationMap.put(station.getStationId(), station);
    }

    /**
     * Adiciona uma conexão entre duas estações ao mapa de rede de transporte.
     *
     * @param connection Conexão a ser adicionada
     */
    public void addConnection(Connection connection) {
        int v = connection.getStation1().getStationId();
        int w = connection.getStation2().getStationId();
        Schedule schedule = connection.getSchedule();

        if (schedule != null) {
            double weight = schedule.getDuration();
            DirectedEdge edge = new DirectedEdge(v, w, weight);
            transportNetwork.addEdge(edge);
        } else {
            System.err.println("Erro: conexão inválida - Schedule nulo.");
        }
    }

    /**
     * Obtém a rede de transporte.
     *
     * @return Rede de transporte
     */
    public EdgeWeightedDigraph getTransportNetwork() {
        return transportNetwork;
    }

    /**
     * Obtém o número de estações no mapa de rede de transporte.
     *
     * @return Número de estações
     */
    public int getNumberOfStations() {
        return stationMap.size();
    }

    /**
     * Lista as estações por tipo de veículo.
     *
     * @param type1 Tipo de veículo 1
     * @param type2 Tipo de veículo 2
     */
    public void listStationsByType(String type1, String type2) {
        for (Station station : stationMap.values()) {
            if (station.getVehicle().equals(type1) || station.getVehicle().equals(type2)) {
                System.out.println(station);
            }
        }
    }

    /**
     * Lista as estações próximas a uma determinada coordenada geográfica.
     *
     * @param latitude  Latitude da coordenada
     * @param longitude Longitude da coordenada
     * @param distance  Distância máxima em que as estações devem estar da coordenada
     * @param type      Tipo de veículo das estações
     */
    public void listStationsNearCoordinate(double latitude, double longitude, double distance, String type) {
        for (Station station : stationMap.values()) {
            if (station.getVehicle().equals(type) && haversineDistance(latitude, longitude, station.getLatitude(), station.getLongitude()) <= distance) {
                System.out.println(station);
            }
        }
    }

    /**
     * Calcula a distância haversine entre duas coordenadas geográficas.
     *
     * @param lat1 Latitude da primeira coordenada
     * @param lon1 Longitude da primeira coordenada
     * @param lat2 Latitude da segunda coordenada
     * @param lon2 Longitude da segunda coordenada
     * @return Distância haversine entre as coordenadas
     */
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

    /**
     * Lista todos os vértices da rede de transporte.
     */
    public void listAllVertices() {
        int totalVertices = transportNetwork.V();
        System.out.println("Vértices:");
        for (int v = 0; v < totalVertices; v++) {
            System.out.println(v);
        }
    }

    /**
     * Lista todas as arestas da rede de transporte.
     */
    public void listAllEdges() {
        int totalVertices = transportNetwork.V();
        System.out.println("Arestas:");
        for (int v = 0; v < totalVertices; v++) {
            for (DirectedEdge edge : transportNetwork.adj(v)) {
                System.out.println(edge);
            }
        }
    }
}
