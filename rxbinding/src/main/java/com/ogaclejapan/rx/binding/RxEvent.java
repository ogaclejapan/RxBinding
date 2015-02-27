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

import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.functions.Func1;
import rx.subjects.PublishSubject;

public class RxEvent<T> extends RxObservable<T> implements Observer<T> {

    private final PublishSubject<T> subject;

    protected RxEvent(PublishSubject<T> subject) {
        super(subject);
        this.subject = subject;
    }

    public static <T> RxEvent<T> create() {
        return new RxEvent<T>(PublishSubject.<T>create());
    }

    public void post(final T event) {
        subject.onNext(event);
    }

    public Subscription bind(Observable<? extends T> observable) {
        return observable.subscribe(this);
    }

    public Subscription bind(RxObservable<? extends T> rxObservable) {
        return bind(rxObservable.asObservable());
    }

    public <E> Subscription bind(Observable<E> observable, Func1<? super E, ? extends T> func) {
        return bind(observable.map(func));
    }

    public <E> Subscription bind(RxObservable<E> rxObservable, Func1<? super E, ? extends T> func) {
        return bind(rxObservable.asObservable().map(func));
    }

    @Override
    public void onCompleted() {
        //Do nothing.
    }

    @Override
    public void onError(final Throwable e) {
        Log.e("RxEvent", "onError", e);
    }

    @Override
    public void onNext(final T t) {
        subject.onNext(t);
    }
}
