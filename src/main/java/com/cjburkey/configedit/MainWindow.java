package com.cjburkey.configedit;

import java.nio.file.Path;
import java.util.regex.Pattern;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class MainWindow extends Stage {
	
	private TilePane root;
	private Scene scene;
	private VBox fileListPlaceholder;
	private Label fileListPlaceholderText;
	private Button fileListPlaceholderButton;
	private ListView<Path> fileList;
	private Label configTablePlaceholderText;
	private TableColumn<Config<?>, Config<?>> configNameColumn;
	private TableColumn<Config<?>, Config<?>> configValueColumn;
	private TableView<Config<?>> configTable;
	
	public MainWindow() {
		super();
		init();
	}
	
	@SuppressWarnings("unchecked")
	private void init() {
		root = new TilePane();
		scene = new Scene(root);
		scene.getStylesheets().add("bootstrap3.css");
		fileListPlaceholder = new VBox();
		fileListPlaceholderText = new Label("No modpack directory selected.");
		fileListPlaceholderButton = new Button("Select Modpack Folder");
		fileList = new ListView<>();
		configTablePlaceholderText = new Label("Please select a config file.");
		configNameColumn = new TableColumn<>("Name");
		configValueColumn = new TableColumn<>("Value");
		configTable = new TableView<>();
		
		addCustomCellFactoryForFileView();
		addCustomCellFactoriesForTable();
		
		configTable.getItems().add(new Config<String>("test", "Hello! I'm a value!"));
		configTable.getItems().add(new Config<String>("test", "Hello! I'm a value!"));
		
		root.setHgap(0.0f);
		root.setPrefColumns(2);
		root.setPrefRows(1);
		
		fileListPlaceholder.setAlignment(Pos.CENTER);
		fileListPlaceholder.setSpacing(10.0d);
		fileListPlaceholder.setPadding(new Insets(10.0f));
		fileListPlaceholderText.setAlignment(Pos.CENTER);
		fileListPlaceholderText.getStyleClass().add("lg");
		fileListPlaceholderButton.setAlignment(Pos.CENTER);
		fileListPlaceholderButton.getStyleClass().addAll("primary", "lg");
		fileListPlaceholder.getChildren().addAll(fileListPlaceholderText, fileListPlaceholderButton);
		
		fileList.setMinSize(500.0d, 250.0d);
		fileList.setEditable(false);
		fileList.setPlaceholder(fileListPlaceholder);
		
		configTablePlaceholderText.getStyleClass().add("lg");
		configNameColumn.setEditable(false);
		configValueColumn.setEditable(false);
		configTable.setPlaceholder(configTablePlaceholderText);
		configTable.setEditable(false);
		configTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		configTable.getColumns().addAll(configNameColumn, configValueColumn);
		
		root.getChildren().addAll(fileList, configTable);
		
		setScene(scene);
		sizeToScene();
		centerOnScreen();
		setTitle("CJ Burkey's ConfigEdit v" + ConfigEdit.VERSION);
	}
	
	private void addCustomCellFactoryForFileView() {
		fileList.setCellFactory(new Callback<ListView<Path>, ListCell<Path>>() {
			public ListCell<Path> call(ListView<Path> arg0) {
				ListCell<Path> cell = new ListCell<Path>() {
					protected void updateItem(Path item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							return;
						}
						if (item != null) {
							setText("/" + ConfigEdit.getRelativePath(item).replaceAll(Pattern.quote("\\"), "/"));
							return;
						}
						setText(null);
					}
				};
				return cell;
			}
		});
	}
	
	// TODO: Make these work or something
	private void addCustomCellFactoriesForTable() {
		configNameColumn.setCellFactory(new Callback<TableColumn<Config<?>, Config<?>>, TableCell<Config<?>, Config<?>>>() {	// Isn't this ugly?
			public TableCell<Config<?>, Config<?>> call(TableColumn<Config<?>, Config<?>> arg0) {
				TableCell<Config<?>, Config<?>> cell = new TableCell<Config<?>, Config<?>>() {
					protected void updateItem(Config<?> item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							return;
						}
						if (item != null) {
							setText(item.getName());
							return;
						}
						setText(null);
					}
				};
				return cell;
			}
		});
		
		configValueColumn.setCellFactory(new Callback<TableColumn<Config<?>, Config<?>>, TableCell<Config<?>, Config<?>>>() {
			public TableCell<Config<?>, Config<?>> call(TableColumn<Config<?>, Config<?>> arg0) {
				TableCell<Config<?>, Config<?>> cell = new TableCell<Config<?>, Config<?>>() {
					protected void updateItem(Config<?> item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							return;
						}
						if (item != null) {
							setText(item.getValue().toString());
							return;
						}
						setText(null);
					}
				};
				return cell;
			}
		});
	}
	
	public void showMain() {
		show();
		root.requestFocus();
		setMinWidth(getWidth());
		setMinHeight(getHeight());
	}
	
	public TilePane getRoot() {
		return root;
	}
	
	public Scene getMainScene() {
		return scene;
	}
	
	public VBox getFileListPlaceholder() {
		return fileListPlaceholder;
	}
	
	public Label getFileListPlaceholderText() {
		return fileListPlaceholderText;
	}
	
	public Button getFileListPlaceholderButton() {
		return fileListPlaceholderButton;
	}
	
	public ListView<Path> getFileList() {
		return fileList;
	}
	
}