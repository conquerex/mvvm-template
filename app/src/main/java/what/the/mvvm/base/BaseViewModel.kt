package what.the.mvvm.base

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import what.the.mvvm.util.SnackbarMessage
import what.the.mvvm.util.SnackbarMessageString


open class BaseViewModel : ViewModel() {

    // 일회성 이벤트를 만들어 내는 라이브 이벤트
    // 뷰는 이러한 이벤트를 바인딩하고 있다가, 적절한 상황이 되면 액티비티를 호출하거나 스낵바를 만듬
    private val snackbarMessage = SnackbarMessage()
    private val snackbarMessageString = SnackbarMessageString()

    /**
     * RxJava 의 observing을 위한 부분.
     * addDisposable을 이용하여 추가하기만 하면 된다
     *
     * RxJava를 사용할 때 스트림을 취소해주지 않으면 계속 돌기 때문에 위험하다. (메모리 릭 발생)
     * 이를 해결하기 위해서는 CompositeDisposable이라는 어레이 스트림에 만들어진 코드를 넣어두고 한 번에 취소하는 방법을 사용한다.
     * CompositeDisposable 변수를 선언하고 생명 주기 변경이 일어나면 clear로 지워준다.
     */
    private val compositeDisposable = CompositeDisposable()

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    /**
     * 스낵바를 보여주고 싶으면 viewModel에서 이 함수를 호출
     */
    fun showSnackbar(stringResourceId:Int) {
        snackbarMessage.value = stringResourceId
    }
    fun showSnackbar(str:String){
        snackbarMessageString.value = str
    }

    /**
     * BaseActivity에서 쓰는 함수
     */
    fun observeSnackbarMessage(lifeCycleOwner: LifecycleOwner, ob:(Int) -> Unit){
        snackbarMessage.observe(lifeCycleOwner, ob)
    }
    fun observeSnackbarMessageStr(lifeCycleOwner: LifecycleOwner, ob:(String) -> Unit) {
        snackbarMessageString.observe(lifeCycleOwner, ob)
    }

}