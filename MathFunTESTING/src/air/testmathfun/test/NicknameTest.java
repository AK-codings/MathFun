package air.testmathfun.test;

import hr.foi.air.main.Nickname;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonRectangle;

public class NicknameTest extends ActivityInstrumentationTestCase2<Nickname>{
	
	private Nickname activity;
	private ButtonRectangle button;
	private TextView textView,textView2;
	private ButtonRectangle button1;

	public NicknameTest() {
		super(Nickname.class);	
	}
	
	@Override
	protected void setUp() throws Exception {
	
		super.setUp();
		activity=getActivity();
		button = (ButtonRectangle) activity.findViewById(air.testmathfun.R.id.btKreni);
		textView =(TextView) activity.findViewById(air.testmathfun.R.id.tvImeIgracaNovog);
		textView2 =(TextView) activity.findViewById(air.testmathfun.R.id.tvIliOdaberi);
	}

	
	public void testPreconditions(){
		assertNotNull("Activity is null", activity);
		assertNotNull("Button is null", button);
		assertNotNull("TextView is null", textView);
	}

	public void testTextonTextView(){
		final String expected=activity.getString(air.testmathfun.R.string.unesite_ime_n_igraca);
		final String real=textView.getText().toString();
		assertEquals("TextView label is not correct", expected, real);
	}
	
	public void testButton(){
		final String expected = activity.getString(air.testmathfun.R.string.kreni);
		final String real=button.getText().toString();
		assertEquals("Button label is not correct", expected, real);
		button.performClick(); //button ce se sam stisnut
		//TouchUtils.clickView(this, button); //isto kao linija gore
	}
	
	public void testTextonTextView2(){
		final String expected = activity.getString(air.testmathfun.R.string.postojeci_igrac);
		final String real=textView2.getText().toString();
		assertEquals("TextView label is not correct", expected, real);
	}

}
