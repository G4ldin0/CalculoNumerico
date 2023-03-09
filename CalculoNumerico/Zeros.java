package CalculoNumerico;

import java.util.function.Function;

public class Zeros{

   private Function<Double,Double> funcao;


   //private Function<Double[], Double> bisseccao = x -> (x[0] + x[1]) / 2;

   public Zeros(Function<Double, Double> funcao){
      this.funcao = funcao;
   }

   public double Bisseccao(double a, double b, double epsilon){
      double zero;
      //System.out.println(Math.pow(10, epsilon) + "\n");
      do{

         zero = (a + b) / 2;

         // System.out.println(a); DEBUG
         // System.out.println(b);
         // System.out.println(zero);
         // System.out.println();


         if(funcao.apply(a) * funcao.apply(zero) < 0){
            b = zero;
         } else{
            a = zero;
         }


      } while(Math.abs(funcao.apply(zero)) > Math.pow(10, -epsilon));
      return zero;
   }

   public double FalsaPosicao(double a, double b, double epsilon){
      double zero;

      do{

         zero = (a * funcao.apply(b) - b * funcao.apply(a)) / (funcao.apply(b) - funcao.apply(a));

         if(funcao.apply(a) * funcao.apply(zero) < 0){
            b = zero;
         } else{
            a = zero;
         }

      }while(Math.abs(funcao.apply(zero)) > Math.pow(10, -epsilon));

      return zero;
   }

   public double PontoFixo(double a, double epsilon){
      double zero = a;
      double teste = funcao.apply(zero);

      while(Math.abs(zero - teste) > Math.pow(10, -epsilon)){
         zero = teste;
         teste = funcao.apply(zero);
         System.out.println(teste);
      }

      return zero;
   }


   public double NewtonHaphson(double a, Function<Double, Double> derivada, double epsilon){
      double zero = a;
      double teste = zero - (funcao.apply(zero)/derivada.apply(zero));

      while(Math.abs(zero - teste) > Math.pow(10, -epsilon)){
         System.out.println(zero);
         
         zero = teste;
         teste = zero - (funcao.apply(zero)/derivada.apply(zero));   
   
      }

      return zero;
   }

   public double Secante(double a, double b, double epsilon){
      double zero = b - (funcao.apply(b) * (b - a))/(funcao.apply(b)-funcao.apply(a));
      while(Math.abs(funcao.apply(zero)) > Math.pow(10, -epsilon)){
         System.out.println(zero);
         a = b;
         b = zero;
         zero =b - (funcao.apply(b) * (b - a))/(funcao.apply(b)-funcao.apply(a));
      }
      return zero;
   }

}