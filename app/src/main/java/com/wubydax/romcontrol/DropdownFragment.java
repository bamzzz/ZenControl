package com.wubydax.romcontrol;

import android.os.Bundle;
import android.preference.PreferenceFragment;


public class DropdownFragment extends PreferenceFragment {
	HandlePreferenceFragments hpf;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		hpf = new HandlePreferenceFragments(getActivity(), this, "dropdown_prefs");
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
