package what.the.mvvm.util

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

/**
 * SingleLiveEvent 설명 :
 * https://zladnrms.tistory.com/146
 */

// MutableLiveData란 변경할 수 있는 LiveData 형입니다.
// 일반적인 LiveData형은 변경할 수 없고 오로지 데이터의 변경값만을 소비하는데 반해
// MutableLiveData는 데이터를 UI Thread와 Background Thread에서 선택적으로 바꿀 수 있습니다.
open class SingleLiveEvent<T> : MutableLiveData<T>() {

    /**
     * 멀티쓰레딩 환경에서 동시성을 보장하는 AtomicBoolean.
     * false로 초기화되어 있음
     */
    private val mPending = AtomicBoolean(false)

    /**
     * View(Activity or Fragment 등 LifeCycleOwner)가 활성화 상태가 되거나
     * setValue로 값이 바뀌었을 때 호출되는 observe 함수.
     * pending.compareAndSet(true, false)라는 것은, 위의 pending 변수가
     * true면 if문 내의 로직을 처리하고 false로 바꾼다는 것이다.
     *
     * 아래의 setValue를 통해서만 pending값이 true로 바뀌기 때문에,
     * Configuration Changed가 일어나도 pending값은 false이기 때문에 observe가
     * 데이터를 전달하지 않는다!
     */
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (hasActiveObservers()) {
            Log.w(TAG, "Multiple observers registered but only one will be notified of changes.")
        }

        // Observe the internal MutableLiveData
        super.observe(owner, Observer { t ->
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        })
    }

    /**
     * setValue : UI Thread, 즉 Main Thread에서 실행
     *
     * LiveData로써 들고있는 데이터의 값을 변경하는 함수.
     * 여기서는 pending(AtomicBoolean)의 변수는 true로 바꾸어
     * observe내의 if문을 처리할 수 있도록 하였음.
     */
    @MainThread
    override fun setValue(t: T?) {
        mPending.set(true)
        super.setValue(t)
    }

    // postValue : Background Thread에서 처리
    override fun postValue(value: T) {
        super.postValue(value)
        // 여기서는 어떤 역할도 하지 않는다.
    }

    /**
     * 데이터의 속성을 지정해주지 않아도 call만으로 setValue를 호출 가능
     */
    @MainThread
    fun call() {
        value = null
    }

    companion object {
        private val TAG = "SingleLiveEvent"
    }
}