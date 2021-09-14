package com.example.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
public class MainActivity extends Activity implements View.OnClickListener{
    private enum Operator{
        Plus,Subtract,Multiply,Divide,Equal,Mod,Power
    }
    TextView result1;
    TextView result2;
    //Instance Variable
    private String numbercurr;
    private String numberleft;
    private String numberright;
    private Operator operatorcurrent;
    private StringBuffer temp=new StringBuffer("");
    private int result;
    private boolean invalid=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numbercurr="";
        result=0;
        result1=findViewById(R.id.bres);
        result2=findViewById(R.id.bres2);
        findViewById(R.id.beql).setOnClickListener(MainActivity.this);

        findViewById(R.id.b0).setOnClickListener(MainActivity.this);
        findViewById(R.id.b1).setOnClickListener(MainActivity.this);
        findViewById(R.id.b2).setOnClickListener(MainActivity.this);
        findViewById(R.id.b3).setOnClickListener(MainActivity.this);
        findViewById(R.id.b4).setOnClickListener(MainActivity.this);
        findViewById(R.id.b5).setOnClickListener(MainActivity.this);
        findViewById(R.id.b6).setOnClickListener(MainActivity.this);
        findViewById(R.id.b7).setOnClickListener(MainActivity.this);
        findViewById(R.id.b8).setOnClickListener(MainActivity.this);
        findViewById(R.id.b9).setOnClickListener(MainActivity.this);
        findViewById(R.id.bmul).setOnClickListener(MainActivity.this);
        findViewById(R.id.badd).setOnClickListener(MainActivity.this);
        findViewById(R.id.bsub).setOnClickListener(MainActivity.this);
        findViewById(R.id.bdiv).setOnClickListener(MainActivity.this);
        findViewById(R.id.bcls).setOnClickListener(MainActivity.this);
        findViewById(R.id.bperc).setOnClickListener(MainActivity.this);
        findViewById(R.id.bpow).setOnClickListener(MainActivity.this);

    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.b0:
                numberTapped(0);
                break;
            case R.id.b1:
                numberTapped(1);
                break;
            case R.id.b2:
                numberTapped(2);
                break;
            case R.id.b3:
                numberTapped(3);
                break;
            case R.id.b4:
                numberTapped(4);
                break;
            case R.id.b5:
                numberTapped(5);
                break;
            case R.id.b6:
                numberTapped(6);
                break;
            case R.id.b7:
                numberTapped(7);
                break;
            case R.id.b8:
                numberTapped(8);
                break;
            case R.id.b9:
                numberTapped(9);
                break;
            case R.id.badd:
                operatortapped(Operator.Plus);
                break;
            case R.id.bsub:
                operatortapped(Operator.Subtract);
                break;
            case R.id.bmul:
                operatortapped(Operator.Multiply);
                break;
            case R.id.bdiv:
                operatortapped(Operator.Divide);
                break;
            case R.id.beql:
                operatortapped(Operator.Equal);
                break;
            case R.id.bperc:
                operatortapped(Operator.Mod);
                break;
            case R.id.bpow:
                operatortapped(Operator.Power);
                break;
            case R.id.bcls:
                reset();
                break;


        }
    }
    private void numberTapped(int n){
        if(invalid){
            numbercurr="";
            reset();
            invalid=false;
        }
        numbercurr=numbercurr+String.valueOf(n);
        temp.append(String.valueOf(n));
        result1.setText(temp);

    }
    private void reset(){

        result2.setText("Last Result = "+"0");
        temp.setLength(0);
        if(!invalid){
        result1.setText("0");}
        operatorcurrent=null;
        numbercurr="";
    }
    private  void operatortapped(Operator t){
        if(operatorcurrent!=null && numbercurr!=""){
            //  Plus,Subtract,Multiply,Divide,Equal
            numberright=numbercurr;
            numbercurr="";

            switch (operatorcurrent){
                case Plus:

                    result=Integer.parseInt(numberleft)+Integer.parseInt(numberright);
                    break;
                case Subtract:

                    result=Integer.parseInt(numberleft)-Integer.parseInt(numberright);
                    break;
                case Multiply:

                    result=Integer.parseInt(numberleft)*Integer.parseInt(numberright);
                    break;
                case Divide:
                    if(Integer.parseInt(numberright)==0) {invalid=true;
                        result1.setText("Can't Divide Number By 0");
                    }
                    else
                    result=Integer.parseInt(numberleft)/Integer.parseInt(numberright);
                    break;



                case Power:

                    result=(int)(Math.pow(Integer.parseInt(numberleft),Integer.parseInt(numberright)));
                    break;
                case Mod:
                    if(Integer.parseInt(numberright)==0) {invalid=true;
                        result1.setText("Can't Divide Number By 0");
                    }
                    else
                    result=Integer.parseInt(numberleft)%Integer.parseInt(numberright);
                    break;

            }
            numberleft=String.valueOf(result);
            temp.setLength(0);
            temp.append(numberleft);
//            result1.setText(temp);
        }
        else{



            numberleft=numbercurr;
            numbercurr="";
        }
        switch (t){
            case Plus:temp.append('+');break;

            case Power:temp.append('^');break;
            case Divide:temp.append('/');break;
            case Multiply:temp.append('*');break;
            case Subtract:temp.append('-');break;
            case Mod:temp.append('%');break;
        }
        if(!invalid){
        result1.setText(temp);
        operatorcurrent=t;
        if(operatorcurrent.name().equals("Equal")){
            result2.setText("Last Result = "+result1.getText());
            temp.setLength(0);
            result1.setText("0");
            numbercurr="";
            operatorcurrent=null;
        }}
    }
}