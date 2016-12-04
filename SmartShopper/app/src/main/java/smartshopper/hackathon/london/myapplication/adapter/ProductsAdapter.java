package smartshopper.hackathon.london.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import smartshopper.hackathon.london.myapplication.R;
import smartshopper.hackathon.london.myapplication.model.Product;
import smartshopper.hackathon.london.myapplication.model.ProductsResponse;


public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {

    private List<Product> mData = Collections.emptyList();
    private LayoutInflater mInflater;
    private Context mContext;

    public ProductsAdapter(Context context, List<Product> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mContext = context;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.view_product_item, viewGroup, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder productViewholder, int i) {
        loadData(productViewholder, i);
    }

    public void fireProduct(String productId) {
        String link = "asos://product?iid=" + productId;
        Uri webpage = Uri.parse(link);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(mContext.getPackageManager()) != null) {
            mContext.startActivity(intent);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private void loadData(final ProductViewHolder productViewholder, int i) {

        final Product product = mData.get(i);
        if (product.getImage() != null) {
            Picasso.with(mContext).load(product.getImage()).into(productViewholder.mProductsImage , new com.squareup.picasso.Callback() {
                @Override
                public void onSuccess() {
                    productViewholder.textView.setText(product.getTitle());
                    productViewholder.price.setText("£ " + product.getPriceCurrent());

                }

                @Override
                public void onError() {
                    //do smth when there is picture loading error
                }
            });

        }

        productViewholder.mProductsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //    https://www.asos.com/pgeproduct.aspx?iid="
                fireProduct(product.getId());
            }
        });
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {


        ImageView mProductsImage;
        TextView textView;
        TextView price;

        public ProductViewHolder(View itemView) {
            super(itemView);
            mProductsImage = (ImageView) itemView.findViewById(R.id.products_image);
            textView = (TextView) itemView.findViewById(R.id.title);
            price = (TextView) itemView.findViewById(R.id.price);
        }


    }

}
