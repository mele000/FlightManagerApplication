package application;

public class BookSeat {

	// polja
	private int red;
	private int kolona;
	private String destinacija;

	// constr
	public BookSeat() {
	}

	public BookSeat(int red, int kolona, String destinacija) {
		this.red = red;
		this.kolona = kolona;
		this.destinacija = destinacija;

		if (postojiLiLet() == true) {
			if (vratiLet().getSeatss()[red][kolona] == true) {
				vratiLet().setSeatss(red, kolona);
				System.out.println("Vase mjesto je rezervisano");
			} else if (vratiLet().getSeatss()[red][kolona] == false) {
				System.out.println("Mjesto je vec rezervisano");
			}
		} else if (postojiLiLet() == false)
			System.out.println("Let " + destinacija + " ne postoji");

	}

	// methods
	public boolean postojiLiLet() {

		for (int i = 0; i < Flight.kreiraniLetovi.size(); i++) {
			if (Flight.kreiraniLetovi.get(i).getDestinacija().equals(destinacija)) {
				return true;
			}

		}

		return false;
	}

	public Flight vratiLet() {

		for (int i = 0; i < Flight.kreiraniLetovi.size(); i++) {
			if (Flight.kreiraniLetovi.get(i).getDestinacija().equals(destinacija)) {
				return Flight.kreiraniLetovi.get(i);
			}
		}

		Flight flight = new Flight();

		return flight;

	}

	// getters
	public int getRed() {
		return red;
	}

	public int getKolona() {
		return kolona;
	}

	public String getDestinacija() {
		return destinacija;
	}

	@Override
	public String toString() {
		return "BookSeat [red=" + red + ", kolona=" + kolona + ", destinacija=" + destinacija + "]";
	}

}
