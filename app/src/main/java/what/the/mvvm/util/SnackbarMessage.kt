package what.the.mvvm.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer


/**
 * A SingleLiveEvent used for Snackbar messages. Like a [SingleLiveEvent] but also prevents
 * null messages and uses a custom observer.
 * 스낵바 메시지에 사용되는 SingleLiveEvent 입니다.
 * [SingleLiveEvent]와 유사하지만 null 메시지를 방지하고 custom observer를 사용합니다.
 *
 * Note that only one observer is going to be notified of changes.
 * 단 하나의 observer만 변경 사항을 알립니다.
 */
class SnackbarMessage : SingleLiveEvent<Int>() {
    fun observe(owner: LifecycleOwner, observer: (Int) -> Unit) {
        super.observe(owner, Observer {
            it?.run {
                observer(it)
            }
        })
    }
}

class SnackbarMessageString : SingleLiveEvent<String>() {
    fun observe(owner: LifecycleOwner, observer: (String) -> Unit) {
        super.observe(owner, Observer {
            it?.run {
                observer(it)
            }
        })
    }
}