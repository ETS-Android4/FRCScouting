/*
 * Copyright 2015 Daniel Logan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.growingstems.scouting;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MatchListFragment extends DataFragment {

	private int teamNum = -1;
	private String eventName = null;

	public MatchListFragment(int team_num) {
		teamNum = team_num;
		eventName = null;
	}

	public MatchListFragment(String event_name) {
		teamNum = -1;
		eventName = event_name;
	}

	public MatchListFragment(String event_name, int team_num) {
		teamNum = team_num;
		eventName = event_name;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		rootView.findViewById(R.id.data_team_input_layout).setVisibility(
				View.GONE);

		return rootView;
	}

	@Override
	protected void refreshData() {
		if (!displayed)
			return;

		// TODO team match fetching

		boolean prac = Prefs.getPracticeMatch(mParent, false);

		List<String> matches = getMatchesForEvent(eventName, prac);
		List<String> matches2 = getMatchesForEvent(eventName, !prac);
		if (matches.size() > 1 && matches2.size() > 1) {
			matches.addAll(matches2);
		} else if (matches.size() <= 1 && matches2.size() > 1) {
			matches = matches2;
		}

		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getActivity(), defaultListResource, matches);
		dataList.setAdapter(adapter);
		dataList.setOnItemClickListener(new MatchClick());

	}

	private List<String> getMatchesForEvent(String eventName, boolean prac) {
		List<String> matches = null;
		if (eventName != null && teamNum <= 0)
			matches = mParent.getDB().getMatchesWithData(eventName, prac);

		if (matches == null)
			matches = new ArrayList<String>(1);
		if (matches.isEmpty()) {
			StringBuilder message = new StringBuilder(
					"No Matches for selected ");
			if (teamNum > 0 && eventName != null) {
				message.append("Team/Event combination");
			} else if (teamNum > 0) {
				message.append("Team");
			} else if (eventName != null) {
				message.append("Event");
			} else {
				message = new StringBuilder("Invalid Event or Team Selected");
			}
			matches.add(message.toString());
		} else {
			matches.add(0, prac ? "Practice Matches" : "Qualification Matches");
		}

		return matches;
	}

	private class MatchClick implements AdapterView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			if (view instanceof TextView) {
				String match = ((TextView) view).getText().toString();
				loadMatch(Integer.valueOf(match));
			}
		}

	}

	private void loadMatch(int match) {
		Toast.makeText(getActivity(), "Open match " + match, Toast.LENGTH_SHORT)
				.show();
		//TODO load match
	}

}
