package application;

import java.io.Serializable;
import java.util.ArrayList;

public class AirLine implements Serializable {

	// polja
	private String imeAvionskeLinije;
	public static ArrayList<AirLine> kreiraneAvionsKeLinije = new ArrayList<>();
	private boolean validacija = true;

	// konstruktori
	public AirLine() {
	}

	public AirLine(String imeAvionskeLinije) {
		this.imeAvionskeLinije = imeAvionskeLinije;

		if (imeAvionskeLinije.length() < 6) {

			for (int i = 0; i < imeAvionskeLinije.length(); i++) {

				if (Character.isAlphabetic(imeAvionskeLinije.charAt(i))) {
					validacija = true;
				} else {
					validacija = false;
					System.out.println("Karakteri u imenu avionske linije nisu alfabeticni");
					break;
				}
			}

		}

		else {
			System.out.println("Ime Avionske linije ima 6 ili vise od 6 karaktera");
			validacija = false;
		}

		for (int i = 0; i < kreiraneAvionsKeLinije.size(); i++) {
			if (kreiraneAvionsKeLinije.get(i).getImeAvionskeLinije().equals(imeAvionskeLinije)) {
				validacija = false;
				System.out.println("Posoji vec jedna avionska linija sa istim nazivom");
			}
		}

	}

	// getters and setters

	public String getImeAvionskeLinije() {
		return imeAvionskeLinije;
	}

	public boolean getValidacija() {
		return validacija;
	}

	@Override
	public String toString() {
		return "AirLine [imeAvionskeLinije=" + imeAvionskeLinije + "]";
	}

}
