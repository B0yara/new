package com.company;

import java.util.ArrayList;
import java.util.Stack;

public class DataProcess {
     private String strFunc;
     private String  minX;
     private String  incX;
     private String  maxX;
     private int[] cordY;
     private int[] cordX;
    private float max;
    private float min;
    private int n;



    void setData(String strFunc,String minX,String incX,String maxX){
    this.strFunc = strFunc;
    this.minX = minX;
    this.incX = incX;
    this.maxX = maxX;
         max = per(maxX) ;
         min = per(minX) ;
        float inc = (per(incX));
         n = (int) (((max - min)/inc) +1);
        cordX =new int[n];
        cordY =new int[n];
        rasschet();
    }

      void    rasschet () {

       float inc = (per(incX));
       int n = (int) (((max - min)/inc) +1);



        ArrayList<Object> func = new ArrayList<>();
        ArrayList<Object> fun;
        String var = new String();
        var = "";
        for (int i = 0; i < strFunc.length(); i++) {
            if (strFunc.charAt(0) == '-') func.add ("0");
            if ((strFunc.charAt(i) != '+') && (strFunc.charAt(i) != '-') && (strFunc.charAt(i) != '*') && (strFunc.charAt(i) != '/') && (strFunc.charAt(i) != '^') && (strFunc.charAt(i) != '(') && (strFunc.charAt(i) != ')') && (strFunc.charAt(i) != 'x') && (strFunc.charAt(i) != 'X')) {
                var = var + strFunc.charAt(i);
                System.out.println(var);
            } else {
                if (var != "")
                    func.add(var);
                System.out.println(strFunc.charAt(i));
                var = "";
                func.add(strFunc.charAt(i));
            }

        }
        if (var != "")
            func.add(var);
        System.out.println(func);
        System.out.println(n);
        float x = min;
        float  mult = 600/(max-min);
        for (int i = 0; i <n; i++) {
            fun = (ArrayList<Object>) func.clone();
            float y = razloj(fun,x);
            System.out.print("x = " + x);
            System.out.println("  y = " + y );
            cordX [i] = (int)( (x* mult)+300);
            cordY [i] = (int)(300- (y* mult));
            x+=inc;

        }







    }


    static Float per (String a)
    {int f=0;int v =0;
        if (a.charAt(0) == '-') {
            v = 1;

        }
        for ( int i = 0; i <a.length();i++)
        {
            if (a.charAt(i) == ','||a.charAt(i) == '.') {

                break;

            }
            f = i;
        }
        byte n =0;
        int h;
        float b = 0;

        for ( int i =f+2; i <a.length();i++){
            n++;

            h = Character.digit(a.charAt(i), 10);






            b = (float) (b+h/Math.pow(10,n));

        }
        n =0;

        for ( int i =f; i>=v;i--){

            h = Character.digit(a.charAt(i), 10);






            b = (float) (b+h*Math.pow(10,n));
            n++;

        }
        if (v==1) b=b*(-1);
        return  (Float) b;
    }

    static Float razloj(ArrayList<Object> fun, float x){

        Stack<String> move = new Stack<>();
        Stack<Float>  value = new Stack<>() ;
        Float a ;
        Float b;
        int s = 0;
        int f = fun.size() ;
        Object ch;
        String str="";
        while (fun.size() != 1){
            s = 0;
            f = fun.size() ;
            for (int i=0;i<fun.size();i++) {
                ch =  fun.get(i);
                str = ch.toString();

                if (str.equals("(")) s= i;
                if (str.equals(")")) f = i+1;
            }
            for (int i=s;i<f;i++) {
                ch = fun.get(i);
                str = ch.toString();
                while (!move.empty() && (prior(str) < prior(move.lastElement()))) {

                    switch (move.pop()) {

                        case ("^"):
                            b = value.pop();
                            a = value.pop();
                            value.add((float) Math.pow(a, b));
                            break;
                        case ("/"):
                            b = value.pop();
                            a = value.pop();
                            value.add(a / b);
                            break;
                        case ("*"):
                            b = value.pop();
                            a = value.pop();
                            value.add(a*b);
                            break;
                        case ("+"):
                            b = value.pop();
                            a = value.pop();
                            value.add(a + b);
                            break;
                        case ("-"):
                            b = value.pop();
                            a = value.pop();
                            value.add(a - b);
                            break;
                        case ("sin"):
                            b = value.pop();

                            value.add((float) Math.sin(b)) ;
                            break;
                        case ("cos"):
                            b = value.pop();

                            value.add((float) Math.cos(b)) ;
                            break;
                        case ("asin"):
                            b = value.pop();

                            value.add((float) Math.asin(b)) ;
                            break;
                        case ("acos"):
                            b = value.pop();

                            value.add((float) Math.acos(b)) ;
                            break;


                        default:
                            break;
                    }
                }


                switch (str) {
                    case ("X"):
                        value.add( x);

                        break;
                    case ("x"):
                        value.add( x);
                        break;
                    case (")"):
                        break;
                    case ("("):
                        break;
                    case ("^"):
                        move.add(str);
                        break;
                    case ("/"):
                        move.add(str);
                        break;
                    case ("*"):
                        move.add(str);
                        break;
                    case ("+"):
                        move.add(str);
                        break;
                    case ("-"):
                        move.add(str);
                        break;
                    case ("sin"):
                        move.add(str);
                        break;
                    case ("asin"):
                        move.add(str);
                        break;
                    case ("cos"):
                        move.add(str);
                        break;
                    case ("acos"):
                        move.add(str);
                        break;
                    default:
                        value.add(per(str));
                        break;
                }
            }
            while (!move.empty()){
                switch (move.pop()) {

                    case ("^"):
                        b = value.pop();
                        a = value.pop();
                        value.add((float) Math.pow(a, b));
                        break;
                    case ("/"):
                        b = value.pop();
                        a = value.pop();
                        value.add(a / b);
                        break;
                    case ("*"):
                        b = value.pop();
                        a = value.pop();
                        value.add(a * b);
                        break;
                    case ("+"):
                        b = value.pop();
                        a = value.pop();
                        value.add(a + b);
                        break;
                    case ("-"):
                        b = value.pop();
                        a = value.pop();
                        value.add(a - b);
                        break;
                    case ("sin"):
                        b = value.pop();
                        value.add((float) Math.sin(b)) ;
                        break;
                    case ("cos"):
                        b = value.pop();

                        value.add((float) Math.cos(b)) ;
                        break;
                    case ("asin"):
                        b = value.pop();

                        value.add((float) Math.asin(b)) ;
                        break;
                    case ("acos"):
                        b = value.pop();

                        value.add((float) Math.acos(b)) ;
                        break;
                    default:
                        break;
                }

            }
            fun.set (s,  value.pop());
            for (int i=s+1;i<f;i++)
                fun .remove(s+1);
            System.out.println(fun);
        }
        return (Float) fun.get(0);
    }




    public static byte prior(String s){
        byte p =127;
        switch (s) {
            case ("sin"):p=4; break;
            case ("asin"):p=4; break;
            case ("cos"):p=4; break;
            case ("acos"):p=4; break;
            case ("^"):p=3; break;
            case ("/"):p=2 ; break;
            case ("*"):p=2 ; break;
            case ("+"):p=1; break;
            case ("-"):p=1; break;
            default:   break;

        }
        return (p);
    }
    public int[] getCordY(){
        return cordY;
    }

    public int[] getCordX(){
        return cordX;
    }
    public float getMin(){
        return min;
    }
    public float getMax(){
        return max;
    }
    public int getN(){
        return n;
    }
}
