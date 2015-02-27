/**
 * Copyright (C) 2015 ogaclejapan <ogaclejapan@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ogaclejapan.rx.binding;

import android.text.Html;
import android.text.Spanned;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.functions.Func3;
import rx.functions.Func4;
import rx.functions.Func5;
import rx.functions.Func6;
import rx.functions.Func7;
import rx.functions.Func8;
import rx.functions.Func9;

public final class RxUtils {

    public static <R> Func1<List<R>, Observable<R>> flatten() {
        return new Func1<List<R>, Observable<R>>() {
            @Override
            public Observable<R> call(final List<R> list) {
                return Observable.from(list);
            }
        };
    }

    public static <T> Func1<String, Spanned> fromHtml() {
        return new Func1<String, Spanned>() {
            @Override
            public Spanned call(final String s) {
                return Html.fromHtml(s);
            }
        };
    }

    public static <T> Func1<T, String> stringify() {
        return new Func1<T, String>() {
            @Override
            public String call(final T t) {
                return String.valueOf(t);
            }
        };
    }

    public static <T> Func1<T, String> formatString(final String format) {
        return new Func1<T, String>() {
            @Override
            public String call(final T s) {
                return String.format(format, s);
            }
        };
    }

    public static Func1<Date, String> formatDate(final String pattern) {
        return new Func1<Date, String>() {
            @Override
            public String call(final Date date) {
                final SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                return sdf.format(date);
            }
        };
    }

    public static <R> Observable<R> toObservable(final Func0<R> func) {
        return Observable.create(new Observable.OnSubscribe<R>() {
            @Override
            public void call(Subscriber<? super R> subscriber) {
                try {
                    final R result = func.call();
                    onNextIfSubscribed(subscriber, result);
                    onCompletedIfSubsribed(subscriber);
                } catch (Exception e) {
                    onErrorIfSubscribed(subscriber, e);
                }
            }
        });
    }

    public static <T1, R> Observable<R> toObservable(final Func1<T1, R> func, final T1 arg1) {
        return Observable.create(new Observable.OnSubscribe<R>() {
            @Override
            public void call(Subscriber<? super R> subscriber) {
                try {
                    final R result = func.call(arg1);
                    onNextIfSubscribed(subscriber, result);
                    onCompletedIfSubsribed(subscriber);
                } catch (Exception e) {
                    onErrorIfSubscribed(subscriber, e);
                }
            }
        });
    }

    public static <T1, T2, R> Observable<R> toObservable(final Func2<T1, T2, R> func,
            final T1 arg1, final T2 arg2) {
        return Observable.create(new Observable.OnSubscribe<R>() {
            @Override
            public void call(Subscriber<? super R> subscriber) {
                try {
                    final R result = func.call(arg1, arg2);
                    onNextIfSubscribed(subscriber, result);
                    onCompletedIfSubsribed(subscriber);
                } catch (Exception e) {
                    onErrorIfSubscribed(subscriber, e);
                }
            }
        });
    }

    public static <T1, T2, T3, R> Observable<R> toObservable(final Func3<T1, T2, T3, R> func,
            final T1 arg1, final T2 arg2, final T3 arg3) {
        return Observable.create(new Observable.OnSubscribe<R>() {
            @Override
            public void call(Subscriber<? super R> subscriber) {
                try {
                    final R result = func.call(arg1, arg2, arg3);
                    onNextIfSubscribed(subscriber, result);
                    onCompletedIfSubsribed(subscriber);
                } catch (Exception e) {
                    onErrorIfSubscribed(subscriber, e);
                }
            }
        });
    }

    public static <T1, T2, T3, T4, R> Observable<R> toObservable(
            final Func4<T1, T2, T3, T4, R> func,
            final T1 arg1, final T2 arg2, final T3 arg3, final T4 arg4) {
        return Observable.create(new Observable.OnSubscribe<R>() {
            @Override
            public void call(Subscriber<? super R> subscriber) {
                try {
                    final R result = func.call(arg1, arg2, arg3, arg4);
                    onNextIfSubscribed(subscriber, result);
                    onCompletedIfSubsribed(subscriber);
                } catch (Exception e) {
                    onErrorIfSubscribed(subscriber, e);
                }
            }
        });
    }

    public static <T1, T2, T3, T4, T5, R> Observable<R> toObservable(
            final Func5<T1, T2, T3, T4, T5, R> func,
            final T1 arg1, final T2 arg2, final T3 arg3, final T4 arg4, final T5 arg5) {
        return Observable.create(new Observable.OnSubscribe<R>() {
            @Override
            public void call(Subscriber<? super R> subscriber) {
                try {
                    final R result = func.call(arg1, arg2, arg3, arg4, arg5);
                    onNextIfSubscribed(subscriber, result);
                    onCompletedIfSubsribed(subscriber);
                } catch (Exception e) {
                    onErrorIfSubscribed(subscriber, e);
                }
            }
        });
    }

    public static <T1, T2, T3, T4, T5, T6, R> Observable<R> toObservable(
            final Func6<T1, T2, T3, T4, T5, T6, R> func,
            final T1 arg1, final T2 arg2, final T3 arg3, final T4 arg4, final T5 arg5,
            final T6 arg6) {
        return Observable.create(new Observable.OnSubscribe<R>() {
            @Override
            public void call(Subscriber<? super R> subscriber) {
                try {
                    final R result = func.call(
                            arg1, arg2, arg3, arg4, arg5,
                            arg6);
                    onNextIfSubscribed(subscriber, result);
                    onCompletedIfSubsribed(subscriber);
                } catch (Exception e) {
                    onErrorIfSubscribed(subscriber, e);
                }
            }
        });
    }

    public static <T1, T2, T3, T4, T5, T6, T7, R> Observable<R> toObservable(
            final Func7<T1, T2, T3, T4, T5, T6, T7, R> func,
            final T1 arg1, final T2 arg2, final T3 arg3, final T4 arg4, final T5 arg5,
            final T6 arg6, final T7 arg7) {
        return Observable.create(new Observable.OnSubscribe<R>() {
            @Override
            public void call(Subscriber<? super R> subscriber) {
                try {
                    final R result = func.call(
                            arg1, arg2, arg3, arg4, arg5,
                            arg6, arg7);
                    onNextIfSubscribed(subscriber, result);
                    onCompletedIfSubsribed(subscriber);
                } catch (Exception e) {
                    onErrorIfSubscribed(subscriber, e);
                }
            }
        });
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Observable<R> toObservable(
            final Func8<T1, T2, T3, T4, T5, T6, T7, T8, R> func,
            final T1 arg1, final T2 arg2, final T3 arg3, final T4 arg4, final T5 arg5,
            final T6 arg6, final T7 arg7, final T8 arg8) {
        return Observable.create(new Observable.OnSubscribe<R>() {
            @Override
            public void call(Subscriber<? super R> subscriber) {
                try {
                    final R result = func.call(
                            arg1, arg2, arg3, arg4, arg5,
                            arg6, arg7, arg8);
                    onNextIfSubscribed(subscriber, result);
                    onCompletedIfSubsribed(subscriber);
                } catch (Exception e) {
                    onErrorIfSubscribed(subscriber, e);
                }
            }
        });
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Observable<R> toObservable(
            final Func9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> func,
            final T1 arg1, final T2 arg2, final T3 arg3, final T4 arg4, final T5 arg5,
            final T6 arg6, final T7 arg7, final T8 arg8, final T9 arg9) {
        return Observable.create(new Observable.OnSubscribe<R>() {
            @Override
            public void call(Subscriber<? super R> subscriber) {
                try {
                    final R result = func.call(
                            arg1, arg2, arg3, arg4, arg5,
                            arg6, arg7, arg8, arg9);
                    onNextIfSubscribed(subscriber, result);
                    onCompletedIfSubsribed(subscriber);
                } catch (Exception e) {
                    onErrorIfSubscribed(subscriber, e);
                }
            }
        });
    }

    public static <R> void onNextIfSubscribed(Subscriber<? super R> subscriber, R result) {
        if (!subscriber.isUnsubscribed()) {
            subscriber.onNext(result);
        }
    }

    public static void onErrorIfSubscribed(Subscriber<?> subscriber, Throwable e) {
        if (!subscriber.isUnsubscribed()) {
            subscriber.onError(e);
        }
    }

    public static void onCompletedIfSubsribed(Subscriber<?> subscriber) {
        if (!subscriber.isUnsubscribed()) {
            subscriber.onCompleted();
        }
    }

    private RxUtils() {
        //No instances
    }

}
