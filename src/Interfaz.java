import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Hashtable;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * 
 * @author Ismael Martín Ramírez
 *
 *         https://github.com/museumis
 *
 */
public class Interfaz {
	// Variables de la clase
	private final int WIDTH_LIENZO = 500;
	private final int HEIGHT_LIENZO = 500;
	// Componentes del frame
	private JFrame frame;
	private JPanel panelDibujo, panelNavegacion;
	private JButton btnPintar;
	private BufferedImage canvas;
	private JLabel lienzo;
	private Graphics grafico;
	private JButton btnReiniciar;
	// Variables Slider
	JPanel panelSlider;
	JSlider sliderTamanio;
	JSlider sliderPoX;
	JSlider sliderPoY;
	// Variables del grafico cuadrado
	private int tamanioGraficoCuadrado = 100;
	private int posXGraficoCuadrado = 0;
	private int posYGraficoCuadrado = 0;
	private Color[] colorGraficoCuadrado = { Color.BLACK, Color.BLUE, Color.GREEN, Color.GRAY, Color.RED };
	private int turnoColor = -1;

	/**
	 * Constructor de la clase
	 */
	public Interfaz() {
		frame = new JFrame("Slider");
		frame.setBounds(250, 0, 800, 760);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Iniciar interfaz
	 */
	public void iniciar() {
		iniciarComponentes();
		iniciarListened();
		frame.setVisible(true);
	}// Fin de iniciar

	/**
	 * Iniciar componentes
	 */
	public void iniciarComponentes() {
		// Variables
		GridBagConstraints data;
		// ***************
		// Frame
		// **************
		frame.setLayout(new GridBagLayout());
		// ***************
		// Panel Dibujo
		// **************
		panelDibujo = new JPanel();
		panelDibujo.setBackground(Color.yellow);
		panelDibujo.setLayout(new GridBagLayout());
		data = new GridBagConstraints();
		data.gridx = 0;
		data.gridy = 0;
		data.weightx = 1;
		data.weighty = 1;
		data.fill = GridBagConstraints.BOTH;
		frame.add(panelDibujo, data);
		// --SLIDER POSX--
		sliderPoX = new JSlider(JSlider.HORIZONTAL, 0, WIDTH_LIENZO, WIDTH_LIENZO/2);
		sliderPoX.setMajorTickSpacing(100);
		sliderPoX.setMinorTickSpacing(20);
		sliderPoX.setPaintTicks(true);
		sliderPoX.setPaintLabels(true);
		data = new GridBagConstraints();
		data.gridx = 1;
		data.gridy = 0;
		data.insets = new Insets(8, 0, 8, 0);
		data.fill = GridBagConstraints.HORIZONTAL;
		panelDibujo.add(sliderPoX, data);
		// --CANVAS--
		lienzo = new JLabel();
		// 1
		canvas = new BufferedImage(WIDTH_LIENZO, HEIGHT_LIENZO, BufferedImage.TYPE_INT_ARGB);
		// fondo canvas
		// 2
		grafico = canvas.getGraphics();
		grafico.setColor(Color.WHITE);
		grafico.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		grafico.dispose();
		// 3
		lienzo.repaint();
		// Cuadrado Inicial 100x100
		grafico = canvas.getGraphics();
		grafico.setColor(colorGraficoCuadrado[obtenerColorNoRepetido()]);
		// Posicion del cuadrado
		posXGraficoCuadrado = (canvas.getWidth() / 2) - (50);
		posYGraficoCuadrado = (canvas.getHeight() / 2) - (50);
		grafico.fillRect(posXGraficoCuadrado, posXGraficoCuadrado, 100, 100);
		grafico.dispose();
		lienzo.repaint();
		// 4
		// Pintar lienzo
		lienzo.setIcon(new ImageIcon(canvas));
		data = new GridBagConstraints();
		data.gridx = 1;
		data.gridy = 1;
		data.fill = GridBagConstraints.BOTH;
		panelDibujo.add(lienzo, data);
		// --SLIDER POSY--
		sliderPoY = new JSlider(JSlider.VERTICAL, 0, HEIGHT_LIENZO, HEIGHT_LIENZO/2);
		sliderPoY.setMajorTickSpacing(100);
		sliderPoY.setMinorTickSpacing(20);
		sliderPoY.setPaintTicks(true);
		sliderPoY.setPaintLabels(true);
		data = new GridBagConstraints();
		data.gridx = 0;
		data.gridy = 1;
		data.insets = new Insets(0, 8, 0, 8);
		data.fill = GridBagConstraints.VERTICAL;
		panelDibujo.add(sliderPoY, data);

		// ***************
		// Panel Navegación
		// **************
		panelNavegacion = new JPanel();
		panelNavegacion.setBackground(Color.blue);
		panelNavegacion.setLayout(new GridBagLayout());
		panelNavegacion
				.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red, 2), "Controles"));
		data = new GridBagConstraints();
		data.gridx = 0;
		data.gridy = 1;
		data.ipady = 10;
		data.fill = GridBagConstraints.BOTH;
		frame.add(panelNavegacion, data);
		// --BOTON Reiniciar--
		btnReiniciar = new JButton("Reiniciar");
		data = new GridBagConstraints();
		data.gridx = 2;
		data.gridy = 0;
		panelNavegacion.add(btnReiniciar, data);
		// --BOTON PINTAR--
		btnPintar = new JButton("Pintar");
		data = new GridBagConstraints();
		data.gridx = 0;
		data.gridy = 0;
		panelNavegacion.add(btnPintar, data);
		// --SLIDER--
		sliderTamanio = new JSlider(JSlider.HORIZONTAL, 0, 300, 150);
		sliderTamanio.setMajorTickSpacing(100);
		sliderTamanio.setMinorTickSpacing(20);
		sliderTamanio.setPaintTicks(true);
		sliderTamanio.setPaintLabels(true);
		data = new GridBagConstraints();
		data.gridx = 1;
		data.gridy = 0;
		data.insets = new Insets(10, 10, 10, 10);
		panelNavegacion.add(sliderTamanio, data);

	}// Fin de iniciar componentes

	/**
	 * Iniciar listened
	 */
	public void iniciarListened() {
		// Bonton de pintar
		btnPintar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Grafico
				grafico = canvas.getGraphics();
				grafico.setColor(colorGraficoCuadrado[obtenerColorNoRepetido()]);
				grafico.fillRect(posXGraficoCuadrado - (tamanioGraficoCuadrado / 2),
						posYGraficoCuadrado - (tamanioGraficoCuadrado / 2), tamanioGraficoCuadrado,
						tamanioGraficoCuadrado);
				grafico.dispose();
				lienzo.repaint();
			}
		});// Fin del listened boton de pintar
			// Slider Tamanio
		sliderTamanio.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				tamanioGraficoCuadrado = sliderTamanio.getValue();
			}
		});// Fin del listened slider tamanio
			// Slider posicion X
		sliderPoX.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				posXGraficoCuadrado = sliderPoX.getValue();
			}
		});// Fin del listened slider posX
			// Slider posicion Y
		sliderPoY.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				// Si slider da 500 se tiene que dibujar en 0
				// Si slider da 0 se tiene que dibujar en 500
				// Si slider da 250 se tiene que dibujar en 250
				// !!!!
				posYGraficoCuadrado = (HEIGHT_LIENZO - sliderPoY.getValue());
			}
		});// Fin del listened slider posY
			// Boton Reiniciar
		btnReiniciar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				grafico = canvas.getGraphics();
				grafico.setColor(Color.WHITE);
				grafico.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
				grafico.dispose();
				lienzo.repaint();
				sliderPoX.setValue(WIDTH_LIENZO / 2);
				sliderPoY.setValue(HEIGHT_LIENZO / 2);

			}
		});// Fin del listened del boton de reiniciar
	}// Fin de iniciar listened

	/**
	 * Metodo para obtener el siguiente color del array de colores
	 * 
	 * @return
	 */
	public int obtenerColorNoRepetido() {
		turnoColor++;
		if (turnoColor > colorGraficoCuadrado.length - 1) {
			turnoColor = 0;
		}
		return turnoColor;
	}
}