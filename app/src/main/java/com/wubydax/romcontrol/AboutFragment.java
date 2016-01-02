package com.wubydax.romcontrol;

import android.os.Bundle;
import android.preference.PreferenceFragment;


public class AboutFragment extends PreferenceFragment {
	HandlePreferenceFragments hpf;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		hpf = new HandlePreferenceFragments(getActivity(), this, "about_prefs");
/*		ImageView image = new ImageView(getActivity());
		image.setImageResource(R.drawable.logo_dark);
		image.setLayoutParams(new FrameLayout.LayoutParams(120, 30, Gravity.CENTER));*/
	}

	@Override
	public void onResume() {
		super.onResume();
		hpf.onResumeFragment();
	}

	@Override
	public void onPause() {
		super.onPause();
		hpf.onPauseFragment();
	}


}
