package com.anjannepal.sequestion;

import java.util.Random;

import com.anjannepal.sequestion.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Sequestion extends Activity implements OnClickListener{
	
	int SIZE_OF_QUESTION = 4;
	
	TextView questionField;
	EditText answerTextField;
	Button submitButton;
	Equation equation;
	int submittedAnswer;
	Random randomMessageFinder;
	
	public Sequestion(){
		
	}
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		randomMessageFinder = new Random();
		equation = new Equation();
		questionField = (TextView) findViewById(R.id.question);
		submitButton = (Button) findViewById(R.id.submit);
		answerTextField = (EditText) findViewById(R.id.answer);
		submitButton.setOnClickListener(this);
		String questionText = "";
		//questionText += equation.getEquation() + "\n"; 
		questionText += equation.getQuestion(SIZE_OF_QUESTION);
		questionField.setText(questionText);
	}
	
	@Override
	public void onClick(View view) {
		if(view == submitButton){
			AlertDialog.Builder dialog = new AlertDialog.Builder(this);
			try{
				submittedAnswer = Integer.parseInt(answerTextField.getText().toString());
			}
			catch(Exception e){
				dialog
				.setTitle("Input Number!")
				.setMessage("Error parsing your input, please input a number")
				.setNegativeButton("Close", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
					}
				})
				.show();
				return;
			}
			
			if(submittedAnswer == equation.getAnswer()){
				dialog
				.setTitle("Correct!")
				.setMessage("Congratulations, your answer is correct")
				.setPositiveButton("Close", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						equation.nextEquation();
						questionField.setText(equation.getQuestion(SIZE_OF_QUESTION));
						answerTextField.setText("");
					}
				})
				.show();	
			}
			else {
				int index = randomMessageFinder.nextInt() % Message.error.length;
				index = (index < 0) ? -1 * index : index;
				String randomMessage = Message.error[index];
				dialog
				.setTitle("Incorrect!")
				.setMessage(randomMessage)
				.setNegativeButton("Close", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
					}
				})
				.show();
			}
		}
	}
}