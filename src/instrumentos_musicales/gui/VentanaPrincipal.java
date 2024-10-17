/*
 * Omar Jiménez Hernández
 * Paradigmas de programación I
 */

package instrumentos_musicales.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class VentanaPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	private JMenu archivoMenu;
	private JMenu operacionesMenu;
	private JMenu ayudaMenu;
	private JMenuItem abrirMenu;
	private JMenuItem guardarMenu;
	private JMenuItem salirMenu;
	private JMenuItem catalogoMenu;
	private JMenuItem consultasMenu;
	private JMenuItem acercaDeMenu;
	private JMenuBar barraMenu;

	public VentanaPrincipal() {
		super("Catálogo de Instrumentos");
		this.setIconImage(
				Toolkit.getDefaultToolkit().getImage(getClass().getResource("/instrumentos_musicales/imagenes/logo.jpg")));

		// Crear el menú
		barraMenu = new JMenuBar();

		// Archivo
		archivoMenu = new JMenu("Archivo");
		archivoMenu.setIcon(new ImageIcon(getClass().getResource("/instrumentos_musicales/imagenes/archivo.png")));
		archivoMenu.setMnemonic(KeyEvent.VK_A);
		archivoMenu.setToolTipText("Despliega opciones de archivo");

		// Abrir
		abrirMenu = new JMenuItem("Abrir");
		abrirMenu.setIcon(new ImageIcon(getClass().getResource("/instrumentos_musicales/imagenes/abrir.png")));
		abrirMenu.setMnemonic(KeyEvent.VK_A);
		abrirMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				InputEvent.ALT_DOWN_MASK + InputEvent.CTRL_DOWN_MASK));
		abrirMenu.setToolTipText("Abre el archivo a utilizar");
		abrirMenu.addActionListener(new ManejoEventos());

		// Guardar
		guardarMenu = new JMenuItem("Guardar");
		guardarMenu.setIcon(new ImageIcon(getClass().getResource("/instrumentos_musicales/imagenes/guardar.png")));
		guardarMenu.setMnemonic(KeyEvent.VK_G);
		guardarMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK));
		guardarMenu.setToolTipText("Guarda el archivo");
		guardarMenu.addActionListener(new ManejoEventos());

		// Salir
		salirMenu = new JMenuItem("Salir");
		salirMenu.setIcon(new ImageIcon(getClass().getResource("/instrumentos_musicales/imagenes/salir.png")));
		salirMenu.setMnemonic(KeyEvent.VK_S);
		salirMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
		salirMenu.setToolTipText("Cierra el catálogo");
		salirMenu.addActionListener(new ManejoEventos());

		// Añadir items al menú Archivo
		archivoMenu.add(abrirMenu);
		archivoMenu.add(guardarMenu);
		archivoMenu.addSeparator();
		archivoMenu.add(salirMenu);

		// Operaciones
		operacionesMenu = new JMenu("Operaciones");
		operacionesMenu.setIcon(new ImageIcon(getClass().getResource("/instrumentos_musicales/imagenes/operaciones.png")));
		operacionesMenu.setMnemonic(KeyEvent.VK_O);
		operacionesMenu.setToolTipText("Despliega opciones de operaciones");

		// Catálogo
		catalogoMenu = new JMenuItem("Catálogo");
		catalogoMenu.setIcon(new ImageIcon(getClass().getResource("/instrumentos_musicales/imagenes/catalogo.png")));
		catalogoMenu.setMnemonic(KeyEvent.VK_C);
		catalogoMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
		catalogoMenu.setToolTipText("Abre el catálogo para ver opciones");
		catalogoMenu.addActionListener(new ManejoEventos());

		// Consultas
		consultasMenu = new JMenuItem("Consultas");
		consultasMenu.setIcon(new ImageIcon(getClass().getResource("/instrumentos_musicales/imagenes/consultar.png")));
		consultasMenu.setMnemonic(KeyEvent.VK_U);
		consultasMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_DOWN_MASK));
		consultasMenu.setToolTipText("Consulta instrumentos");
		consultasMenu.addActionListener(new ManejoEventos());

		// Añadir items al menú Operaciones
		operacionesMenu.add(catalogoMenu);
		operacionesMenu.addSeparator();
		operacionesMenu.add(consultasMenu);

		// Ayuda
		ayudaMenu = new JMenu("Ayuda");
		ayudaMenu.setIcon(new ImageIcon(getClass().getResource("/instrumentos_musicales/imagenes/ayuda.png")));
		ayudaMenu.setMnemonic(KeyEvent.VK_Y);
		ayudaMenu.setToolTipText("Despliega opciones de ayuda");

		// Acerca de
		acercaDeMenu = new JMenuItem("Acerca de...");
		acercaDeMenu.setIcon(new ImageIcon(getClass().getResource("/instrumentos_musicales/imagenes/acercaDe.png")));
		acercaDeMenu.setMnemonic(KeyEvent.VK_C);
		acercaDeMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		acercaDeMenu.setToolTipText("Muestra los créditos del sistema");
		acercaDeMenu.addActionListener(new ManejoEventos());

		// Añadir items al menú Ayuda
		ayudaMenu.add(acercaDeMenu);

		// Agregar menús a la barra de menú
		barraMenu.add(archivoMenu);
		barraMenu.add(operacionesMenu);
		barraMenu.add(ayudaMenu);

		// Configurar barra de menú
		this.setJMenuBar(barraMenu);

		// Tamaño de la ventana
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,
				Toolkit.getDefaultToolkit().getScreenSize().height);

		// Configuración del contenido de la ventana
		this.getContentPane().setLayout(new FlowLayout());
		JLabel fondo = new JLabel();
		ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/instrumentos_musicales/imagenes/fondo.jpg"));
		Image imagenEscalada = imagenFondo.getImage().getScaledInstance(-1, getSize().height - 80, Image.SCALE_SMOOTH);
		fondo.setIcon(new ImageIcon(imagenEscalada));
		this.getContentPane().add(fondo);
		this.getContentPane().setBackground(Color.WHITE);

		// Configuraciones adicionales de la ventana
		this.setLocationRelativeTo(null);// centrar la pantalla
		this.setResizable(false);
		
		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		// Implementar WindowListener usando clase anónima
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				salir();
			}
		});

		// Hacer visible la ventana
		this.setVisible(true);
	}

	// Clase interna
	private class ManejoEventos implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(abrirMenu)) {
				abrir();
			} else if (e.getSource().equals(guardarMenu)) {
				guardar();
			} else if (e.getSource().equals(salirMenu)) {
				salir();
			} else if (e.getSource().equals(catalogoMenu)) {
				catalogo();
			} else if (e.getSource().equals(consultasMenu)) {
				consultas();
			} else if (e.getSource().equals(acercaDeMenu)) {
				acercaDe();
			}
		}
	}

	// Método salir
	private void salir() {
		System.exit(0);
	}
	// Método guardar
	private void guardar() {

	}

	// Método abrir
	private void abrir() {

	}

	// Método catalogo
	private void catalogo() {
		new DialogoInstrumentos(this);
	}

	// Método consultas
	private void consultas() {

	}

	// Método acerca de...
	private void acercaDe() {
		JOptionPane.showMessageDialog(this,
				"Catálogo de Instrumentos" + "\n\n" + "Realizado por:" + "\nOmar Jiménez Hernández" + "\n\n"
						+ "Derechos reservados UMAR " + '\u00A9' + " 2024",
				"Acerca de... Catálogo de Instrumentos", JOptionPane.INFORMATION_MESSAGE,
				new ImageIcon(getClass().getResource("/instrumentos_musicales/imagenes/logo.jpg")));
	}

}
