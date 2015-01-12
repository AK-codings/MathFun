package hr.foi.air.adapter;

import hr.foi.air.db.Difficulties;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * Adapter klasa koja nam sluzi za povezivanje podataka i "view" elementa
 * @author FunFactory
 *
 */
public class LevelsAdapter extends BaseAdapter {
	private Context context;
	private List<Difficulties> levels;
	/**
	 * Konstruktor
	 * @param context - objekt konteksta
	 * @param levels - lista razina
	 */
	public LevelsAdapter(Context context, List<Difficulties> levels) {
		this.context=context;
		this.levels=levels;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return levels.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return levels.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	/**
	 * Metoda koja vraèa layout za adapter
	 * @author FunFactory
	 */
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view=inflater.inflate(air.testmathfun.R.layout.odabir_razine_pojedinacni, null,false);
		
		TextView tvOpisRazine=(TextView) view.findViewById(air.testmathfun.R.id.tvOpisRazine);
		TextView tvRazina=(TextView) view.findViewById(air.testmathfun.R.id.tvRazina);
		ImageView ivSlika=(ImageView) view.findViewById(air.testmathfun.R.id.ivSlikaRazine);
		
		tvRazina.setText(levels.get(position).getName());
		tvOpisRazine.setText(levels.get(position).getOpis());
		ivSlika.setImageResource(levels.get(position).getSlika());
		
		return view;
	}

}
