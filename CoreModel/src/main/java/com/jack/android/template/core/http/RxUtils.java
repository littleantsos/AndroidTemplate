package com.jack.android.template.core.http;

import android.content.Context;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/29.
 */

public class RxUtils {

    /**
     * io线程执行，主线程观察
     * .compose(RxUtils.<T>applySchedulers())
     */
    public static <T> Observable.Transformer<T, T> applySchedulers() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

//    public static <T extends ResponseData> Func1<T, T> isSuccessFunc() {
//        return new Func1<T, T>() {
//            @Override
//            public T call(T commonResponse) {
//                // 检测token过期
////                if (BaseApplication.getInstance().isRealAccount()
////                        && TextUtils.equals(commonResponse.getReturnCode(), "-4002")) {
////                    EventBus.getDefault().post(new UnauthorizeEvent());
////                }
////                if (!commonResponse.isSuccess()) {
////                    throw new BusinessException(commonResponse.getMessage());
////                }
//                if (commonResponse.checkResponseCode())
//                    return commonResponse;
//                else {
//                    throw new BusinessException(commonResponse);
//                }
//            }
//        };
//    }
//
//    public static <T extends ResponseDataType> Func1<T, Boolean> isResponseDataTypeSuccessFunc() {
//        return new Func1<T, Boolean>() {
//            @Override
//            public Boolean call(T t) {
//                return t.checkResponseCode();
//            }
//        };
//    }
//
//    public static <T> Func1<T, T> isLiveSuccessFunc() {
//        return new Func1<T, T>() {
//            @Override
//            public T call(T commonResponse) {
//                // 检测token过期
//                return commonResponse;
//            }
//        };
//    }
//
//    public static Action1<Throwable> commonErrorAction(final Context context) {
//        return new Action1<Throwable>() {
//            @Override
//            public void call(Throwable throwable) {
//                LogUtils.e(throwable);
//                if (throwable instanceof RxUtils.BusinessException) {
//                    LogUtils.e("response error" + throwable.getMessage());
//                } else if (throwable instanceof IllegalStateException) {
//                    throwable.printStackTrace();
//                }
//                if (context != null)
//                    new FollowMeToast(context, R.string.send_request_fail);
//            }
//        };
//    }
//
//    public static class BusinessException extends RuntimeException {
//        public BusinessException(String detailMessage) {
//            super(detailMessage);
//        }
//
//        public BusinessException(ResponseData commonResponse) {
//            super("commonResponseError  type = " + commonResponse.getRequestType() + ", " +
//                    "response Code = " + commonResponse.getResponseCode() + ", message = " + commonResponse.getResponseMessage());
//        }
//    }
}
