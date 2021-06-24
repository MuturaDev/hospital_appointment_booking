package com.robert.hospitalappointmentbookingsystem;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.robert.hospitalappointmentbookingsystem.home.HomeFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FragmentTransition {


    public static void displayThePassedFragment(Class fragClass, HashMap<String, Object> message, FragmentManager fragmentManager) {//message is nullable


        try {
            Class fragmentClass = fragClass;
            Fragment fragment = (Fragment) fragmentClass.newInstance();
            if (message != null) {
                Bundle b = new Bundle();
                b.putSerializable("message", message);
                fragment.setArguments(b);
            }
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.nav_host_fragment, fragment).commit();

        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        }


    }

    public static void moveToFragmentWithHost(Class fragClass, HashMap<String, Object> message, FragmentManager fragmentManager, int layout) {//message is nullable
        try {
            Class fragmentClass = fragClass;
            Fragment fragment = (Fragment) fragmentClass.newInstance();
            if (message != null) {
                Bundle b = new Bundle();
                b.putSerializable("message", message);
                fragment.setArguments(b);
            }

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(layout, fragment).commit();


        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        }


    }

    public static void removeFragments(FragmentManager fragmentManager) {

        for (Fragment removeFrag : fragmentManager.getFragments()) {
            if (removeFrag.isVisible()) {
                fragmentManager.beginTransaction().remove(removeFrag);
            }
        }

    }


}
