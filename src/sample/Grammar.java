package sample;
import java.util.ArrayList;
import java.util.Iterator;

public class Grammar {

    public static token head;
    public static int count = 0;
    public  ArrayList<token> tokenList;
    public Iterator iter ;



    public Grammar(ArrayList<token> tl){
        tokenList =(ArrayList<token>) tl.clone();
        iter = tokenList.iterator();
    }
    public  String gramAnalysis() {
        if(iter.hasNext()){
            head = (token)iter.next();
            double r= expr();
            String reString=String.valueOf(r);
            return reString;
        }
        return "";
    }

    public  double expr() {
        double result = term();
        double tempResult;
        String operate;
        while ((operate = head.s).equals("+") || operate.equals("-")) {
            if(iter.hasNext())
                head = (token)iter.next();
            tempResult = term();
            switch (operate) {
                case "+":
                    System.out.println(result + "+" + tempResult + "=" + (result + tempResult));
                    result += tempResult;
                    break;
                case "-":
                    System.out.println(result + "-" + tempResult + "=" + (result - tempResult));
                    result -= tempResult;
            }
        }
        return result;
    }
    public  double term(){
        double result = factor();
        double tempResult;
        String operate;
        while ((operate = head.s).equals("*") || operate.equals("/")) {
            if(iter.hasNext())
                head = (token)iter.next();
            tempResult = factor();
            switch (operate) {
                case "*":
                    System.out.println(result + "*" + tempResult + "=" + (result * tempResult));
                    result *= tempResult;
                    break;
                case "/":
                    if(tempResult==0)
                    {
                        System.out.println("除数不能为0");
                        System.exit(-1);
                    }
                    System.out.println(result + "/" + tempResult + "=" + (result / tempResult));
                    result /= tempResult;
            }
        }
        return result;
    }
    public  double factor(){
        double factor=0;
        if(head.name.equals("NUMBER")){
            factor=Double.parseDouble(head.s);
            if(iter.hasNext())
                head = (token)iter.next();
        }else{
            match("(");
            if(!head.name.equals("RPARENT"))
                factor = expr();
            else{
                System.out.println("括号内无数字");
                System.exit(-1);
            }
            match(")");

        }
        return factor;
    }

    public  boolean match(String str){
        if (str.equals(head.s)){
            if(iter.hasNext())
                head = (token)iter.next();
            return true;
        }
        throw new RuntimeException("syntax error at "+head);
    }
}

//    m
//    M -> +E|-E|E
//    E -> TE~
//    E~ -> +TE~|-TE~|&
//    T -> FT~
//    T~ -> *FT~|/FT~|&
//    F -> (E)|digit

//expr —> term + expr | term - expr | term
//term —> factor * term | factor/term | factor
//factor—> digit ｜(expr)

//    m
//    M -> +E|-E|E
//    E -> TE~
//    E~ -> +TE~|-TE~|&
//    T -> FT~
//    T~ -> *FT~|/FT~|&
//    F -> (E)|digit

//expr —> term + expr | term - expr | term
//term —> factor * term | factor/term | factor
//factor—> digit ｜(expr)