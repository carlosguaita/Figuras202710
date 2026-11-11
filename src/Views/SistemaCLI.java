package Views;

import Models.Circulo;
import Models.Figura;
import Models.Triangulo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaCLI {

    Scanner sc;
    List<Figura> figuras = new ArrayList<>();

    public SistemaCLI(){
        sc = new Scanner(System.in);
    }

    public int menu(){
        System.out.println("Seleccione una opción: ");
        System.out.println("1.Crear figuras");
        System.out.println("2.Listar figuras");
        System.out.print(">> ");
        int opc = sc.nextInt();
        return opc;
    }

    public void crearFigura(){
        System.out.println("Seleccione el tipo de figura a crear:");
        System.out.println("1.Models.Circulo");
        System.out.println("2.Models.Triangulo");
        int sel = sc.nextInt();
        switch (sel){
            case 1:
                System.out.print("Ingrese el radio:");
                double radio = sc.nextDouble();
                Circulo circulo = new Circulo(radio);
                figuras.add(circulo);
                break;
            case 2:
                System.out.print("Ingrese el lado 1: ");
                double lado1 = sc.nextDouble();
                System.out.print("Ingrese el lado 2: ");
                double lado2 = sc.nextDouble();
                System.out.print("Ingrese el lado 3: ");
                double lado3 = sc.nextDouble();
                Triangulo triangulo = new Triangulo(lado1,lado2,lado3);
                figuras.add(triangulo);
                break;
            default:
                System.out.println("No existe la ópcion");
                break;
        }
    }

    public void mostrarFiguras(){
        for (Figura figura : figuras){
            if(figura instanceof Circulo){
                Circulo circulo = (Circulo)figura;
                System.out.println(circulo);
            } else if (figura instanceof Triangulo) {
                Triangulo triangulo = (Triangulo)figura;
                System.out.println(triangulo);
            }
        }
    }

    public void inicio(){
        int opc2;
        do {
            int opc = menu();
            switch (opc) {
                case 1:
                    crearFigura();
                    break;
                case 2:
                    mostrarFiguras();
                    break;
                default:
                    System.out.println("No existe la opcion");
            }
            System.out.println("Desea seleccionar otra opcion 1.Si/2.No: ");
            opc2 = sc.nextInt();
        }while(opc2==1);
    }


}
