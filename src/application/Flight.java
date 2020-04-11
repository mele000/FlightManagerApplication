package application;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.crypto.AEADBadTagException;

public class Flight implements Serializable {

	public static void main(String[] args)
			throws EOFException, IOException, ClassNotFoundException, FileNotFoundException {

		try {
			FileInputStream fi = new FileInputStream("flights.txt");
			ObjectInputStream input = new ObjectInputStream(fi);

			try {
				while (true) {
					Airport airport = (Airport) input.readObject();
					Airport.kreiraniAerodromi.add(airport);
					System.out.println(Airport.kreiraniAerodromi.toString());
				}

			} catch (EOFException e) {

			}
		} catch (EOFException e1) {

		}

		try {
			FileInputStream fii = new FileInputStream("flights_airline.txt");
			ObjectInputStream inputt = new ObjectInputStream(fii);

			try {
				while (true) {
					AirLine airline = (AirLine) inputt.readObject();
					AirLine.kreiraneAvionsKeLinije.add(airline);
					System.out.println(AirLine.kreiraneAvionsKeLinije.toString());
				}

			} catch (EOFException e) {

			}

		} catch (EOFException e) {

		}

		Scanner unos = new Scanner(System.in);

		System.out.println("Unesite" + "\n1 ako hocete kreirati aerodrom," + "\n2 ako hocete kreirati avonsku liniju,"
				+ "\n3 ako hocete kreirati let," + "\n4 ako hocete rezervisati sjediste"
				+ "\nbilo koji drugi broj ako hocete zavrsiti program");

		int broj = unos.nextInt();

		while (true) {

			if (broj == 1) {

				System.out.println("\nUnesite ime aredroma");
				String imeAerodroma = unos.next();

				Airport airport = new Airport(imeAerodroma);

				if (airport.getValidacija() == true) {
					System.out.println("Kreirali ste aerodrom");
					airport.kreiraniAerodromi.add(airport);
				}

				System.out.println(airport.kreiraniAerodromi);

			}

			else if (broj == 2) {

				System.out.println("\nUnesite ime avionske linije");
				String imeAvionskeLinije = unos.next();

				AirLine airLine = new AirLine(imeAvionskeLinije);

				if (airLine.getValidacija() == true) {
					System.out.println("Kreirali ste avionsku liniju");
					airLine.kreiraneAvionsKeLinije.add(airLine);
				}

				System.out.println(airLine.kreiraneAvionsKeLinije);

			}

			else if (broj == 3) {

				// int [] arr = new int[6];

				System.out.println("\nUnesite ime aredroma");
				String imeAerodroma = unos.next();

				System.out.println("\nUnesite ime avionske linije");
				String imeAvionskeLinije = unos.next();

				System.out.println("\nUnesite svoju destinaciju ");
				String destinacija = unos.next();

				System.out.println("Unesite kolone");
				int kolone = unos.nextInt();

				/*
				 * for (int i = 0; i < 6; i++) { arr[i] = unos.nextInt(); }
				 */

				Flight flight = new Flight(imeAerodroma, imeAvionskeLinije, destinacija, kolone);

				if (flight.getValidacija() == true) {
					flight.kreiraniLetovi.add(flight);
				}
				System.out.println(flight.kreiraniLetovi);

			} else if (broj == 4) {

				System.out.println("Uneiste vasu destinaciju");
				String destinacija = unos.next();
				System.out.println("Unesite broj reda mjesta koje zelite rezervisati");
				int red = unos.nextInt();
				System.out.println("Unesite broj kolone mjesta koje zelite rezervisati");
				int kolona = unos.nextInt();

				try {
					BookSeat bookseat = new BookSeat(red, kolona, destinacija);
				} catch (IndexOutOfBoundsException e) {
					System.out.println("Mjesto je zauzeto");
				}

			}

			else
				break;

			System.out.println("Unesite" + "\n1 ako hocete kreirati aerodrom,"
					+ "\n2 ako hocete kreirati avonsku liniju," + "\n3 ako hocete kreirati let,"
					+ "\n4 ako hocete rezervisati sjediste" + "\nbilo koji drugi broj ako hocete zavrsiti program");

			broj = unos.nextInt();

		}

		FileOutputStream fo = new FileOutputStream("flights.txt");
		ObjectOutputStream output = new ObjectOutputStream(fo);

		for (Airport airport : Airport.kreiraniAerodromi) {
			output.writeObject(airport);
		}
		output.close();
		fo.close();

		FileOutputStream foo = new FileOutputStream("flights_airline.txt");
		ObjectOutputStream outputt = new ObjectOutputStream(foo);

		for (AirLine airline : AirLine.kreiraneAvionsKeLinije) {
			outputt.writeObject(airline);
		}
		outputt.close();
		foo.close();

	}

	// polja
	private String imeAerodroma;
	private String imeAvionskeLinije;
	private String destinacija;
	public static ArrayList<Flight> kreiraniLetovi = new ArrayList<>();
	private boolean validacija = true;
	static int kolone;


	
	
	// constructors
	public Flight() {
	}

	private int brojKojiOznacavaBrojKolona;

	public Flight(String imeAerodroma, String imeAvionskeLinije, String destinacija, int kolone) {
		this.imeAerodroma = imeAerodroma;
		this.imeAvionskeLinije = imeAvionskeLinije;
		this.destinacija = destinacija;
		this.kolone = kolone;

		boolean[][] seats = new boolean[6][kolone];

		if (postojiLiAerodrom()) {
			if (postojiLiAvionskaLinija()) {

				for (int i = 0; i < 6; i++) {
					for (int j = 0; j < kolone; j++) {
						seats[i][j] = true;
					}
				}

			} else {
				System.out.println("Ne postoji avionska linija sa ovim imenom");
				validacija = false;
			}

		}

		else {
			System.out.println("Ne postoji aerodrom sa ovim imenom");
			validacija = false;
		}

		brojKojiOznacavaBrojKolona = seats[0].length;

		System.arraycopy(seats, 0, seatss, 0, seatss.length);

	}

	private boolean[][] seatss = new boolean[6][brojKojiOznacavaBrojKolona];

	// methods for airport

	public boolean postojiLiAerodrom() {

		for (int i = 0; i < Airport.kreiraniAerodromi.size(); i++) {
			if (Airport.kreiraniAerodromi.get(i).getImeAerodroma().equals(imeAerodroma)) {
				return true;
			}
		}

		return false;
	}

	// methods for arline

	public boolean postojiLiAvionskaLinija() {

		for (int i = 0; i < AirLine.kreiraneAvionsKeLinije.size(); i++) {
			if (AirLine.kreiraneAvionsKeLinije.get(i).getImeAvionskeLinije().equals(imeAvionskeLinije)) {
				return true;
			}
		}

		return false;
	}

	// getters

	public int getKolone() {
		return kolone;
	}

	public boolean[][] getSeatss() {
		return seatss;
	}

	public void setSeatss(int red, int kolona) {
		seatss[red][kolona] = false;

	}	
	
	public String getDestinacija() {
		return destinacija;
	}

	public boolean getValidacija() {
		return validacija;
	}

	@Override
	public String toString() {
		return "Flight [imeAerodroma=" + imeAerodroma + ", imeAvionskeLinije=" + imeAvionskeLinije + ", destinacija="
				+ destinacija + "]";
	}

}
