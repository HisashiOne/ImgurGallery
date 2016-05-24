package com.example.macmini_desarrollo2.androidimgur_customgallery.Fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.etsy.android.grid.StaggeredGridView;
import com.example.macmini_desarrollo2.androidimgur_customgallery.Adapters.rowStagedAdapter;
import com.example.macmini_desarrollo2.androidimgur_customgallery.Clients.RestClient;
import com.example.macmini_desarrollo2.androidimgur_customgallery.Dialogs.DetailDialog;
import com.example.macmini_desarrollo2.androidimgur_customgallery.Models.JsonModel;
import com.example.macmini_desarrollo2.androidimgur_customgallery.R;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class StagedFragment extends Fragment implements AbsListView.OnScrollListener, AbsListView.OnItemClickListener {

    private StaggeredGridView mGridView;
    rowStagedAdapter mAdapter;
    RestClient client;
    LinearLayout loadingView;

    String baseURL;



    public StagedFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_staged, container, false);

        mGridView = (StaggeredGridView) view.findViewById(R.id.staged_view);
        mGridView.setOnScrollListener(this);
        mGridView.setOnItemClickListener(this);
        loadingView = (LinearLayout) view.findViewById(R.id.loadingGrid);

        client = new RestClient();
       // Load Variables From User Defaults
        SharedPreferences shared_1 = PreferenceManager.getDefaultSharedPreferences(getActivity());
        int saveDefaults =  shared_1.getInt("value", 0);
        String sort = shared_1.getString("sort", "viral");
        Boolean showViral = shared_1.getBoolean("showViral", true);

        //Update URL with User Variables
        switch (saveDefaults){
            case 0:
                baseURL = "hot/"+sort +"/0/day/" +showViral;
                client.get_1(baseURL,null, handler);
                break;
            case 1:
                baseURL = "top/"+sort +"/0/day/" +showViral;
                client.get_1(baseURL,null, handler);
                break;

            case 2:
                baseURL = "user/"+sort +"/0/day/" +showViral;
                client.get_1(baseURL,null, handler);
                break;
            default:
                baseURL = "hot/"+sort +"/0/day/" +showViral;
                client.get_1(baseURL,null, handler);
                break;
        }

        return view;

    }


    @Override
    public void onScrollStateChanged(final AbsListView view, final int scrollState) {
    }

    @Override
    public void onScroll(final AbsListView view, final int firstVisibleItem, final int visibleItemCount, final int totalItemCount) {
// our handling
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }


    private JsonHttpResponseHandler handler = new JsonHttpResponseHandler() {

        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

            try {
                JSONArray array = response.getJSONArray("data");

                final JsonModel model1 = new JsonModel();
                model1.arrayObject(array);

                //Asign adapter Row
                mAdapter = new rowStagedAdapter(getActivity(), android.R.layout.simple_list_item_1, model1.linkArray, model1.titleArray);
                mGridView.setAdapter(mAdapter);

                mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        // Create a dialog whit a array position
                        DetailDialog detailDialog = new DetailDialog();
                        detailDialog.showDialog(getActivity(),model1.linkArray.get(position),model1.titleArray.get(position),model1.decriptionArray.get(position),
                        String.valueOf(model1.viewsArray.get(position)), String.valueOf(model1.scoreArray.get(position)), String.valueOf(model1.upsArray.get(position))
                        ,String.valueOf(model1.downsArray.get(position)));



                    }
                });


            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                loadingView.setVisibility(View.GONE);
            }
        }
        @Override
        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
            super.onFailure(statusCode, headers, throwable, errorResponse);

            Log.d("Tag_2", "Error Responce" + errorResponse);

            Toast.makeText(getActivity(), "Conection Error", Toast.LENGTH_SHORT).show();
            loadingView.setVisibility(View.GONE);
        }


    };
}
