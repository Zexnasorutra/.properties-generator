package es.arturo.bean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Row;

public class MacBean {
	private String mac;
	private String descripcion;

	public MacBean() {

	}

	/**
	 * Constructor a partir de una fila de un excel
	 * 
	 * @param row
	 *            objeto de la fila del excel
	 */
	public MacBean(Row row) throws Exception {
		if (row.getCell(0) != null) {
			this.mac = row.getCell(0).getStringCellValue().trim();
		}
		if (row.getCell(1) != null) {
			this.descripcion = row.getCell(1).getStringCellValue().trim();
		}
	}

	public boolean isMacValida() throws Exception {
		boolean valida = true;

		// La mac esta vacia
		if (this.mac == null || this.mac.trim().equals("")) {
			return false;
		}
		
		// Comprueba si tiene formato mac
		String macPattern = "^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$";
		Pattern p = Pattern.compile(macPattern);
		Matcher m = p.matcher(this.mac);
		if (!m.matches()) {
			throw new Exception("El formato de la mac "+ this.mac+" es erroneo\n");
		}
		

		// La descripcion es 'bloquear'
		if (this.descripcion != null && 
			this.descripcion.toLowerCase().trim().equals("bloquear")) {
			return false;
		}

		return valida;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "MacBean [mac=" + mac + ", descripcion=" + descripcion + "]";
	}

}
