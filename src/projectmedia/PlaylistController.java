/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmedia;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author MRCOM
 */
public class PlaylistController implements Initializable {

    /**
     * Initializes the controller class.
     */
    MediaPlayer mediaPlayer;
    @FXML
    private VBox vbox;
    @FXML
    private BorderPane newscreen;
    @FXML
    String selecteditem;
    @FXML
    private Button add;
    @FXML
    private Button play;
    @FXML
    private Button stop;
    @FXML
    private ListView<String> listveiw;
    @FXML
    private File file;
    @FXML
    private ObservableList<String> list=FXCollections.observableArrayList();
    @FXML
    private List<File> filelist;
    @FXML
    private MediaView mv;
    @FXML
    private void load(){


    }
    @FXML
    private void gotoplaylist(){
        mediaPlayer.stop();
        vbox.setVisible(true);
        newscreen.setVisible(false);

    }
    @FXML
    private void play(){
        mediaPlayer.play();

    } @FXML
    private void stop(){
        mediaPlayer.stop();

    }
    @FXML
    private void selected(){
        vbox.setVisible(false);
        newscreen.setVisible(true);
        selecteditem=listveiw.getSelectionModel().getSelectedItem();
        Media media =new Media(new File(selecteditem).toURI().toString());
        mediaPlayer=new MediaPlayer(media);
        DoubleProperty width=mv.fitWidthProperty();
        DoubleProperty height=mv.fitHeightProperty();
        width.bind(javafx.beans.binding.Bindings.selectDouble(mv.sceneProperty(),"width"));
        height.bind(javafx.beans.binding.Bindings.selectDouble(mv.sceneProperty(), "height"));
        mv.setMediaPlayer(mediaPlayer);
        mediaPlayer.setAutoPlay(true);
    }
    @FXML
    private void handleButtonAction(ActionEvent event){

        FileChooser fc=new FileChooser();
        if(filelist!=null){
            if(!filelist.isEmpty()){
                File existdirectory=filelist.get(0).getParentFile();
                fc.setInitialDirectory(existdirectory);
            }
        }
        FileChooser.ExtensionFilter filter =new FileChooser.ExtensionFilter("select a file (*.mp4) ","*.mp4");
        fc.getExtensionFilters().add(filter);
        filelist =fc.showOpenMultipleDialog(null);
        list.clear();
        for(int i=0;i<filelist.size();i++){
            list.add(filelist.get(i).getAbsolutePath());
        }
        listveiw.setItems(list);
        System.out.println(listveiw.getSelectionModel());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        newscreen.setVisible(false);
    }
}
