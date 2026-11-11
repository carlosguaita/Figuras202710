package Views;

import Models.Figura;

public class FabricaFigurasTest {
    public static void main(String[] args) {
        Figura circulo = FabricaFiguras.crearCirculo("2,5");
        comprobar(Math.abs(circulo.getArea() - Math.PI * 6.25) < 1e-10, "Área del círculo");
        comprobar(Math.abs(circulo.getPerimetro() - 5 * Math.PI) < 1e-10, "Perímetro del círculo");

        Figura triangulo = FabricaFiguras.crearTriangulo("3", "4", "5");
        comprobar(triangulo.getArea() == 6, "Área del triángulo");
        comprobar(triangulo.getPerimetro() == 12, "Perímetro del triángulo");

        invalido(() -> FabricaFiguras.crearCirculo(""));
        invalido(() -> FabricaFiguras.crearCirculo("0"));
        invalido(() -> FabricaFiguras.crearCirculo("-1"));
        invalido(() -> FabricaFiguras.crearCirculo("NaN"));
        invalido(() -> FabricaFiguras.crearCirculo("Infinity"));
        invalido(() -> FabricaFiguras.crearCirculo("1e308"));
        invalido(() -> FabricaFiguras.crearTriangulo("1", "2", "3"));
        invalido(() -> FabricaFiguras.crearTriangulo("5", "1", "1"));
        invalido(() -> FabricaFiguras.crearTriangulo("3", "abc", "5"));
        System.out.println("Pruebas de figuras correctas.");
    }

    private static void invalido(Runnable accion) {
        try {
            accion.run();
        } catch (IllegalArgumentException ex) {
            return;
        }
        throw new AssertionError("Se esperaba un error de validación.");
    }

    private static void comprobar(boolean condicion, String mensaje) {
        if (!condicion) {
            throw new AssertionError(mensaje);
        }
    }
}
