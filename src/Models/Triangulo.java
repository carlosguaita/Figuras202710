package Models;

public class Triangulo extends Figura{

    private double lado1;
    private double lado2;
    private double lado3;
    private double semi;

    public Triangulo(double lado1, double lado2, double lado3) {
        super("Models.Triangulo", 3);
        this.lado1 = lado1;
        this.lado2 = lado2;
        this.lado3 = lado3;
    }

    @Override
    public double calcularPerimetro(){
        perimetro = lado1 + lado2 + lado3;
        return  perimetro;
    }

    public double calcularSemiPerimetro(){
        semi = this.calcularPerimetro()/2;
        return semi;
    }

    @Override
    public double calcularArea(){
        double s = this.calcularSemiPerimetro();
        area = Math.sqrt(s * (s - lado1) * (s - lado2) * (s - lado3));
        return area;
    }

    public double getLado1() {
        return lado1;
    }

    public void setLado1(double lado1) {
        this.lado1 = lado1;
    }

    public double getLado2() {
        return lado2;
    }

    public void setLado2(double lado2) {
        this.lado2 = lado2;
    }

    public double getLado3() {
        return lado3;
    }

    public void setLado3(double lado3) {
        this.lado3 = lado3;
    }

    public double getSemi() {
        return semi;
    }

    public void setSemi(double semi) {
        this.semi = semi;
    }

    @Override
    public String toString(){
        return "Tipo: " + getNombre() +
                " Lado1: " + getLado1() +
                " Lado2: " + getLado2() +
                " Lado3: " + getLado3() +
                " Perimetro: " + calcularPerimetro()+
                " Area: " + calcularArea();
    }



}
