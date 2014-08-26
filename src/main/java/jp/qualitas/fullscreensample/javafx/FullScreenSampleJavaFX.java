package jp.qualitas.fullscreensample.javafx;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FullScreenSampleJavaFX extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Group root = new Group();

		Scene scene = new Scene(root, 640, 480);

		stage.setTitle("Translate Transition Demo");
		stage.setScene(scene);
		stage.show();
		stage.setFullScreen(true);

		this.appendAnimation(root, "hiragana_01_a.png");
		// this.appendAnimation(root, "hiragana_02_i.png");
	}

	private int x = 50;
	private int y = 50;

	private void appendAnimation(final Group root, final String hiraganaImage) {
		final ImageView hiragana = new ImageView(new Image(hiraganaImage));
		hiragana.setScaleX(0.1);
		hiragana.setScaleY(0.1);

		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

		System.out.println("primaryScreenBounds.getWidth():"
				+ primaryScreenBounds.getWidth());
		hiragana.setLayoutX(primaryScreenBounds.getWidth() / 2
				- hiragana.getLayoutBounds().getWidth() / 2);
		hiragana.setLayoutY(primaryScreenBounds.getHeight() / 2
				- hiragana.getLayoutBounds().getHeight() / 2);
		y += 150;
		root.getChildren().add(hiragana);

		// 移動を行なうアニメーション
		// TranslateTransition transition = new TranslateTransition();
		// // アニメーション対象の設定
		// transition.setNode(hiragana);
		// // アニメーションの時間は4000ミリ秒
		// transition.setDuration(Duration.millis(4_000L + y));
		// // 開始位置の設定
		// transition.setFromX(0.0);
		// // 終了位置の設定
		// transition.setToX(300.0);
		// // 繰り返し回数の設定
		// transition.setCycleCount(2);
		// // アニメーションを反転させる
		// transition.setAutoReverse(true);

		// System.out.println(transition.getInterpolator());

		ScaleTransition scale = new ScaleTransition(Duration.millis(4_000),
				hiragana);
		scale.setFromX(0.1);
		scale.setFromY(0.1);
		scale.setToX(2.0);
		scale.setToY(2.0);

		FadeTransition fade = new FadeTransition(Duration.millis(3000),
				hiragana);
		fade.setFromValue(0.1);
		fade.setToValue(1.0);

		RotateTransition rotate = new RotateTransition(Duration.millis(4_000),
				hiragana);
		rotate.setFromAngle(0.0);
		rotate.setToAngle(1440.0);

		ParallelTransition transition = new ParallelTransition(scale, fade);
		transition.setCycleCount(4);
		transition.setAutoReverse(false);

		transition.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("on Animation Finished");
				root.getChildren().remove(hiragana);
			}
		});

		transition.play();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
