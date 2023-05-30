import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import java.util.Map;
import java.util.HashMap;
import edu.princeton.cs.algs4.DirectedEdge;

public class TransportNetworkApp extends Application {
    private NetworkMap networkMap;
    private ObservableList<Station> stations = FXCollections.observableArrayList();

    public TransportNetworkApp() {
        this.networkMap = new NetworkMap();
    }
/*
    public void drawGraph(Pane pane) {
        double paneWidth = pane.getWidth();
        double paneHeight = pane.getHeight();

        // Desenhar vértices
        for (Station station : networkMap.getStationMap().values()) {
            Circle circle = new Circle();
            circle.setCenterX(station.getLatitude() * paneWidth);
            circle.setCenterY(station.getLongitude() * paneHeight);
            circle.setRadius(5);

            // Definir cores diferentes para tipos de estação diferentes
            if (station.getVehicle().equals("Type1")) {
                circle.setFill(Color.RED);
            } else if (station.getVehicle().equals("Type2")) {
                circle.setFill(Color.BLUE);
            } else {
                circle.setFill(Color.BLACK);
            }

            pane.getChildren().add(circle);
        }

        // Desenhar arestas
        for (int v = 0; v < networkMap.getTransportNetwork().V(); v++) {
            for (DirectedEdge edge : networkMap.getTransportNetwork().adj(v)) {
                int fromStationId = edge.from();
                int toStationId = edge.to();

                Station fromStation = networkMap.getStationMap().get(fromStationId);
                Station toStation = networkMap.getStationMap().get(toStationId);

                Line line = new Line();
                line.setStartX(fromStation.getLatitude() * paneWidth);
                line.setStartY(fromStation.getLongitude() * paneHeight);
                line.setEndX(toStation.getLatitude() * paneWidth);
                line.setEndY(toStation.getLongitude() * paneHeight);

                // Definir cores diferentes para tipos de conexão diferentes
                if (fromStation.getVehicle().equals("Type1") && toStation.getVehicle().equals("Type1")) {
                    line.setStroke(Color.RED);
                } else if (fromStation.getVehicle().equals("Type2") && toStation.getVehicle().equals("Type2")) {
                    line.setStroke(Color.BLUE);
                } else {
                    line.setStroke(Color.GRAY);
                }

                pane.getChildren().add(line);
            }
        }
    }
*/
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Transport Network");

        // Tabela para mostrar as estações
        TableView<Station> table = new TableView<>();
        TableColumn<Station, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("stationId"));
        TableColumn<Station, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("stationName"));
        TableColumn<Station, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("vehicle"));
        table.getColumns().addAll(idColumn, nameColumn, typeColumn);
        table.setItems(stations);

        TextField idField = new TextField();
        idField.setPromptText("ID da estação");
        TextField nameField = new TextField();
        nameField.setPromptText("Nome da estação");
        TextField typeField = new TextField();
        typeField.setPromptText("Tipo da estação");
        TextField latitudeField = new TextField();
        latitudeField.setPromptText("Latitude da estação");
        TextField longitudeField = new TextField();
        longitudeField.setPromptText("Longitude da estação");

        Button addButton = new Button("Adicionar estação");
        addButton.setOnAction(e -> {
            if (!idField.getText().isEmpty() && !latitudeField.getText().isEmpty() && !longitudeField.getText().isEmpty()) {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String type = typeField.getText();
                double latitude = Double.parseDouble(latitudeField.getText());
                double longitude = Double.parseDouble(longitudeField.getText());

                Station newStation = new Station(id, name, type, latitude, longitude);
                networkMap.addStation(newStation);
                stations.add(newStation);

                idField.clear();
                nameField.clear();
                typeField.clear();
                latitudeField.clear();
                longitudeField.clear();
            } else {
                System.out.println("Por favor, preencha todos os campos.");
            }
        });

        VBox layout = new VBox(10, idField, nameField, typeField, latitudeField, longitudeField, addButton, table);
        primaryStage.setScene(new Scene(layout, 300, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
