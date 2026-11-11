package Views;

import Models.Circulo;
import Models.Figura;
import Models.Triangulo;

import java.util.Arrays;

final class FabricaFiguras {
    private FabricaFiguras() {
    }

    static Circulo crearCirculo(String radio) {
        return comprobarResultado(new Circulo(leerMedida(radio, "Radio")));
    }

    static Triangulo crearTriangulo(String lado1, String lado2, String lado3) {
        double a = leerMedida(lado1, "Lado 1");
        double b = leerMedida(lado2, "Lado 2");
        double c = leerMedida(lado3, "Lado 3");
        double[] lados = {a, b, c};
        Arrays.sort(lados);
        if (lados[0] <= lados[2] - lados[1]) {
            throw new IllegalArgumentException("Los lados no forman un triángulo válido.");
        }
        return comprobarResultado(new Triangulo(a, b, c));
    }

    private static double leerMedida(String texto, String nombre) {
        double valor;
        try {
            valor = Double.parseDouble(texto.trim().replace(',', '.'));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(nombre + " debe ser un número positivo.");
        }
        if (!Double.isFinite(valor) || valor <= 0) {
            throw new IllegalArgumentException(nombre + " debe ser un número positivo y finito.");
        }
        return valor;
    }

    private static <T extends Figura> T comprobarResultado(T figura) {
        if (!Double.isFinite(figura.calcularPerimetro()) || !Double.isFinite(figura.calcularArea())) {
            throw new IllegalArgumentException("Las medidas son demasiado grandes para calcular la figura.");
        }
        return figura;
    }
}
