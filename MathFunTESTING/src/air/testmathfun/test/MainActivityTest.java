package air.testmathfun.test;

import hr.foi.air.main.MainActivity;
import android.test.ActivityInstrumentationTestCase2;

import com.gc.materialdesign.views.ButtonRectangle;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity>{
	
	private MainActivity activity;
	private ButtonRectangle button;
	private ButtonRectangle button1;

	public MainActivityTest() {
		super(MainActivity.class);	
	}

	
	@Override
	protected void setUp() throws Exception {
	
		super.setUp();
		activity=getActivity();
		button = (ButtonRectangle) activity.findViewById(air.testmathfun.R.id.btIgraj);
		button1 = (ButtonRectangle) activity.findViewById(air.testmathfun.R.id.btPravila);
	}
	
	public void testPreconditions(){
		assertNotNull("Activity is null", activity);
		assertNotNull("Button is null", button);
	}
	
	public void testButton(){
		final String expected = activity.getString(air.testmathfun.R.string.igraj);
		final String real=button.getText().toString();
		assertEquals("Button label is not correct", expected, real);
		button.performClick(); //button ce se sam stisnut
		//TouchUtils.clickView(this, button); //isto kao linija gore
	}
	
	public void testButton1(){
		final String expected = activity.getString(air.testmathfun.R.string.pravila1);
		final String real=button1.getText().toString();
		assertEquals("Button label is not correct", expected, real);
		button.performClick(); //button ce se sam stisnut
		//TouchUtils.clickView(this, button1); //isto kao linija gore
	}
	
}
