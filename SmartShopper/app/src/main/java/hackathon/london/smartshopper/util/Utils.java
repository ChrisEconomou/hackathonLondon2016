package hackathon.london.smartshopper.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Utils {

    public static String fromStream(InputStream in) throws IOException {
        if (in != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder out = new StringBuilder();
            String newLine = System.getProperty("line.separator");
            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line);
                out.append(newLine);
            }
            return out.toString();
        }
        return null;
    }



    public static void replaceFragment(FragmentActivity fragmentActivity, int containerId, Fragment fragment, String fragmentTag, boolean hasAnimation) {
        if (hasAnimation) {
            createFragmentTransaction(fragmentActivity).replace(containerId, fragment, fragmentTag).commit();
        } else {
            createSimpleFragmentTansaction(fragmentActivity).replace(containerId, fragment, fragmentTag).commit();

        }

    }


    public static void addFragment(FragmentActivity fragmentActivity, int containerId, Fragment fragment, String fragmentTag, boolean hasAnimation) {
        if (hasAnimation) {
            createFragmentTransaction(fragmentActivity).add(containerId, fragment, fragmentTag).commit();
        } else {
            createSimpleFragmentTansaction(fragmentActivity).add(containerId, fragment, fragmentTag).commit();

        }

    }

    public static void addFragment(FragmentActivity fragmentActivity, Fragment fragment, String fragmentTag) {
        createFragmentTransaction(fragmentActivity).add(fragment, fragmentTag).commit();
    }
    private static FragmentTransaction createFragmentTransaction(FragmentActivity fragmentActivity) {

        FragmentTransaction fragmentTransaction = createSimpleFragmentTansaction(fragmentActivity);

        fragmentTransaction.addToBackStack(null);
        return fragmentTransaction;
    }

    private static FragmentTransaction createSimpleFragmentTansaction(FragmentActivity fragmentActivity) {
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        return fragmentTransaction;
    }





}
