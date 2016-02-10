package com.example.claudiocavallaro.progettogad.vista;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by claudiocavallaro on 11/12/15.
 */
public class ViewPagerAdapter2 extends FragmentStatePagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter2(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        if (position == 0) // if the position is 0 we are returning the First tab
        {
            TabPrezzi tabPrezzi = new TabPrezzi();

            return tabPrezzi;
        }
        // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
        if (position == 1) {
            TabAbbonamenti tab2 = new TabAbbonamenti();
            return tab2;
        }
        if (position == 2){
            TabSpecifiche tabSpecifiche = new TabSpecifiche();
            return tabSpecifiche;
        }

        return null;

    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}