package CalculoNumerico;

import java.util.function.Function;

public class Erro{
   public static Double erroRelativo(Function<Double, Double> funcao, Function<Double, Double> exato, int iteracao){
      double retorno = 0;
      
      double somatorio = 0;

      for(double i = 0; i < iteracao; i++){
         somatorio += 1 / funcao.apply(i);
      }

      return somatorio / exato.apply((double)iteracao);
   }

}