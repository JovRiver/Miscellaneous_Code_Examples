package martian_gui;

import java.util.ArrayList;
import java.util.Arrays;

public class MartianTest {

	public static void main(String[] args) {
		// Test 1 - Martian.equals()
		RedMartian r1 = new RedMartian(2);
		GreenMartian g1 = new GreenMartian(11);
		GreenMartian g2 = new GreenMartian(2, 17);
		RedMartian r2 = new RedMartian(33);

		ArrayList<Martian> martians = new ArrayList<>(
				Arrays.asList(r1,g1,g2,r2));
		
		// Have all martian speak
		System.out.println("-->Test martians speaking");
		for(Martian m : martians) {
			System.out.println(m.speak());
		}

		// Have a green martian teleport
		System.out.println("\n-->Test green martian teleporting");
		String destination = "Saturn";
		System.out.println(g1.teleport(destination));
		System.out.println(g2.teleport(destination));

	}
}
