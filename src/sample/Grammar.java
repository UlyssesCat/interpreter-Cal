package sample;
import java.math.BigDecimal;
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
            BigDecimal r= expr();
            // String reString=String.valueOf(r)
            //System.out.println(r.toPlainString());
            return r.toPlainString();
        }

        return "";


    }

    public  BigDecimal expr() {
        BigDecimal result = term();
        BigDecimal tempResult;
        String operate;
        while ((operate = head.s).equals("+") || operate.equals("-")) {
            if(iter.hasNext())
                head = (token)iter.next();
            tempResult = term();
            switch (operate) {
                case "+":
                    System.out.println(result + "+" + tempResult + "=" + (result.add(tempResult)));
                    result=result.add(tempResult);
                    break;
                case "-":
                    System.out.println(result + "-" + tempResult + "=" + (result.subtract(tempResult)));
                    result=result.subtract(tempResult);
            }
        }
        return result;
    }
    public  BigDecimal term(){
        BigDecimal result = factor();
        BigDecimal tempResult;
        String operate;
        while ((operate = head.s).equals("*") || operate.equals("/")) {
            if(iter.hasNext())
                head = (token)iter.next();
            tempResult = factor();
            switch (operate) {
                case "*":
                    System.out.println(result + "*" + tempResult + "=" + (result.multiply(tempResult)));
                    result=result.multiply(tempResult);
                    break;
                case "/":


                    if(tempResult.compareTo(BigDecimal.ZERO)==0)
                    {
                        System.out.println("除数不能为0!!");
                        Controller.er+=("除数不能为0!!");
                    }
                    System.out.println(result + "/" + tempResult + "=" + (result.divide(tempResult,3,BigDecimal.ROUND_HALF_UP)));
                    result=result.divide(tempResult);
            }
        }
        return result;
    }
    public  BigDecimal factor(){
        BigDecimal factor=null;
        if(head.name.equals("NUMBER")){
            factor=new BigDecimal(head.s);
            if(iter.hasNext())
                head = (token)iter.next();
        }else{
            match("(");
            if(!head.name.equals("RPARENT"))
                factor = expr();
            else{
                System.out.println("括号内无数字");
                Controller.er+=("括号内无数字");
                //System.exit(-1);
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
        Controller.er+=("syntax error at "+head+"\n");
        //System.out.println("syntax error at "+head);
        return  false;
        //throw new RuntimeException("syntax error at "+head);
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