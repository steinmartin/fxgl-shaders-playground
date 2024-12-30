package org.example.fxglshadersplayground;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
//import com.almasb.fxgl.texture.GLImageView;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.List;
import java.util.Map;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameScene;
import static javafx.application.Application.launch;

public class BasicGameApp extends GameApplication {

    private Entity player;

    public static void main(String[] args) {
        launch(args);
        System.out.println(FXGL.getGameScene().getRoot().getScene());
    }

    @Override
    protected void initSettings(GameSettings settings) {

        settings.setWidth(1280);
        settings.setHeight(720);
        settings.setTitle("Basic Game App");
        settings.setVersion("0.1");
    }

    @Override
    protected void initGame() {

        player = FXGL.entityBuilder()
                .at(0, 0)
                .view(new Rectangle(25, 25, Color.BLUE))
                .buildAndAttach();
    }

    @Override
    protected void initInput() {


        System.out.println("Hello\nTiago Stein Margarido");


        FXGL.onKey(KeyCode.D, () -> {
            player.translateX(5); // move right 5 pixels
            FXGL.inc("pixelsMoved", +5);
        });

        FXGL.onKey(KeyCode.A, () -> {
            player.translateX(-5); // move left 5 pixels
            FXGL.inc("pixelsMoved", +5);
        });

        FXGL.onKey(KeyCode.W, () -> {
            player.translateY(-5); // move up 5 pixels
            FXGL.inc("pixelsMoved", +5);
        });

        FXGL.onKey(KeyCode.S, () -> {
            player.translateY(5); // move down 5 pixels
            FXGL.inc("pixelsMoved", +5);

        });

//        node.addEventHandler(DragEvent.DRAG_ENTERED,
//                new EventHandler<DragEvent>() {
//                    public void handle(DragEvent) { ... };
//                });
//
//        eventHandler = event -> {
//            if (event.getButton() == MouseButton.PRIMARY) {
//                primaryClick();
//            } else if (event.getButton() == MouseButton.SECONDARY) {
//                secondaryClick();
//            }
//        };
//        getInput().addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
//
//        FXGL.getInput().addEventHandler(TouchEvent.ANY, System.out::println);
        FXGL.getInput().addEventHandler(TouchEvent.TOUCH_MOVED,  new EventHandler<TouchEvent>() {
            public void handle(TouchEvent te) {
                List touchPoints = te.getTouchPoints();

                if (touchPoints.size() > 1) {
                    TouchPoint tp0 = (TouchPoint) touchPoints.get(0);
                    TouchPoint tp1 = (TouchPoint) touchPoints.get(1);

                    double delta_x = tp1.getX() - tp0.getX();

                    player.translateY(delta_x); // move down 5 pixels
                    FXGL.inc("pixelsMoved", +5);
                }
            };
        });

    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("pixelsMoved", 0);
    }

    @Override
    protected void initUI() {
        Text textPixels = new Text();
        textPixels.setTranslateX(50); // x = 50
        textPixels.setTranslateY(100); // y = 100


        textPixels.textProperty().bind(FXGL.getWorldProperties().intProperty("pixelsMoved").asString());
        FXGL.getGameScene().addUINode(textPixels); // add to the scene graph

        System.out.println("Hello Jan");

    }

    @Override
    protected void onPreInit() {
        super.onPreInit();
        //FXGL.getGameScene().getRoot().getScene().addEventFilter(TouchEvent.ANY, System.out::println);

    }
}
