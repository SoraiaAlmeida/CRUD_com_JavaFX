package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import control.RodoviarioControl;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;

public class Rodoviario_Tela extends Application {
    private TextField txtNome = new TextField();
    private TextField txtCPF = new TextField();
    private TextField txtorigem = new TextField();
    private TextField txtdestino = new TextField();
    private DatePicker dpdataViagem = new DatePicker();
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private TextField txthorario = new TextField();
      

    private RodoviarioControl control = new RodoviarioControl();

    public void reservar() {
        control.reservar();
    }

    public void alterar() {
        control.alterar();
    }

    public void excluir() {
        control.excluir();
    }

    public void pesquisar() {
        control.pesquisar();
    }

    public void ligacoes() {
        Bindings.bindBidirectional(txtNome.textProperty(), control.nomeProperty());
        Bindings.bindBidirectional(txtCPF.textProperty(), control.cpfProperty());
        Bindings.bindBidirectional(txtorigem.textProperty(), control.origemProperty());
        Bindings.bindBidirectional(txtdestino.textProperty(), control.destinoProperty());
        Bindings.bindBidirectional(txthorario.textProperty(), control.horarioProperty());
        
        StringConverter<LocalDate> converter = new LocalDateStringConverter(dtf, dtf);
        dpdataViagem.setConverter(converter);
        dpdataViagem.valueProperty().bindBidirectional(control.dataViagemProperty());
     
    }

    @Override
    public void start(Stage stage) throws Exception {
    	
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("Nome: "), 0, 0);
        grid.add(txtNome, 1, 0);
        
        grid.add(new Label("CPF: "), 0, 1);
        grid.add(txtCPF, 1, 1);
        
        grid.add(new Label("Origem: "), 0, 2);
        grid.add(txtorigem, 1, 2);
        
        grid.add(new Label("Destino: "), 0, 3);
        grid.add(txtdestino, 1, 3);
        
        grid.add(new Label("Data da Viagem: "), 0, 4);
        grid.add(dpdataViagem, 1, 4);
        
        grid.add(new Label("Horario: "), 0, 5);
        grid.add(txthorario, 1, 5);
       

        Button btnReservar = new Button("Reservar");
        btnReservar.setOnAction(e -> reservar());
        
        Button btnAlterar = new Button("Alterar");
        btnAlterar.setOnAction(e -> alterar());
        
        Button btnExcluir = new Button("Excluir");
        btnExcluir.setOnAction(e -> excluir());
        
        Button btnPesquisar = new Button("Pesquisar");
        btnPesquisar.setOnAction(e -> pesquisar());

        ligacoes();

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(btnReservar, btnAlterar, btnExcluir, btnPesquisar);
        buttonBox.setSpacing(50); // Espaçamento entre os botões


        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(10));
        
        Label titleLabel = new Label("Compra de Passagem Rodoviária");
        titleLabel.setFont(Font.font("Arial", 17));
        titleLabel.setStyle("-fx-font-weight: bold;");
        root.add(titleLabel, 0, 0, 2, 1); 
        root.add(grid, 0, 1, 2, 1);
        root.add(buttonBox, 0, 2, 2, 1);


        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        RowConstraints row3 = new RowConstraints();
        row3.setVgrow(Priority.ALWAYS);
        root.getRowConstraints().addAll(row1, row2, row3);

        
        Scene scn = new Scene(root, 600, 410);
        stage.setScene(scn);
        stage.setTitle("Compra de passagem");
        stage.show();
    }
    

    public static void main(String[] args) {
    	Application.launch(args);
    }
}
