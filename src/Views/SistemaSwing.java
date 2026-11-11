package Views;

import Models.Circulo;
import Models.Figura;
import Models.Triangulo;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class SistemaSwing extends JFrame {
    private static final String CIRCULO = "Círculo";
    private static final String TRIANGULO = "Triángulo";

    private final JComboBox<String> tipo = new JComboBox<>(new String[]{CIRCULO, TRIANGULO});
    private final JTextField radio = new JTextField(12);
    private final JTextField lado1 = new JTextField(12);
    private final JTextField lado2 = new JTextField(12);
    private final JTextField lado3 = new JTextField(12);
    private final DefaultTableModel modelo = new DefaultTableModel(
            new Object[]{"Tipo", "Medidas", "Perímetro", "Área"}, 0) {
        @Override
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };

    public SistemaSwing() {
        super("Figuras geométricas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setBorder(BorderFactory.createTitledBorder("Crear figura"));
        GridBagConstraints posicion = new GridBagConstraints();
        posicion.insets = new Insets(4, 4, 4, 4);
        posicion.anchor = GridBagConstraints.WEST;
        posicion.gridx = 0;
        posicion.gridy = 0;
        formulario.add(new JLabel("Tipo de figura:"), posicion);
        posicion.gridx = 1;
        posicion.fill = GridBagConstraints.HORIZONTAL;
        formulario.add(tipo, posicion);

        CardLayout tarjetas = new CardLayout();
        JPanel campos = new JPanel(tarjetas);
        JPanel camposCirculo = crearCampos(new String[]{"Radio:"}, new JTextField[]{radio});
        JPanel camposTriangulo = crearCampos(
                new String[]{"Lado 1:", "Lado 2:", "Lado 3:"},
                new JTextField[]{lado1, lado2, lado3});
        campos.add(camposCirculo, CIRCULO);
        campos.add(camposTriangulo, TRIANGULO);
        posicion.gridx = 0;
        posicion.gridy = 1;
        posicion.gridwidth = 2;
        formulario.add(campos, posicion);
        tipo.addActionListener(evento -> tarjetas.show(campos, (String) tipo.getSelectedItem()));

        JButton crear = new JButton("Crear figura");
        crear.addActionListener(evento -> crearFigura());
        posicion.gridy = 2;
        posicion.fill = GridBagConstraints.NONE;
        formulario.add(crear, posicion);
        posicion.gridy = 3;
        posicion.weighty = 1;
        formulario.add(new JPanel(), posicion);
        add(formulario, BorderLayout.WEST);

        JTable tabla = new JTable(modelo);
        tabla.setFillsViewportHeight(true);
        JScrollPane matriz = new JScrollPane(tabla);
        matriz.setBorder(BorderFactory.createTitledBorder("Figuras creadas"));
        add(matriz, BorderLayout.CENTER);

        setSize(850, 400);
        setLocationRelativeTo(null);
    }

    private JPanel crearCampos(String[] etiquetas, JTextField[] entradas) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints posicion = new GridBagConstraints();
        posicion.insets = new Insets(4, 0, 4, 8);
        posicion.anchor = GridBagConstraints.WEST;
        for (int i = 0; i < etiquetas.length; i++) {
            posicion.gridx = 0;
            posicion.gridy = i;
            panel.add(new JLabel(etiquetas[i]), posicion);
            posicion.gridx = 1;
            panel.add(entradas[i], posicion);
        }
        posicion.gridx = 0;
        posicion.gridy = etiquetas.length;
        posicion.weighty = 1;
        panel.add(new JPanel(), posicion);
        return panel;
    }

    private void crearFigura() {
        try {
            Figura figura;
            String medidas;
            if (CIRCULO.equals(tipo.getSelectedItem())) {
                Circulo circulo = FabricaFiguras.crearCirculo(radio.getText());
                figura = circulo;
                medidas = "Radio: " + circulo.getRadio();
            } else {
                Triangulo triangulo = FabricaFiguras.crearTriangulo(
                        lado1.getText(), lado2.getText(), lado3.getText());
                figura = triangulo;
                medidas = "Lados: " + triangulo.getLado1() + ", "
                        + triangulo.getLado2() + ", " + triangulo.getLado3();
            }
            modelo.addRow(new Object[]{tipo.getSelectedItem(), medidas,
                    figura.getPerimetro(), figura.getArea()});
            radio.setText("");
            lado1.setText("");
            lado2.setText("");
            lado3.setText("");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Datos inválidos", JOptionPane.ERROR_MESSAGE);
        }
    }
}
