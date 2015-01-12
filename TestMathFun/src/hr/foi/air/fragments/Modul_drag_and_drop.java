package hr.foi.air.fragments;

import hr.foi.air.generator.Questions;
import air.testmathfun.R;
import android.os.Bundle;
import android.view.View;
//Klasa još nije gotova!
public class Modul_drag_and_drop extends BaseFragment {
	@SuppressWarnings("unused")
	private Questions question;
	
	public Modul_drag_and_drop(Questions question) {
		this.question=question;
	}
	@Override
	protected int getLayoutId() {
		return R.layout.modul_3;
	}

	@Override
	protected void initView(View view, Bundle bundle) {
		//TODO za razvoj
	}

}
