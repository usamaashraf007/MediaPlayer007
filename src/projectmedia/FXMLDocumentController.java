package projectmedia;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.DoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class FXMLDocumentController implements Initializable {
    String currentdir="home.user";
    public String songname;

    @FXML
    private BorderPane playborderpane;
    // public String songname;
    JFXButton dasboard;
    JFXButton playlist;

    @FXML
    private ListView<String> listveiw;
    @FXML
    private File file;

    @FXML
    private MediaView mv;
    @FXML
    private ObservableList<String> list=FXCollections.observableArrayList();
    @FXML
    private List<File> filelist;
    @FXML
    private JFXButton fav;
    @FXML
    String selecteditem;
    @FXML
    private JFXButton safari;
    @FXML
    private JFXButton artist;
    @FXML
    private JFXButton mp3;
    @FXML
    private JFXButton mp4;
    @FXML
    private JFXButton setting;
    Media media;

    @FXML
    private JFXButton create;
    private ListView listview;
    private final Label filelabel=new Label();
    MediaPlayer mediaPlayer;

    @FXML
    private BorderPane listviewborder;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        listviewborder.setVisible(false);
        mv.setVisible(false);
    }
    @FXML
    private void createmusic(ActionEvent event) {
        FileChooser fc=new FileChooser();
        File file=fc.showOpenDialog(null);
        if(file!=null){
            listview.getItems().add(file.getName());
        }else{
            System.out.println("File is not Valid");
        }
    }
    @FXML
    private void playitemplaylist(){
        //listviewborder.setVisible(false);
        mv.setVisible(true);
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
    private void dashboard(ActionEvent event) {

    }

    @FXML
    private void playlist(ActionEvent event) throws IOException {
        listviewborder.setVisible(true);
        playborderpane.setVisible(false);
        //  listviewborder.autosize();
////
////
//    ((Node)event.getSource()).getScene().getWindow().hide();
//        Parent PlaylistParent=FXMLLoader.load(getClass().getResource("Playlist.fxml"));
//        Scene  FXML2Scene=new Scene(PlaylistParent);
//        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
//        window.setScene(FXML2Scene);
//        window.show();
//

    }

    @FXML
    private void favourite(ActionEvent event) {
    }

    @FXML
    private void safari(ActionEvent event) {
        WebView mywebview=new WebView();
        WebEngine engine=mywebview.getEngine();
        engine.load("https://www.google.com");
    }

    @FXML
    private void artist(ActionEvent event) {
        System.out.println("Comming Soon");
    }@FXML
    private void gotonext(ActionEvent event) throws IOException {
//        MediaPlayer.Status status=MediaPlayer.Status.PLAYING;
//
//if(status!=MediaPlayer.Status.PLAYING){
        songname="mp4";
//}
        System.out.println(songname);

        ((Node)event.getSource()).getScene().getWindow().hide();
        Parent PlayerParent=FXMLLoader.load(getClass().getResource("Player.fxml"));
        Scene  FXML2Scene=new Scene(PlayerParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(FXML2Scene);
        window.show();
    }

    @FXML
    private void audio(ActionEvent event) throws Exception {
//         MediaPlayer.Status status=MediaPlayer.;
//
//if(status!=MediaPlayer.Status.PLAYING){
        songname="mp3";


        System.out.println(songname);
//}
        ((Node)event.getSource()).getScene().getWindow().hide();
        Parent PlayerParent=FXMLLoader.load(getClass().getResource("Player.fxml"));
        Scene  FXML2Scene=new Scene(PlayerParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(FXML2Scene);

        window.show();

//        songname="*.mp3";
    }

    @FXML
    private void video(ActionEvent event) throws Exception {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Parent PlayerParent=FXMLLoader.load(getClass().getResource("Player.fxml"));
        Scene  FXML2Scene=new Scene(PlayerParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(FXML2Scene);
        window.show();
//     songname="*.mp4";



    }
    @FXML
    private void addplaylist(){

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
    @FXML
    private void setting(ActionEvent event) {
        System.out.println("Comming Soon");
    }

    @FXML
    private void close_app(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void minimize_ap(MouseEvent event) {
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void maximize_app(MouseEvent event) {
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        if(stage.isMaximized()){

            stage.resizableProperty();
            stage.setMaximized(false);
        }else
        {
            stage.resizableProperty();
            stage.isResizable();
            stage.setMaximized(true);
        }
    }
}
