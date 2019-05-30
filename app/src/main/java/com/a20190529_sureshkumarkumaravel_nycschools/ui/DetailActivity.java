package com.a20190529_sureshkumarkumaravel_nycschools.ui;

import android.os.Bundle;

import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import com.a20190529_sureshkumarkumaravel_nycschools.R;
import com.a20190529_sureshkumarkumaravel_nycschools.databinding.ActivityDetailBinding;
import com.a20190529_sureshkumarkumaravel_nycschools.model.HighSchool;
import com.a20190529_sureshkumarkumaravel_nycschools.util.DataSourceProvider;
import com.a20190529_sureshkumarkumaravel_nycschools.viewmodel.DetailViewModel;

/**
 * Activity to show the detail information on the school and SAT requirements.
 * Created on 2019-05-29.
 *
 * @author kumars
 */
public class DetailActivity extends AppCompatActivity {

    public static final String DATA_EXTRA = "DATA_EXTRA";

    private ActivityDetailBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        HighSchool highSchool = (HighSchool) getIntent().getSerializableExtra(DATA_EXTRA);

        mBinding.setHighSchool(highSchool);

        DetailViewModel viewModel = ViewModelProviders.of(this,
                DetailViewModel.Companion.getFACTORY().invoke(DataSourceProvider.Companion.getSchoolInstance())
        ).get(DetailViewModel.class);

        viewModel.isLoadingLiveData().observe(this, showLoading -> mBinding.loadingView.setVisibility(showLoading ? View.VISIBLE : View.GONE));
        viewModel.getSchoolSATLiveDate().observe(this, schoolSATDetail -> {
            if (schoolSATDetail == null) {
                mBinding.satErrorTv.setVisibility(View.VISIBLE);
                return;
            }

            mBinding.satViews.setVisibility(View.VISIBLE);
            mBinding.setSchoolSAT(schoolSATDetail);
        });

        viewModel.loadSchoolSATData(highSchool);
    }
}
