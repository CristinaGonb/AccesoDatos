package Ejercicio1Ex22En;

public class CuentaSuspenso {
	// private String nombreAlumno;
	private int notasSuspensas;

	public CuentaSuspenso(int notaSuspensa) {
		this.notasSuspensas = notaSuspensa;
	}

	public int getNotaSuspensa() {
		return notasSuspensas;
	}

	public void setNotaSuspensa(int notaSuspensa) {
		int suspendida = 0;

		if (notaSuspensa >= 0 || notaSuspensa < 5) {
			this.notasSuspensas = notaSuspensa;
			suspendida++;
		}
	}

	@Override
	public String toString() {
		return "CuentaSuspenso [notaSuspensa=" + notasSuspensas + "]";
	}

}
