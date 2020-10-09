package bo.com.emprendeya.ui.adapters;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import bo.com.emprendeya.R;
import bo.com.emprendeya.ui.fragment.StartupDetailsFragment;
import bo.com.emprendeya.ui.fragment.StartupPostsFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class StartupDetailsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{
            R.string.tab_details,
            R.string.tab_posts
    };
    private final Context mContext;
    private List<Fragment> fragments = new ArrayList<>();

    public StartupDetailsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
        fragments.add(StartupDetailsFragment.newInstance(0));
        fragments.add(StartupPostsFragment.newInstance(1));
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}