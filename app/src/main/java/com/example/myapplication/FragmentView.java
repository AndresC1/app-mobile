package com.example.myapplication;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.Fragment.EditUserFragment;
import com.example.myapplication.Fragment.InfoUserFragment;
import com.google.android.material.tabs.TabLayout;

public class FragmentView extends AppCompatActivity {

    FrameLayout frameLayout;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fragment_view);

        frameLayout = (FrameLayout) findViewById(R.id.fragmentContainer);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new InfoUserFragment())
                .addToBackStack(null)
                .commit();

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = null;
                switch (tab.getPosition()){
                    case 0:
                        fragment = new InfoUserFragment();
                        break;
                    case 1:
                        fragment = new EditUserFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}