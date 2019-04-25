import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaTrabajadores implements Utilizable {
	private String nombre;
	private double sueldo;
    List<String> lista = new ArrayList<>();

    public String muestraTodos() {
    	int cont = 0;
    	String salida = "";
    	for (String s : lista ) {
    		salida += s;
    // salida += (cont++ % 2 == 0 ? ", " : "\n"); 
    		if(cont % 2 == 0) {
    			salida += ", ";
    		} else {
    			salida += "\n";
    		}
    		cont++;
    	}
        return salida;
    }

    public void leeDeFichero(File fichero) {
        try {
        	DataInputStream i = new DataInputStream(new FileInputStream(fichero.getPath()));
        	String[] inputRead = i.readUTF().split("\n");
        	for (String s : inputRead) {
        		lista.add(s);
        	}
            i.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void guardaEnFichero(File fichero) {
        try {
			DataOutputStream o = new DataOutputStream(new FileOutputStream(fichero.getPath()));
			o.writeUTF(lista.stream().collect(Collectors.joining("\n")));
			o.close();
        } catch (FileNotFoundException nfe) {
            nfe.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pideYAnyade() {
    	try {
			nombre = JOptionPane.showInputDialog(null, "¿Nombre?");
			sueldo = Double.parseDouble(JOptionPane.showInputDialog(null, "¿Sueldo?"));
			lista.add(nombre);
			lista.add(Double.toString(sueldo));
    	} catch (NumberFormatException nfe) {
    		nfe.printStackTrace();
    	}
    }
}
