package application;

import java.io.Serializable;
import java.util.ArrayList;

public class Airport implements Serializable {

	// polja
	private String imeAerodroma;
	public static ArrayList<Airport> kreiraniAerodromi = new ArrayList<>();
	private boolean validacija = true;

	// konstruktori
	public Airport() {
	}

	public Airport(String imeAerodroma) {
		this.imeAerodroma = imeAerodroma;

		if (imeAerodroma.length() == 3) {

			for (int i = 0; i < imeAerodroma.length(); i++) {

				if (Character.isAlphabetic(imeAerodroma.charAt(i))) {
					validacija = true;
				} else {
					validacija = false;
					System.out.println("Karakteri u imenu areodroma nisu alfabeticni");
					break;
				}
			}

		}

		else {
			System.out.println("Ime Aerodrom nema tacno tri karaktera");
			validacija = false;
		}

		for (int i = 0; i < kreiraniAerodromi.size(); i++) {
			if (kreiraniAerodromi.get(i).getImeAerodroma().equals(imeAerodroma)) {
				validacija = false;
				System.out.println("Posoji vec jedan aerodrom sa istim nazivom");
			}
		}

	}

	// getters and setters
	public String getImeAerodroma() {
		return imeAerodroma;
	}

	public boolean getValidacija() {
		return validacija;
	}

	@Override
	public String toString() {
		return "Airport [imeAerodroma=" + imeAerodroma + "]";
	}

}
