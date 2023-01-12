package Calculadora;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;


public class Calculadora extends JFrame {



	
	JTextField pantalla;

	double resultado;

	String operacion;

	JPanel pNumeros, pOperaciones;

	boolean nuevaOperacion = true;


	public Calculadora() {
		super();
		setSize(400, 300);
		setTitle("Calculadora");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);

		JPanel panel = (JPanel) this.getContentPane();
		panel.setLayout(new BorderLayout());

		pantalla = new JTextField("0", 20);
		pantalla.setBorder(new EmptyBorder(4, 4, 4, 4));
		pantalla.setFont(new Font("Arial", Font.BOLD, 25));
		pantalla.setHorizontalAlignment(JTextField.RIGHT);
		pantalla.setEditable(false);
		pantalla.setBackground(Color.WHITE);
		panel.add("North", pantalla);

		pNumeros = new JPanel();
		pNumeros.setLayout(new GridLayout(4, 3));
		pNumeros.setBorder(new EmptyBorder(4, 4, 4, 4));

		for (int i = 9; i >= 0; i--) {
			nuevoBotonNumerico("" + i);
		}

		nuevoBotonNumerico(".");
                

		panel.add("Center", pNumeros);

		pOperaciones = new JPanel();
		pOperaciones.setLayout(new GridLayout(6, 1));
		pOperaciones.setBorder(new EmptyBorder(4, 4, 4, 4));

		nuevoBotonOperacion("+");
		nuevoBotonOperacion("-");
		nuevoBotonOperacion("*");
		nuevoBotonOperacion("/");
		nuevoBotonOperacion("=");
		nuevoBotonOperacion("CE");

		panel.add("East", pOperaciones);

		validate();
	}

	
	private void nuevoBotonNumerico(String digito) {
		JButton boton = new JButton();
		boton.setText(digito);
		boton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent evt) {
				JButton boton = (JButton) evt.getSource();
				numeroPulsado(boton.getText());
			}
		});

		pNumeros.add(boton);
	}


	private void nuevoBotonOperacion(String operacion) {
		JButton boton = new JButton(operacion);
		boton.setForeground(Color.BLUE);

		boton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent evt) {
				JButton boton = (JButton) evt.getSource();
				operacionPulsado(boton.getText());
			}
		});

		pOperaciones.add(boton);
	}

	
	private void numeroPulsado(String digito) {
		if (pantalla.getText().equals("0") || nuevaOperacion) {
			pantalla.setText(digito);
		} else {
			pantalla.setText(pantalla.getText() + digito);
		}
		nuevaOperacion = false;
	}

	
	private void operacionPulsado(String tecla) {
		if (tecla.equals("=")) {
			calcularResultado();
		} else if (tecla.equals("CE")) {
			resultado = 0;
			pantalla.setText("");
			nuevaOperacion = true;
		} else {
			operacion = tecla;
			if ((resultado > 0) && !nuevaOperacion) {
				calcularResultado();
			} else {
				resultado = new Double(pantalla.getText());
			}
		}

		nuevaOperacion = true;
	}

	
	private void calcularResultado() {
            switch (operacion) {
                case "+":
                    resultado += new Double(pantalla.getText());
                    break;
                case "-":
                    resultado -= new Double(pantalla.getText());
                    break;
                case "/":
                    resultado /= new Double(pantalla.getText());
                    break;
                case "*":
                    resultado *= new Double(pantalla.getText());
                    break;
                default:
                    break;
            }

		pantalla.setText("" + resultado);
		operacion = "";
	}
}