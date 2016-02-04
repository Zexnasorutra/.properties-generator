package es.arturo.util;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileUtil {
	/**
	 * Guarda el fichero con el texto que quieres
	 * 
	 * @param ruta Ruta para guardar el fichero
	 * @param texto Texto para escribir
	 */
	public static void escribirFichero(String ruta, String texto) throws Exception {
		FileOutputStream archivo = null;
		OutputStreamWriter output = null;
		try {
			archivo = new FileOutputStream(ruta);
			output = new OutputStreamWriter(archivo);
			output.write(texto);
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Abre una ventana para buscar el fichero
	 * @return Ruta del fichero seleccionado
	 */
	public static String buscarFichero() {
		JDialog j = new JDialog();
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Ficheros XLSX", "xlsx");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(j);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile().toString();
		}

		return "";
	}

}
