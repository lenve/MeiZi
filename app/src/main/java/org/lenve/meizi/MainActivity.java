package org.lenve.meizi;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.lenve.meizi.adapter.FgAdapter;
import org.lenve.meizi.bean.ClassfyBean;
import org.lenve.meizi.db.ClassfyDB;
import org.lenve.meizi.fragment.BlankFragment;
import org.lenve.meizi.net.ApiService;
import org.lenve.meizi.net.NetUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    private ClassfyDB classfyDB;
    private List<ClassfyBean.TngouBean> classfies;
    private List<Fragment> fragments;
    private ViewPager viewpager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        initData();
    }

    private void initView() {
        viewpager = ((ViewPager) findViewById(R.id.viewpager));
        tabLayout = ((TabLayout) findViewById(R.id.tablayout));
    }

    private void initData() {
        classfyDB = new ClassfyDB(this);
        classfies = classfyDB.getClassfies();
        if (classfies == null || classfies.size() == 0) {
            ApiService apiService = NetUtils.getApiService();
            Call<ClassfyBean> call = apiService.getClassfy();
            call.enqueue(new Callback<ClassfyBean>() {
                @Override
                public void onResponse(Call<ClassfyBean> call, Response<ClassfyBean> response) {
                    ClassfyBean list = response.body();
                    classfyDB.add(list.getTngou());
                    classfies.addAll(list.getTngou());
                    initFragment();
                }

                @Override
                public void onFailure(Call<ClassfyBean> call, Throwable t) {

                }
            });
        } else {
            initFragment();
        }
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        for (ClassfyBean.TngouBean classfy : classfies) {
            BlankFragment fragment = BlankFragment.newInstance(String.valueOf(classfy.getId()));
            fragments.add(fragment);
        }
        FgAdapter adapter = new FgAdapter(getSupportFragmentManager(), classfies, fragments);
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
}
