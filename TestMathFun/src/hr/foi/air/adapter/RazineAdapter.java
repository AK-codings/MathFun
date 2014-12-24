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

public class RazineAdapter extends BaseAdapter {
	private Context context;
	private List<Difficulties> razine;
	
	public RazineAdapter(Context context, List<Difficulties> razine) {
		this.context=context;
		this.razine=razine;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return razine.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return razine.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view=inflater.inflate(air.testmathfun.R.layout.odabir_razine_pojedinacni, null,false);
		
		TextView tvOpisRazine=(TextView) view.findViewById(air.testmathfun.R.id.tvOpisRazine);
		TextView tvRazina=(TextView) view.findViewById(air.testmathfun.R.id.tvRazina);
		ImageView ivSlika=(ImageView) view.findViewById(air.testmathfun.R.id.ivSlikaRazine);
		
		tvRazina.setText(razine.get(position).getName());
		tvOpisRazine.setText(razine.get(position).getOpis());
		ivSlika.setImageResource(razine.get(position).getSlika());
		
		return view;
	}

}
