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

public class TransportNetworkApp extends Application {
    private NetworkMap networkMap;
    private ObservableList<Station> stations = FXCollections.observableArrayList();

    public TransportNetworkApp() {
        this.networkMap = new NetworkMap();
        this.stations = FXCollections.observableArrayList();
    }

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
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("stationType"));
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



        VBox layout = new VBox(10, idField, nameField, typeField, latitudeField, longitudeField, addButton);
        primaryStage.setScene(new Scene(layout, 300, 250));
        primaryStage.show();
    }


    /*
        TabPane tabPane = new TabPane();

        // Tab for Stations
        Tab stationTab = new Tab("Station");
        VBox stationLayout = new VBox();

        TableView<Station> stationTable = new TableView<>();
        ObservableList<Station> stations = FXCollections.observableArrayList();
        // Here you should fill the station list with the existing data
        // stations.addAll(...);

        TableColumn<Station, Integer> stationIdCol = new TableColumn<>("ID");
        stationIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Station, String> stationNameCol = new TableColumn<>("Name");
        stationNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        stationTable.getColumns().add(stationIdCol);
        stationTable.getColumns().add(stationNameCol);
        stationTable.setItems(stations);

        stationLayout.getChildren().add(stationTable);
        stationTab.setContent(stationLayout);

        // Tab for Users
        Tab userTab = new Tab("User");
        VBox userLayout = new VBox();

        TableView<User> userTable = new TableView<>();
        ObservableList<User> users = FXCollections.observableArrayList();
        // Here you should fill the user list with the existing data
        // users.addAll(...);

        TableColumn<User, Integer> userIdCol = new TableColumn<>("ID");
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<User, String> userNameCol = new TableColumn<>("Name");
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<User, String> userEmailCol = new TableColumn<>("Email");
        userEmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        userTable.getColumns().add(userIdCol);
        userTable.getColumns().add(userNameCol);
        userTable.getColumns().add(userEmailCol);
        userTable.setItems(users);

        userLayout.getChildren().add(userTable);
        userTab.setContent(userLayout);

        tabPane.getTabs().add(stationTab);
        tabPane.getTabs().add(userTab);

        Scene scene = new Scene(tabPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }*/

    public static void main(String[] args) {
        launch(args);
    }
}
