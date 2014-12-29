package hr.foi.air.fragments;

import java.util.Random;

import hr.foi.air.generator.Pitanje;
import air.testmathfun.R;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Modul_1 extends BaseFragment {
	private TextView tvPitanje, tvBrojTrenutnogPitanja;
	private int broj1,broj2;
	private static int rjesenje;
	private char operacija;
	private static int trenutnoPitanje=1;
	private  Random rnd;
	private Pitanje pitanje;
	
	public Modul_1(Pitanje pitanje) {
		this.pitanje=pitanje;
		broj1=pitanje.getBrojevi()[0];
		broj2=pitanje.getBrojevi()[1];
		operacija=pitanje.getSimboli()[0];
		rjesenje=pitanje.getBrojevi()[2];
	}
	
	
	@Override
	protected int getLayoutId() {
		return R.layout.modul_1;
	}

	@Override
	protected void initView(View view, Bundle bundle) {
		rnd=new Random();
		if(trenutnoPitanje>10) trenutnoPitanje=1;
		if(rjesenje!=pitanje.getBrojevi()[2]) rjesenje=pitanje.getBrojevi()[2];
		
		tvBrojTrenutnogPitanja=(TextView) view.findViewById(R.id.tvBrojPitanja);
		tvBrojTrenutnogPitanja.setText("Pitanje broj "+ (trenutnoPitanje++));
		
		createTask();
		
		tvPitanje=(TextView) view.findViewById(R.id.tvPitanje);
		tvPitanje.setText(broj1 +""+operacija+""+broj2+"="+rjesenje);
		
	}
	
	private void createTask() {
		if(rnd.nextInt(2)==1){
			if(rnd.nextInt(2)==1){
				rjesenje=rjesenje-rnd.nextInt(5);
			}else{
				rjesenje=rjesenje+rnd.nextInt(5);
			}
		}
	}


	public static boolean calculate(Pitanje pitanje, String odgovor){
			
			if (pitanje.getBrojevi()[2]==rjesenje && odgovor.equals("tocno")) {
				return true;
			}else if(pitanje.getBrojevi()[2]!=rjesenje && odgovor.equals("netocno")){
				return true;
			}else if(pitanje.getBrojevi()[2]==rjesenje && odgovor.equals("netocno")){
				return false;
			}else{
				return false;
			}
		}

}
