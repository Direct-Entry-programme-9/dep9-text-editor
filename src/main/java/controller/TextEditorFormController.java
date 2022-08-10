package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.web.HTMLEditor;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.util.Optional;

public class TextEditorFormController {
    public MenuItem mnuNew;
    public MenuItem mnuOpen;
    public MenuItem mnuSave;
    public MenuItem mnuAbout;
    public HTMLEditor txtEditor;
    public MenuItem mnuClose;
    public MenuItem mnuSelectAll;
    public MenuItem mnuPrint;

    public void initialize(){
        mnuAbout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Parent container=FXMLLoader.load(this.getClass().getResource("/view/AboutForm.fxml"));
                    Scene scene = new Scene(container);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("About");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        mnuNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                txtEditor.setHtmlText("");
                txtEditor.requestFocus();
            }
        });

        mnuOpen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
                    fileChooser.setTitle("Open the text doc");
                    fileChooser.getExtensionFilters()
                            .add(new FileChooser.ExtensionFilter("Text document (*.txt)","*.txt",
                                    "Text editor document (*.dep9)", "*.dep9"));
                    File file = fileChooser.showOpenDialog(txtEditor.getScene().getWindow());

                    if (file == null) return;

                    FileInputStream fis = new FileInputStream(file);
                    for (int i = 0; i < file.length(); i++) {
                        txtEditor.setHtmlText(txtEditor.getHtmlText()+""+ (char) fis.read());
                    }

                    fis.close();


                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        mnuSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    DirectoryChooser directoryChooser = new DirectoryChooser();
                    directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
                    directoryChooser.setTitle("Select a location to save your file");
                    File filePath = directoryChooser.showDialog(txtEditor.getScene().getWindow());
                    if (filePath == null) return;
                    File destFile = new File(filePath, "Untitled.dep9");

                    if (! destFile.exists()) {
                        destFile.createNewFile();
                    } else {
                        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION,
                                "File already exists. Do you want to overwrite?",
                                ButtonType.YES, ButtonType.NO).showAndWait();
                        if (result.get() == ButtonType.NO) {
                            return;
                        }
                    }
                    FileOutputStream fos = new FileOutputStream(destFile);
                    byte[] bytes = txtEditor.getHtmlText().getBytes();
                    for (byte aByte : bytes) {
                        fos.write(aByte);
                    }
                    new Alert(Alert.AlertType.INFORMATION, "File saved successfully ✅").showAndWait();
                    txtEditor.setHtmlText("");
                    fos.close();


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }
        });

        mnuPrint.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (Printer.getDefaultPrinter() == null){
                    new Alert(Alert.AlertType.ERROR, "Couldn't find the printer, Please check and try again.").showAndWait();
                    return;
                }

                PrinterJob printerJob = PrinterJob.createPrinterJob();
                if (printerJob != null){
                    printerJob.showPrintDialog(txtEditor.getScene().getWindow());
                    boolean isSuccess = printerJob.printPage(txtEditor);
                    if (isSuccess){
                        printerJob.endJob();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Fail to print, try again").showAndWait();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Fail to initialize new printer job, Please try again").showAndWait();

                }
            }
        });

        mnuClose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });

        mnuSelectAll.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            }
        });
    }
}
