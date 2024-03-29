package com.example.newww;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends FragmentActivity {

		String[] titles={"Exam","Today","Week","Month"};
		DrawerLayout mDrawerLayout;
		ListView mDrawerList;
		ActionBarDrawerToggle mDrawerToggle;

		private CharSequence mDrawerTitle;
		private CharSequence mTitle;
		

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);

			mTitle = mDrawerTitle = getTitle();
		
			mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
			mDrawerList = (ListView) findViewById(R.id.left_drawer);

			// set a custom shadow that overlays the main content when the drawer
			// opens
			
			// set up the drawer's list view with items and click listener
			mDrawerList.setAdapter(new ArrayAdapter<String>(this,
					R.layout.drawer_list_item, titles));
			mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

			// enable ActionBar app icon to behave as action to toggle nav drawer
			getActionBar().setDisplayHomeAsUpEnabled(true);
			getActionBar().setHomeButtonEnabled(true);

			// ActionBarDrawerToggle ties together the the proper interactions
			// between the sliding drawer and the action bar app icon
			mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
			mDrawerLayout, /* DrawerLayout object */
			R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
			R.string.app_name, /* "open drawer" description for accessibility */
			R.string.app_name /* "close drawer" description for accessibility */
			) {
				public void onDrawerClosed(View view) {
					getActionBar().setTitle(mTitle);
					invalidateOptionsMenu(); // creates call to
												// onPrepareOptionsMenu()
				}

				public void onDrawerOpened(View drawerView) {
					getActionBar().setTitle(mDrawerTitle);
					invalidateOptionsMenu(); // creates call to
												// onPrepareOptionsMenu()
				}
			};
			mDrawerLayout.setDrawerListener(mDrawerToggle);

			if (savedInstanceState == null) {
				selectItem(0);
			}

		}

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			return super.onCreateOptionsMenu(menu);
		}

		@Override
		public boolean onOptionsItemSelected(
				MenuItem item) {

			switch (item.getItemId()) {

			case android.R.id.home: {
				if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
					mDrawerLayout.closeDrawer(mDrawerList);
				} else {
					mDrawerLayout.openDrawer(mDrawerList);
				}
				break;
			}

			

			}

			return super.onOptionsItemSelected(item);
		}

		// The click listener for ListView in the navigation drawer
		private class DrawerItemClickListener implements
				ListView.OnItemClickListener {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				selectItem(position);
			}
		}
		

		@Override
		protected void onPostCreate(Bundle savedInstanceState) {
			super.onPostCreate(savedInstanceState);
			// Sync the toggle state after onRestoreInstanceState has occurred.
			mDrawerToggle.syncState();
		}

		@Override
		public void onConfigurationChanged(Configuration newConfig) {
			super.onConfigurationChanged(newConfig);
			// Pass any configuration change to the drawer toggles
			mDrawerToggle.onConfigurationChanged(newConfig);
		}

		private void selectItem(int position) {

			switch (position) {
			case 0:
				getSupportFragmentManager()
						.beginTransaction()
						.add(R.id.content,
								PagerAdapter.newInstance(),
								PagerAdapter.TAG).commit();
				break;
			/*default:

				SherlockFragment fragment = new PlanetFragment();
				Bundle args = new Bundle();
				args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
				fragment.setArguments(args);

				getSupportFragmentManager().beginTransaction()
						.add(R.id.content, fragment).commit();
				break;*/
			}

			mDrawerLayout.closeDrawer(mDrawerList);
		}



}
    

