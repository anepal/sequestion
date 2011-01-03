package com.anjannepal.sequestion;

import java.util.Random;

public class Equation {
	//equation will be of the format ax^b + cx + d
	//b = 0,1,2,3
	int a, b, c, d, e;
	int x;
	int answer;
	Random random;
	public Equation(){
		random = new Random();
		nextEquation();
	}
	
	public void nextEquation(){
//		a = getRandomSign() * (random.nextInt() % 10); //a = -9..0..9
//		b =  random.nextInt() % 4; //b = 0,1,2,3
//		if(b < 0){
//			b = -1 * b;
//		}
//		c = getRandomSign() * (random.nextInt() % 10); //c = -9..0..9
//		d = getRandomSign() * (random.nextInt() % 51); //d = -50..0..50
		
		a = random.nextInt() % 10; //a = -9..0..9
		b =  random.nextInt() % 4; //b = 0,1,2,3
		if(b < 0){
			b = -1 * b;
		}
		c = random.nextInt() % 10; //c = -9..0..9
		d = random.nextInt() % 51; //d = -50..0..50
	}
	
	public int getRandomSign(){
		return (random.nextInt() % 2 == 0) ? 1 : -1;
			
	}
	
	public String getEquation(){
		return "" + a + "x^" + b + "  " + c + "x  " + d;
	}
	
	public int getEquationValue( int x){
		return (int) (a * Math.pow(x, b) + c * x + d); 
	}
	
	public String getQuestion(int seq_count){
		x = seq_count; //set the position upto which sequence is shown in the question
		String question = "";
		for(int i=0; i<seq_count; i++){
			question += getEquationValue(i) + ", ";
		}
		question += "?";
		answer = getEquationValue(x);
		return question;
	}
	
	public int getAnswer(){
		return answer;
	}
}
