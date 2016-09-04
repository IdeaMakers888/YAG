package we.can.change.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import we.can.change.YetAnotherGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "RPG game. Set in OurGame-desktop -> DesktopLauncher.java";
		config.width = 1280;
		config.height = 1024;
		config.resizable = false;
		new LwjglApplication(new YetAnotherGame(), config);
	}
}
