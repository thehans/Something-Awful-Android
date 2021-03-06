package com.ferg.awful.thread;

import com.ferg.awful.R;
import com.ferg.awful.preferences.AwfulPreferences;
import com.ferg.awful.service.AwfulServiceConnection.AwfulListAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class AwfulPageCount implements AwfulDisplayItem {

	private AwfulListAdapter adapter;

	public AwfulPageCount(AwfulListAdapter parent) {
		adapter = parent;
	}

	@Override
	public View getView(LayoutInflater inf, View current, ViewGroup parent, AwfulPreferences mPrefs) {
		if(current == null || current.getId() != R.layout.page_count){
			 current = inf.inflate(R.layout.page_count, null, false);
		}
		TextView pageCountText = (TextView) current.findViewById(R.id.page_count);
		ImageButton next = (ImageButton) current.findViewById(R.id.next_page);
		ImageButton prev = (ImageButton) current.findViewById(R.id.prev_page);

		pageCountText.setText("Page "+adapter.getPage()+"/"+adapter.getLastPage());
		
		prev.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				adapter.goToPage(adapter.getPage()-1);
			}
		});
		if(adapter.getPage() <= 1){
			prev.setVisibility(View.INVISIBLE);
		}else{
			prev.setVisibility(View.VISIBLE);
		}
		if(adapter.getPage() == adapter.getLastPage()){
			next.setImageResource(android.R.drawable.stat_notify_sync);
			next.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					adapter.refresh();
				}
			});
		}else{
			next.setImageResource(R.drawable.r_arrow);
			next.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					adapter.goToPage(adapter.getPage()+1);
				}
			});
		}
		return current;
	}

	@Override
	public int getID() {
		return -2;
	}

	@Override
	public DISPLAY_TYPE getType() {
		return DISPLAY_TYPE.PAGE_COUNT;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

}
