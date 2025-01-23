package com.example.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class MediaPlayerLearningHub {
    private MediaPlayer mediaPlayer;
    private MediaView mediaView;
    private Slider timeSlider;
    private Text videoDescription;
    private Label currentTimeLabel;
    private Label totalTimeLabel;
    private ComboBox<String> comboBox;
    private VBox mainLayout;
    private ScrollPane mainScrollPane;
    private VBox videoInfoBox;
    private VBox videos;
    private Label noVideosLabel; // New label for no videos message
    private Map<String, List<VideoInfo>> videoSets;

    MediaPlayerLearningHub() {
        showMediaPlayer();
    }

    public HBox showMediaPlayer() {

        String initialVideoFilePath = getClass().getResource("/videos/video1.mp4").toExternalForm(); // Use relative
                                                                                                     // path

        // Media and MediaPlayer
        Media media = new Media(initialVideoFilePath);
        mediaPlayer = new MediaPlayer(media);
        mediaView = new MediaView(mediaPlayer);
        mediaView.setFitWidth(1200);
        mediaView.setFitHeight(500);
        mediaView.setStyle("-fx-effect: dropshadow(gaussian, black, 10, 0, 0, 0);");

        // Play and Pause buttons
        Button playButton = new Button("Play");
        playButton.setPrefWidth(80);
        playButton.setStyle("-fx-background-color:white; -fx-background-radius:15; -fx-font-size:25; -fx-padding:5");
        playButton.setOnAction(e -> mediaPlayer.play());

        Button pauseButton = new Button("Pause");
        pauseButton.setPrefWidth(80);
        pauseButton.setStyle("-fx-background-color:white; -fx-background-radius:15; -fx-font-size:25; -fx-padding:5");
        pauseButton.setOnAction(e -> mediaPlayer.pause());

        // Volume slider
        Slider volumeSlider = new Slider(0, 100, 50); // Min value 0, Max value 100, Initial value 50
        volumeSlider.valueProperty()
                .addListener((obs, oldVal, newVal) -> mediaPlayer.setVolume(newVal.doubleValue() / 100));

        // Time slider and labels for current time and total time
        timeSlider = new Slider();
        timeSlider.setMaxWidth(1000);
        currentTimeLabel = new Label("00:00");
        currentTimeLabel.setFont(new Font("verdana", 25));
        totalTimeLabel = new Label();
        totalTimeLabel.setFont(new Font("verdana", 25));

        HBox timeBox = new HBox(10, currentTimeLabel, timeSlider, totalTimeLabel);
        timeBox.setAlignment(Pos.CENTER);

        mediaPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
            if (!timeSlider.isValueChanging()) {
                timeSlider.setValue(newTime.toSeconds());
            }
            currentTimeLabel.setText(formatTime(newTime));
        });

        mediaPlayer.setOnReady(() -> {
            Duration total = mediaPlayer.getMedia().getDuration();
            timeSlider.setMax(total.toSeconds());
            totalTimeLabel.setText(formatTime(total));
        });

        timeSlider.valueChangingProperty().addListener((obs, wasChanging, isChanging) -> {
            if (!isChanging) {
                mediaPlayer.seek(Duration.seconds(timeSlider.getValue()));
            }
        });

        Label vol = new Label("Volume");
        vol.setFont(new Font("verdana", 25));
        HBox controlBox = new HBox(30, playButton, pauseButton, vol, volumeSlider);
        controlBox.setAlignment(Pos.CENTER);

        // Video title
        Label videoTitle = new Label("Sample Video Title");
        videoTitle.setFont(new Font("verdana", 40));
        videoTitle.setTextFill(Color.web("Black"));
        videoTitle.setStyle("-fx-font-weight: Bold");

        // Video description
        videoDescription = new Text(
                "This is a sample description of the video. Here you can add details about the video content.");
        videoDescription.setWrappingWidth(650);
        videoDescription.setFont(new Font("verdana", 20));
        videoDescription.setFill(Color.web("white"));

        // ComboBox
        comboBox = new ComboBox<>();
        comboBox.getItems().addAll("New Farming techniques", "New Equipments", "Crops");
        comboBox.setPromptText("Select an option");
        comboBox.setStyle("-fx-background-color:lightgray; -fx-prompt-text-fill: white; -fx-font-size:25;");

        // Add listener to ComboBox
        comboBox.setOnAction(e -> loadContent(comboBox.getValue()));

        // Initialize video sets
        initializeVideoSets();

        // Label for no videos message
        noVideosLabel = new Label("No videos available");
        noVideosLabel.setFont(new Font("verdana", 20));
        noVideosLabel.setTextFill(Color.web("black"));
        noVideosLabel.setVisible(false);

        // Layout
        videoInfoBox = new VBox(15, videoTitle, videoDescription);
        videoInfoBox.setPadding(new Insets(10));

        mainLayout = new VBox(15, comboBox, mediaView, timeBox, controlBox, videoInfoBox, noVideosLabel);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPrefWidth(1200);
        mainLayout.setStyle("-fx-background-color: black");

        mainScrollPane = new ScrollPane(mainLayout);
        mainScrollPane.setFitToWidth(true);
        mainScrollPane.setStyle("-fx-background: black");

        ScrollPane videoList = new ScrollPane();
        videoList.setPrefSize(800, 800);
        videoList.setStyle("-fx-background: black; -fx-padding:20");

        videos = new VBox(20);
        videoList.setContent(videos);

        HBox hBox = new HBox(mainScrollPane, videoList);
        //hBox.setPadding(new Insets(20));

        // Add click-to-play functionality to mediaView
        mediaView.setOnMouseClicked(e -> {
            if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                mediaPlayer.pause();
            } else {
                mediaPlayer.play();
            }
        });

        return hBox;

    }

    private void loadContent(String option) {
        List<VideoInfo> selectedVideos = videoSets.get(option);
        videos.getChildren().clear();
        if (selectedVideos != null && !selectedVideos.isEmpty()) {
            for (VideoInfo videoInfo : selectedVideos) {
                System.out.println(videoInfo.videoPath);
                videos.getChildren().add(createVideoLink(videoInfo.title, videoInfo.videoPath, videoInfo.thumbnailPath,
                        videoInfo.description));
            }
            noVideosLabel.setVisible(false);
        } else {
            noVideosLabel.setVisible(true);
        }
    }

    private HBox createVideoLink(String videoTitle, String videoPath, String thumbnailPath, String description) {
        ImageView thumbnailView = new ImageView(new Image(getClass().getResourceAsStream(thumbnailPath)));
        thumbnailView.setFitWidth(300);
        thumbnailView.setFitHeight(300);
        thumbnailView.setPreserveRatio(true);

        StackPane thumbnailContainer = new StackPane(thumbnailView);
        thumbnailContainer.setStyle("-fx-background-radius: 25; -fx-border-radius: 25;");

        Rectangle clip = new Rectangle(thumbnailView.getFitWidth(), thumbnailView.getFitHeight());
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        thumbnailView.setClip(clip);

        Hyperlink videoLink = new Hyperlink(videoTitle);
        videoLink.setStyle("-fx-text-fill:white");
        videoLink.setMaxWidth(330);
        videoLink.setOnAction(e -> {
            mediaPlayer.stop(); // Stop the current media player
            mediaPlayer.dispose(); // Dispose of the current media player

            Media newMedia = new Media(getClass().getResource(videoPath).toExternalForm());
            mediaPlayer = new MediaPlayer(newMedia);
            mediaView.setMediaPlayer(mediaPlayer); // Set the new media player to the media view

            // Update the video description
            videoDescription.setText(description);

            // Bind the new media player's current time to the time slider and labels
            mediaPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
                if (!timeSlider.isValueChanging()) {
                    timeSlider.setValue(newTime.toSeconds());
                }
                currentTimeLabel.setText(formatTime(newTime));
            });

            // When the media is ready, update the total duration label and time slider's
            // max value
            mediaPlayer.setOnReady(() -> {
                Duration total = mediaPlayer.getMedia().getDuration();
                timeSlider.setMax(total.toSeconds());
                timeSlider.setValue(0); // Reset the time slider to the start
                totalTimeLabel.setText(formatTime(total));
            });

            // Update the time slider's value based on user interaction
            timeSlider.valueChangingProperty().addListener((obs, wasChanging, isChanging) -> {
                if (!isChanging) {
                    mediaPlayer.seek(Duration.seconds(timeSlider.getValue()));
                }
            });

            mediaPlayer.play(); // Automatically play the new video
        });

        VBox videoDetails = new VBox(30, videoLink);
        videoDetails.setAlignment(Pos.CENTER_LEFT);

        HBox videoLinkBox = new HBox(50, thumbnailContainer, videoDetails);
        videoLinkBox.setAlignment(Pos.CENTER_LEFT);
        return videoLinkBox;
    }

    String desc1 = "आज हम नारियल और मछली पालन प्रणाली के एकीकृत खेती के बारे में चर्चा करेंगे। यदि आप इस वीडियो को अंत तक देखते हैं, तो आप समझ सकते हैं कि आप अपनी मौजूदा जमीन से अपनी आय को कैसे तिगुना कर सकते हैं। तो इस वीडियो को अंत तक देखें।\r\n"
            + //
            "\r\n" + //
            "**नारियल की खेती**\r\n" + //
            "\r\n" + //
            "नारियल के पेड़ को कल्पवृक्ष, स्वर्ग का पेड़ कहा जाता है, क्योंकि इस पेड़ का हर हिस्सा किसी न किसी रूप में मानव के लिए उपयोगी होता है। यह भोजन, पेय, ईंधन और लकड़ी प्रदान करता है। अच्छे कृषि प्रबंधन प्रथाओं के साथ किसान अधिकतम उपज और अच्छा लाभ प्राप्त कर सकते हैं। दुनिया के 93 से अधिक देशों में नारियल की खेती की जाती है और इंडोनेशिया, फिलीपींस और भारत दुनिया में प्रमुख उत्पादक देश हैं।\r\n"
            + //
            "\r\n" + //
            "**मछली पालन**\r\n" + //
            "\r\n" + //
            "मछली भारतीयों के भोजन मेनू में लगभग 60% लोगों के लिए पसंदीदा वस्तु है। इसलिए मछली की मांग हमेशा उच्च रहती है। अगर हम वैश्विक मांग को भी ध्यान में रखें, तो कुल आवश्यकता बहुत अधिक हो जाती है। मछली का मुख्य स्रोत समुद्र है, लेकिन समुद्र से वैश्विक मछली की कटाई तेजी से घट रही है। मछलियों की बढ़ती मांग को पूरा करने के लिए मछली पालन ही एकमात्र उपाय है। अंतर्देशीय ताजे पानी की मछली यहां प्रमुख भूमिका निभाती है। एक अच्छे व्यापार योजना और निर्णय के साथ, हम इस अवसर को एक सफल छोटे पैमाने के फार्म में बदल सकते हैं।\r\n"
            + //
            "\r\n" + //
            "**नारियल और मछली पालन का एकीकृत खेती**\r\n" + //
            "\r\n" + //
            "एकीकृत खेती एक संपूर्ण फार्म प्रबंधन प्रणाली है जिसका उद्देश्य अधिक स्थायी कृषि प्रदान करना है। एकीकृत खेती आधुनिक उपकरणों और प्रौद्योगिकियों को पारंपरिक प्रथाओं के साथ मिलाती है, जो दिए गए स्थल और स्थिति के अनुसार होती है, अक्सर एक छोटे से बढ़ते क्षेत्र में कई कृषि तकनीकों को अपनाती है। नारियल को बहुत अधिक पानी की आवश्यकता होती है। इसलिए नारियल की खेती में नहर सिंचाई प्रणाली लोकप्रिय हो रही थी। पहले से ही कई किसानों ने इस तकनीक को अपनाया है। यदि किसान नारियल की खेती के साथ मछली पालन को जोड़ते हैं, तो वे अच्छा लाभ कमा सकते हैं। नहरों में, वे बहुत आसानी से मछली पाल सकते हैं।\r\n"
            + //
            "\r\n" + //
            "पहले जेसीबी की मदद से नहरें और बिस्तर बनाए जाते हैं। बिस्तर में, आप नारियल के पौधे लगा सकते हैं। उन पौधों के बीच, आप अनानास भी लगा सकते हैं। तो, आप और भी अधिक लाभ प्राप्त कर सकते हैं। फिर नहर के पानी में मछली पालें।\r\n"
            + //
            "\r\n" + //
            "**मछली की नस्लों का चयन**\r\n" + //
            "\r\n" + //
            "मछली की नस्लों का चयन हमारे व्यवसाय की स्थिरता में महत्वपूर्ण भूमिका निभाता है। निर्णय बाजार की मांग, रखरखाव के दृष्टिकोण से, संसाधन की उपलब्धता, संसाधनों के प्रभावी उपयोग आदि के आधार पर होना चाहिए। भारतीय तालाबों के लिए कैटला, रोहू, ग्रास कार्प, कॉमन कार्प जैसी कार्प आइटम उपयुक्त हैं। अन्य नस्लें जैसे तिलापिया, कैटफिश आदि भी भारतीय तालाबों में पाली जाती हैं। पॉलीकल्चर, एक ही तालाब में दो या अधिक मछली नस्लों को उगाना संसाधनों के अनुकूल उपयोग के लिए उपयुक्त रणनीति है। आप नजदीकी मछली किसान या मत्स्य विभाग से गुणवत्ता वाली बेबी मछलियां प्राप्त कर सकते हैं।\r\n"
            + //
            "\r\n" + //
            "**निष्कर्ष**\r\n" + //
            "\r\n" + //
            "इस प्रकार आप मछली पालन को नारियल की खेती के साथ जोड़ सकते हैं। नारियल के पौधों के बीच कुछ अन्य फसलें भी उगाएं। ताकि आप एक छोटे क्षेत्र से अधिक भोजन उत्पन्न कर सकें, साथ ही आप अपनी मौजूदा जमीन से अधिक लाभ कमा सकें।";

    private void initializeVideoSets() {
        videoSets = new HashMap<>();

        List<VideoInfo> option1Videos = new ArrayList<>();
        option1Videos.add(new VideoInfo("कृषि क्षेत्र में बेहतरीन व्यवसाय विचारों\n की एक पूरी मामला अध्ययन के साथ",
                "/videos/video1.mp4", "/videos/v1.png", desc1));
        option1Videos.add(new VideoInfo("संयुक्त नारळ आणि मासवाली पालन", "/videos/video2.mp4", "/videos/v2.png",
                "Description for Video 2"));
        option1Videos.add(new VideoInfo("कमाल के कृषि यंत्र ", "/videos/video3.mp4", "/videos/v3.png",
                "Description for Video 3."));
        option1Videos.add(new VideoInfo("बिना जमीन उगाते हैं सब्जियां, 5 गुना\n तक कमाई __ Technical Farming __",
                "/videos/video5.mp4", "/videos/v5.png", "Description for Video 5"));
        option1Videos.add(new VideoInfo("खेती करने की ऐसी तकनीक जिससे\n किसान लाखों रुपए कमा रहे हैं _ shorts curio",
                "/videos/video4.mp4", "/videos/v4.png", "Description for Video 4."));
       

        List<VideoInfo> option2Videos = new ArrayList<>();
        option2Videos.add(new VideoInfo("Video 1", "/videos/video1.mp4", "/download.jpeg", "Description for Video 1."));
        option2Videos.add(new VideoInfo("Video 2", "/videos/video2.mp4", "/download.jpeg", "Description for Video 2."));
        option2Videos.add(new VideoInfo("Video 3", "/videos/video3.mp4", "/download.jpeg", "Description for Video 3."));
        option2Videos.add(new VideoInfo("Video 5", "/videos/video5.mp4", "/download.jpeg", "Description for Video 5."));
        option2Videos.add(new VideoInfo("Video 4", "/videos/video4.mp4", "/download.jpeg", "Description for Video 4."));

        List<VideoInfo> option3Videos = new ArrayList<>();
        option3Videos.add(new VideoInfo("Video 1", "/videos/video1.mp4", "/download.jpeg", "Description for Video 1."));
        option3Videos.add(new VideoInfo("Video 2", "/videos/video2.mp4", "/download.jpeg", "Description for Video 2."));
        option3Videos.add(new VideoInfo("Video 3", "/videos/video3.mp4", "/download.jpeg", "Description for Video 3."));
        option3Videos.add(new VideoInfo("Video 4", "/videos/video4.mp4", "/download.jpeg", "Description for Video 4."));
        option3Videos.add(new VideoInfo("Video 5", "/videos/video5.mp4", "/download.jpeg", "Description for Video 5."));

        // List<VideoInfo> option4Videos = new ArrayList<>();
        // option4Videos.add(new VideoInfo("Video 1", "/videos/video1.mp4",
        // "/download.jpeg", "Description for Video 1."));
        // option4Videos.add(new VideoInfo("Video 2", "/videos/video2.mp4",
        // "/download.jpeg", "Description for Video 2."));
        // option4Videos.add(new VideoInfo("Video 3", "/videos/video3.mp4",
        // "/download.jpeg", "Description for Video 3."));
        // option4Videos.add(new VideoInfo("Video 4", "/videos/video4.mp4",
        // "/download.jpeg", "Description for Video 4."));
        // option4Videos.add(new VideoInfo("Video 5", "/videos/video5.mp4",
        // "/download.jpeg", "Description for Video 5."));

        // List<VideoInfo> option5Videos = new ArrayList<>();
        // option5Videos.add(new VideoInfo("Video 1", "/videos/video1.mp4",
        // "/download.jpeg", "Description for Video 1."));
        // option5Videos.add(new VideoInfo("Video 2", "/videos/video2.mp4",
        // "/download.jpeg", "Description for Video 2."));
        // option5Videos.add(new VideoInfo("Video 3", "/videos/video3.mp4",
        // "/download.jpeg", "Description for Video 3."));
        // option5Videos.add(new VideoInfo("Video 4", "/videos/video4.mp4",
        // "/download.jpeg", "Description for Video 4."));
        // option5Videos.add(new VideoInfo("Video 5", "/videos/video5.mp4",
        // "/download.jpeg", "Description for Video 5."));

        // List<VideoInfo> option6Videos = new ArrayList<>();
        // option6Videos.add(new VideoInfo("Video 11", "/video2.mp4", "/download.jpeg",
        // "Description for Video 6."));
        // option6Videos.add(new VideoInfo("Video 12", "/video2.mp4", "/download.jpeg",
        // "Description for Video 7."));
        // option6Videos.add(new VideoInfo("Video 13", "/video2.mp4", "/download.jpeg",
        // "Description for Video 8."));
        // option6Videos.add(new VideoInfo("Video 14", "/video2.mp4", "/download.jpeg",
        // "Description for Video 9."));
        // option6Videos.add(new VideoInfo("Video 15", "/video.mp4", "/download.jpeg",
        // "Description for Video 10."));

        // List<VideoInfo> option7Videos = new ArrayList<>();
        // option7Videos.add(new VideoInfo("Video 11", "/video2.mp4", "/download.jpeg",
        // "Description for Video 6."));
        // option7Videos.add(new VideoInfo("Video 12", "/video2.mp4", "/download.jpeg",
        // "Description for Video 7."));
        // option7Videos.add(new VideoInfo("Video 13", "/video2.mp4", "/download.jpeg",
        // "Description for Video 8."));
        // option7Videos.add(new VideoInfo("Video 14", "/video2.mp4", "/download.jpeg",
        // "Description for Video 9."));
        // option7Videos.add(new VideoInfo("Video 15", "/video.mp4", "/download.jpeg",
        // "Description for Video 10."));

        // List<VideoInfo> option8Videos = new ArrayList<>();
        // option8Videos.add(new VideoInfo("Video 11", "/video2.mp4", "/download.jpeg",
        // "Description for Video 6."));
        // option8Videos.add(new VideoInfo("Video 12", "/video2.mp4", "/download.jpeg",
        // "Description for Video 7."));
        // option8Videos.add(new VideoInfo("Video 13", "/video2.mp4", "/download.jpeg",
        // "Description for Video 8."));
        // option8Videos.add(new VideoInfo("Video 14", "/video2.mp4", "/download.jpeg",
        // "Description for Video 9."));
        // option8Videos.add(new VideoInfo("Video 15", "/video.mp4", "/download.jpeg",
        // "Description for Video 10."));

        videoSets.put("New Farming techniques", option1Videos);
        videoSets.put("New Equipments", option2Videos);
        videoSets.put("Crops", option3Videos);
        // videoSets.put("Option 4", option4Videos);
        // videoSets.put("Option 5", option5Videos);
    }

    private String formatTime(Duration time) {
        int minutes = (int) time.toMinutes();
        int seconds = (int) time.toSeconds() % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    private static class VideoInfo {
        String title;
        String videoPath;
        String thumbnailPath;
        String description;

        VideoInfo(String title, String videoPath, String thumbnailPath, String description) {
            this.title = title;
            this.videoPath = videoPath;
            this.thumbnailPath = thumbnailPath;
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public String getVideoPath() {
            return videoPath;
        }

        public String getThumbnailPath() {
            return thumbnailPath;
        }

        public String getDescription() {
            return description;
        }
    }
}
