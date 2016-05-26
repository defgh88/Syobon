/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package matchinggame;

/**
 *
 * @author acao6
 */
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MatchingGame extends JFrame {
	public static void main(String[] args) {
		new MatchingGame();
	}

	public static GameManager manager = new GameManager();

	public MatchingGame() {
		setSize(manager.WIDTH, manager.HEIGHT);//sets size for JFrame
		setResizable(false);//makes size not changeable
		setTitle("Matching Game");//give title
		setBackground(new Color(0, 150, 0));//change background image
		setDefaultCloseOperation(EXIT_ON_CLOSE);
                //adding cards to grid
		setLayout(new GridLayout(4, 4));
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				add(manager.cards[i][j]);
			}
		}
                //centering cards
		setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2
				- getWidth() / 2,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2
						- getHeight() / 2);

		setVisible(true);//showing board
	}
}

