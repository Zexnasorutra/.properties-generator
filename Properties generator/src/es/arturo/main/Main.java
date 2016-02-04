package es.arturo.main;

import java.util.ArrayList;
import java.util.List;

import es.arturo.bean.MacBean;
import es.arturo.util.FileUtil;
import es.arturo.util.LectorUtil;

public class Main {
	public static final String LINEAPRINCIPAL = "config.system_cfg.1=ebtables.1.cmd=-A FORWARD -i ath1 -j DROP";
	public static final String LINEAMAC1 = "config.system_cfg.";
	public static final String LINEAMAC2 = "=ebtables.";
	public static final String LINEAMAC3 = ".cmd=-I FORWARD -i ath1 -s ";

	public static final String RUTADESTINO = "config.properties";
	public static final String RUTAERROR = "error.info";

	public static void main(String args[]) {
		StringBuffer errores = new StringBuffer();

		// BUSCA EL NOMBRE DEL FICHERO
		String ficheroInput = null;
		ficheroInput = FileUtil.buscarFichero();
		// Si no ha seleccionado ningun fichero sale de la aplicacion
		if (ficheroInput == null || ficheroInput.trim().equals("")) {
			return;
		}

		// LEE EL FICHERO DE ENTRADA
		List<MacBean> macs = new ArrayList<MacBean>();
		try {
			macs = LectorUtil.readXLSX(ficheroInput);
		} catch (Exception e) {
			errores.append("Error al leer el fichero de entrada " + e + "\n");
			e.printStackTrace();
		}

		// GENERA EL TEXTO PARA EL FICHERO
		StringBuffer textoFichero = new StringBuffer();
		textoFichero.append(LINEAPRINCIPAL + "\n");
		int line = 2;
		for (MacBean mac : macs) {
			try {
				if (mac.isMacValida()) {
					textoFichero.append(LINEAMAC1+line+LINEAMAC2+line+LINEAMAC3 + mac.getMac() + "\n");
					line++;
				}
			} catch (Exception e) {
				errores.append(e.getMessage());
			}
		}

		// GUARDA EL FICHERO
		try {
			FileUtil.escribirFichero(RUTADESTINO, textoFichero.toString());
		} catch (Exception e) {
			errores.append("Error al guardar el fichero de salida " + e + "\n");
			e.printStackTrace();
		}

		// GUARDA EL FICHERO DE ERROR SI LO HAY
		try {
			if (!errores.toString().equals("")) {
				FileUtil.escribirFichero(RUTAERROR, errores.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.exit(0);

	}

}
