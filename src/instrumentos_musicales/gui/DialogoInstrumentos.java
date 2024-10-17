/*
 * Omar Jiménez Hernández
 * Paradigmas de programación I
 * Práctica 1
 */
package instrumentos_musicales.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;

import instrumentos_musicales.dominio.InstrumentoMusical;
// import instrumentos_musicales.dominio.InstrumentoMusical;
import instrumentos_musicales.utilerias.MiFocusTraversalPolicy;

public class DialogoInstrumentos extends JDialog implements ItemListener {

	private static final long serialVersionUID = 1L;
	private VentanaPrincipal ventanaPrincipal;

	// BOTONES PARA EL MENÚ INTERNO
	private JMenu operacionesMenu;
	private JMenuItem nuevoMenu;
	private JMenuItem modificarMenu;
	private JMenuItem guardarMenu;
	private JMenuItem eliminarMenu;
	private JMenuItem cancelarMenu;
	private JMenuBar barraMenu;

	// BOTONES EN LA PANTALLA
	private JButton nuevoBoton;
	private JButton modificarBoton;
	private JButton guardarBoton;
	private JButton eliminarBoton;
	private JButton cancelarBoton;

	// ELEMENTOS DE MI ENTIDAD

	// ENTIDAD
	private JLabel entidadEtiqueta;
	private JComboBox<InstrumentoMusical> entidad;

	// Año - Número
	private JLabel añoEtiqueta;
	private JTextField año;

	// Precio - Número con rango
	private JLabel precioEtiqueta;
	private JTextField precio;

	// Nombre - Texto con formato libre
	private JLabel nombreEtiqueta;
	private JTextField nombre;

	// Número de serie - Texto con formato predefinido
	private JLabel numeroSerieEtiqueta;
	private JTextField numeroSerie;

	// Fecha - Fecha(JTextField temporal)
	private JLabel fechaEtiqueta;
	private JTextField fecha;

	// Condición - Dato obtenido de opciones mutuamente excluyentes fijas
	private JLabel condicionEtiqueta;
	private JRadioButton usado;
	private JRadioButton nuevo;
	private JRadioButton reparado;

	// Marca - Dato obtenido de opciones mutuamente excluyentes dinámicas
	private JLabel marcaEtiqueta;
	private JComboBox<String> marca; // (editable)

	// Tipo de Materiales - Dato multivalorado de opciones no excluyentes fijas
	private JLabel tiposMaterialesEtiqueta; // Etiqueta para múltiples tipos
	private JList<String> tiposMateriales; // Lista para los tipos de materiales
	private JScrollPane scrollMateriales; // Scroll para la lista
	private DefaultListModel<String> modeloMateriales; // Modelo para la lista

	// Géneros Musicales - Dato multivalorado de opciones no excluyentes dinámicas.
	private JLabel generosMusicalesEtiqueta; // Etiqueta para múltiples géneros
	private JList<String> generosMusicales; // Lista que contendrá varios géneros musicales
	private JScrollPane scrollGenerosMusicales; // Scroll pane para la lista de géneros
	private DefaultListModel<String> modeloGeneros;

	private JButton adjuntar;
	private JButton quitar;

	private JComboBox<String> comboAdjuntar;

	// Imagén
	private JLabel imagenEtiqueta;
	private JTextField imagen;
	private JButton seleccionar;

	// Acciones
	private Action nuevoE;
	private Action modificar;
	private Action adjuntarAction;
	private Action quitarAction;
	private Action seleccionarAction;
	private Action guardar;
	private Action eliminar;
	private Action cancelar;

	// CONSTRUCTOR
	public DialogoInstrumentos(JFrame principal) {
		super(principal, "Catálogo de instrumentos", true);
		ventanaPrincipal = (VentanaPrincipal) principal;
		crearAcciones();
		inicializarComponentes();
		establecerPoliticaFoco();
		inicializar();
		crearPantalla();

	}

	// Método Inicializar componentes
	private void inicializarComponentes() {
		// PRIMERO LOS MENÚS
		operacionesMenu = new JMenu("Operaciones");
		operacionesMenu
				.setIcon(new ImageIcon(getClass().getResource("/instrumentos_musicales/imagenes/operaciones.png")));
		operacionesMenu.setMnemonic(KeyEvent.VK_O);
		// nuevo
		nuevoMenu = new JMenuItem(nuevoE);

		// Modificar
		modificarMenu = new JMenuItem(modificar);

		// guardar
		guardarMenu = new JMenuItem(guardar);

		// eliminar
		eliminarMenu = new JMenuItem(eliminar);

		// cancelar
		cancelarMenu = new JMenuItem(cancelar);

		// AHORA LOS BOTONES
		nuevoBoton = new JButton(nuevoE);
		nuevoBoton.getActionMap().put("botonNuevo", nuevoE);
		nuevoBoton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) nuevoE.getValue(Action.ACCELERATOR_KEY), "botonNuevo");

		modificarBoton = new JButton(modificar);
		modificarBoton.getActionMap().put("botonModifiar", modificar);
		modificarBoton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) modificar.getValue(Action.ACCELERATOR_KEY), "botonModifiar");

		guardarBoton = new JButton(guardar);
		guardarBoton.getActionMap().put("botonGuardar", guardar);
		guardarBoton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) guardar.getValue(Action.ACCELERATOR_KEY), "botonGuardar");

		eliminarBoton = new JButton(eliminar);
		eliminarBoton.getActionMap().put("botonEliminar", eliminar);
		eliminarBoton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) eliminar.getValue(Action.ACCELERATOR_KEY), "botonEliminar");

		cancelarBoton = new JButton(cancelar);
		cancelarBoton.getActionMap().put("botonCancelar", cancelar);
		cancelarBoton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) cancelar.getValue(Action.ACCELERATOR_KEY), "botonCancelar");

		// Crear el menú
		barraMenu = new JMenuBar();

		// Añadir a operaciones
		operacionesMenu.add(nuevoMenu);
		operacionesMenu.add(modificarMenu);
		operacionesMenu.add(guardarMenu);
		operacionesMenu.add(eliminarMenu);
		operacionesMenu.add(cancelarMenu);

		// ALÑADIR A BARRA MENÚ
		barraMenu.add(operacionesMenu);

		// CONFIGURAR BARRA MENÚ
		this.setJMenuBar(barraMenu);

		// AHORA LOS COMPONENTES, DESPUES METEMOS LAS ACCIONES DE LOS BOTONES

		// ENTIDAD
		entidadEtiqueta = new JLabel("Instrumentos:");
		entidadEtiqueta.setDisplayedMnemonic(KeyEvent.VK_T);

		entidad = new JComboBox<InstrumentoMusical>();
		entidad.setPreferredSize(new Dimension(260, 20));
		entidad.setToolTipText("Seleccione su entidad");
		entidad.addItemListener(this);//// evento

		// Año - Número
		añoEtiqueta = new JLabel("Año:");
		año = new JTextField(20);
		añoEtiqueta.setLabelFor(año);
		añoEtiqueta.setDisplayedMnemonic(KeyEvent.VK_A);
		año.setToolTipText("Año de fabricación del intrumento");

		// Precio - Número con rango
		precioEtiqueta = new JLabel("Precio:");
		precio = new JTextField(20);
		precioEtiqueta.setLabelFor(precio);
		precioEtiqueta.setDisplayedMnemonic(KeyEvent.VK_P);
		precio.setToolTipText("Ingrese el precio de su instrumento");

		// Nombre - Texto con formato libre
		nombreEtiqueta = new JLabel("Nombre:");
		nombre = new JTextField(20); // Longitud de 20 caracteres
		nombreEtiqueta.setDisplayedMnemonic(KeyEvent.VK_B);
		nombreEtiqueta.setLabelFor(nombre);
		nombre.setToolTipText("Ingrese el nombre del instrumento");

		// Número de serie - Texto con formato predefinido
		numeroSerieEtiqueta = new JLabel("Número de serie:");
		numeroSerie = new JTextField(20);
		numeroSerieEtiqueta.setDisplayedMnemonic(KeyEvent.VK_S);
		numeroSerieEtiqueta.setLabelFor(numeroSerie);
		numeroSerie.setToolTipText("El número de serie debe ser 1 mayuscula y 5 números");

		// Fecha - Fecha(JTextField temporal)
		fechaEtiqueta = new JLabel("Fecha:");
		fecha = new JTextField(20); // Longitud de 10 caracteres
		fechaEtiqueta.setDisplayedMnemonic(KeyEvent.VK_F);
		fechaEtiqueta.setLabelFor(fecha);
		fecha.setToolTipText("Ingrese la fecha actual");

		// Condición - Dato obtenido de opciones mutuamente excluyentes fijas
		condicionEtiqueta = new JLabel("Condición:");
		condicionEtiqueta.setToolTipText("Seleccione la condición del instrumento");
		usado = new JRadioButton("Usado");
		nuevo = new JRadioButton("Nuevo");
		reparado = new JRadioButton("Reparado");
		condicionEtiqueta.setDisplayedMnemonic(KeyEvent.VK_I);
		condicionEtiqueta.setLabelFor(usado); // Vincular con el primer radio button
		// usado.setMnemonic(KeyEvent.VK_U);
		usado.setToolTipText("Seleccione si el instrumento es usado");
		usado.setSelected(true);
		// nuevo.setMnemonic(KeyEvent.VK_V);
		nuevo.setToolTipText("Seleccione si el instrumento es nuevo");
		// reparado.setMnemonic(KeyEvent.VK_D);
		reparado.setToolTipText("Seleccione si el instrumento es reparado");

		// Grupo de botones de radio
		ButtonGroup grupoCondicion = new ButtonGroup();
		grupoCondicion.add(usado);
		grupoCondicion.add(nuevo);
		grupoCondicion.add(reparado);

		// Marca - Dato obtenido de opciones mutuamente excluyentes dinámicas
		marcaEtiqueta = new JLabel("Marca:");
		marca = new JComboBox<>();
		marca.setPreferredSize(new Dimension(220, 20));
		marcaEtiqueta.setDisplayedMnemonic(KeyEvent.VK_R);
		marcaEtiqueta.setLabelFor(marca);
		marca.setToolTipText("Seleccione la marca del instrumento");
		// Hacer el JComboBox editable
		marca.setEditable(true);

		// Tipo de Materiales - Dato multivalorado de opciones no excluyentes fijas
		tiposMaterialesEtiqueta = new JLabel("Tipos de materiales:");
		modeloMateriales = new DefaultListModel<>(); // Modelo para la lista
		tiposMateriales = new JList<>(modeloMateriales); // Inicializa la lista con el modelo
		scrollMateriales = new JScrollPane(tiposMateriales); // Asocia la lista con el JScrollPane
		scrollMateriales.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Muestra siempre la barra
		tiposMaterialesEtiqueta.setDisplayedMnemonic(KeyEvent.VK_M);
		tiposMaterialesEtiqueta.setLabelFor(tiposMateriales);
		tiposMateriales.setToolTipText("Seleccione el material de su instrumento"); ///
		scrollMateriales.setPreferredSize(new Dimension(260, 70));

		// Habilitar selección múltiple
		tiposMateriales.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); // Permitir selección múltiple

		// Géneros Musicales - Dato multivalorado de opciones no excluyentes dinámicas.
		generosMusicalesEtiqueta = new JLabel("Géneros musicales:");
		modeloGeneros = new DefaultListModel<>(); /// modelo para la lista
		generosMusicales = new JList<>(modeloGeneros); // Inicializa con el modelo
		scrollGenerosMusicales = new JScrollPane(generosMusicales); // Asocia la lista con el JScrollPane
		scrollGenerosMusicales.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		generosMusicalesEtiqueta.setDisplayedMnemonic(KeyEvent.VK_N);
		generosMusicalesEtiqueta.setLabelFor(generosMusicales);
		generosMusicales.setToolTipText("Seleccione uno o más géneros musicales."); ///
		scrollGenerosMusicales.setPreferredSize(new Dimension(130, 70));

		// combo adjuntar
		comboAdjuntar = new JComboBox<>();
		comboAdjuntar.setPreferredSize(new Dimension(130, 30));
		comboAdjuntar.setEditable(true); // editable
		comboAdjuntar.setToolTipText("Coloque el instrumento a adjuntar");
		// Botones para agregar y quitar
		adjuntar = new JButton(adjuntarAction);
		adjuntar.setMnemonic(KeyEvent.VK_J);
		// agregar.setToolTipText("Agregar un nuevo género musical");
		adjuntar.setIcon(new ImageIcon(getClass().getResource("/instrumentos_musicales/imagenes/agregar.png")));
		adjuntar.setPreferredSize(new Dimension(115, 30));

		quitar = new JButton(quitarAction);
		quitar.setMnemonic(KeyEvent.VK_Q); // Agregar un mnemotécnico
		// quitar.setToolTipText("Quitar el género musical seleccionado"); // Tooltip
		// para el botón "Quitar"
		quitar.setIcon(new ImageIcon(getClass().getResource("/instrumentos_musicales/imagenes/quitar.png")));
		quitar.setPreferredSize(new Dimension(115, 30));

		// Imagen
		imagenEtiqueta = new JLabel("Imagen:");
		imagenEtiqueta.setLabelFor(imagen);
		imagen = new JTextField("");
		imagen.setPreferredSize(new Dimension(200, 77));
		imagen.setEditable(false);
		imagen.setToolTipText("Imagen referente a su instrumento");

		seleccionar = new JButton(seleccionarAction);
		imagenEtiqueta.setDisplayedMnemonic(KeyEvent.VK_G);
		imagenEtiqueta.setLabelFor(imagen);
		seleccionar.setMnemonic(KeyEvent.VK_L);
		// seleccionar.setToolTipText("Seleciona una imagen para mostrar");

		// CREACION DE PANELES

		// PANEL NORTE
		JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelNorte.add(entidadEtiqueta);
		panelNorte.add(entidad);

		// PANEL ESTE
		JPanel panelEste = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		JPanel panel1 = new JPanel(new GridLayout(9, 1));
		panel1.add(nuevoBoton);
		panel1.add(new JPanel());
		panel1.add(modificarBoton);
		panel1.add(new JPanel());
		panel1.add(guardarBoton);
		panel1.add(new JPanel());
		panel1.add(eliminarBoton);
		panel1.add(new JPanel());
		panel1.add(cancelarBoton);
		panelEste.add(panel1);

		// PANEL CENTRO 1ra Parte
		JPanel panelCentro1 = new JPanel(new GridLayout(6, 2));

		JPanel lateral1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		lateral1.add(nombreEtiqueta);
		JPanel lateral2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		lateral2.add(nombre);
		JPanel lateral3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		lateral3.add(marcaEtiqueta);
		JPanel lateral4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		lateral4.add(marca);
		JPanel lateral5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		lateral5.add(condicionEtiqueta);
		JPanel lateral6 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		lateral6.add(usado);
		lateral6.add(nuevo);
		lateral6.add(reparado);
		JPanel lateral7 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		lateral7.add(añoEtiqueta);
		JPanel lateral8 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		lateral8.add(año);
		JPanel lateral9 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		lateral9.add(fechaEtiqueta);
		JPanel lateral10 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		lateral10.add(fecha);
		JPanel lateral11 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		lateral11.add(numeroSerieEtiqueta);
		JPanel lateral12 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		lateral12.add(numeroSerie);

		// PANEL CENTRO 2da Parte
		JPanel panelCentro2 = new JPanel(new GridLayout(6, 2));

		JPanel lateralD0 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		lateralD0.add(tiposMaterialesEtiqueta);
		JPanel lateralD1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		lateralD1.add(scrollMateriales);
		JPanel lateralD2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		lateralD2.add(generosMusicalesEtiqueta);

		JPanel lateralD3 = new JPanel(new GridLayout(1, 2));
		JPanel auxL = new JPanel(new FlowLayout(FlowLayout.LEFT));
		auxL.add(comboAdjuntar);
		JPanel auxL1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		auxL1.add(adjuntar);
		lateralD3.add(auxL);
		lateralD3.add(auxL1);

		JPanel lateralD4 = new JPanel();
		lateralD4.add(new JPanel());

		JPanel lateralD5 = new JPanel(new GridLayout(1, 2));
		JPanel aux1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		aux1.add(scrollGenerosMusicales);
		JPanel aux2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		aux2.add(quitar);
		lateralD5.add(aux1);
		lateralD5.add(aux2);

		JPanel lateralD6 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		lateralD6.add(imagenEtiqueta);
		JPanel lateralD7 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		lateralD7.add(imagen);
		JPanel lateralD8 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		lateralD8.add(new JPanel());
		JPanel lateralD9 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		lateralD9.add(seleccionar);
		JPanel lateralD10 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		lateralD10.add(precioEtiqueta);
		JPanel lateralD11 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		lateralD11.add(precio);

		// AÑADIR AL PANEL CENTRO 1
		panelCentro1.add(lateral1);
		panelCentro1.add(lateral2);
		panelCentro1.add(lateral3);
		panelCentro1.add(lateral4);
		panelCentro1.add(lateral5);
		panelCentro1.add(lateral6);
		panelCentro1.add(lateral7);
		panelCentro1.add(lateral8);
		panelCentro1.add(lateral9);
		panelCentro1.add(lateral10);
		panelCentro1.add(lateral11);
		panelCentro1.add(lateral12);

		// AÑADIR AL PANEL CENTRO 2
		panelCentro2.add(lateralD0);
		panelCentro2.add(lateralD1);
		panelCentro2.add(lateralD2);
		panelCentro2.add(lateralD3);
		panelCentro2.add(lateralD4);
		panelCentro2.add(lateralD5);
		panelCentro2.add(lateralD6);
		panelCentro2.add(lateralD7);
		panelCentro2.add(lateralD8);
		panelCentro2.add(lateralD9);
		panelCentro2.add(lateralD10);
		panelCentro2.add(lateralD11);

		// AÑADIR AL PANEL CENTRO
		JPanel panelCentro = new JPanel(new GridLayout(1, 2));
		panelCentro.add(panelCentro1);
		panelCentro.add(panelCentro2);

		// PANEL PRINCIPAL
		JPanel panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.add(panelNorte, BorderLayout.NORTH);
		panelPrincipal.add(panelEste, BorderLayout.EAST);
		panelPrincipal.add(panelCentro, BorderLayout.CENTER);

		// AGREGAR AL FRAME PRINCIPAL
		this.add(panelPrincipal);
	}

	private void crearPantalla() {

		this.setSize(1200, 600);
		this.setLocationRelativeTo(ventanaPrincipal); // Centrar respecto a ventana
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(getClass().getResource("/instrumentos_musicales/imagenes/logo.jpg")));

		// principal
		this.setResizable(false); // No redimensionable
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // El diálogo se destruirá y liberará sus recursos
		// cuando se cierre.
		this.setModal(true); // Hacer el diálogo modal
		this.setVisible(true);
	}

	private void crearAcciones() {
		// ACCIONES

		nuevoE = new AbstractAction("Nuevo",
				new ImageIcon(getClass().getResource("/instrumentos_musicales/imagenes/nuevo.png"))) {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				nuevo();
			}
		};
		nuevoE.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		nuevoE.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_N);
		nuevoE.putValue(Action.SHORT_DESCRIPTION, "Nuevo Instrumento");

		modificar = new AbstractAction("Modificar",
				new ImageIcon(getClass().getResource("/instrumentos_musicales/imagenes/modificar.png"))) {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				modificar();
			}
		};

		modificar.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK));
		modificar.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_M);
		modificar.putValue(Action.SHORT_DESCRIPTION, "Modificar Instrumento");

		guardar = new AbstractAction("Guardar",
				new ImageIcon(getClass().getResource("/instrumentos_musicales/imagenes/guardar.png"))) {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		};
		guardar.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK));
		guardar.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_G);
		guardar.putValue(Action.SHORT_DESCRIPTION, "Guardar Cambios");

		eliminar = new AbstractAction("Eliminar",
				new ImageIcon(getClass().getResource("/instrumentos_musicales/imagenes/eliminar.png"))) {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				eliminar();
			}
		};
		eliminar.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_E);
		eliminar.putValue(Action.SHORT_DESCRIPTION, "Eliminar Instrumento");

		cancelar = new AbstractAction("Cancelar",
				new ImageIcon(getClass().getResource("/instrumentos_musicales/imagenes/cancelar.png"))) {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				cancelar();
			}
		};
		cancelar.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_C);
		cancelar.putValue(Action.SHORT_DESCRIPTION, "Cancelar Operación");

		adjuntarAction = new AbstractAction("Adjuntar",
				new ImageIcon(getClass().getResource("/instrumentos_musicales/imagenes/agregar.png"))) {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				adjuntar();
			}
		};
		adjuntarAction.putValue(Action.SHORT_DESCRIPTION, "Adjuntar un nuevo género musical");

		quitarAction = new AbstractAction("Quitar",
				new ImageIcon(getClass().getResource("/instrumentos_musicales/imagenes/quitar.png"))) {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				quitar();
			}
		};
		quitarAction.putValue(Action.SHORT_DESCRIPTION, "Quitar genero musical seleccionado");

		seleccionarAction = new AbstractAction("Seleccionar",
				new ImageIcon(getClass().getResource("/instrumentos_musicales/imagenes/seleccionar.png"))) {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				seleccionar();
			}
		};
		seleccionarAction.putValue(Action.SHORT_DESCRIPTION, "Seleciona una imagen para mostrar");

	}

	private void nuevo() {
		// habilitar y kimpiar los elementos graficos.
		habilitarCampos(true);
		limpiarCampos();
		// Habilitar "Guardar" y "Cancelar"
		activarBotones(true);

		// Desabilitar "Nuevo", "Modificar" y "Eliminar"
		activarNuevo(false);
		modificarBoton.setEnabled(false);
		eliminarBoton.setEnabled(false);

		modificarMenu.setEnabled(false);
		eliminarBoton.setEnabled(false);
		// JOptionPane.showMessageDialog(null, "Nuevo")
		// Deshabilitar la lista desplegable principal
		entidad.setEnabled(false);
	}

	// Botón y menú "Nuevo"
	private void activarNuevo(boolean condicion) {
		// habilitar botón y menú "nuevo"
		nuevoMenu.setEnabled(condicion);
		nuevoBoton.setEnabled(condicion);
	}

	private void activarBotones(boolean valor) {
		guardarMenu.setEnabled(valor);
		guardarBoton.setEnabled(valor);

		cancelarMenu.setEnabled(valor);
		cancelarBoton.setEnabled(valor);
	}

	private void modificar() {
		JOptionPane.showMessageDialog(null, "Modificar");
	}

	private void guardar() {

		// JOptionPane.showMessageDialog(null, "Guardar");

		// Crear el objeto de la entidad
		InstrumentoMusical instrumento = new InstrumentoMusical();

		// Primero campos de textos

		/*
		 * Utilizar solo los campos: número libre, número con rango, texto en formato
		 * libre, texto con formato predefinido y el dato obtenido de las opciones
		 * mutuamente excluyentes fijas.
		 */

		instrumento.setAño(año.getText());
		instrumento.setPrecio(precio.getText());
		instrumento.setNombre(nombre.getText());
		instrumento.setNumeroSerie(numeroSerie.getText());

		// Radios: nuevo, usado, reparado

		if (nuevo.isSelected()) {
			instrumento.setCondicion(nuevo.getText());
		} else if (usado.isSelected()) {
			instrumento.setCondicion(usado.getText());
		} else if (reparado.isSelected()) {
			instrumento.setCondicion(reparado.getText());
		}

		// Mostrar mensaje de éxito
		JOptionPane.showMessageDialog(this, "Entidad guardada con éxito");

		// Agregar el nombre del instrumento al comboBox
		entidad.addItem(instrumento);
		// Llamar a la funcionalidad de cancelar
		cancelar();

	}

	private void eliminar() {
		JOptionPane.showMessageDialog(null, "Eliminar");
	}

	private void cancelar() {
		// Deshabilitar elementos graficos
		habilitarCampos(false);
		// Limpiar campos
		limpiarCampos();
		// Habilitar "Nuevo"
		activarNuevo(true);
		// Deshabilitar "Guardar" y "Cancelar"
		activarBotones(false);
		// JOptionPane.showMessageDialog(null, "Cancelar");
		verificarLista();

	}

	private void seleccionar() {
		JOptionPane.showMessageDialog(null, "Seleccionar");
	}

	private void quitar() {
		JOptionPane.showMessageDialog(null, "Quitar");
	}

	private void adjuntar() {
		JOptionPane.showMessageDialog(null, "Adjuntar");
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		// Implementar el Punto 20

		InstrumentoMusical instrumentoSeleccionado = (InstrumentoMusical) entidad.getSelectedItem();

		año.setText(String.valueOf(instrumentoSeleccionado.getAño()));
		precio.setText(String.valueOf(instrumentoSeleccionado.getPrecio()));
		numeroSerie.setText(instrumentoSeleccionado.getNumeroSerie());
		nombre.setText(instrumentoSeleccionado.getNombre());

		// Actualizar los radios con la condición del objeto seleccionado
		String condicion = instrumentoSeleccionado.getCondicion();
		if (condicion.equals(nuevo.getText())) {
			nuevo.setSelected(true);
		} else if (condicion.equals(usado.getText())) {
			usado.setSelected(true);
		} else if (condicion.equals(reparado.getText())) {
			reparado.setSelected(true);
		}

	}
	
	
	
	// 13._ Método privado: limpiar campos
	private void limpiarCampos() {
		nombre.setText("");
		precio.setText("");
		numeroSerie.setText("");
		fecha.setText("");
		usado.setSelected(true);// radio
		tiposMateriales.setSelectedIndex(0);
		año.setText("");

	}

	// 14._ Método privado: habilitar campos
	private void habilitarCampos(boolean habilitar) {
		// ignorar la lista principapy las acciones
		nombre.setEditable(habilitar);
		año.setEditable(habilitar);
		precio.setEditable(habilitar);
		numeroSerie.setEditable(habilitar);
		fecha.setEditable(habilitar);
		usado.setEnabled(habilitar);
		nuevo.setEnabled(habilitar);
		reparado.setEnabled(habilitar);
		marca.setEnabled(habilitar);
		scrollMateriales.setEnabled(habilitar);
		tiposMateriales.setEnabled(habilitar);
		scrollGenerosMusicales.setEnabled(habilitar);
		adjuntar.setEnabled(habilitar);
		quitar.setEnabled(habilitar);
		comboAdjuntar.setEditable(habilitar);
		imagen.setEditable(habilitar); //////
		seleccionar.setEnabled(habilitar);
	}

	// 15._ Método privado: verificar lista
	private void verificarLista() {
		// verificamos si hay elementos
		boolean tieneElementos = entidad != null && entidad.getItemCount() > 0;
		entidad.setEnabled(tieneElementos);
		// se establece dependiendo de la condición
		habilitarComponentes(tieneElementos); // Habilita o deshabilita los componentes
	}

	// método auxiliar del 15
	private void habilitarComponentes(boolean habilitar) {
		// botones
		modificarBoton.setEnabled(habilitar);
		eliminarBoton.setEnabled(habilitar);
		// menus
		modificarMenu.setEnabled(habilitar);
		eliminarMenu.setEnabled(habilitar);
	}

	// 16._ Método privado: inicializar
	private void inicializar() {
		// deshabilitar elementos graficos
		entidad.setEnabled(false);
		año.setEditable(false);
		precio.setEditable(false);
		nombre.setEditable(false);
		numeroSerie.setEditable(false);
		fecha.setEditable(false);
		usado.setEnabled(false);
		nuevo.setEnabled(false);
		reparado.setEnabled(false);
		marca.setEnabled(false);
		// scrollGenerosMusicales.setEnabled(false);
		generosMusicales.setEnabled(false);
		adjuntar.setEnabled(false);
		quitar.setEnabled(false);
		comboAdjuntar.setEnabled(false);
		// scrollMateriales.setEnabled(false);
		tiposMateriales.setEnabled(false);
		imagen.setEditable(false); //////
		seleccionar.setEnabled(false);

		/////////////////// botones /////////////////
		// habilitar botón y menú "nuevo"
		activarNuevo(true);
		// desabilitar botón y menú: "Guardar" y "Cancelar"
		activarBotones(false);
		// llamar a verificarLista
		verificarLista();

		// Insertar contenido
		// Materiales del instrumento:
		modeloMateriales.addElement("Metal");
		modeloMateriales.addElement("Plástico");
		modeloMateriales.addElement("Madera");
		modeloMateriales.addElement("Fibra");
		modeloMateriales.addElement("Bronce");
		modeloMateriales.addElement("Latón");

	}

	private void establecerPoliticaFoco() {
		// Crear el vector de componentes ordenados
		Vector<Component> componentesOrdenados = new Vector<>();

		// Agregar los componentes en el orden lógico deseado

		componentesOrdenados.add(nuevoBoton);
		componentesOrdenados.add(modificarBoton);
		componentesOrdenados.add(guardarBoton);
		componentesOrdenados.add(eliminarBoton);
		componentesOrdenados.add(cancelarBoton);
		componentesOrdenados.add(entidad);
		componentesOrdenados.add(nombre);
		componentesOrdenados.add(usado);
		componentesOrdenados.add(año);
		componentesOrdenados.add(fecha);
		componentesOrdenados.add(numeroSerie);
		componentesOrdenados.add(scrollMateriales);
		componentesOrdenados.add(adjuntar);
		componentesOrdenados.add(scrollGenerosMusicales);
		componentesOrdenados.add(quitar);
		componentesOrdenados.add(seleccionar);
		componentesOrdenados.add(precio);

		// Crear la política de foco con la lista de componentes ordenados
		MiFocusTraversalPolicy politicaFoco = new MiFocusTraversalPolicy(componentesOrdenados);

		// Establecer la política de foco en el diálogo
		this.setFocusTraversalPolicy(politicaFoco);

	}
}
