package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import model.Atencion;
import model.Tipo;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class GuiPACOMPIA extends JFrame {

	private JPanel contentPane;

	private JTextArea txtSalida;
	private JTextField txtNum_atencion;
	private JComboBox cboTipos;
	private JTextField txtFecha;
	private JTextField txtNom_paciente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiPACOMPIA frame = new GuiPACOMPIA();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GuiPACOMPIA() {
		setTitle(":::::::::Registro de atencion ::::::");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		btnRegistrar.setBounds(324, 29, 89, 23);
		contentPane.add(btnRegistrar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 171, 414, 143);
		contentPane.add(scrollPane);

		txtSalida = new JTextArea();
		scrollPane.setViewportView(txtSalida);

		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}
		});
		btnListado.setBounds(177, 322, 89, 23);
		contentPane.add(btnListado);

		txtNum_atencion = new JTextField();
		txtNum_atencion.setBounds(177, 11, 112, 20);
		contentPane.add(txtNum_atencion);
		txtNum_atencion.setColumns(10);

		JLabel lblCodigo = new JLabel("N° de Atencion :");
		lblCodigo.setBounds(10, 14, 102, 14);
		contentPane.add(lblCodigo);

		cboTipos = new JComboBox();
		cboTipos.setBounds(132, 101, 186, 22);
		contentPane.add(cboTipos);

		JLabel lblCategora = new JLabel("Especialidad (tipo):");
		lblCategora.setBounds(10, 101, 102, 14);
		contentPane.add(lblCategora);

		JLabel lblNomProducto = new JLabel("Fecha sistema (dd/mm/yyyy) :");
		lblNomProducto.setBounds(10, 45, 146, 14);
		contentPane.add(lblNomProducto);

		txtFecha = new JTextField();
		txtFecha.setEditable(false);
		txtFecha.setColumns(10);
		txtFecha.setBounds(177, 42, 112, 20);
		contentPane.add(txtFecha);

		JLabel lblStock = new JLabel("Nombre y apellidos :");
		lblStock.setBounds(10, 76, 102, 14);
		contentPane.add(lblStock);

		txtNom_paciente = new JTextField();
		txtNom_paciente.setColumns(10);
		txtNom_paciente.setBounds(132, 73, 186, 20);
		contentPane.add(txtNom_paciente);

		llenaCombo1();

	}

	void llenaCombo1() {
		// TODO Auto-generated method stub
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("LPII_T1_PACOMPIALOPEZ");
		// 2. CREAR UN MANEJADOR DE LAS ENTIDADES
		EntityManager em = fabrica.createEntityManager();
		// 3. PARA HCER UN LISTADO DE TODOS LOS USUARIOS LO HACEMOS CON SELECT * FROM
		// NOMRMALMENTE
		// pero lo haremos haciendo otra cosa (primero pasa el sql que es mi cdena y
		// luego le paso mi entidad Usuario)
		// 4. ste es mi sentencia
		String jpql = "select t from Tipo t"; // debe ir el nombre de la entidad Producto, y a la enteidad Producto le
												// guarda en la variable p, esta p tiene el codigo,nombre,apellido etc
												// etc
		List<Tipo> lstTipos = em.createQuery(jpql, Tipo.class).getResultList();
		// 5. mostrar el contenido del listado en un combobox
		cboTipos.addItem("Seleccione...");
		for (Tipo t : lstTipos) {
			cboTipos.addItem(t.getNom_tipo_atencion());
		}

		em.close();

	}

	void registrar() {

		// TODO Auto-generated method stub
		// 1. Obtener la conexion->tiene que llamar a la unidad de persistencia
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("LPII_T1_PACOMPIALOPEZ");
		// 2. CREAR UN MANEJADOR DE LAS ENTIDADES
		EntityManager em = fabrica.createEntityManager();

		// entradas
		String num_atencion = txtNum_atencion.getText();
		String nom_paciente=txtNom_paciente.getText();
		// validaciones
		if (!num_atencion.matches("[Pp][0-9]{4}")) {
			aviso("Codigo incorrecto");
			return;
		}
		if (!nom_paciente.matches("^[A-Za-zñÑáéíóúÁÉÍÓÚ\\s]+$")) {
			aviso("El nombre debe ser conformado por letras");
			return;
		}
		

		// 3. PROCESOS
		Atencion a = new Atencion();
		a.setNum_atencion(num_atencion);
		a.setFecha("2023/09/29");
		a.setNom_paciente(nom_paciente);
		a.setCod_tipo_atencion(cboTipos.getSelectedIndex());
		try {
			
			em.getTransaction().begin(); // esto le dice que voy a empezar una transaccion
			em.persist(a);			
			em.getTransaction().commit(); // confirmo la transaccion
			aviso("Registro OK");
			
			txtNom_paciente.setText("");
			cboTipos.setSelectedIndex(0);
			
		} catch (Exception e) {
			aviso("Error al registar Atencion\n" + e.getCause().getMessage());
		}
		em.close();
	}
	
	

	void aviso(String s) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this, s, "Aviso", JOptionPane.INFORMATION_MESSAGE);
	}

	void listado() {
		// TODO Auto-generated method stub
		// 1. Obtener la conexion->tiene que llamar a la unidad de persistencia
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("LPII_T1_PACOMPIALOPEZ");
		// 2. CREAR UN MANEJADOR DE LAS ENTIDADES
		EntityManager em = fabrica.createEntityManager();

		// 4. ste es mi sentencia
		String jpql = "select a from Atencion a";
		List<Atencion> lstAtenciones = em.createQuery(jpql, Atencion.class).getResultList();
		// 5. mostrar el contenido del listado
		for (Atencion a : lstAtenciones) {
			imprimir("Fecha..............: " + a.getFecha() + "\n");
			imprimir("Nombre de Paciente.....: " + a.getNom_paciente() + "\n");
			imprimir("Tipo de atencion (especialidad).:" + a.getObjTipo().getNom_tipo_atencion() + "\n");
			imprimir("Precio a pagar (S/.).:..........:" + a.getObjTipo().getPrecio() + "\n");
			imprimir("-------------------------------");
			imprimir("\n\n");
		}

		em.close();

	}

	void imprimir(String s) {
		// TODO Auto-generated method stub
		txtSalida.append(s + "\n");

	}
}
