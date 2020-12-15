package com.example.android.uptransfer;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

class SectionPagerAdapter extends FragmentPagerAdapter {

    public SectionPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                ChatsFragment chatsFragment = new ChatsFragment();
                return chatsFragment;
            case 1:
                Ftp ftp = new Ftp();
                return ftp;
            default:
                return null;


        }
    }

    @Override
    public int getCount() {
        return 2;
    }


    public CharSequence getPageTitle(int position){

        switch (position)
        {
            case 0:

                return "CHAT";
            case 1:

                return "FTP";
            default:
                return null;


        }
    }
}
