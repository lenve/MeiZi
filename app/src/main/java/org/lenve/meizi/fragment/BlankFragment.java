package org.lenve.meizi.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.lenve.meizi.R;
import org.lenve.meizi.adapter.RvAdapter;
import org.lenve.meizi.bean.Gallery;
import org.lenve.meizi.net.NetUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlankFragment extends BaseFragment {
    private static final String ID = "id";
    private RecyclerView recyclerView;

    public static BlankFragment newInstance(String id) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        Call<Gallery> call = NetUtils.getApiService().getList(getArguments().getString("id"));
        call.enqueue(new Callback<Gallery>() {
            @Override
            public void onResponse(Call<Gallery> call, Response<Gallery> response) {
                if (response.isSuccessful()) {
                    Gallery galleries = response.body();
                    RvAdapter adapter = new RvAdapter(getActivity(), galleries);
                    recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Gallery> call, Throwable t) {

            }
        });
    }

    private void initView(View view) {
        recyclerView = ((RecyclerView) view.findViewById(R.id.recycler_view));
    }

}
