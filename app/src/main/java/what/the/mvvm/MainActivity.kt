package what.the.mvvm

import org.koin.androidx.viewmodel.ext.android.viewModel
import what.the.mvvm.base.BaseActivity
import what.the.mvvm.databinding.ActivityMainBinding
import what.the.mvvm.viewmodel.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_main

    override val viewModel: MainViewModel by viewModel()

    // todo : mainSearchRecyclerViewAdapter

    override fun initStartView() {
        // todo : main_activity_search_recycler_view.run
    }

    override fun initDataBinding() {
        // todo : viewModel.imageSearchResponseLiveData
    }

    override fun initAfterBinding() {
        // todo : main_activity_search_button.setOnClickListener
    }

}