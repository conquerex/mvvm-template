package what.the.mvvm

import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import what.the.mvvm.base.BaseActivity
import what.the.mvvm.databinding.ActivityMainBinding
import what.the.mvvm.viewmodel.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_main // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: MainViewModel by viewModel()

    // todo : mainSearchRecyclerViewAdapter

    override fun initStartView() {
        // todo : main_activity_search_recycler_view.run
    }

    override fun initDataBinding() {
        // todo : viewModel.imageSearchResponseLiveData
    }

    override fun initAfterBinding() {
        main_activity_search_button.setOnClickListener {
            viewModel.getImageSearch(main_activity_search_text_view.text.toString(), 1, 80)
        }
    }

}