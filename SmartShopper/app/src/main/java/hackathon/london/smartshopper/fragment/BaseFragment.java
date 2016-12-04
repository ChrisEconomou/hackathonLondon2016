package hackathon.london.smartshopper.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;


/**
 * Base fragment, that has access to global instances, manages errors and token responses.
 */
public abstract class BaseFragment extends Fragment {








    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
      ;


    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    protected abstract int getLayoutId();

    protected abstract void initializeViews(Bundle savedInstanceState);






}
