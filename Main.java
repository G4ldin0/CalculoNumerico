import CalculoNumerico.*;

public class Main {
   public static void main(String[] args) {
      
      Matriz exemplo = new Matriz(new double[][]{{-1,0,2},{4,1,-1}});
      Matriz exemplo2 = new Matriz(new double[][]{{4,1,-1},{-1,0,2}});

      exemplo.ExibirMatriz();
      Interpolacao.Newton(exemplo).ExibirMatriz();
   } 
   
}
