package hr.foi.air.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {
	protected abstract int getLayoutId();
	protected abstract void initView(View view, Bundle bundle);
	
	/**
	 * Metoda koja povezuje layout
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(getLayoutId(), null);
	}
	
	/**
	 * Metoda koja se pokreèe nakon što se poveže layout i uèita
	 */
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initView(view, savedInstanceState);
	}
}
