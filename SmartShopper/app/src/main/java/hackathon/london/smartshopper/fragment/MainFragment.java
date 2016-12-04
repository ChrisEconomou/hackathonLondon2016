package hackathon.london.smartshopper.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chriseconomou.depop.R;
import com.chriseconomou.depop.adapter.ProductsViewpagerAdapter;
import com.chriseconomou.depop.controllers.GetProductsListener;
import com.chriseconomou.depop.controllers.GetUserDetailsListener;
import com.chriseconomou.depop.data.HeaderResponse;
import com.chriseconomou.depop.data.Product;
import com.chriseconomou.depop.data.ProductsResponse;
import com.chriseconomou.depop.util.MockDataUtils;
import com.chriseconomou.depop.view.RoundedImageView;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.InjectView;


public class MainFragment extends BaseFragment  {

    public static String TAG = MainFragment.class.getSimpleName();

//
//
//    @BindView(R.id.header_image_profile)
//    RoundedImageView mImageProfile;
//    @BindView(R.id.header_text_name)
//    TextView mTextName;
//    @BindView(R.id.header_text_following_value)
//    TextView mTextFollowingValue;
//    @BindView(R.id.header_text_followers_value)
//    TextView mTextFollowersValue;
//    @BindView(R.id.header_text_description)
//    TextView mTextDescription;
//    @BindView(R.id.header_text_url)
//    TextView mTextUrl;


    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initializeViews(Bundle savedInstanceState) {

    }

//    @Override
//    protected void initializeViews(Bundle savedInstanceState) {
//
//        onGetUserDetailsSuccesful(MockDataUtils.getHeaderResponse(getActivity()));
//        onGetProductsSuccesful(MockDataUtils.getProductsResponse(getActivity()));
//
//    }


//
//    @Override
//    public void onGetProductsSuccesful(ProductsResponse productsResponse) {
//        mProductsViewpagerAdapter = new ProductsViewpagerAdapter(getActivity(), productsResponse.selling, productsResponse.liked, new String[]{getString(R.string.selling), getString(R.string.likes)});
//        setViewPagerHeight(productsResponse.selling.size() >= productsResponse.liked.size() ? productsResponse.selling.size() : productsResponse.liked.size());
//        mViewpager.setAdapter(mProductsViewpagerAdapter);
//        mIndicator.setViewPager(mViewpager);
//    }



//    private void setViewPagerHeight(int listSize) {
//        int rowCount = getRowCount(mProductsViewpagerAdapter.getSpanCount(), listSize);
//        int height = rowCount * (int) getResources().getDimension(R.dimen.product_image_height);
//        ViewGroup.LayoutParams layoutParams = mViewpager.getLayoutParams();
//        layoutParams.height = height;
//        mViewpager.setLayoutParams(layoutParams);
//
//    }

    private int getRowCount(int columnCount, int listSize) {

        return (listSize % columnCount == 0 ? listSize / columnCount : listSize / columnCount + 1);
    }

//    private void populateHeaderData(HeaderResponse headerResponse) {
//        if (headerResponse.picture != null) {
//            Picasso.with(getActivity()).load(headerResponse.picture).into(mImageProfile);
//        }
//        mTextName.setText(headerResponse.firstName + " " + headerResponse.lastName);
//        mTextFollowingValue.setText(headerResponse.followingCount.toString());
//        mTextFollowersValue.setText(headerResponse.followersCount.toString());
//        mTextDescription.setText(headerResponse.bio);
//        mTextUrl.setText(headerResponse.website);
//
//    }
}
