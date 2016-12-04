package smartshopper.hackathon.london.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.models.consumer.PNPublishResult;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;
import com.squareup.otto.Subscribe;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import smartshopper.hackathon.london.myapplication.fragment.ProductsFragment;
import smartshopper.hackathon.london.myapplication.util.BusProvider;
import smartshopper.hackathon.london.myapplication.util.MessageReceivedEvent;
import smartshopper.hackathon.london.myapplication.util.PlaceOrderEvent;
import smartshopper.hackathon.london.myapplication.util.Utils;

import static smartshopper.hackathon.london.myapplication.Constants.CHANNEL;
import static smartshopper.hackathon.london.myapplication.util.MockDataUtils.getMessageReceivedEvent;

public class MainActivity extends AppCompatActivity {

    private PubNub pubnub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        pubnub();

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Map message = new HashMap();
//                message.put("hello", "there");
//
//                pubnub.publish()
//                        .channel(CHANNEL)
//                        .message(message)
//                        .async(new PNCallback<PNPublishResult>() {
//                            @Override
//                            public void onResponse(PNPublishResult result, PNStatus status) {
//                                if (status.isError()) {
//                                    Log.v("Test", "Error");
//                                } else {
//                                    Log.v("Test", "Published");
//                                }
//                            }
//                        });
//            }
//        });

        Utils.replaceFragment(this, R.id.container, ProductsFragment.newInstance(), ProductsFragment.TAG, true);

    }

    @Override
    protected void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
@Subscribe
    public void placeOrder(PlaceOrderEvent placeOrderEvent){

}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void pubnub() {
        PNConfiguration pnc = new PNConfiguration().setSubscribeKey(Constants.PUB_NUB_SUBSCRIBE_KEY).setPublishKey(Constants.PUB_NUB_PUBLISH_KEY);
        pubnub = new PubNub(pnc);

        pubnub.addListener(new com.pubnub.api.callbacks.SubscribeCallback() {
            @Override
            public void status(PubNub pubnub, PNStatus status) {
                Log.v("Test", "status");
            }

            @Override
            public void message(PubNub pubnub, PNMessageResult message) {
                Log.v("Test", message.getMessage().asText());

                BusProvider.getInstance().post(new MessageReceivedEvent(message.getMessage().asText()));
            }

            @Override
            public void presence(PubNub pubnub, PNPresenceEventResult presence) {
                Log.v("Test", "presence ");
            }
        });

        pubnub.subscribe().channels(Arrays.asList(CHANNEL)).execute();

    }
}
