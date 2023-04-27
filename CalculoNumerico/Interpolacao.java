package CalculoNumerico;

public class Interpolacao{

   //Retorno é um vetor de N valores equivalente aos termos no polinômio  interpolador

   private static Matriz Origem;
   

   public static Matriz Lagrange(Matriz origem)
   {
      Origem = origem;
      Matriz Retorno = new Matriz(1, origem.Linha());

      //{ x = {-1,0,2}, y = {4,1,-1}}

      //Pn(x) = somatorio(n/k=0)(yk * Lk(x))
      //Lk(x) = (Produtorio(n/j=0,j!=k)(x-xj))/(Produtorio(n/j=0,j!=k)(xk-xj))

      for(int i = 0; i < origem.Linha(); i++) 
      {
         Matriz placeholder = L(i);
         placeholder.ExibirMatriz();
         placeholder.escalar(origem.get(1,i));
         Retorno.soma(placeholder);
         Retorno.ExibirMatriz();
         System.out.println();
      }

      //P(x) = 4.(x²-2x)/3 + (x²-x-2)/-2 - (x²+x)/6
      //P(x) = 4x²/3 -8x/3 - x²/2 +x/2 +2/2 -x²/6 -x/6
      //P(x) = 4x²/3 -x²/2 -x²/6 -8x/3 +x/2 -x/6 + 1
      //P(x) = ( 8x²/6 -3x²/6 -x²/6 ) + (-16x/6 +3x/6 - x/6 ) + ( 1 )
      //P(x) = 4x²/6 -14x/6 + 1
      //P(x) = 2x²/3 -7x/3 + 1
      
      // for(int i = 0; i < origem.Linha(); i++) Retorno.set(Retorno.get(i,0) * Origem.get(1, i), i, 0);

      return Retorno;
   }

   private static Matriz L(int ordem)
   {
      double controle = Origem.get(0, ordem) - Origem.get(0, (ordem + 1) % Origem.Linha());
      Matriz placeholder = new Matriz(1,Origem.Linha());

      for(int i = 2; i < Origem.Linha(); i++)
      {
         controle *= Origem.get(0,ordem) - Origem.get(0, (i+ordem)%Origem.Linha());
      }

      //o2 = a² - 2ab + b²
      //o3 = a³ - 3a²b + 3ab² - b³
      //o4 = a4 - 4a³b + 6a²b² - 4ab³ + b4
      //o5 = a5 - 5a4b + 10a³b² - 10a²b³ + 5ab4 - b5
      //o6 = a6 - 6a5b + 15a4b² - 20a³b³ + 15a²b4 - 6ab5 + b6

      //(a - b) = a - b = 1*a¹ -b*a°
      //(a - b) * (a - c) = a² -ac -ab +bc = 1*a² -(c + b)*a¹ + bc*a°
      //(a - b) * (a - c) * (a - d) = (a - d) * ( a² -ac -ab +bc ) = a³ -a²c -a²b +abc -a²d + acd +abd -bcd = 1*a³ -(b + c + d)*a² + (bc + cd + bd)*a¹ + (-bcd)*a° 
      //(a - b) * (a - c) * (a - d) * (a - e) =  (a - e) * (a³ -a²c -a²b +abc -a²d + acd +abd -bcd) 
      // = a4 -a³c -a³b + a²bc -a³d + a²cd + a²bd - abcd -a³e + a²ce + a²be - abce + a²de - acde - abde + bcde 
      // = 1*a4 - (b + c + d + e)*a³ + (bc + cd + bd + ce + be + de)*a² - (bcd + bce + cde + bde)*a¹ + (bcde)*a°

      /*/
      for(int i = 0; i < Origem.Linha(); i++)
      {
         placeholder.set(0,i,0);
      }

      return placeholder;
      */
      return new Matriz(new double[][]{
         {1/controle},
         {(- Origem.get(0, (ordem + 1)% Origem.Linha()) - Origem.get(0,(ordem + 2)% Origem.Linha())) / controle},
         {( Origem.get(0, (ordem + 1)% Origem.Linha()) * Origem.get(0, (ordem + 2)% Origem.Linha())) / controle}
      });
      
   }


   public static Matriz Newton(Matriz origem){
      //P(x) = d0 + d1(x-x0) + d2(x-x0)(x-x1)
      //P(x) d2*x² -d2*x1*x -d2*x0*x + d1*x - d1*x0 + d0
      Origem = origem;
      Matriz Retorno = new Matriz(1, Origem.Linha());

      double valor = f(new int[]{0,1,2});
      System.out.println(valor);
      Retorno.set(valor,0,0);
      Retorno.set(- valor * origem.get(0,1) - valor * origem.get(0,0),1,0);
      
      valor = f(new int[]{0,1});
      System.out.println(valor);
      Retorno.set(Retorno.get(1,0) + valor,1,0);
      Retorno.set(- valor * origem.get(0,0) + origem.get(1,0),2,0);

      return Retorno;
   }

   private static double f(int[] x){
      if(x.length == 1)
      {
         // System.out.println( Origem.get(1, x[0]));
         return Origem.get(1, x[0]);
      } 
      else
      {
         int[] value1 = new int[x.length-1];
         int[] value2 = new int[x.length-1];

         for(int i = 0; i < x.length-1; i++)
            value1[i] = x[i];
         
         
         for(int i = 1; i < x.length; i++)
            value2[i-1] = x[i];

         return (f(value2) - f(value1)) / (Origem.get(0, x.length-1) - Origem.get(0, 0));
      }
   }


}