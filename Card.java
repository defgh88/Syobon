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
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class Card extends JButton implements ActionListener {
	// four possible states for each card
	enum state {
		FACE_DOWN, SELECTED, FACE_UP, REMOVED
	}

	public int cardNum = -1;

	public static Image CARD_BACK_IMAGE;
	public Image FACE_UP_IMAGE;
	// finds the images for the cards
	static {
		try {
			CARD_BACK_IMAGE = ImageIO.read(MatchingGame.class.getResource("card-back.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public state currentState = state.FACE_DOWN;

	public Card(int _cardNum) {
		cardNum = _cardNum;
		try {
			// choosing the card image
			FACE_UP_IMAGE = ImageIO.read(MatchingGame.class.getResource("card-" + (_cardNum + 1) + ".png"));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		addActionListener(this);
	}

	// returns what the card image is currently, face down or face up
	public Image getCurrentImage() {
		if (currentState == state.SELECTED || currentState == state.FACE_UP) {
			return FACE_UP_IMAGE;
		}
		return CARD_BACK_IMAGE;
	}

	// checks if two cards match
	public boolean match(Card other) {
		return cardNum == other.cardNum;
	}

	@Override
	// overrides paint method for buttons to make each button a card shape
	public void paint(Graphics g) {
		if (currentState == state.REMOVED) {
			g.clearRect(0, 0, getWidth(), getHeight());
			// g.setColor(new Color(0, 0, 0, 0));
			// g.fillRect(0, 0, getWidth(), getHeight());
		} else {
			g.drawImage(getCurrentImage(), 0, 0, getWidth(), getHeight(), this);
		}
	}

	@Override
	// actions for when a card is clicked
	public void actionPerformed(ActionEvent e) {
		if (currentState == state.FACE_DOWN) {
			if (!MatchingGame.manager.selected) {
				currentState = state.SELECTED;
				MatchingGame.manager.selected = true;
				MatchingGame.manager.selectedCard = this;// makes the current
															// card selected for
															// the manager
			} else {
				currentState = state.FACE_UP;
				MatchingGame.manager.selectedCard.currentState = state.FACE_UP;
				repaint();
				try {
					System.out.println(currentState);
					Thread.sleep(800);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				// if matches removes cards
				if (match(MatchingGame.manager.selectedCard)) {
					currentState = state.REMOVED;
					MatchingGame.manager.selectedCard.currentState = state.REMOVED;
					System.out.println("REMOVED");
				}
				// otherwise flips both cards over
				else {
					currentState = state.FACE_DOWN;
					MatchingGame.manager.selectedCard.currentState = state.FACE_DOWN;
				}
				MatchingGame.manager.selected = false;
				MatchingGame.manager.selectedCard = null;
			}
		}
		// if card is face up and clicked you flip it over
		else if (currentState == state.FACE_UP) {
			currentState = state.FACE_DOWN;
		} else {
			// DO NOTHING
		}
		// repaint all cards with new conditions
		for (Card[] cards : MatchingGame.manager.cards) {
			for (Card card : cards) {
				card.repaint();
			}
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				Card c = MatchingGame.manager.cards[i][j];
				System.out.print((c.cardNum + 1) + ": " + c.currentState + "\t");
			}
			System.out.println();
		}
	}
}
