package eu.veldsoft.politrics;

import java.util.HashMap;
import java.util.Map;

import eu.veldsoft.politrics.model.Enemies;
import eu.veldsoft.politrics.model.Figure;
import eu.veldsoft.politrics.model.Board;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class GameActivity extends Activity {
	private ImageView darkLineUp[] = new ImageView[17];
	private ImageView lightLineUp[] = new ImageView[17];

	private Map<Object, Integer> objectToImageId = new HashMap<Object, Integer>();

	private Board board = new Board();

	private void updateViews() {
		// TODO Used object model to visualize.

		int i = 0;
		for (Figure figure : board.getLineups().get(Enemies.DARK)) {
			if (figure == null) {
				darkLineUp[i].setImageBitmap(null);
				i++;
			} else {
				darkLineUp[i].setImageResource(objectToImageId.get(figure));
				i++;
			}
		}

		i = 0;
		for (Figure figure : board.getLineups().get(Enemies.LIGHT)) {
			if (figure == null) {
				lightLineUp[i].setImageBitmap(null);
				i++;
			} else {
				lightLineUp[i].setImageResource(objectToImageId.get(figure));
				i++;
			}
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		darkLineUp[0] = (ImageView) findViewById(R.id.dark_president);
		darkLineUp[1] = (ImageView) findViewById(R.id.dark_voter_1);
		darkLineUp[2] = (ImageView) findViewById(R.id.dark_voter_2);
		darkLineUp[3] = (ImageView) findViewById(R.id.dark_voter_3);
		darkLineUp[4] = (ImageView) findViewById(R.id.dark_voter_4);
		darkLineUp[5] = (ImageView) findViewById(R.id.dark_minister_1);
		darkLineUp[6] = (ImageView) findViewById(R.id.dark_minister_2);
		darkLineUp[7] = (ImageView) findViewById(R.id.dark_minister_3);
		darkLineUp[8] = (ImageView) findViewById(R.id.dark_minister_4);
		darkLineUp[9] = (ImageView) findViewById(R.id.dark_delegate_1);
		darkLineUp[10] = (ImageView) findViewById(R.id.dark_delegate_2);
		darkLineUp[11] = (ImageView) findViewById(R.id.dark_delegate_3);
		darkLineUp[12] = (ImageView) findViewById(R.id.dark_delegate_4);
		darkLineUp[13] = (ImageView) findViewById(R.id.dark_servant_1);
		darkLineUp[14] = (ImageView) findViewById(R.id.dark_servant_2);
		darkLineUp[15] = (ImageView) findViewById(R.id.dark_servant_3);
		darkLineUp[16] = (ImageView) findViewById(R.id.dark_servant_4);

		lightLineUp[0] = (ImageView) findViewById(R.id.light_president);
		lightLineUp[1] = (ImageView) findViewById(R.id.light_voter_1);
		lightLineUp[2] = (ImageView) findViewById(R.id.light_voter_2);
		lightLineUp[3] = (ImageView) findViewById(R.id.light_voter_3);
		lightLineUp[4] = (ImageView) findViewById(R.id.light_voter_4);
		lightLineUp[5] = (ImageView) findViewById(R.id.light_minister_1);
		lightLineUp[6] = (ImageView) findViewById(R.id.light_minister_2);
		lightLineUp[7] = (ImageView) findViewById(R.id.light_minister_3);
		lightLineUp[8] = (ImageView) findViewById(R.id.light_minister_4);
		lightLineUp[9] = (ImageView) findViewById(R.id.light_delegate_1);
		lightLineUp[10] = (ImageView) findViewById(R.id.light_delegate_2);
		lightLineUp[11] = (ImageView) findViewById(R.id.light_delegate_3);
		lightLineUp[12] = (ImageView) findViewById(R.id.light_delegate_4);
		lightLineUp[13] = (ImageView) findViewById(R.id.light_servant_1);
		lightLineUp[14] = (ImageView) findViewById(R.id.light_servant_2);
		lightLineUp[15] = (ImageView) findViewById(R.id.light_servant_3);
		lightLineUp[16] = (ImageView) findViewById(R.id.light_servant_4);

		objectToImageId.put(board.getLineups().get(Enemies.DARK).elementAt(0),
				R.drawable.president_violet);
		objectToImageId.put(board.getLineups().get(Enemies.DARK).elementAt(1),
				R.drawable.voter_violet);
		objectToImageId.put(board.getLineups().get(Enemies.DARK).elementAt(2),
				R.drawable.voter_violet);
		objectToImageId.put(board.getLineups().get(Enemies.DARK).elementAt(3),
				R.drawable.voter_violet);
		objectToImageId.put(board.getLineups().get(Enemies.DARK).elementAt(4),
				R.drawable.voter_violet);
		objectToImageId.put(board.getLineups().get(Enemies.DARK).elementAt(5),
				R.drawable.minister_violet);
		objectToImageId.put(board.getLineups().get(Enemies.DARK).elementAt(6),
				R.drawable.minister_violet);
		objectToImageId.put(board.getLineups().get(Enemies.DARK).elementAt(7),
				R.drawable.minister_violet);
		objectToImageId.put(board.getLineups().get(Enemies.DARK).elementAt(8),
				R.drawable.minister_violet);
		objectToImageId.put(board.getLineups().get(Enemies.DARK).elementAt(9),
				R.drawable.delegate_violet);
		objectToImageId.put(board.getLineups().get(Enemies.DARK).elementAt(10),
				R.drawable.delegate_violet);
		objectToImageId.put(board.getLineups().get(Enemies.DARK).elementAt(11),
				R.drawable.delegate_violet);
		objectToImageId.put(board.getLineups().get(Enemies.DARK).elementAt(12),
				R.drawable.delegate_violet);
		objectToImageId.put(board.getLineups().get(Enemies.DARK).elementAt(13),
				R.drawable.servant_violet);
		objectToImageId.put(board.getLineups().get(Enemies.DARK).elementAt(14),
				R.drawable.servant_violet);
		objectToImageId.put(board.getLineups().get(Enemies.DARK).elementAt(15),
				R.drawable.servant_violet);
		objectToImageId.put(board.getLineups().get(Enemies.DARK).elementAt(16),
				R.drawable.servant_violet);

		objectToImageId.put(board.getLineups().get(Enemies.LIGHT).elementAt(0),
				R.drawable.president_orange);
		objectToImageId.put(board.getLineups().get(Enemies.LIGHT).elementAt(1),
				R.drawable.voter_orange);
		objectToImageId.put(board.getLineups().get(Enemies.LIGHT).elementAt(2),
				R.drawable.voter_orange);
		objectToImageId.put(board.getLineups().get(Enemies.LIGHT).elementAt(3),
				R.drawable.voter_orange);
		objectToImageId.put(board.getLineups().get(Enemies.LIGHT).elementAt(4),
				R.drawable.voter_orange);
		objectToImageId.put(board.getLineups().get(Enemies.LIGHT).elementAt(5),
				R.drawable.minister_orange);
		objectToImageId.put(board.getLineups().get(Enemies.LIGHT).elementAt(6),
				R.drawable.minister_orange);
		objectToImageId.put(board.getLineups().get(Enemies.LIGHT).elementAt(7),
				R.drawable.minister_orange);
		objectToImageId.put(board.getLineups().get(Enemies.LIGHT).elementAt(8),
				R.drawable.minister_orange);
		objectToImageId.put(board.getLineups().get(Enemies.LIGHT).elementAt(9),
				R.drawable.delegate_orange);
		objectToImageId.put(
				board.getLineups().get(Enemies.LIGHT).elementAt(10),
				R.drawable.delegate_orange);
		objectToImageId.put(
				board.getLineups().get(Enemies.LIGHT).elementAt(11),
				R.drawable.delegate_orange);
		objectToImageId.put(
				board.getLineups().get(Enemies.LIGHT).elementAt(12),
				R.drawable.delegate_orange);
		objectToImageId.put(
				board.getLineups().get(Enemies.LIGHT).elementAt(13),
				R.drawable.servant_orange);
		objectToImageId.put(
				board.getLineups().get(Enemies.LIGHT).elementAt(14),
				R.drawable.servant_orange);
		objectToImageId.put(
				board.getLineups().get(Enemies.LIGHT).elementAt(15),
				R.drawable.servant_orange);
		objectToImageId.put(
				board.getLineups().get(Enemies.LIGHT).elementAt(16),
				R.drawable.servant_orange);

		updateViews();
	}
}
