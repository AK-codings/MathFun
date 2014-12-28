package hr.foi.air.fragments;

import hr.foi.air.generator.Pitanje;
import air.testmathfun.R;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Modul_1 extends BaseFragment {
	private Pitanje pitanje;
	
	public Modul_1(Pitanje pitanje) {
		this.pitanje=pitanje;
	}
	@Override
	protected int getLayoutId() {
		return R.layout.modul_1;
	}

	@Override
	protected void initView(View view, Bundle bundle) {
		Toast.makeText(getActivity().getBaseContext(), "JOS NIJE U FUNKCIJI", Toast.LENGTH_LONG).show();
	}

}
