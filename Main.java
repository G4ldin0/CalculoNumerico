import CalculoNumerico.*;

public class Main {
   public static void main(String[] args) {
      
      Matriz exemplo = new Matriz(new double[][]{{2,1},{5,3}});

      exemplo.ExibirMatriz();
      System.out.println(exemplo.determinante());


   }
   
}
