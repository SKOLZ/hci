package com.example.fragments;

import java.util.HashSet;
import java.util.Set;

import android.app.ActionBar;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.clickntravel.R;
import com.example.utils.Deal;
import com.example.utils.FlightsDbAdapter;

public class MyDealsFragment extends Fragment {

	public static Set<Deal> dealsList = new HashSet<Deal>();
	private ListView mListView;
	private FlightsDbAdapter mDbHelper;
	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		ActionBar actionBar = getActivity().getActionBar();

		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		actionBar.setCustomView(R.layout.mydeals_abs_layout);

		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(R.string.main_button_mydeals);
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setHomeButtonEnabled(true);

		if (view == null)
			view = inflater.inflate(R.layout.my_deal_list_fragment, container,
					false);

		mListView = (ListView) view.findViewById(R.id.my_deals_list_view);

		mDbHelper = new FlightsDbAdapter(this.getActivity());

		mDbHelper = mDbHelper.open();

		mDbHelper.deleteAllFlights();

		for (Deal curr : dealsList) {

			mDbHelper.createFlights(curr.getPrice(), curr.getNameFrom(),
					curr.getNameTo(), curr.getDepTime(), curr.getArrivalTime(),
					curr.getAirlineId());
		}

		showResults();

		if (dealsList.isEmpty()) {

			Toast toast = Toast.makeText(getActivity(), R.string.no_deals,
					Toast.LENGTH_LONG);

			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		}

		return view;
	}

	private void showResults() {

		Cursor cursor = mDbHelper.searchFlights();

		if (cursor == null) {

			return;
		}

		// Specify the columns we want to display in the result
		String[] from = new String[] { FlightsDbAdapter.KEY_PRICE,
				FlightsDbAdapter.KEY_FROM, FlightsDbAdapter.KEY_TO,
				FlightsDbAdapter.KEY_DEPDATE, FlightsDbAdapter.KEY_RETDATE,
				FlightsDbAdapter.KEY_IMG };

		// Specify the Corresponding layout elements where we want the columns
		// to go
		int[] to = new int[] { R.id.price, R.id.from, R.id.to, R.id.depDate,
				R.id.retDate, R.id.airline_image_deal };

		// Create a simple cursor adapter for the definitions and apply them
		// to the ListView
		@SuppressWarnings("deprecation")
		SimpleCursorAdapter Flights = new SimpleCursorAdapter(
				this.getActivity(), R.layout.dealresult, cursor, from, to);
		mListView.setAdapter(Flights);

		// Define the on-click listener for the list items
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				int color_on_selected = Color.rgb(204, 204, 204);
				
				view.setBackgroundColor(color_on_selected);
				
				for(int i = 0; i < ((ViewGroup)view).getChildCount(); i++) {
				   
					View nextChild = ((ViewGroup)view).getChildAt(i);
				    			    
				    nextChild.setBackgroundColor(color_on_selected);
				}
				
				// Get the cursor, positioned to the corresponding row in
				// the result set
				Cursor cursor = (Cursor) mListView.getItemAtPosition(position);

				// Get the city from this row in the database
				String price = cursor.getString(cursor
						.getColumnIndexOrThrow("price"));
				String from = cursor.getString(cursor
						.getColumnIndexOrThrow("fromCity"));
				String to = cursor.getString(cursor
						.getColumnIndexOrThrow("toCity"));
				String depDate = cursor.getString(cursor
						.getColumnIndexOrThrow("depDate"));
				String retDate = cursor.getString(cursor
						.getColumnIndexOrThrow("retDate"));
				String airlineId = cursor.getString(cursor
						.getColumnIndexOrThrow("img"));

				Deal newDeal = new Deal(from, to, depDate, retDate, price,
						airlineId);

				dealsList.remove(newDeal);
			}
		});
	}

	@Override
	public void onDestroyView() {

		if (view != null)
			((ViewGroup) view.getParent()).removeAllViews();

		ListView lv = (ListView) getActivity().findViewById(
				R.id.deals_list_view);

		if (lv != null) {
			((ViewGroup) lv.getParent()).removeView(lv);
			lv.removeAllViews();
		}

		super.onDestroyView();
	}
}
