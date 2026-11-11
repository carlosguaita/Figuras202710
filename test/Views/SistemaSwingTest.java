package Views;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

public class SistemaSwingTest {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            SistemaSwing ventana = new SistemaSwing();
            try {
                ventana.setVisible(true);
                JComboBox<?> selector = buscar(ventana, JComboBox.class).get(0);
                List<JTextField> campos = buscar(ventana, JTextField.class);
                JTable tabla = buscar(ventana, JTable.class).get(0);
                JButton crear = buscar(ventana, JButton.class).stream()
                        .filter(boton -> "Crear figura".equals(boton.getText()))
                        .findFirst().orElseThrow(() -> new AssertionError("Falta el botón de creación"));
                JPanel circulo = (JPanel) campos.get(0).getParent();
                JPanel triangulo = (JPanel) campos.get(1).getParent();

                comprobar(circulo.isVisible() && !triangulo.isVisible(), "Formulario inicial");
                comprobarTamano(selector, "Selector");
                comprobarTamano(campos.get(0), "Radio");
                ventana.setSize(1200, 800);
                ventana.validate();
                comprobarTamano(selector, "Selector después de ampliar la ventana");
                comprobarTamano(campos.get(0), "Radio después de ampliar la ventana");
                campos.get(0).setText("2");
                crear.doClick();
                comprobar(tabla.getRowCount() == 1, "Fila del círculo");
                comprobar("Círculo".equals(tabla.getValueAt(0, 0)), "Tipo del círculo");
                comprobar(Math.abs((Double) tabla.getValueAt(0, 3) - 4 * Math.PI) < 1e-10,
                        "Área del círculo");
                comprobar(campos.get(0).getText().isEmpty(), "Limpieza del formulario");

                selector.setSelectedIndex(1);
                ventana.validate();
                comprobar(!circulo.isVisible() && triangulo.isVisible(), "Cambio de formulario");
                for (int i = 1; i < campos.size(); i++) {
                    comprobarTamano(campos.get(i), "Lado " + i);
                }
                campos.get(1).setText("3");
                campos.get(2).setText("4");
                campos.get(3).setText("5");
                crear.doClick();
                comprobar(tabla.getRowCount() == 2, "Fila del triángulo");
                comprobar("Triángulo".equals(tabla.getValueAt(1, 0)), "Tipo del triángulo");
                comprobar((Double) tabla.getValueAt(1, 2) == 12, "Perímetro del triángulo");
                comprobar((Double) tabla.getValueAt(1, 3) == 6, "Área del triángulo");
                comprobar(!tabla.isCellEditable(0, 0), "Tabla no editable");
                ventana.setSize(850, 400);
                ventana.validate();
                for (int i = 1; i < campos.size(); i++) {
                    comprobarTamano(campos.get(i), "Lado " + i + " después de reducir la ventana");
                }
                selector.setSelectedIndex(0);
                ventana.validate();
                comprobarTamano(campos.get(0), "Radio al volver al círculo");
            } finally {
                ventana.dispose();
            }
        });
        System.out.println("Pruebas de interfaz correctas.");
    }

    private static <T extends Component> List<T> buscar(Container raiz, Class<T> tipo) {
        List<T> encontrados = new ArrayList<>();
        for (Component componente : raiz.getComponents()) {
            if (tipo.isInstance(componente)) {
                encontrados.add(tipo.cast(componente));
            }
            if (componente instanceof Container) {
                encontrados.addAll(buscar((Container) componente, tipo));
            }
        }
        return encontrados;
    }

    private static void comprobar(boolean condicion, String mensaje) {
        if (!condicion) {
            throw new AssertionError(mensaje);
        }
    }

    private static void comprobarTamano(Component componente, String mensaje) {
        comprobar(componente.getHeight() == componente.getPreferredSize().height,
                mensaje + ": altura deformada");
        comprobar(componente.getWidth() >= componente.getMinimumSize().width,
                mensaje + ": ancho insuficiente");
        comprobar(componente.getWidth() <= componente.getPreferredSize().width + 4,
                mensaje + ": ancho deformado");
    }
}
