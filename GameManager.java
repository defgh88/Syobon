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
import java.util.Random;

public class GameManager {
	// intstance variables
	public int WIDTH, HEIGHT;
	public final int CARD_WIDTH = 120, CARD_HEIGHT = 175;

	public boolean selected = false;
	public Card selectedCard;

	public Card[][] cards = new Card[4][4];
	public int[] nums = new int[16];

	public GameManager() {
		WIDTH = 4 * CARD_WIDTH;
		HEIGHT = 4 * CARD_HEIGHT;
		// creating the cards and adding them to a deck
		for (int i = 0; i < 8; i++) {
			nums[i * 2] = i;
			nums[i * 2 + 1] = i;
		}
		for (int i = 0; i < 8; i++) {
			shuffle(nums);
		}

		for (int i = 0; i < 16; i++) {
			cards[i / 4][i % 4] = new Card(nums[i]);
		}
	}

	// shuffles teh cards
	public void shuffle(int[] array) {
		int index, temp;
		Random random = new Random();
		for (int i = array.length - 1; i > 0; i--) {
			index = random.nextInt(i + 1);
			temp = array[index];
			array[index] = array[i];
			array[i] = temp;
		}
	}
}
