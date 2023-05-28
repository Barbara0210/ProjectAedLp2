public class TestNetworkMap {

    public static void main(String[] args) {
        DataBase database = new DataBase();
        database.populateDatabase();
        database.testNetworkMap();
        // Criação de uma nova rede
        NetworkMap networkMap = new NetworkMap();

        // Adicionando algumas estações
        networkMap.addStation(new Station(1, "Estação 1", "Aeroporto", 41.14961, -8.61099)); // Aeroporto do Porto
        networkMap.addStation(new Station(2, "Estação 2", "Ferroviária", 38.70775, -9.13659)); // Estação de Santa Apolónia em Lisboa
        networkMap.addStation(new Station(3, "Estação 3", "Ferroviária", 40.6405, -8.6538)); // Estação de Aveiro
        networkMap.addStation(new Station(4, "Estação 4", "Aeroporttttto", 38.7742, -9.1342)); // Aeroporto de Lisboa

        // Teste 1: Listar estações que são aeroportos ou estações ferroviárias
        System.out.println("Teste 1: Listar estações que são aeroportos ou estações ferroviárias:");
        networkMap.listStationsByType("Aeroporto", "Ferroviária");
        System.out.println("\n");

        // Teste 2: Listar estações ferroviárias que ficam a menos de 100km de uma coordenada geográfica (por exemplo, do Porto)
        System.out.println("Teste 2: Listar estações ferroviárias que ficam a menos de 100km de uma coordenada geográfica (por exemplo, do Porto):");
        networkMap.listStationsNearCoordinate(41.14961, -8.61099, 100, "Ferroviária");
        database.generateGlobalReport();

    }
}
