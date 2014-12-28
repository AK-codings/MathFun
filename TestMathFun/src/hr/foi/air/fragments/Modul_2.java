package hr.foi.air.fragments;

import hr.foi.air.generator.Pitanje;
import air.testmathfun.R;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Modul_2 extends BaseFragment  {
	private TextView tvPitanje, tvBrojTrenutnogPitanja;
	private static EditText etOdgovor;
	private int broj1,broj2;
	private int rjesenje;
	private char operacija;
	private View view;
	private static int trenutnoPitanje=1;
		
	public Modul_2(){}
	public Modul_2(Pitanje pitanje) {
		broj1=pitanje.getBrojevi()[0];
		broj2=pitanje.getBrojevi()[1];
		operacija=pitanje.getSimboli()[0];
		rjesenje=pitanje.getBrojevi()[2];
	}

	@Override
	protected int getLayoutId() {
		return R.layout.modul_2;
	}

	@Override
	protected void initView(View view, Bundle bundle) {
		this.view=view;
		
		if(trenutnoPitanje>10) trenutnoPitanje=1;
		
		tvPitanje=(TextView) view.findViewById(R.id.tvPitanje);
		tvBrojTrenutnogPitanja=(TextView) view.findViewById(R.id.tvBrojPitanja);
		etOdgovor=(EditText) view.findViewById(R.id.etOdgovor);
		
		tvPitanje.setText("Koliko je "+ broj1 + operacija + broj2 +"?");
		tvBrojTrenutnogPitanja.setText("Pitanje broj "+ (trenutnoPitanje++));
				
	}

	
	public static boolean calculate(Pitanje pitanje)  {
		if(etOdgovor.getText().toString().isEmpty()) etOdgovor.setText("0"); 
		if (Integer.parseInt(etOdgovor.getText().toString()) == pitanje.getBrojevi()[2]) {
			return true;
		}else{
			return false;
		}
	}

}
