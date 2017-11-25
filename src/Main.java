import java.awt.EventQueue;

/**
 * 
 * @author Ismael Martín Ramírez
 *
 * https://github.com/museumis
 *
 */
public class Main {

	/**
	 * Metodo que inicia el programa
	 * @param args
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			/**
			 * Metodo para iniciar el hilo de la interfaz
			 */
			public void run() {
				try {
					Interfaz ventana = new Interfaz();
					ventana.iniciar();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}//Fin de run
		});//Fin Runnable del hilo
	}

}
