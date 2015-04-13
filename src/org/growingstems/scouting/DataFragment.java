package org.growingstems.scouting;

import java.util.Locale;

import org.frc836.database.DBActivity;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;

public abstract class DataFragment extends Fragment {
	public static final int PT_EVENTS = 0;
	public static final int PT_TEAMS = 1;

	protected static final int defaultListResource = android.R.layout.simple_list_item_1;

	protected ListView dataList;
	protected AutoCompleteTextView autoText;
	protected Button loadB;
	protected boolean displayed = false;

	protected View rootView;

	protected int mSectionType;

	protected DBActivity mParent;

	public static DataFragment newInstance(int section_title, DBActivity parent) {
		DataFragment fragment;
		switch (section_title) {
		case PT_EVENTS:
			fragment = new EventDataFragment();
			break;
		case PT_TEAMS:
			fragment = new TeamDataFragment();
			break;
		default:
			return null;
		}
		fragment.mParent = parent;
		fragment.mSectionType = section_title;
		return fragment;
	}

	public static String getPageTitle(int section_title, Context context) {
		Locale l = Locale.getDefault();
		switch (section_title) {
		case PT_EVENTS:
			return context.getString(R.string.title_event_section).toUpperCase(
					l);
		case PT_TEAMS:
			return context.getString(R.string.title_team_section)
					.toUpperCase(l);
		}
		return null;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		rootView = inflater.inflate(R.layout.fragment_data, container, false);
		dataList = (ListView) rootView.findViewById(R.id.dataList);

		autoText = (AutoCompleteTextView) rootView
				.findViewById(R.id.data_team_id);
		loadB = (Button) rootView.findViewById(R.id.data_teamB);

		displayed = true;
		refreshData();
		return rootView;
	}

	protected abstract void refreshData();

}
