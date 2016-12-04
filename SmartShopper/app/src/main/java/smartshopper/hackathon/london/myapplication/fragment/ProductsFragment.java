package smartshopper.hackathon.london.myapplication.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.otto.Subscribe;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;


import butterknife.ButterKnife;
import smartshopper.hackathon.london.myapplication.MainActivity;
import smartshopper.hackathon.london.myapplication.R;
import smartshopper.hackathon.london.myapplication.adapter.ProductsAdapter;
import smartshopper.hackathon.london.myapplication.model.Product;
import smartshopper.hackathon.london.myapplication.model.ProductsResponse;
import smartshopper.hackathon.london.myapplication.util.BusProvider;
import smartshopper.hackathon.london.myapplication.util.GsonUtil;
import smartshopper.hackathon.london.myapplication.util.MessageReceivedEvent;
import smartshopper.hackathon.london.myapplication.util.PlaceOrderEvent;

import static smartshopper.hackathon.london.myapplication.util.MockDataUtils.getMessageReceivedEvent;


public class ProductsFragment extends Fragment {


    RecyclerView mList;
    ImageView logo;
    private static final int COUNT = 2;
    private static final int SPAN_COUNT = 2;
    public static String TAG = ProductsFragment.class.getSimpleName();
    private List<Product> products = new ArrayList<>();
    private ProductsAdapter adapter;
    private AVLoadingIndicatorView loader;
    TextView orderPlaced;

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


    public static ProductsFragment newInstance() {
        ProductsFragment fragment = new ProductsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);

    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mList = (RecyclerView) getView().findViewById(R.id.products);
        logo = (ImageView) getView().findViewById(R.id.logo);
        loader = (AVLoadingIndicatorView) getView().findViewById(R.id.avi);
        orderPlaced = (TextView) getView().findViewById(R.id.order_title);
        loader.smoothToShow();
        adapter = new ProductsAdapter(getActivity(), products);
        mList.setAdapter(adapter);
        mList.setLayoutManager(new GridLayoutManager(getActivity(), getSpanCount()));

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Subscribe
    public void messageReceived(MessageReceivedEvent event) {
        ProductsResponse response = (ProductsResponse) GsonUtil.deserialize(event.getMessage(), ProductsResponse.class);
        if (response != null) {
            if (response.getMessageType().equalsIgnoreCase("Products")) {
                populateRecyclerView(response.getProducts());
            } else if (response.getMessageType().equalsIgnoreCase("Click")) {
                List<Product> products = response.getProducts();
                if (products != null && products.size() > 0) {
                    String productPrice = products.get(0).getPriceCurrent();
                    adapter.fireProduct(productPrice);

                }

            } else if (response.getMessageType().equalsIgnoreCase("OrderComplete")) {
                mList.setVisibility(View.GONE);
                logo.setVisibility(View.VISIBLE);
                loader.hide();
                orderPlaced.setVisibility(View.VISIBLE);
                BusProvider.getInstance().post(new PlaceOrderEvent(response));
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        reset();
                    }
                }, 5000);
            }
        }
    }

    private void populateRecyclerView(List<Product> products) {
        if (mList != null) {
            this.products.clear();
            this.products.addAll(products);
            adapter.notifyDataSetChanged();

            if (products.size() > 0) {
                mList.setVisibility(View.VISIBLE);
                logo.setVisibility(View.GONE);
                orderPlaced.setVisibility(View.GONE);
                loader.smoothToHide();
                if (products.size() == 1) {
                    mList.setLayoutManager(new GridLayoutManager(getActivity(), 1));
                } else {
                    mList.setLayoutManager(new GridLayoutManager(getActivity(), getSpanCount()));
                }

            } else {
                reset();
            }


        }
    }

    private void reset() {
        mList.setVisibility(View.GONE);
        logo.setVisibility(View.VISIBLE);
        orderPlaced.setVisibility(View.GONE);
        loader.smoothToShow();
    }

    public int getSpanCount() {
        return SPAN_COUNT;
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
