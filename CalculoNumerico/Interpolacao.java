package CalculoNumerico;

public class Interpolacao{

   private static Matriz Origem;
   private static Matriz Retorno;
   

   public static Matriz Lagrange(Matriz origem)
   {
      Origem = origem;
      Retorno = new Matriz(1, origem.Linha());

      //{ x = {-1,0,2}, y = {4,1,-1}}

      //Pn(x) = somatorio(n/k=0)(yk * Lk(x))
      //Lk(x) = (Produtorio(n/j=0,j!=k)(x-xj))/(Produtorio(n/j=0,j!=k)(xk-xj))


      Origem.ExibirMatriz();
      Retorno.ExibirMatriz();

      for(int i = 0; i < origem.Linha(); i++) L(i);
      
      for(int i = 0; i < origem.Linha(); i++) Retorno.set(Retorno.get(i,0) * Origem.get(1, i), i, 0);

      return Retorno;
   }

   private static void L(int ordem)
   {
      double controle = 
         Origem.get(0, ordem) * Origem.get(0, ordem)
       - Origem.get(0, ordem) * Origem.get(0,  (ordem + 2)% Origem.Linha()) 
       - Origem.get(0, (ordem + 1)% Origem.Linha()) * Origem.get(0, ordem) 
       + Origem.get(0, (ordem + 1)% Origem.Linha()) * Origem.get(0, (ordem + 2) % Origem.Linha());


      Retorno.set(Retorno.get(0,0) + 1 / controle,0, 0);
      Retorno.set(Retorno.get(1,0) + (- Origem.get(0, (ordem + 1)% Origem.Linha()) - Origem.get(0,(ordem + 2)% Origem.Linha()) / controle),1, 0);
      Retorno.set(Retorno.get(2,0) + (Origem.get(0, (ordem + 1)% Origem.Linha()) * Origem.get(0, (ordem + 2)% Origem.Linha()) / controle),2, 0);


   }

}