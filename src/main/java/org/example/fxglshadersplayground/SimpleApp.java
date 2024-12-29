package org.example.fxglshadersplayground;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.GLImageView;

public class SimpleApp extends GameApplication {

        private static final String SHADER = """
            #version 460
            
            uniform float red;
            
            void main() {
                gl_FragColor = vec4(red, 0.0, 0.0, 1.0);
            }
            
            """;

        @Override
        protected void initSettings(GameSettings settings) {
            settings.setWidth(1280);
            settings.setHeight(720);
        }

        @Override
        protected void initGame() {
            GLImageView view = new GLImageView(1280, 720, SHADER);
            view.getProperties().setValue("red", 0.5);

            FXGL.entityBuilder()
                    .at(0, 0)
                    .view(view)
                    .buildAndAttach();
        }

        public static void main(String[] args) {
            launch(args);
        }
}
