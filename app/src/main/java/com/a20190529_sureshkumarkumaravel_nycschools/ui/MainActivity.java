package com.a20190529_sureshkumarkumaravel_nycschools.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.a20190529_sureshkumarkumaravel_nycschools.R;
import com.a20190529_sureshkumarkumaravel_nycschools.databinding.ActivityMainBinding;
import com.a20190529_sureshkumarkumaravel_nycschools.model.HighSchool;
import com.a20190529_sureshkumarkumaravel_nycschools.util.DataSourceProvider;
import com.a20190529_sureshkumarkumaravel_nycschools.viewmodel.MainViewModel;

import java.util.List;

/**
 * Activity to show the list of NYC High schools
 * Created on 2019-05-29.
 *
 * @author kumars
 */
public class MainActivity extends AppCompatActivity implements ListAdapter.Listener {

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainViewModel viewModel = ViewModelProviders.of(this,
                MainViewModel.Companion.getFACTORY().invoke(DataSourceProvider.Companion.getSchoolInstance())
        ).get(MainViewModel.class);


        viewModel.isLoadingLiveData().observe(this, showLoading -> mBinding.loadingView.setVisibility(showLoading ? View.VISIBLE : View.GONE));
        viewModel.getSchoolListLiveDate().observe(this, this::showSchoolLists);

        viewModel.loadHighSchoolLists();
    }

    private void showSchoolLists(@Nullable List<HighSchool> highSchools) {
        if (highSchools == null || highSchools.isEmpty()) {
            Toast.makeText(this, getString(R.string.problem_retrieving), Toast.LENGTH_LONG).show();
            return;
        }

        mBinding.recyclerView.setHasFixedSize(true);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBinding.recyclerView.setAdapter(new ListAdapter(highSchools, this));
    }

    @Override
    public void onHighSchoolSelected(HighSchool highSchool) {
        // start the detail activity with the high school information to show
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.DATA_EXTRA, highSchool);
        startActivity(intent);
    }
}
