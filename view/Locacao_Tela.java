package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import control.LocacaoControl;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;

public class Locacao_Tela extends Application {
    private TextField txtNome = new TextField();
    private TextField txtCPF = new TextField();
    private TextField txtLocalRetirada = new TextField();
    private DatePicker dpDataRetirada = new DatePicker();
    private TextField txtLocalDevolucao = new TextField();
    private DatePicker dpDataDevolucao = new DatePicker();
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private LocacaoControl control = new LocacaoControl();

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
        Bindings.bindBidirectional(txtLocalRetirada.textProperty(), control.localRetiradaProperty());
        Bindings.bindBidirectional(txtLocalDevolucao.textProperty(), control.localDevolucaoProperty());
        
        StringConverter<LocalDate> converter = new LocalDateStringConverter(dtf, dtf);
        dpDataRetirada.setConverter(converter);
        dpDataRetirada.valueProperty().bindBidirectional(control.dataRetiradaProperty());
        dpDataDevolucao.setConverter(converter);
        dpDataDevolucao.valueProperty().bindBidirectional(control.dataDevolucaoProperty());
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
        
        grid.add(new Label("Local de Retirada: "), 0, 2);
        grid.add(txtLocalRetirada, 1, 2);
        
        grid.add(new Label("Data de Retirada: "), 0, 3);
        grid.add(dpDataRetirada, 1, 3);
        
        grid.add(new Label("Local de Devolução: "), 0, 4);
        grid.add(txtLocalDevolucao, 1, 4);
        
        grid.add(new Label("Data de Devolução: "), 0, 5);
        grid.add(dpDataDevolucao, 1, 5);
       

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
        
        Label titleLabel = new Label("Reserva de Locação de Veículos");
        titleLabel.setFont(Font.font("Arial", 18));
        titleLabel.setStyle("-fx-font-weight: bold;");
        root.add(titleLabel, 0, 0, 2, 1); 
        root.add(grid, 0, 1, 2, 1);
        root.add(buttonBox, 0, 2, 2, 1);

        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.ALWAYS);
        root.getColumnConstraints().addAll(col1, col2);

        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        RowConstraints row3 = new RowConstraints();
        row3.setVgrow(Priority.ALWAYS);
        root.getRowConstraints().addAll(row1, row2, row3);

        
        Scene scn = new Scene(root, 600, 410);
        stage.setScene(scn);
        stage.setTitle("Locação de Veículos");
        stage.show();
    }
    

    public static void main(String[] args) {
    	Application.launch(args);
    }
}
