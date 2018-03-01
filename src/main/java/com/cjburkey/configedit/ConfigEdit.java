package com.cjburkey.configedit;

import java.io.File;
import java.nio.file.Path;
import javafx.application.Application;
import javafx.stage.Stage;

public class ConfigEdit extends Application {
	
	public static final String VERSION = "0.0.1";
	
	private static MainWindow mainWindow;
	private static Path currentPack;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) {
		updatePack(new File("C:\\Users\\CJ Burkey\\Documents\\ATSMP").toPath());
		mainWindow = WindowHandler.createMainWindow();
		mainWindow.showMain();
	}
	
	public static void updatePack(Path path) {
		currentPack = path;
	}
	
	public static MainWindow getMainWindow() {
		return mainWindow;
	}
	
	public static Path getCurrentPack() {
		return currentPack;
	}
	
	public static String getRelativePath(Path file) {
		return getCurrentPack().relativize(file).toString();
	}
	
}