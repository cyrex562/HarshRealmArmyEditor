package com.example.harshrealmarmyeditor;

import java.util.Locale;

import com.example.harshrealmarmyeditor.model.DataModelService;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {
	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	private SectionsPagerAdapter mSectionsPagerAdapter;
	/** The {@link ViewPager} that will host the section contents. */
	private ViewPager mViewPager;
	/** a reference to the data model service. */
	private DataModelService dataModelSvc;
	/** TODO. */
	private boolean isBound;
	/**
	 * TODO.
	 */
	private ServiceConnection dataModelSvcConn = new ServiceConnection() {
		/**
		 * TODO.
		 * @param className TODO.
		 * @param service TODO.
		 */
		@Override
		public void onServiceConnected(ComponentName className, 
			IBinder service) {
			dataModelSvc =
				((DataModelService.DataModelSvcBinder)service).getService();
			Log.d("ServiceConnection.onServiceConnected",
				"Connected to data model service");
		}

		/**
		 * TODO. 
		 * @param className TODO.
		 */
		public void onServiceDisconnected(ComponentName className) {
			dataModelSvc = null;
			Log.d("ServiceConnection.onServiceDisconnected",
				"Disconnected from the data model service");
		}
	};

	/**
	 * TODO.
	 * @param savedInstanceState TODO.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity_layout);

		// bind to the data model service
		doBindService();

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter =
			new SectionsPagerAdapter(getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager)findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
	}

	/**
	 * TODO.
	 * @param menu TODO.
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * Called when the action bar icon is touched
	 * @param item TODO.
	 * @return TODO.
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// icon home option
		case android.R.id.home:
			// app icon in action bar clicked return to home screen
			Intent intent = new Intent(this, MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			return true;
			// settings menu option
		case R.id.action_settings:
			intent = new Intent(this, SettingsActivity.class);
			startActivity(intent);
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * TODO.
	 */
	@Override
	public void onDestroy() {
		super.onDestroy();
		doUnbindService();
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = new DummySectionFragment();
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			View rootView =
				inflater
					.inflate(R.layout.fragment_main_dummy, container, false);
			TextView dummyTextView =
				(TextView)rootView.findViewById(R.id.section_label);
			dummyTextView.setText(Integer.toString(getArguments().getInt(
				ARG_SECTION_NUMBER)));
			return rootView;
		}
	}

	/**
	 * TODO.
	 */
	private void doBindService() {
		bindService(new Intent(MainActivity.this, DataModelService.class),
			dataModelSvcConn, Context.BIND_AUTO_CREATE);
		isBound = true;
	}

	/**
	 * TODO.
	 */
	private void doUnbindService() {
		if (isBound) {
			unbindService(dataModelSvcConn);
			isBound = false;
		}
	}
}
