package io.emqx.mqtt;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.location.FusedLocationProviderClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import java.util.ArrayList;
import java.util.List;

public class PublishFragment extends BaseFragment {
    public EditText mTopic;
    private RadioGroup mQosRadioGroup;
    private RadioGroup mRetainedRadioGroup;
    private FusedLocationProviderClient mFusedLocationClient;
    PublishRecyclerViewAdapter mAdapter;
    List<Publish> mPublishList = new ArrayList<>();
    public Location ltd;
    Runnable runnable;
    boolean durum = true;
    public PublishFragment() {
    }


    public static PublishFragment newInstance() {
        return new PublishFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_publish_list;
    }

    @Override
    protected void setUpView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.publication_list);
        mAdapter = new PublishRecyclerViewAdapter(mPublishList);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(fragmentActivity, DividerItemDecoration.VERTICAL));

        mTopic = view.findViewById(R.id.topic);

        Button pubBtn = view.findViewById(R.id.publish);
        pubBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (durum==true){
                    durum=false;
                    final Publish publish = getPublish();
                    final Handler hndler= new Handler();
                    runnable=new Runnable() {
                        @Override
                        public void run() {
                            ((MainActivity)fragmentActivity).
                                    publish(publish, new IMqttActionListener() {
                                        @Override
                                        public void onSuccess (IMqttToken asyncActionToken){
                                            mPublishList.add(0, publish);
                                            mAdapter.notifyItemInserted(0);
                                        }

                                        @Override
                                        public void onFailure (IMqttToken asyncActionToken, Throwable exception)
                                        {
                                            Toast.makeText(fragmentActivity, "Failed to publish", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                            hndler.postDelayed(runnable,500);
                        }
                    };
                    runnable.run();
                }
                else {
                    durum=true;
                }
            }
        });
    }

    public Publish getPublish() {
        String message="5";
        String topic = mTopic.getText().toString();

        int qos = 0;
        boolean retained = false;
        return new Publish(topic, message, qos, retained);
    }



}
