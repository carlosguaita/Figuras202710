package Models;

public class Circulo extends Figura {

    private double radio;
    private double diametro;

    public Circulo(double radio) {
        super("Models.Circulo", 0);
        this.radio = radio;
    }

    public double calcularDiametro(){
        diametro = 2 * radio;
        return diametro;
    }

    @Override
    public double calcularArea(){
        area = Math.PI * Math.pow(radio,2);
        return area;
    }

    @Override
    public double calcularPerimetro(){
        perimetro = 2 * Math.PI * radio;
        return perimetro;
    }

    public double getRadio() {
        return radio;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }

    @Override
    public String toString(){
        return "Tipo: " + getNombre() +
                " Radio: " + getRadio() +
                " Perimetro: " + calcularPerimetro() +
                " Area: " + calcularArea();
    }
}
