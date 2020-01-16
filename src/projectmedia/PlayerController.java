/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmedia;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author MRCOM
 */
public class PlayerController implements Initializable {
    private MediaPlayer mediaplayer;
    @FXML
    private MediaView mediaview;

    private String  filepath;
    @FXML
    private Slider seekslider;
    @FXML
    private Slider slider;
    @FXML
    private Button playbutton;
    @FXML
    private Button audiosong;
    @FXML
    private Button videobutton;

    @FXML
    private void audiosongaction(ActionEvent event){


        FileChooser filechooser=new FileChooser();
        // ProjectMedia project=new ProjectMedia();
        // System.out.println(fx.songname);
//       if(fx.songname.equals("mp4"))
        FileChooser.ExtensionFilter filter =new FileChooser.ExtensionFilter("select a file (*.mp3) ","*.mp3");

        filechooser.getExtensionFilters().add(filter);

        File file =filechooser.showOpenDialog(null);
        filepath=file.toURI().toString();
        if(mediaplayer!=null){
            mediaplayer.dispose();
        }
        if(filepath!=null){
            Media media=new Media(filepath);
            mediaplayer =new MediaPlayer(media);
            mediaview.setMediaPlayer(mediaplayer);
            DoubleProperty width=mediaview.fitWidthProperty();
            DoubleProperty height=mediaview.fitHeightProperty();
            width.bind(javafx.beans.binding.Bindings.selectDouble(mediaview.sceneProperty(),"width"));
            height.bind(javafx.beans.binding.Bindings.selectDouble(mediaview.sceneProperty(), "height"));
            slider.setValue(mediaplayer.getVolume()*100);

            slider.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {

                    mediaplayer.setVolume(slider.getValue()/100);
                }
            });



            mediaplayer.setOnReady(()->{
                seekslider.setMin(0);
                seekslider.setMax(mediaplayer.getMedia().getDuration().toMinutes());
                seekslider.setValue(0);
                seekslider.setMin(mediaplayer.getCurrentTime().toMinutes());
            });






            mediaplayer.currentTimeProperty().addListener(new  ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                    Duration d=mediaplayer.getCurrentTime();
                    seekslider.setValue(d.toMinutes());
                    // seekslider.setMin(d.toMinutes());
                }
            });

//                    seekslider.setOnMouseClicked(new EventHandler<Event>() {
//                      @Override
//                      public void handle(Event event) {
//                          mediaplayer.seek(Duration.seconds(seekslider.getValue()));
//
//                           }
//                  });
//
//              mediaplayer.play();

        }


        seekslider.valueProperty().addListener(new ChangeListener<Number>(){


                                                   @Override
                                                   public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                                                       if(seekslider.isPressed()){
                                                           double val =seekslider.getValue();

                                                           mediaplayer.seek(new Duration(val*60*1000));

                                                       }


                                                   }
                                               }
        );
//                    seekslider.valueProperty().addListener(new ChangeListener<Number>(){
//
//
//                      @Override
//                      public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//double val =seekslider.getValue();
//mediaplayer.seek(new Duration(val*1000));
//
//
//
//                      }
//                    }
//                    );


    }
    @FXML
    private void handleButtonAction(ActionEvent event){

        FXMLDocumentController fx=new FXMLDocumentController();

        FileChooser filechooser=new FileChooser();
        // ProjectMedia project=new ProjectMedia();
        // System.out.println(fx.songname);
//       if(fx.songname.equals("mp4"))
        FileChooser.ExtensionFilter filter =new FileChooser.ExtensionFilter("select a file (*.mp4) ","*.mp4");

        filechooser.getExtensionFilters().add(filter);

        File file =filechooser.showOpenDialog(null);
        filepath=file.toURI().toString();
        if(mediaplayer!=null){
            mediaplayer.dispose();
        }
        if(filepath!=null){
            Media media=new Media(filepath);
            mediaplayer =new MediaPlayer(media);
            mediaview.setMediaPlayer(mediaplayer);
            DoubleProperty width=mediaview.fitWidthProperty();
            DoubleProperty height=mediaview.fitHeightProperty();
            width.bind(javafx.beans.binding.Bindings.selectDouble(mediaview.sceneProperty(),"width"));
            height.bind(javafx.beans.binding.Bindings.selectDouble(mediaview.sceneProperty(), "height"));
            slider.setValue(mediaplayer.getVolume()*100);

            slider.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {

                    mediaplayer.setVolume(slider.getValue()/100);
                }
            });



            mediaplayer.setOnReady(()->{
                seekslider.setMin(0);
                seekslider.setMax(mediaplayer.getMedia().getDuration().toMinutes());
                seekslider.setValue(0);
                seekslider.setMin(mediaplayer.getCurrentTime().toMinutes());
            });






            mediaplayer.currentTimeProperty().addListener(new  ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                    Duration d=mediaplayer.getCurrentTime();
                    seekslider.setValue(d.toMinutes());
                    // seekslider.setMin(d.toMinutes());
                }
            });

//                    seekslider.setOnMouseClicked(new EventHandler<Event>() {
//                      @Override
//                      public void handle(Event event) {
//                          mediaplayer.seek(Duration.seconds(seekslider.getValue()));
//
//                           }
//                  });
//
//              mediaplayer.play();

        }


        seekslider.valueProperty().addListener(new ChangeListener<Number>(){


                                                   @Override
                                                   public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                                                       if(seekslider.isPressed()){
                                                           double val =seekslider.getValue();

                                                           mediaplayer.seek(new Duration(val*60*1000));

                                                       }


                                                   }
                                               }
        );
//                    seekslider.valueProperty().addListener(new ChangeListener<Number>(){
//
//
//                      @Override
//                      public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//double val =seekslider.getValue();
//mediaplayer.seek(new Duration(val*1000));
//
//
//
//                      }
//                    }
//                    );

    }
    @FXML
    private void pauseAction(ActionEvent event){

        mediaplayer.pause();
    }
    @FXML
    private void playAction(ActionEvent event){
//        MediaPlayer.Status status=MediaPlayer.Status.PLAYING;
//
//if(!mediaplayer.OnPlaying()){
//     mediaplayer.pause();
//      mediaplayer.setRate(1);
//    playbutton.setText("play");
//
//        }else {
        mediaplayer.play();

//    playbutton.setText("pause");
        mediaplayer.setRate(1);
//        }
    } @FXML
    private void stopAction(ActionEvent event){

        mediaplayer.stop();


    } @FXML
    private void fastmoveAction(ActionEvent event){
        mediaplayer.setRate(1.5);

    } @FXML
    private void ExitAction(ActionEvent event) throws IOException{
        MediaPlayer.Status status=MediaPlayer.Status.PLAYING;

        if(status==MediaPlayer.Status.PLAYING){
            mediaplayer.stop();
        }

        ((Node)event.getSource()).getScene().getWindow().hide();
        Parent ProjectMediaParent=FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene  FXML2Scene=new Scene(ProjectMediaParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(FXML2Scene);
        window.show();

    } @FXML
    private void slowAction(ActionEvent event){
        mediaplayer.setRate(.75);

    } @FXML
    private void lowestAction(ActionEvent event){

        mediaplayer.setRate(5);
    }
    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

//        FXMLDocumentController fx=new FXMLDocumentController();
//        if(fx.songname.equals("mp4")){
//            audiosong.setVisible(false);
//        }else
//        {
//            videobutton.setVisible(false);
//        }

    }

}
