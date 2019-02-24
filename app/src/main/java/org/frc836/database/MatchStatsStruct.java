/*
-----------------------------------------------------
StatsStructGen.py 1.1

This file was autogenerated with run cmd:
  "gen_scripts/StatsStructGen.py --packagename=org.frc836.database --classname=MatchStatsStruct --tablename=fact_match_data_2019 --infile=FRC_Scouting_Server/scouting.sql --outfile=app/src/main/java/org/frc836/database/MatchStatsStruct.java"

python version info:
  3.7.2 (tags/v3.7.2:9a3ffc0492, Dec 23 2018, 22:20:52) [MSC v.1916 32 bit (Intel)]

Please take heed of modifying unnecessarily
-----------------------------------------------------
*/

package org.frc836.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import org.frc836.database.FRCScoutingContract.FACT_MATCH_DATA_2019_Entry;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MatchStatsStruct {


	public String event_id;
	public int team_id;
	public int match_id;
	public boolean practice_match;
	public String position_id;
	public boolean prematch_robot_cargo;
	public boolean prematch_robot_hatch;
	public boolean prematch_hab2_left;
	public int prematch_hab_level;
	public boolean sandstorm_bonus;
	public int sandstorm_hatch_ship;
	public int sandstorm_hatch_rocket_1;
	public int sandstorm_hatch_rocket_2;
	public int sandstorm_hatch_rocket_3;
	public int sandstorm_hatch_dropped;
	public int sandstorm_cargo_ship;
	public int sandstorm_cargo_rocket_1;
	public int sandstorm_cargo_rocket_2;
	public int sandstorm_cargo_rocket_3;
	public int sandstorm_cargo_dropped;
	public int hatch_ship;
	public int hatch_rocket_1;
	public int hatch_rocket_2;
	public int hatch_rocket_3;
	public int hatch_dropped;
	public int cargo_ship;
	public int cargo_rocket_1;
	public int cargo_rocket_2;
	public int cargo_rocket_3;
	public int cargo_dropped;
	public int hab_climb_level;
	public int hab_climb_level_attempted;
	public boolean hab_climb_2_left;
	public boolean floor_pickup_cargo;
	public boolean floor_pickup_hatch;
	public boolean foul;
	public boolean yellow_card;
	public boolean red_card;
	public boolean tip_over;
	public String notes;
	

	public static final String TABLE_NAME = FACT_MATCH_DATA_2019_Entry.TABLE_NAME;
	public static final String COLUMN_NAME_ID = FACT_MATCH_DATA_2019_Entry.COLUMN_NAME_ID;
	public static final String COLUMN_NAME_EVENT_ID = FACT_MATCH_DATA_2019_Entry.COLUMN_NAME_EVENT_ID;
	public static final String COLUMN_NAME_TEAM_ID = FACT_MATCH_DATA_2019_Entry.COLUMN_NAME_TEAM_ID;
	public static final String COLUMN_NAME_MATCH_ID = FACT_MATCH_DATA_2019_Entry.COLUMN_NAME_MATCH_ID;
	public static final String COLUMN_NAME_PRACTICE_MATCH = FACT_MATCH_DATA_2019_Entry.COLUMN_NAME_PRACTICE_MATCH;
	public static final String COLUMN_NAME_POSITION_ID = FACT_MATCH_DATA_2019_Entry.COLUMN_NAME_POSITION_ID;
	public static final String COLUMN_NAME_PREMATCH_ROBOT_CARGO = FACT_MATCH_DATA_2019_Entry.COLUMN_NAME_PREMATCH_ROBOT_CARGO;
	public static final String COLUMN_NAME_PREMATCH_ROBOT_HATCH = FACT_MATCH_DATA_2019_Entry.COLUMN_NAME_PREMATCH_ROBOT_HATCH;
	public static final String COLUMN_NAME_PREMATCH_HAB2_LEFT = FACT_MATCH_DATA_2019_Entry.COLUMN_NAME_PREMATCH_HAB2_LEFT;
	public static final String COLUMN_NAME_PREMATCH_HAB_LEVEL = FACT_MATCH_DATA_2019_Entry.COLUMN_NAME_PREMATCH_HAB_LEVEL;
	public static final String COLUMN_NAME_SANDSTORM_BONUS = FACT_MATCH_DATA_2019_Entry.COLUMN_NAME_SANDSTORM_BONUS;
	public static final String COLUMN_NAME_SANDSTORM_HATCH_SHIP = FACT_MATCH_DATA_2019_Entry.COLUMN_NAME_SANDSTORM_HATCH_SHIP;
	public static final String COLUMN_NAME_SANDSTORM_HATCH_ROCKET_1 = FACT_MATCH_DATA_2019_Entry.COLUMN_NAME_SANDSTORM_HATCH_ROCKET_1;
	public static final String COLUMN_NAME_SANDSTORM_HATCH_ROCKET_2 = FACT_MATCH_DATA_2019_Entry.COLUMN_NAME_SANDSTORM_HATCH_ROCKET_2;
	public static final String COLUMN_NAME_SANDSTORM_HATCH_ROCKET_3 = FACT_MATCH_DATA_2019_Entry.COLUMN_NAME_SANDSTORM_HATCH_ROCKET_3;
	public static final String COLUMN_NAME_SANDSTORM_HATCH_DROPPED = FACT_MATCH_DATA_2019_Entry.COLUMN_NAME_SANDSTORM_HATCH_DROPPED;
	public static final String COLUMN_NAME_SANDSTORM_CARGO_SHIP = FACT_MATCH_DATA_2019_Entry.COLUMN_NAME_SANDSTORM_CARGO_SHIP;
	public static final String COLUMN_NAME_SANDSTORM_CARGO_ROCKET_1 = FACT_MATCH_DATA_2019_Entry.COLUMN_NAME_SANDSTORM_CARGO_ROCKET_1;
	public static final String COLUMN_NAME_SANDSTORM_CARGO_ROCKET_2 = FACT_MATCH_DATA_2019_Entry.COLUMN_NAME_SANDSTORM_CARGO_ROCKET_2;
	public static final String COLUMN_NAME_SANDSTORM_CARGO_ROCKET_3 = FACT_MATCH_DATA_2019_Entry.COLUMN_NAME_SANDSTORM_CARGO_ROCKET_3;
	public static final String COLUMN_NAME_SANDSTORM_CARGO_DROPPED = FACT_MATCH_DATA_2019_Entry.COLUMN_NAME_SANDSTORM_CARGO_DROPPED;
	public static final String COLUMN_NAME_HATCH_SHIP = FACT_MATCH_DATA_2019_Entry.COLUMN_NAME_HATCH_SHIP;
	public static final String COLUMN_NAME_HATCH_ROCKET_1 = FACT_MATCH_DATA_2019_Entry.COLUMN_NAME_HATCH_ROCKET_1;
	public static final String COLUMN_NAME_HATCH_ROCKET_2 = FACT_MATCH_DATA_2019_Entry.COLUMN_NAME_HATCH_ROCKET_2;
	public static final String COLUMN_NAME_HATCH_ROCKET_3 = FACT_MATCH_DATA_2019_Entry.COLUMN_NAME_HATCH_ROCKET_3;
	public static final String COLUMN_NAME_HATCH_DROPPED = FACT_MATCH_DATA_2019_Entry.COLUMN_NAME_HATCH_DROPPED;
	public static final String COLUMN_NAME_CARGO_SHIP = FACT_MATCH_DATA_2019_Entry.COLUMN_NAME_CARGO_SHIP;
	public static final String COLUMN_NAME_CARGO_ROCKET_1 = FACT_MATCH_DATA_2019_Entry.COLUMN_NAME_CARGO_ROCKET_1;
	public static final String COLUMN_NAME_CARGO_ROCKET_2 = FACT_MATCH_DATA_2019_Entry.COLUMN_NAME_CARGO_ROCKET_2;
	public static final String COLUMN_NAME_CARGO_ROCKET_3 = FACT_MATCH_DATA_2019_Entry.COLUMN_NAME_CARGO_ROCKET_3;
	public static final String COLUMN_NAME_CARGO_DROPPED = FACT_MATCH_DATA_2019_Entry.COLUMN_NAME_CARGO_DROPPED;
	public static final String COLUMN_NAME_HAB_CLIMB_LEVEL = FACT_MATCH_DATA_2019_Entry.COLUMN_NAME_HAB_CLIMB_LEVEL;
	public static final String COLUMN_NAME_HAB_CLIMB_LEVEL_ATTEMPTED = FACT_MATCH_DATA_2019_Entry.COLUMN_NAME_HAB_CLIMB_LEVEL_ATTEMPTED;
	public static final String COLUMN_NAME_HAB_CLIMB_2_LEFT = FACT_MATCH_DATA_2019_Entry.COLUMN_NAME_HAB_CLIMB_2_LEFT;
	public static final String COLUMN_NAME_FLOOR_PICKUP_CARGO = FACT_MATCH_DATA_2019_Entry.COLUMN_NAME_FLOOR_PICKUP_CARGO;
	public static final String COLUMN_NAME_FLOOR_PICKUP_HATCH = FACT_MATCH_DATA_2019_Entry.COLUMN_NAME_FLOOR_PICKUP_HATCH;
	public static final String COLUMN_NAME_FOUL = FACT_MATCH_DATA_2019_Entry.COLUMN_NAME_FOUL;
	public static final String COLUMN_NAME_YELLOW_CARD = FACT_MATCH_DATA_2019_Entry.COLUMN_NAME_YELLOW_CARD;
	public static final String COLUMN_NAME_RED_CARD = FACT_MATCH_DATA_2019_Entry.COLUMN_NAME_RED_CARD;
	public static final String COLUMN_NAME_TIP_OVER = FACT_MATCH_DATA_2019_Entry.COLUMN_NAME_TIP_OVER;
	public static final String COLUMN_NAME_NOTES = FACT_MATCH_DATA_2019_Entry.COLUMN_NAME_NOTES;
	public static final String COLUMN_NAME_INVALID = FACT_MATCH_DATA_2019_Entry.COLUMN_NAME_INVALID;
	public static final String COLUMN_NAME_TIMESTAMP = FACT_MATCH_DATA_2019_Entry.COLUMN_NAME_TIMESTAMP;
	

	public MatchStatsStruct() {
		init();
	}
	
	public void init() {
		event_id = "";
		team_id = 0;
		match_id = 0;
		practice_match = false;
		position_id = "Red 1";
		prematch_robot_cargo = false;
		prematch_robot_hatch = false;
		prematch_hab2_left = false;
		prematch_hab_level = 0;
		sandstorm_bonus = false;
		sandstorm_hatch_ship = 0;
		sandstorm_hatch_rocket_1 = 0;
		sandstorm_hatch_rocket_2 = 0;
		sandstorm_hatch_rocket_3 = 0;
		sandstorm_hatch_dropped = 0;
		sandstorm_cargo_ship = 0;
		sandstorm_cargo_rocket_1 = 0;
		sandstorm_cargo_rocket_2 = 0;
		sandstorm_cargo_rocket_3 = 0;
		sandstorm_cargo_dropped = 0;
		hatch_ship = 0;
		hatch_rocket_1 = 0;
		hatch_rocket_2 = 0;
		hatch_rocket_3 = 0;
		hatch_dropped = 0;
		cargo_ship = 0;
		cargo_rocket_1 = 0;
		cargo_rocket_2 = 0;
		cargo_rocket_3 = 0;
		cargo_dropped = 0;
		hab_climb_level = 0;
		hab_climb_level_attempted = 0;
		hab_climb_2_left = false;
		floor_pickup_cargo = false;
		floor_pickup_hatch = false;
		foul = false;
		yellow_card = false;
		red_card = false;
		tip_over = false;
		notes = "";
	}
	
	public MatchStatsStruct(int team, String event, int match) {
		init();
		this.team_id = team;
		this.event_id = event;
		this.prematch_hab_level = match;
	}
	
	public MatchStatsStruct(int team, String event, int match, boolean practice) {
		init();
		this.team_id = team;
		this.event_id = event;
		this.prematch_hab_level = match;
		this.practice_match = practice;
	}

	public ContentValues getValues(DB db, SQLiteDatabase database) {
		ContentValues vals = new ContentValues();
		long ev = db.getEventIDFromName(event_id, database);
		vals.put(COLUMN_NAME_ID, ev * 10000000 + match_id * 10000 + team_id);
		vals.put(COLUMN_NAME_EVENT_ID, ev);
		vals.put(COLUMN_NAME_TEAM_ID, team_id);
		vals.put(COLUMN_NAME_MATCH_ID, match_id);
		vals.put(COLUMN_NAME_PRACTICE_MATCH, practice_match ? 1 : 0);
		vals.put(COLUMN_NAME_POSITION_ID, db.getPosIDFromName(position_id, database));
		vals.put(COLUMN_NAME_PREMATCH_ROBOT_CARGO, prematch_robot_cargo ? 1 : 0);
		vals.put(COLUMN_NAME_PREMATCH_ROBOT_HATCH, prematch_robot_hatch ? 1 : 0);
		vals.put(COLUMN_NAME_PREMATCH_HAB2_LEFT, prematch_hab2_left ? 1 : 0);
		vals.put(COLUMN_NAME_PREMATCH_HAB_LEVEL, prematch_hab_level);
		vals.put(COLUMN_NAME_SANDSTORM_BONUS, sandstorm_bonus ? 1 : 0);
		vals.put(COLUMN_NAME_SANDSTORM_HATCH_SHIP, sandstorm_hatch_ship);
		vals.put(COLUMN_NAME_SANDSTORM_HATCH_ROCKET_1, sandstorm_hatch_rocket_1);
		vals.put(COLUMN_NAME_SANDSTORM_HATCH_ROCKET_2, sandstorm_hatch_rocket_2);
		vals.put(COLUMN_NAME_SANDSTORM_HATCH_ROCKET_3, sandstorm_hatch_rocket_3);
		vals.put(COLUMN_NAME_SANDSTORM_HATCH_DROPPED, sandstorm_hatch_dropped);
		vals.put(COLUMN_NAME_SANDSTORM_CARGO_SHIP, sandstorm_cargo_ship);
		vals.put(COLUMN_NAME_SANDSTORM_CARGO_ROCKET_1, sandstorm_cargo_rocket_1);
		vals.put(COLUMN_NAME_SANDSTORM_CARGO_ROCKET_2, sandstorm_cargo_rocket_2);
		vals.put(COLUMN_NAME_SANDSTORM_CARGO_ROCKET_3, sandstorm_cargo_rocket_3);
		vals.put(COLUMN_NAME_SANDSTORM_CARGO_DROPPED, sandstorm_cargo_dropped);
		vals.put(COLUMN_NAME_HATCH_SHIP, hatch_ship);
		vals.put(COLUMN_NAME_HATCH_ROCKET_1, hatch_rocket_1);
		vals.put(COLUMN_NAME_HATCH_ROCKET_2, hatch_rocket_2);
		vals.put(COLUMN_NAME_HATCH_ROCKET_3, hatch_rocket_3);
		vals.put(COLUMN_NAME_HATCH_DROPPED, hatch_dropped);
		vals.put(COLUMN_NAME_CARGO_SHIP, cargo_ship);
		vals.put(COLUMN_NAME_CARGO_ROCKET_1, cargo_rocket_1);
		vals.put(COLUMN_NAME_CARGO_ROCKET_2, cargo_rocket_2);
		vals.put(COLUMN_NAME_CARGO_ROCKET_3, cargo_rocket_3);
		vals.put(COLUMN_NAME_CARGO_DROPPED, cargo_dropped);
		vals.put(COLUMN_NAME_HAB_CLIMB_LEVEL, hab_climb_level);
		vals.put(COLUMN_NAME_HAB_CLIMB_LEVEL_ATTEMPTED, hab_climb_level_attempted);
		vals.put(COLUMN_NAME_HAB_CLIMB_2_LEFT, hab_climb_2_left ? 1 : 0);
		vals.put(COLUMN_NAME_FLOOR_PICKUP_CARGO, floor_pickup_cargo ? 1 : 0);
		vals.put(COLUMN_NAME_FLOOR_PICKUP_HATCH, floor_pickup_hatch ? 1 : 0);
		vals.put(COLUMN_NAME_FOUL, foul ? 1 : 0);
		vals.put(COLUMN_NAME_YELLOW_CARD, yellow_card ? 1 : 0);
		vals.put(COLUMN_NAME_RED_CARD, red_card ? 1 : 0);
		vals.put(COLUMN_NAME_TIP_OVER, tip_over ? 1 : 0);
		vals.put(COLUMN_NAME_NOTES, notes);
		vals.put(COLUMN_NAME_INVALID, 1);
	
		return vals;
	}

	public void fromCursor(Cursor c, DB db, SQLiteDatabase database) {
		fromCursor(c, db, database, 0);
	}
	
	public void fromCursor(Cursor c, DB db, SQLiteDatabase database, int pos) {
		c.moveToPosition(pos);
		event_id = DB.getEventNameFromID(c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_EVENT_ID)), database);
		team_id = c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_TEAM_ID));
		match_id = c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_MATCH_ID));
		practice_match = c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_PRACTICE_MATCH)) != 0;
		position_id = DB.getPosNameFromID(c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_POSITION_ID)), database);
		prematch_robot_cargo = c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_PREMATCH_ROBOT_CARGO)) != 0;
		prematch_robot_hatch = c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_PREMATCH_ROBOT_HATCH)) != 0;
		prematch_hab2_left = c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_PREMATCH_HAB2_LEFT)) != 0;
		prematch_hab_level = c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_PREMATCH_HAB_LEVEL));
		sandstorm_bonus = c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_SANDSTORM_BONUS)) != 0;
		sandstorm_hatch_ship = c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_SANDSTORM_HATCH_SHIP));
		sandstorm_hatch_rocket_1 = c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_SANDSTORM_HATCH_ROCKET_1));
		sandstorm_hatch_rocket_2 = c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_SANDSTORM_HATCH_ROCKET_2));
		sandstorm_hatch_rocket_3 = c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_SANDSTORM_HATCH_ROCKET_3));
		sandstorm_hatch_dropped = c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_SANDSTORM_HATCH_DROPPED));
		sandstorm_cargo_ship = c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_SANDSTORM_CARGO_SHIP));
		sandstorm_cargo_rocket_1 = c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_SANDSTORM_CARGO_ROCKET_1));
		sandstorm_cargo_rocket_2 = c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_SANDSTORM_CARGO_ROCKET_2));
		sandstorm_cargo_rocket_3 = c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_SANDSTORM_CARGO_ROCKET_3));
		sandstorm_cargo_dropped = c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_SANDSTORM_CARGO_DROPPED));
		hatch_ship = c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_HATCH_SHIP));
		hatch_rocket_1 = c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_HATCH_ROCKET_1));
		hatch_rocket_2 = c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_HATCH_ROCKET_2));
		hatch_rocket_3 = c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_HATCH_ROCKET_3));
		hatch_dropped = c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_HATCH_DROPPED));
		cargo_ship = c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_CARGO_SHIP));
		cargo_rocket_1 = c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_CARGO_ROCKET_1));
		cargo_rocket_2 = c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_CARGO_ROCKET_2));
		cargo_rocket_3 = c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_CARGO_ROCKET_3));
		cargo_dropped = c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_CARGO_DROPPED));
		hab_climb_level = c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_HAB_CLIMB_LEVEL));
		hab_climb_level_attempted = c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_HAB_CLIMB_LEVEL_ATTEMPTED));
		hab_climb_2_left = c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_HAB_CLIMB_2_LEFT)) != 0;
		floor_pickup_cargo = c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_FLOOR_PICKUP_CARGO)) != 0;
		floor_pickup_hatch = c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_FLOOR_PICKUP_HATCH)) != 0;
		foul = c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_FOUL)) != 0;
		yellow_card = c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_YELLOW_CARD)) != 0;
		red_card = c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_RED_CARD)) != 0;
		tip_over = c.getInt(c.getColumnIndexOrThrow(COLUMN_NAME_TIP_OVER)) != 0;
		notes = c.getString(c.getColumnIndexOrThrow(COLUMN_NAME_NOTES));
	}

	public String[] getProjection() {
		List<String> temp = new ArrayList<String>(40);
		temp.add(COLUMN_NAME_EVENT_ID);
		temp.add(COLUMN_NAME_TEAM_ID);
		temp.add(COLUMN_NAME_MATCH_ID);
		temp.add(COLUMN_NAME_PRACTICE_MATCH);
		temp.add(COLUMN_NAME_POSITION_ID);
		temp.add(COLUMN_NAME_PREMATCH_ROBOT_CARGO);
		temp.add(COLUMN_NAME_PREMATCH_ROBOT_HATCH);
		temp.add(COLUMN_NAME_PREMATCH_HAB2_LEFT);
		temp.add(COLUMN_NAME_PREMATCH_HAB_LEVEL);
		temp.add(COLUMN_NAME_SANDSTORM_BONUS);
		temp.add(COLUMN_NAME_SANDSTORM_HATCH_SHIP);
		temp.add(COLUMN_NAME_SANDSTORM_HATCH_ROCKET_1);
		temp.add(COLUMN_NAME_SANDSTORM_HATCH_ROCKET_2);
		temp.add(COLUMN_NAME_SANDSTORM_HATCH_ROCKET_3);
		temp.add(COLUMN_NAME_SANDSTORM_HATCH_DROPPED);
		temp.add(COLUMN_NAME_SANDSTORM_CARGO_SHIP);
		temp.add(COLUMN_NAME_SANDSTORM_CARGO_ROCKET_1);
		temp.add(COLUMN_NAME_SANDSTORM_CARGO_ROCKET_2);
		temp.add(COLUMN_NAME_SANDSTORM_CARGO_ROCKET_3);
		temp.add(COLUMN_NAME_SANDSTORM_CARGO_DROPPED);
		temp.add(COLUMN_NAME_HATCH_SHIP);
		temp.add(COLUMN_NAME_HATCH_ROCKET_1);
		temp.add(COLUMN_NAME_HATCH_ROCKET_2);
		temp.add(COLUMN_NAME_HATCH_ROCKET_3);
		temp.add(COLUMN_NAME_HATCH_DROPPED);
		temp.add(COLUMN_NAME_CARGO_SHIP);
		temp.add(COLUMN_NAME_CARGO_ROCKET_1);
		temp.add(COLUMN_NAME_CARGO_ROCKET_2);
		temp.add(COLUMN_NAME_CARGO_ROCKET_3);
		temp.add(COLUMN_NAME_CARGO_DROPPED);
		temp.add(COLUMN_NAME_HAB_CLIMB_LEVEL);
		temp.add(COLUMN_NAME_HAB_CLIMB_LEVEL_ATTEMPTED);
		temp.add(COLUMN_NAME_HAB_CLIMB_2_LEFT);
		temp.add(COLUMN_NAME_FLOOR_PICKUP_CARGO);
		temp.add(COLUMN_NAME_FLOOR_PICKUP_HATCH);
		temp.add(COLUMN_NAME_FOUL);
		temp.add(COLUMN_NAME_YELLOW_CARD);
		temp.add(COLUMN_NAME_RED_CARD);
		temp.add(COLUMN_NAME_TIP_OVER);
		temp.add(COLUMN_NAME_NOTES);
		String[] projection = new String[temp.size()];
		return temp.toArray(projection);
	}

	public boolean isTextField(String column_name) {
		if (COLUMN_NAME_NOTES.equalsIgnoreCase(column_name)) return true;
	
		return false;
	}

	public boolean needsConvertedToText(String column_name) {
		if (COLUMN_NAME_EVENT_ID.equalsIgnoreCase(column_name)) return true;
	
		if (COLUMN_NAME_POSITION_ID.equalsIgnoreCase(column_name)) return true;
	
		return false;
	}

	public ContentValues jsonToCV(JSONObject json) throws JSONException {
		ContentValues vals = new ContentValues();
		vals.put(COLUMN_NAME_ID, json.has(COLUMN_NAME_ID) ? json.getInt(COLUMN_NAME_ID) : 0);
		vals.put(COLUMN_NAME_EVENT_ID, json.has(COLUMN_NAME_EVENT_ID) ? json.getInt(COLUMN_NAME_EVENT_ID) : 0);
		vals.put(COLUMN_NAME_TEAM_ID, json.has(COLUMN_NAME_TEAM_ID) ? json.getInt(COLUMN_NAME_TEAM_ID) : 0);
		vals.put(COLUMN_NAME_MATCH_ID, json.has(COLUMN_NAME_MATCH_ID) ? json.getInt(COLUMN_NAME_MATCH_ID) : 0);
		vals.put(COLUMN_NAME_PRACTICE_MATCH, json.has(COLUMN_NAME_PRACTICE_MATCH) ? json.getInt(COLUMN_NAME_PRACTICE_MATCH) : 0);
		vals.put(COLUMN_NAME_POSITION_ID, json.has(COLUMN_NAME_POSITION_ID) ? json.getInt(COLUMN_NAME_POSITION_ID) : 0);
		vals.put(COLUMN_NAME_PREMATCH_ROBOT_CARGO, json.has(COLUMN_NAME_PREMATCH_ROBOT_CARGO) ? json.getInt(COLUMN_NAME_PREMATCH_ROBOT_CARGO) : 0);
		vals.put(COLUMN_NAME_PREMATCH_ROBOT_HATCH, json.has(COLUMN_NAME_PREMATCH_ROBOT_HATCH) ? json.getInt(COLUMN_NAME_PREMATCH_ROBOT_HATCH) : 0);
		vals.put(COLUMN_NAME_PREMATCH_HAB2_LEFT, json.has(COLUMN_NAME_PREMATCH_HAB2_LEFT) ? json.getInt(COLUMN_NAME_PREMATCH_HAB2_LEFT) : 0);
		vals.put(COLUMN_NAME_PREMATCH_HAB_LEVEL, json.has(COLUMN_NAME_PREMATCH_HAB_LEVEL) ? json.getInt(COLUMN_NAME_PREMATCH_HAB_LEVEL) : 0);
		vals.put(COLUMN_NAME_SANDSTORM_BONUS, json.has(COLUMN_NAME_SANDSTORM_BONUS) ? json.getInt(COLUMN_NAME_SANDSTORM_BONUS) : 0);
		vals.put(COLUMN_NAME_SANDSTORM_HATCH_SHIP, json.has(COLUMN_NAME_SANDSTORM_HATCH_SHIP) ? json.getInt(COLUMN_NAME_SANDSTORM_HATCH_SHIP) : 0);
		vals.put(COLUMN_NAME_SANDSTORM_HATCH_ROCKET_1, json.has(COLUMN_NAME_SANDSTORM_HATCH_ROCKET_1) ? json.getInt(COLUMN_NAME_SANDSTORM_HATCH_ROCKET_1) : 0);
		vals.put(COLUMN_NAME_SANDSTORM_HATCH_ROCKET_2, json.has(COLUMN_NAME_SANDSTORM_HATCH_ROCKET_2) ? json.getInt(COLUMN_NAME_SANDSTORM_HATCH_ROCKET_2) : 0);
		vals.put(COLUMN_NAME_SANDSTORM_HATCH_ROCKET_3, json.has(COLUMN_NAME_SANDSTORM_HATCH_ROCKET_3) ? json.getInt(COLUMN_NAME_SANDSTORM_HATCH_ROCKET_3) : 0);
		vals.put(COLUMN_NAME_SANDSTORM_HATCH_DROPPED, json.has(COLUMN_NAME_SANDSTORM_HATCH_DROPPED) ? json.getInt(COLUMN_NAME_SANDSTORM_HATCH_DROPPED) : 0);
		vals.put(COLUMN_NAME_SANDSTORM_CARGO_SHIP, json.has(COLUMN_NAME_SANDSTORM_CARGO_SHIP) ? json.getInt(COLUMN_NAME_SANDSTORM_CARGO_SHIP) : 0);
		vals.put(COLUMN_NAME_SANDSTORM_CARGO_ROCKET_1, json.has(COLUMN_NAME_SANDSTORM_CARGO_ROCKET_1) ? json.getInt(COLUMN_NAME_SANDSTORM_CARGO_ROCKET_1) : 0);
		vals.put(COLUMN_NAME_SANDSTORM_CARGO_ROCKET_2, json.has(COLUMN_NAME_SANDSTORM_CARGO_ROCKET_2) ? json.getInt(COLUMN_NAME_SANDSTORM_CARGO_ROCKET_2) : 0);
		vals.put(COLUMN_NAME_SANDSTORM_CARGO_ROCKET_3, json.has(COLUMN_NAME_SANDSTORM_CARGO_ROCKET_3) ? json.getInt(COLUMN_NAME_SANDSTORM_CARGO_ROCKET_3) : 0);
		vals.put(COLUMN_NAME_SANDSTORM_CARGO_DROPPED, json.has(COLUMN_NAME_SANDSTORM_CARGO_DROPPED) ? json.getInt(COLUMN_NAME_SANDSTORM_CARGO_DROPPED) : 0);
		vals.put(COLUMN_NAME_HATCH_SHIP, json.has(COLUMN_NAME_HATCH_SHIP) ? json.getInt(COLUMN_NAME_HATCH_SHIP) : 0);
		vals.put(COLUMN_NAME_HATCH_ROCKET_1, json.has(COLUMN_NAME_HATCH_ROCKET_1) ? json.getInt(COLUMN_NAME_HATCH_ROCKET_1) : 0);
		vals.put(COLUMN_NAME_HATCH_ROCKET_2, json.has(COLUMN_NAME_HATCH_ROCKET_2) ? json.getInt(COLUMN_NAME_HATCH_ROCKET_2) : 0);
		vals.put(COLUMN_NAME_HATCH_ROCKET_3, json.has(COLUMN_NAME_HATCH_ROCKET_3) ? json.getInt(COLUMN_NAME_HATCH_ROCKET_3) : 0);
		vals.put(COLUMN_NAME_HATCH_DROPPED, json.has(COLUMN_NAME_HATCH_DROPPED) ? json.getInt(COLUMN_NAME_HATCH_DROPPED) : 0);
		vals.put(COLUMN_NAME_CARGO_SHIP, json.has(COLUMN_NAME_CARGO_SHIP) ? json.getInt(COLUMN_NAME_CARGO_SHIP) : 0);
		vals.put(COLUMN_NAME_CARGO_ROCKET_1, json.has(COLUMN_NAME_CARGO_ROCKET_1) ? json.getInt(COLUMN_NAME_CARGO_ROCKET_1) : 0);
		vals.put(COLUMN_NAME_CARGO_ROCKET_2, json.has(COLUMN_NAME_CARGO_ROCKET_2) ? json.getInt(COLUMN_NAME_CARGO_ROCKET_2) : 0);
		vals.put(COLUMN_NAME_CARGO_ROCKET_3, json.has(COLUMN_NAME_CARGO_ROCKET_3) ? json.getInt(COLUMN_NAME_CARGO_ROCKET_3) : 0);
		vals.put(COLUMN_NAME_CARGO_DROPPED, json.has(COLUMN_NAME_CARGO_DROPPED) ? json.getInt(COLUMN_NAME_CARGO_DROPPED) : 0);
		vals.put(COLUMN_NAME_HAB_CLIMB_LEVEL, json.has(COLUMN_NAME_HAB_CLIMB_LEVEL) ? json.getInt(COLUMN_NAME_HAB_CLIMB_LEVEL) : 0);
		vals.put(COLUMN_NAME_HAB_CLIMB_LEVEL_ATTEMPTED, json.has(COLUMN_NAME_HAB_CLIMB_LEVEL_ATTEMPTED) ? json.getInt(COLUMN_NAME_HAB_CLIMB_LEVEL_ATTEMPTED) : 0);
		vals.put(COLUMN_NAME_HAB_CLIMB_2_LEFT, json.has(COLUMN_NAME_HAB_CLIMB_2_LEFT) ? json.getInt(COLUMN_NAME_HAB_CLIMB_2_LEFT) : 0);
		vals.put(COLUMN_NAME_FLOOR_PICKUP_CARGO, json.has(COLUMN_NAME_FLOOR_PICKUP_CARGO) ? json.getInt(COLUMN_NAME_FLOOR_PICKUP_CARGO) : 0);
		vals.put(COLUMN_NAME_FLOOR_PICKUP_HATCH, json.has(COLUMN_NAME_FLOOR_PICKUP_HATCH) ? json.getInt(COLUMN_NAME_FLOOR_PICKUP_HATCH) : 0);
		vals.put(COLUMN_NAME_FOUL, json.has(COLUMN_NAME_FOUL) ? json.getInt(COLUMN_NAME_FOUL) : 0);
		vals.put(COLUMN_NAME_YELLOW_CARD, json.has(COLUMN_NAME_YELLOW_CARD) ? json.getInt(COLUMN_NAME_YELLOW_CARD) : 0);
		vals.put(COLUMN_NAME_RED_CARD, json.has(COLUMN_NAME_RED_CARD) ? json.getInt(COLUMN_NAME_RED_CARD) : 0);
		vals.put(COLUMN_NAME_TIP_OVER, json.has(COLUMN_NAME_TIP_OVER) ? json.getInt(COLUMN_NAME_TIP_OVER) : 0);
		vals.put(COLUMN_NAME_NOTES, json.has(COLUMN_NAME_NOTES) ? json.getString(COLUMN_NAME_NOTES) : "");
		vals.put(COLUMN_NAME_INVALID, 0);
		vals.put(COLUMN_NAME_TIMESTAMP, DB.dateParser.format(new Date(json.getLong(COLUMN_NAME_TIMESTAMP) * 1000)));
		return vals;
	}

	public LinkedHashMap<String,String> getDisplayData() {
		LinkedHashMap<String,String> vals = new LinkedHashMap<String,String>();
		vals.put( COLUMN_NAME_EVENT_ID, event_id);
		vals.put( COLUMN_NAME_TEAM_ID, String.valueOf(team_id));
		vals.put( COLUMN_NAME_MATCH_ID, String.valueOf(match_id));
		vals.put( COLUMN_NAME_PRACTICE_MATCH, String.valueOf(practice_match ? 1 : 0));
		vals.put( COLUMN_NAME_POSITION_ID, position_id);
		vals.put( COLUMN_NAME_PREMATCH_ROBOT_CARGO, String.valueOf(prematch_robot_cargo ? 1 : 0));
		vals.put( COLUMN_NAME_PREMATCH_ROBOT_HATCH, String.valueOf(prematch_robot_hatch ? 1 : 0));
		vals.put( COLUMN_NAME_PREMATCH_HAB2_LEFT, String.valueOf(prematch_hab2_left ? 1 : 0));
		vals.put( COLUMN_NAME_PREMATCH_HAB_LEVEL, String.valueOf(prematch_hab_level));
		vals.put( COLUMN_NAME_SANDSTORM_BONUS, String.valueOf(sandstorm_bonus ? 1 : 0));
		vals.put( COLUMN_NAME_SANDSTORM_HATCH_SHIP, String.valueOf(sandstorm_hatch_ship));
		vals.put( COLUMN_NAME_SANDSTORM_HATCH_ROCKET_1, String.valueOf(sandstorm_hatch_rocket_1));
		vals.put( COLUMN_NAME_SANDSTORM_HATCH_ROCKET_2, String.valueOf(sandstorm_hatch_rocket_2));
		vals.put( COLUMN_NAME_SANDSTORM_HATCH_ROCKET_3, String.valueOf(sandstorm_hatch_rocket_3));
		vals.put( COLUMN_NAME_SANDSTORM_HATCH_DROPPED, String.valueOf(sandstorm_hatch_dropped));
		vals.put( COLUMN_NAME_SANDSTORM_CARGO_SHIP, String.valueOf(sandstorm_cargo_ship));
		vals.put( COLUMN_NAME_SANDSTORM_CARGO_ROCKET_1, String.valueOf(sandstorm_cargo_rocket_1));
		vals.put( COLUMN_NAME_SANDSTORM_CARGO_ROCKET_2, String.valueOf(sandstorm_cargo_rocket_2));
		vals.put( COLUMN_NAME_SANDSTORM_CARGO_ROCKET_3, String.valueOf(sandstorm_cargo_rocket_3));
		vals.put( COLUMN_NAME_SANDSTORM_CARGO_DROPPED, String.valueOf(sandstorm_cargo_dropped));
		vals.put( COLUMN_NAME_HATCH_SHIP, String.valueOf(hatch_ship));
		vals.put( COLUMN_NAME_HATCH_ROCKET_1, String.valueOf(hatch_rocket_1));
		vals.put( COLUMN_NAME_HATCH_ROCKET_2, String.valueOf(hatch_rocket_2));
		vals.put( COLUMN_NAME_HATCH_ROCKET_3, String.valueOf(hatch_rocket_3));
		vals.put( COLUMN_NAME_HATCH_DROPPED, String.valueOf(hatch_dropped));
		vals.put( COLUMN_NAME_CARGO_SHIP, String.valueOf(cargo_ship));
		vals.put( COLUMN_NAME_CARGO_ROCKET_1, String.valueOf(cargo_rocket_1));
		vals.put( COLUMN_NAME_CARGO_ROCKET_2, String.valueOf(cargo_rocket_2));
		vals.put( COLUMN_NAME_CARGO_ROCKET_3, String.valueOf(cargo_rocket_3));
		vals.put( COLUMN_NAME_CARGO_DROPPED, String.valueOf(cargo_dropped));
		vals.put( COLUMN_NAME_HAB_CLIMB_LEVEL, String.valueOf(hab_climb_level));
		vals.put( COLUMN_NAME_HAB_CLIMB_LEVEL_ATTEMPTED, String.valueOf(hab_climb_level_attempted));
		vals.put( COLUMN_NAME_HAB_CLIMB_2_LEFT, String.valueOf(hab_climb_2_left ? 1 : 0));
		vals.put( COLUMN_NAME_FLOOR_PICKUP_CARGO, String.valueOf(floor_pickup_cargo ? 1 : 0));
		vals.put( COLUMN_NAME_FLOOR_PICKUP_HATCH, String.valueOf(floor_pickup_hatch ? 1 : 0));
		vals.put( COLUMN_NAME_FOUL, String.valueOf(foul ? 1 : 0));
		vals.put( COLUMN_NAME_YELLOW_CARD, String.valueOf(yellow_card ? 1 : 0));
		vals.put( COLUMN_NAME_RED_CARD, String.valueOf(red_card ? 1 : 0));
		vals.put( COLUMN_NAME_TIP_OVER, String.valueOf(tip_over ? 1 : 0));
		vals.put( COLUMN_NAME_NOTES, notes);
		return vals;
	}

}