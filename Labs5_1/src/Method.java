/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Shwartskopff
 */
public class Method {
    
    private static final double EPS = 1e-5; 
    
    
    public static double meth(Function function, double l, double r, double eps) {
        while((r - l) > eps) {
            double a = (l + r) / 2;
            if(function.f(a) == 0)
                return a;
            if(function.f(l) < 0 && function.f(a) < 0
                    || function.f(l) > 0 && function.f(a) > 0)
                l = a;
            else if(function.f(a) < 0 && function.f(r) < 0
                    || function.f(a) > 0 && function.f(r) > 0)
                r = a;
        }
        return l;
    }

    
    private static class Func implements Function {
        @Override
        public double f(double x) {
           return Math.sin(x) - 0.75;
        }
    }
    
    private static class Ln {
        double eval(double x) {
            return Math.log(x * x * x) - 2;
        }
    }   

   public static void main(String[] args) {
       
       Function f1 = new Function() {
        public double f(double x) {
                return Math.exp(-x) - 0.5;
            }
        };
        System.out.println("");
        System.out.println("Реализация интерфеса с помощью вложенного класса: ");
        System.out.println(meth(f1, 0.01, 2, EPS));
                
        Function f2 = new Func();
        System.out.println("");
        System.out.println("Реализация интерфеса с помощью анонимного класса: ");
        System.out.println(meth(f2, 2, 3, EPS));
        
        Function f3 = Math::tan;
        System.out.println("");
        System.out.println("Реализация интерфеса с помощью ссылки на статический метод: ");
        System.out.println(meth(f3, 2, 4, EPS));
        
        Ln ln = new Ln();
        Function f4 = ln::eval;
        System.out.println("");
        System.out.println("Реализация интерфеса с помощью ссылки на метод экземпляра: ");
        System.out.println(meth(f4, 1, 3, EPS));
        
        Function f5 = x -> x*x*x - 8*x + 2;
        System.out.println("");
        System.out.println("Реализация интерфейса с помощью лямбда-выражения: ");
        System.out.println(meth(f5, 1, 5, EPS));
   }
}
    
  