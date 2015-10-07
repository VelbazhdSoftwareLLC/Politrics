package eu.veldsoft.politrics;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
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
	private ImageView cells[][] = new ImageView[Board.SIZE][Board.SIZE];

	private Map<Object, Integer> objectToImageId = new HashMap<Object, Integer>();

	private Board board = new Board();
	
	Object winner[] = {null, 0};

	private View.OnClickListener listener = new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			boolean moveDone = false;
			
			if (board.getState() == State.FINISHED) {
				Toast.makeText(GameActivity.this, "Start new game!",
						Toast.LENGTH_LONG).show();
			}

			loop1: for (int k = 0; k < darkLineUp.length; k++) {
				if (darkLineUp[k] == view) {
					moveDone = board.lineUpClick(k, Enemy.DARK);
					if(moveDone == true) {
						winner = board.winner();
						board.nextTurn();
					}
					break loop1;
				}
			}

			loop2: for (int k = 0; k < lightLineUp.length; k++) {
				if (lightLineUp[k] == view) {
					moveDone = board.lineUpClick(k, Enemy.LIGHT);
					if(moveDone == true) {
						winner = board.winner();
						board.nextTurn();
					}
					break loop2;
				}
			}

			loop3: for (int i = 0; i < cells.length; i++) {
				for (int j = 0; j < cells[i].length; j++) {
					if (cells[i][j] == view) {
						moveDone = board.fieldClick(i, j);
						if(moveDone == true) {
							winner = board.winner();
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
		 * Figures placed on the board visualization.
		 */
		Figure figures[][] = board.getFigures();
		for (int j = 0; j < board.SIZE; j++) {
			for (int i = 0; i < board.SIZE; i++) {
				cells[i][j].setAlpha(1.0F);

				if (figures[i][j] == null) {
					cells[i][j].setImageBitmap(null);
					continue;
				}

				if (figures[i][j].isSelected() == true) {
					cells[i][j].setAlpha(0.5F);
				}

				cells[i][j]
						.setImageResource(objectToImageId.get(figures[i][j]));
			}
		}

		/*
		 * Show winner message.
		 */
		if ((Integer) winner[1] > 0) {
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

		cells[0][0] = (ImageView) findViewById(R.id.figure00);
		cells[1][0] = (ImageView) findViewById(R.id.figure10);
		cells[2][0] = (ImageView) findViewById(R.id.figure20);
		cells[3][0] = (ImageView) findViewById(R.id.figure30);
		cells[4][0] = (ImageView) findViewById(R.id.figure40);
		cells[5][0] = (ImageView) findViewById(R.id.figure50);
		cells[6][0] = (ImageView) findViewById(R.id.figure60);
		cells[7][0] = (ImageView) findViewById(R.id.figure70);
		cells[8][0] = (ImageView) findViewById(R.id.figure80);
		cells[0][1] = (ImageView) findViewById(R.id.figure01);
		cells[1][1] = (ImageView) findViewById(R.id.figure11);
		cells[2][1] = (ImageView) findViewById(R.id.figure21);
		cells[3][1] = (ImageView) findViewById(R.id.figure31);
		cells[4][1] = (ImageView) findViewById(R.id.figure41);
		cells[5][1] = (ImageView) findViewById(R.id.figure51);
		cells[6][1] = (ImageView) findViewById(R.id.figure61);
		cells[7][1] = (ImageView) findViewById(R.id.figure71);
		cells[8][1] = (ImageView) findViewById(R.id.figure81);
		cells[0][2] = (ImageView) findViewById(R.id.figure02);
		cells[1][2] = (ImageView) findViewById(R.id.figure12);
		cells[2][2] = (ImageView) findViewById(R.id.figure22);
		cells[3][2] = (ImageView) findViewById(R.id.figure32);
		cells[4][2] = (ImageView) findViewById(R.id.figure42);
		cells[5][2] = (ImageView) findViewById(R.id.figure52);
		cells[6][2] = (ImageView) findViewById(R.id.figure62);
		cells[7][2] = (ImageView) findViewById(R.id.figure72);
		cells[8][2] = (ImageView) findViewById(R.id.figure82);
		cells[0][3] = (ImageView) findViewById(R.id.figure03);
		cells[1][3] = (ImageView) findViewById(R.id.figure13);
		cells[2][3] = (ImageView) findViewById(R.id.figure23);
		cells[3][3] = (ImageView) findViewById(R.id.figure33);
		cells[4][3] = (ImageView) findViewById(R.id.figure43);
		cells[5][3] = (ImageView) findViewById(R.id.figure53);
		cells[6][3] = (ImageView) findViewById(R.id.figure63);
		cells[7][3] = (ImageView) findViewById(R.id.figure73);
		cells[8][3] = (ImageView) findViewById(R.id.figure83);
		cells[0][4] = (ImageView) findViewById(R.id.figure04);
		cells[1][4] = (ImageView) findViewById(R.id.figure14);
		cells[2][4] = (ImageView) findViewById(R.id.figure24);
		cells[3][4] = (ImageView) findViewById(R.id.figure34);
		cells[4][4] = (ImageView) findViewById(R.id.figure44);
		cells[5][4] = (ImageView) findViewById(R.id.figure54);
		cells[6][4] = (ImageView) findViewById(R.id.figure64);
		cells[7][4] = (ImageView) findViewById(R.id.figure74);
		cells[8][4] = (ImageView) findViewById(R.id.figure84);
		cells[0][5] = (ImageView) findViewById(R.id.figure05);
		cells[1][5] = (ImageView) findViewById(R.id.figure15);
		cells[2][5] = (ImageView) findViewById(R.id.figure25);
		cells[3][5] = (ImageView) findViewById(R.id.figure35);
		cells[4][5] = (ImageView) findViewById(R.id.figure45);
		cells[5][5] = (ImageView) findViewById(R.id.figure55);
		cells[6][5] = (ImageView) findViewById(R.id.figure65);
		cells[7][5] = (ImageView) findViewById(R.id.figure75);
		cells[8][5] = (ImageView) findViewById(R.id.figure85);
		cells[0][6] = (ImageView) findViewById(R.id.figure06);
		cells[1][6] = (ImageView) findViewById(R.id.figure16);
		cells[2][6] = (ImageView) findViewById(R.id.figure26);
		cells[3][6] = (ImageView) findViewById(R.id.figure36);
		cells[4][6] = (ImageView) findViewById(R.id.figure46);
		cells[5][6] = (ImageView) findViewById(R.id.figure56);
		cells[6][6] = (ImageView) findViewById(R.id.figure66);
		cells[7][6] = (ImageView) findViewById(R.id.figure76);
		cells[8][6] = (ImageView) findViewById(R.id.figure86);
		cells[0][7] = (ImageView) findViewById(R.id.figure07);
		cells[1][7] = (ImageView) findViewById(R.id.figure17);
		cells[2][7] = (ImageView) findViewById(R.id.figure27);
		cells[3][7] = (ImageView) findViewById(R.id.figure37);
		cells[4][7] = (ImageView) findViewById(R.id.figure47);
		cells[5][7] = (ImageView) findViewById(R.id.figure57);
		cells[6][7] = (ImageView) findViewById(R.id.figure67);
		cells[7][7] = (ImageView) findViewById(R.id.figure77);
		cells[8][7] = (ImageView) findViewById(R.id.figure87);
		cells[0][8] = (ImageView) findViewById(R.id.figure08);
		cells[1][8] = (ImageView) findViewById(R.id.figure18);
		cells[2][8] = (ImageView) findViewById(R.id.figure28);
		cells[3][8] = (ImageView) findViewById(R.id.figure38);
		cells[4][8] = (ImageView) findViewById(R.id.figure48);
		cells[5][8] = (ImageView) findViewById(R.id.figure58);
		cells[6][8] = (ImageView) findViewById(R.id.figure68);
		cells[7][8] = (ImageView) findViewById(R.id.figure78);
		cells[8][8] = (ImageView) findViewById(R.id.figure88);

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
		for (View array[] : cells) {
			for (View view : array) {
				view.setOnClickListener(listener);
			}
		}

		updateViews();
	}
}
