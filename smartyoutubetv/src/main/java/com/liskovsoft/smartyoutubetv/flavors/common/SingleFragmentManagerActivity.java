package com.liskovsoft.smartyoutubetv.flavors.common;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.liskovsoft.smartyoutubetv.fragments.BrowserFragment;
import com.liskovsoft.smartyoutubetv.R;

public abstract class SingleFragmentManagerActivity extends FragmentManagerActivity {
    private BrowserFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
        getLoadingManager().show();

        initBrowserFragment();
        setupEvents();
    }

    private void setupEvents() {
        setActiveFragment(mFragment, true);
    }

    private void initBrowserFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        mFragment = getBrowserFragment();
        transaction.add(R.id.browser_wrapper, (Fragment) mFragment);
        transaction.commit();
    }

    protected abstract BrowserFragment getBrowserFragment();
}
