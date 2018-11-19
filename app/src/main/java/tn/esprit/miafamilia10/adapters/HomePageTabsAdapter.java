package tn.esprit.miafamilia10.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import tn.esprit.miafamilia10.fragments.RootFragmentChat;
import tn.esprit.miafamilia10.fragments.RootFragmentHome;
import tn.esprit.miafamilia10.fragments.RootFragmentShopList;

public class HomePageTabsAdapter  extends FragmentPagerAdapter {

    private int NUM_ITEMS ;


    public HomePageTabsAdapter(FragmentManager fm ,int NUM_ITEMS) {
        super(fm);
        this.NUM_ITEMS = NUM_ITEMS;
    }




    @Override
    public Fragment getItem(int position) {
        switch (position) {
            /** because to switch fragments inside a tab we need a root FrameLayout,
             in which we load fragments in each time ( getFragmentManager.replace(root,newFrag) )**/
            case 0:
                return new RootFragmentHome();
            case 1:
                return new RootFragmentChat();
            case 2:
                return new RootFragmentShopList();

            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }


}
