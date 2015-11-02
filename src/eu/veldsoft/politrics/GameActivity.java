package eu.veldsoft.politrics;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import eu.veldsoft.politrics.model.Board;
import eu.veldsoft.politrics.model.Enemy;
import eu.veldsoft.politrics.model.Figure;
import eu.veldsoft.politrics.model.State;

public class GameActivity extends Activity {
	private ImageView darkLineUp[] = new ImageView[Board.PIECES];
	private ImageView lightLineUp[] = new ImageView[Board.PIECES];
	private ImageView faces[][] = new ImageView[Board.SIZE][Board.SIZE];
	private ImageView auras[][] = new ImageView[Board.SIZE][Board.SIZE];

	private Map<Object, Integer> objectToImageId = new HashMap<Object, Integer>();

	private Board board = new Board();
	private Object winner[] = { null, 0 };

	private View.OnClickListener listener = new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			boolean moveDone = false;

			if (board.getState() == State.FINISHED) {
				// TODO Use string resources.
				Toast.makeText(GameActivity.this, "Start new game!",
						Toast.LENGTH_LONG).show();
				return;
			}

			loop1: for (int k = 0; k < darkLineUp.length; k++) {
				if (darkLineUp[k] == view) {
					board.lineUpClick(k, Enemy.DARK);
					break loop1;
				}
			}

			loop2: for (int k = 0; k < lightLineUp.length; k++) {
				if (lightLineUp[k] == view) {
					board.lineUpClick(k, Enemy.LIGHT);
					break loop2;
				}
			}

			loop3: for (int i = 0; i < faces.length; i++) {
				for (int j = 0; j < faces[i].length; j++) {
					if (faces[i][j] == view) {
						moveDone = board.fieldClick(i, j);
						if (moveDone == true) {
							winner = board.winner();
							board.unselectAll();
							board.nextTurn();
						}
						break loop3;
					}
				}
			}

			updateViews();
		}
	};

	/**
	 * Used object model to visualize.
	 */
	private void updateViews() {
		/*
		 * Dark line-up visualization.
		 */
		int k = 0;
		for (Figure figure : board.getLineups().get(Enemy.DARK)) {
			darkLineUp[k].setAlpha(1.0F);

			if (figure == null) {
				darkLineUp[k].setImageBitmap(null);
				k++;
			} else {
				if (figure.isSelected() == true) {
					darkLineUp[k].setAlpha(0.5F);
				}

				darkLineUp[k].setImageResource(objectToImageId.get(figure));
				k++;
			}
		}

		/*
		 * Light line-up visualization.
		 */
		k = 0;
		for (Figure figure : board.getLineups().get(Enemy.LIGHT)) {
			lightLineUp[k].setAlpha(1.0F);

			if (figure == null) {
				lightLineUp[k].setImageBitmap(null);
				k++;
			} else {
				if (figure.isSelected() == true) {
					lightLineUp[k].setAlpha(0.5F);
				}

				lightLineUp[k].setImageResource(objectToImageId.get(figure));
				k++;
			}
		}

		/*
		 * Visualize aura on the board.
		 */
		Figure figures[][] = board.getFigures();
		boolean aura[][] = board.getAura();
		for (int j = 0; j < board.SIZE; j++) {
			for (int i = 0; i < board.SIZE; i++) {
				if (aura[i][j] == false) {
					auras[i][j].setImageBitmap(null);
				} else if (aura[i][j] == true && figures[i][j] == null) {
					auras[i][j].setImageResource(R.drawable.aura);
				} else {
					auras[i][j].setImageBitmap(null);
				}
			}
		}

		/*
		 * Figures placed on the board visualization.
		 */
		for (int j = 0; j < board.SIZE; j++) {
			for (int i = 0; i < board.SIZE; i++) {
				faces[i][j].setAlpha(1.0F);

				if (figures[i][j] == null) {
					faces[i][j].setImageBitmap(null);
					continue;
				}

				if (figures[i][j].isSelected() == true) {
					faces[i][j].setAlpha(0.5F);
				}

				faces[i][j]
						.setImageResource(objectToImageId.get(figures[i][j]));
			}
		}

		/*
		 * Show winner message.
		 */
		if ((Integer) winner[1] > 0) {
			// TODO Use string resources.
			Toast.makeText(
					GameActivity.this,
					"You (" + (Enemy) winner[0] + ") win: "
							+ (Integer) winner[1] + " !", Toast.LENGTH_LONG)
					.show();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		darkLineUp[0] = (ImageView) findViewById(R.id.dark_president);
		darkLineUp[1] = (ImageView) findViewById(R.id.dark_voter_4);
		darkLineUp[2] = (ImageView) findViewById(R.id.dark_voter_3);
		darkLineUp[3] = (ImageView) findViewById(R.id.dark_voter_2);
		darkLineUp[4] = (ImageView) findViewById(R.id.dark_voter_1);
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

		faces[0][0] = (ImageView) findViewById(R.id.figure00);
		faces[1][0] = (ImageView) findViewById(R.id.figure10);
		faces[2][0] = (ImageView) findViewById(R.id.figure20);
		faces[3][0] = (ImageView) findViewById(R.id.figure30);
		faces[4][0] = (ImageView) findViewById(R.id.figure40);
		faces[5][0] = (ImageView) findViewById(R.id.figure50);
		faces[6][0] = (ImageView) findViewById(R.id.figure60);
		faces[7][0] = (ImageView) findViewById(R.id.figure70);
		faces[8][0] = (ImageView) findViewById(R.id.figure80);
		faces[0][1] = (ImageView) findViewById(R.id.figure01);
		faces[1][1] = (ImageView) findViewById(R.id.figure11);
		faces[2][1] = (ImageView) findViewById(R.id.figure21);
		faces[3][1] = (ImageView) findViewById(R.id.figure31);
		faces[4][1] = (ImageView) findViewById(R.id.figure41);
		faces[5][1] = (ImageView) findViewById(R.id.figure51);
		faces[6][1] = (ImageView) findViewById(R.id.figure61);
		faces[7][1] = (ImageView) findViewById(R.id.figure71);
		faces[8][1] = (ImageView) findViewById(R.id.figure81);
		faces[0][2] = (ImageView) findViewById(R.id.figure02);
		faces[1][2] = (ImageView) findViewById(R.id.figure12);
		faces[2][2] = (ImageView) findViewById(R.id.figure22);
		faces[3][2] = (ImageView) findViewById(R.id.figure32);
		faces[4][2] = (ImageView) findViewById(R.id.figure42);
		faces[5][2] = (ImageView) findViewById(R.id.figure52);
		faces[6][2] = (ImageView) findViewById(R.id.figure62);
		faces[7][2] = (ImageView) findViewById(R.id.figure72);
		faces[8][2] = (ImageView) findViewById(R.id.figure82);
		faces[0][3] = (ImageView) findViewById(R.id.figure03);
		faces[1][3] = (ImageView) findViewById(R.id.figure13);
		faces[2][3] = (ImageView) findViewById(R.id.figure23);
		faces[3][3] = (ImageView) findViewById(R.id.figure33);
		faces[4][3] = (ImageView) findViewById(R.id.figure43);
		faces[5][3] = (ImageView) findViewById(R.id.figure53);
		faces[6][3] = (ImageView) findViewById(R.id.figure63);
		faces[7][3] = (ImageView) findViewById(R.id.figure73);
		faces[8][3] = (ImageView) findViewById(R.id.figure83);
		faces[0][4] = (ImageView) findViewById(R.id.figure04);
		faces[1][4] = (ImageView) findViewById(R.id.figure14);
		faces[2][4] = (ImageView) findViewById(R.id.figure24);
		faces[3][4] = (ImageView) findViewById(R.id.figure34);
		faces[4][4] = (ImageView) findViewById(R.id.figure44);
		faces[5][4] = (ImageView) findViewById(R.id.figure54);
		faces[6][4] = (ImageView) findViewById(R.id.figure64);
		faces[7][4] = (ImageView) findViewById(R.id.figure74);
		faces[8][4] = (ImageView) findViewById(R.id.figure84);
		faces[0][5] = (ImageView) findViewById(R.id.figure05);
		faces[1][5] = (ImageView) findViewById(R.id.figure15);
		faces[2][5] = (ImageView) findViewById(R.id.figure25);
		faces[3][5] = (ImageView) findViewById(R.id.figure35);
		faces[4][5] = (ImageView) findViewById(R.id.figure45);
		faces[5][5] = (ImageView) findViewById(R.id.figure55);
		faces[6][5] = (ImageView) findViewById(R.id.figure65);
		faces[7][5] = (ImageView) findViewById(R.id.figure75);
		faces[8][5] = (ImageView) findViewById(R.id.figure85);
		faces[0][6] = (ImageView) findViewById(R.id.figure06);
		faces[1][6] = (ImageView) findViewById(R.id.figure16);
		faces[2][6] = (ImageView) findViewById(R.id.figure26);
		faces[3][6] = (ImageView) findViewById(R.id.figure36);
		faces[4][6] = (ImageView) findViewById(R.id.figure46);
		faces[5][6] = (ImageView) findViewById(R.id.figure56);
		faces[6][6] = (ImageView) findViewById(R.id.figure66);
		faces[7][6] = (ImageView) findViewById(R.id.figure76);
		faces[8][6] = (ImageView) findViewById(R.id.figure86);
		faces[0][7] = (ImageView) findViewById(R.id.figure07);
		faces[1][7] = (ImageView) findViewById(R.id.figure17);
		faces[2][7] = (ImageView) findViewById(R.id.figure27);
		faces[3][7] = (ImageView) findViewById(R.id.figure37);
		faces[4][7] = (ImageView) findViewById(R.id.figure47);
		faces[5][7] = (ImageView) findViewById(R.id.figure57);
		faces[6][7] = (ImageView) findViewById(R.id.figure67);
		faces[7][7] = (ImageView) findViewById(R.id.figure77);
		faces[8][7] = (ImageView) findViewById(R.id.figure87);
		faces[0][8] = (ImageView) findViewById(R.id.figure08);
		faces[1][8] = (ImageView) findViewById(R.id.figure18);
		faces[2][8] = (ImageView) findViewById(R.id.figure28);
		faces[3][8] = (ImageView) findViewById(R.id.figure38);
		faces[4][8] = (ImageView) findViewById(R.id.figure48);
		faces[5][8] = (ImageView) findViewById(R.id.figure58);
		faces[6][8] = (ImageView) findViewById(R.id.figure68);
		faces[7][8] = (ImageView) findViewById(R.id.figure78);
		faces[8][8] = (ImageView) findViewById(R.id.figure88);

		auras[0][0] = (ImageView) findViewById(R.id.aura00);
		auras[1][0] = (ImageView) findViewById(R.id.aura10);
		auras[2][0] = (ImageView) findViewById(R.id.aura20);
		auras[3][0] = (ImageView) findViewById(R.id.aura30);
		auras[4][0] = (ImageView) findViewById(R.id.aura40);
		auras[5][0] = (ImageView) findViewById(R.id.aura50);
		auras[6][0] = (ImageView) findViewById(R.id.aura60);
		auras[7][0] = (ImageView) findViewById(R.id.aura70);
		auras[8][0] = (ImageView) findViewById(R.id.aura80);
		auras[0][1] = (ImageView) findViewById(R.id.aura01);
		auras[1][1] = (ImageView) findViewById(R.id.aura11);
		auras[2][1] = (ImageView) findViewById(R.id.aura21);
		auras[3][1] = (ImageView) findViewById(R.id.aura31);
		auras[4][1] = (ImageView) findViewById(R.id.aura41);
		auras[5][1] = (ImageView) findViewById(R.id.aura51);
		auras[6][1] = (ImageView) findViewById(R.id.aura61);
		auras[7][1] = (ImageView) findViewById(R.id.aura71);
		auras[8][1] = (ImageView) findViewById(R.id.aura81);
		auras[0][2] = (ImageView) findViewById(R.id.aura02);
		auras[1][2] = (ImageView) findViewById(R.id.aura12);
		auras[2][2] = (ImageView) findViewById(R.id.aura22);
		auras[3][2] = (ImageView) findViewById(R.id.aura32);
		auras[4][2] = (ImageView) findViewById(R.id.aura42);
		auras[5][2] = (ImageView) findViewById(R.id.aura52);
		auras[6][2] = (ImageView) findViewById(R.id.aura62);
		auras[7][2] = (ImageView) findViewById(R.id.aura72);
		auras[8][2] = (ImageView) findViewById(R.id.aura82);
		auras[0][3] = (ImageView) findViewById(R.id.aura03);
		auras[1][3] = (ImageView) findViewById(R.id.aura13);
		auras[2][3] = (ImageView) findViewById(R.id.aura23);
		auras[3][3] = (ImageView) findViewById(R.id.aura33);
		auras[4][3] = (ImageView) findViewById(R.id.aura43);
		auras[5][3] = (ImageView) findViewById(R.id.aura53);
		auras[6][3] = (ImageView) findViewById(R.id.aura63);
		auras[7][3] = (ImageView) findViewById(R.id.aura73);
		auras[8][3] = (ImageView) findViewById(R.id.aura83);
		auras[0][4] = (ImageView) findViewById(R.id.aura04);
		auras[1][4] = (ImageView) findViewById(R.id.aura14);
		auras[2][4] = (ImageView) findViewById(R.id.aura24);
		auras[3][4] = (ImageView) findViewById(R.id.aura34);
		auras[4][4] = (ImageView) findViewById(R.id.aura44);
		auras[5][4] = (ImageView) findViewById(R.id.aura54);
		auras[6][4] = (ImageView) findViewById(R.id.aura64);
		auras[7][4] = (ImageView) findViewById(R.id.aura74);
		auras[8][4] = (ImageView) findViewById(R.id.aura84);
		auras[0][5] = (ImageView) findViewById(R.id.aura05);
		auras[1][5] = (ImageView) findViewById(R.id.aura15);
		auras[2][5] = (ImageView) findViewById(R.id.aura25);
		auras[3][5] = (ImageView) findViewById(R.id.aura35);
		auras[4][5] = (ImageView) findViewById(R.id.aura45);
		auras[5][5] = (ImageView) findViewById(R.id.aura55);
		auras[6][5] = (ImageView) findViewById(R.id.aura65);
		auras[7][5] = (ImageView) findViewById(R.id.aura75);
		auras[8][5] = (ImageView) findViewById(R.id.aura85);
		auras[0][6] = (ImageView) findViewById(R.id.aura06);
		auras[1][6] = (ImageView) findViewById(R.id.aura16);
		auras[2][6] = (ImageView) findViewById(R.id.aura26);
		auras[3][6] = (ImageView) findViewById(R.id.aura36);
		auras[4][6] = (ImageView) findViewById(R.id.aura46);
		auras[5][6] = (ImageView) findViewById(R.id.aura56);
		auras[6][6] = (ImageView) findViewById(R.id.aura66);
		auras[7][6] = (ImageView) findViewById(R.id.aura76);
		auras[8][6] = (ImageView) findViewById(R.id.aura86);
		auras[0][7] = (ImageView) findViewById(R.id.aura07);
		auras[1][7] = (ImageView) findViewById(R.id.aura17);
		auras[2][7] = (ImageView) findViewById(R.id.aura27);
		auras[3][7] = (ImageView) findViewById(R.id.aura37);
		auras[4][7] = (ImageView) findViewById(R.id.aura47);
		auras[5][7] = (ImageView) findViewById(R.id.aura57);
		auras[6][7] = (ImageView) findViewById(R.id.aura67);
		auras[7][7] = (ImageView) findViewById(R.id.aura77);
		auras[8][7] = (ImageView) findViewById(R.id.aura87);
		auras[0][8] = (ImageView) findViewById(R.id.aura08);
		auras[1][8] = (ImageView) findViewById(R.id.aura18);
		auras[2][8] = (ImageView) findViewById(R.id.aura28);
		auras[3][8] = (ImageView) findViewById(R.id.aura38);
		auras[4][8] = (ImageView) findViewById(R.id.aura48);
		auras[5][8] = (ImageView) findViewById(R.id.aura58);
		auras[6][8] = (ImageView) findViewById(R.id.aura68);
		auras[7][8] = (ImageView) findViewById(R.id.aura78);
		auras[8][8] = (ImageView) findViewById(R.id.aura88);

		objectToImageId.put(board.getLineups().get(Enemy.DARK).elementAt(0),
				R.drawable.president_violet);
		objectToImageId.put(board.getLineups().get(Enemy.DARK).elementAt(1),
				R.drawable.voter_violet);
		objectToImageId.put(board.getLineups().get(Enemy.DARK).elementAt(2),
				R.drawable.voter_violet);
		objectToImageId.put(board.getLineups().get(Enemy.DARK).elementAt(3),
				R.drawable.voter_violet);
		objectToImageId.put(board.getLineups().get(Enemy.DARK).elementAt(4),
				R.drawable.voter_violet);
		objectToImageId.put(board.getLineups().get(Enemy.DARK).elementAt(5),
				R.drawable.minister_violet);
		objectToImageId.put(board.getLineups().get(Enemy.DARK).elementAt(6),
				R.drawable.minister_violet);
		objectToImageId.put(board.getLineups().get(Enemy.DARK).elementAt(7),
				R.drawable.minister_violet);
		objectToImageId.put(board.getLineups().get(Enemy.DARK).elementAt(8),
				R.drawable.minister_violet);
		objectToImageId.put(board.getLineups().get(Enemy.DARK).elementAt(9),
				R.drawable.delegate_violet);
		objectToImageId.put(board.getLineups().get(Enemy.DARK).elementAt(10),
				R.drawable.delegate_violet);
		objectToImageId.put(board.getLineups().get(Enemy.DARK).elementAt(11),
				R.drawable.delegate_violet);
		objectToImageId.put(board.getLineups().get(Enemy.DARK).elementAt(12),
				R.drawable.delegate_violet);
		objectToImageId.put(board.getLineups().get(Enemy.DARK).elementAt(13),
				R.drawable.servant_violet);
		objectToImageId.put(board.getLineups().get(Enemy.DARK).elementAt(14),
				R.drawable.servant_violet);
		objectToImageId.put(board.getLineups().get(Enemy.DARK).elementAt(15),
				R.drawable.servant_violet);
		objectToImageId.put(board.getLineups().get(Enemy.DARK).elementAt(16),
				R.drawable.servant_violet);

		objectToImageId.put(board.getLineups().get(Enemy.LIGHT).elementAt(0),
				R.drawable.president_orange);
		objectToImageId.put(board.getLineups().get(Enemy.LIGHT).elementAt(1),
				R.drawable.voter_orange);
		objectToImageId.put(board.getLineups().get(Enemy.LIGHT).elementAt(2),
				R.drawable.voter_orange);
		objectToImageId.put(board.getLineups().get(Enemy.LIGHT).elementAt(3),
				R.drawable.voter_orange);
		objectToImageId.put(board.getLineups().get(Enemy.LIGHT).elementAt(4),
				R.drawable.voter_orange);
		objectToImageId.put(board.getLineups().get(Enemy.LIGHT).elementAt(5),
				R.drawable.minister_orange);
		objectToImageId.put(board.getLineups().get(Enemy.LIGHT).elementAt(6),
				R.drawable.minister_orange);
		objectToImageId.put(board.getLineups().get(Enemy.LIGHT).elementAt(7),
				R.drawable.minister_orange);
		objectToImageId.put(board.getLineups().get(Enemy.LIGHT).elementAt(8),
				R.drawable.minister_orange);
		objectToImageId.put(board.getLineups().get(Enemy.LIGHT).elementAt(9),
				R.drawable.delegate_orange);
		objectToImageId.put(board.getLineups().get(Enemy.LIGHT).elementAt(10),
				R.drawable.delegate_orange);
		objectToImageId.put(board.getLineups().get(Enemy.LIGHT).elementAt(11),
				R.drawable.delegate_orange);
		objectToImageId.put(board.getLineups().get(Enemy.LIGHT).elementAt(12),
				R.drawable.delegate_orange);
		objectToImageId.put(board.getLineups().get(Enemy.LIGHT).elementAt(13),
				R.drawable.servant_orange);
		objectToImageId.put(board.getLineups().get(Enemy.LIGHT).elementAt(14),
				R.drawable.servant_orange);
		objectToImageId.put(board.getLineups().get(Enemy.LIGHT).elementAt(15),
				R.drawable.servant_orange);
		objectToImageId.put(board.getLineups().get(Enemy.LIGHT).elementAt(16),
				R.drawable.servant_orange);

		/*
		 * Attach on-click listener.
		 */
		for (View view : darkLineUp) {
			view.setOnClickListener(listener);
		}
		for (View view : lightLineUp) {
			view.setOnClickListener(listener);
		}
		for (View array[] : faces) {
			for (View view : array) {
				view.setOnClickListener(listener);
			}
		}

		updateViews();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.game_option_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.new_game:
			board.reset();
			winner[0] = null;
			winner[1] = new Integer(0);
			updateViews();
			break;
		case R.id.help_game:
			startActivity(new Intent(GameActivity.this, HelpActivity.class));
			break;
		case R.id.about_game:
			startActivity(new Intent(GameActivity.this, AboutActivity.class));
			break;
		}

		return true;
	}
}
