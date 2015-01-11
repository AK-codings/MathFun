package hr.foi.air.fragments;

import java.util.Random;

import hr.foi.air.generator.Questions;
import air.testmathfun.R;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Modul_yes_no extends BaseFragment {
	private TextView tvQuestion, tvQuestionNumber;
	private int number1,number2;
	private static int solution;
	private char operator;
	private static int currentQuestion=1;
	private  Random rnd;
	private Questions question;
	
	public Modul_yes_no(Questions pitanje) {
		this.question=pitanje;
		number1=pitanje.getNumbers()[0];
		number2=pitanje.getNumbers()[1];
		operator=pitanje.getSimbols()[0];
		solution=pitanje.getNumbers()[2];
	}
	
	
	@Override
	protected int getLayoutId() {
		return R.layout.modul_1;
	}

	@Override
	protected void initView(View view, Bundle bundle) {
		rnd=new Random();
		if(currentQuestion>10) currentQuestion=1;
		if(solution!=question.getNumbers()[2]) solution=question.getNumbers()[2];
		
		tvQuestionNumber=(TextView) view.findViewById(R.id.tvBrojPitanja);
		tvQuestionNumber.setText("Pitanje broj "+ (currentQuestion++));
		
		createTask();
		
		tvQuestion=(TextView) view.findViewById(R.id.tvPitanje);
		tvQuestion.setText(number1 +""+operator+""+number2+"="+solution);
		
	}
	
	private void createTask() {
		if(rnd.nextInt(2)==1){
			if(rnd.nextInt(2)==1){
				solution=solution-rnd.nextInt(5);
			}else{
				solution=solution+rnd.nextInt(5);
			}
		}
	}


	public static boolean calculate(Questions pitanje, String odgovor){
			
			if (pitanje.getNumbers()[2]==solution && odgovor.equals("tocno")) {
				return true;
			}else if(pitanje.getNumbers()[2]!=solution && odgovor.equals("netocno")){
				return true;
			}else if(pitanje.getNumbers()[2]==solution && odgovor.equals("netocno")){
				return false;
			}else{
				return false;
			}
		}

}
