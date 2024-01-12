package TP2;

public class Services{

    public static String menu() {
        return "    __  ___                \n   /  |/  /__  ____  __  __\n  / /|_/ / _ \\/ __ \\/ / / /\n / /  / /  __/ / / / /_/ / \n/_/  /_/\\___/_/ /_/\\__,_/  \n1. Tax\n2. Insurance\n3. Bonus\n4. SalaireNet";
    }


    public static double Tax(double salaire) {
        return (salaire/100)*87;
    }


    public static double Insurance(double salaire) {
        return (salaire/100)*96;
    }


    public static double Bonus(double salaire) {
        return salaire+(salaire/100)*22;
    }


    public static double SalaireNet(double salaire) {
        double res=0;
        res = Bonus(Insurance(Tax(salaire)));
        return res;
    }
}
