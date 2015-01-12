package hr.foi.air.fragments;

import hr.foi.air.generator.Questions;
import air.testmathfun.R;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Modul_solve extends BaseFragment  {
	private TextView tvQuestion, tvQuestionNumber;
	private static EditText etSolution;
	private int number1,number2;
	@SuppressWarnings("unused")
	private int solution;
	private char operator;
	@SuppressWarnings("unused")
	private View view;
	private static int currentQuestion=1;
		
	/**
	 * Konstruktor
	 * @param question
	 */
	public Modul_solve(Questions question) {
		number1=question.getNumbers()[0];
		number2=question.getNumbers()[1];
		operator=question.getSimbols()[0];
		solution=question.getNumbers()[2];
	}
	
	/**
	 * Metoda za povezivanje layouta
	 */
	@Override
	protected int getLayoutId() {
		return R.layout.modul_2;
	}

	/**
	 * Metoda koja se obavlja nakon povezivanja layouta
	 */	
	@Override
	protected void initView(View view, Bundle bundle) {
		this.view=view;
		
		if(currentQuestion>10) currentQuestion=1;
		
		tvQuestion=(TextView) view.findViewById(R.id.tvPitanje);
		tvQuestionNumber=(TextView) view.findViewById(R.id.tvBrojPitanja);
		etSolution=(EditText) view.findViewById(R.id.etOdgovor);
		
		tvQuestion.setText("Koliko je "+ number1 + operator + number2 +"?");
		tvQuestionNumber.setText("Pitanje broj "+ (currentQuestion++));
				
	}

	/**
	 * Metoda koja raèuna dali je unešen odgovor toèan odnosno jednak rezultatu
	 * @param pitanje
	 * @return boolean
	 */
	public static boolean calculate(Questions pitanje)  {
		if(etSolution.getText().toString().isEmpty()) etSolution.setText("0"); 
		if (Integer.parseInt(etSolution.getText().toString()) == pitanje.getNumbers()[2]) {
			return true;
		}else{
			return false;
		}
	}

}
