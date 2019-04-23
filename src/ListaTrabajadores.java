import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

public class ListaTrabajadores implements Utilizable {
	
	List<Double> lista = new ArrayList<>();

	@Override
	public String muestraTodos() {
		return lista.stream().map(Objects::toString).collect(Collectors.joining(", "));
	}

	@Override
	public void leeDeFichero(File nombreFichero) {
		try {
			DataInputStream i = new DataInputStream(new FileInputStream(nombreFichero.getPath()));
			int byteRead;
			while ((byteRead = i.read()) != -1) {
				lista.add((double)byteRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void guardaEnFichero(File nombreFichero) {
		try (PrintWriter out = new PrintWriter(nombreFichero)) {
			out.print(lista.stream().map(Objects::toString).collect(Collectors.joining("\n")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void pideYAnyade() {
		lista.add(Double.parseDouble(JOptionPane.showInputDialog(null, "¿Número a añadir?")));

	}
}
