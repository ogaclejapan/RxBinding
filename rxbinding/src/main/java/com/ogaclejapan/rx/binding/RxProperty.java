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
import rx.subjects.BehaviorSubject;

public class RxProperty<T> extends RxReadOnlyProperty<T> implements Observer<T> {

    private final BehaviorSubject<T> subject;

    protected RxProperty(BehaviorSubject<T> subject) {
        super(subject);
        this.subject = subject;
    }

    public static <T> RxProperty<T> create() {
        return new RxProperty<>(BehaviorSubject.<T>create());
    }

    public static <T> RxProperty<T> of(T defaultValue) {
        return new RxProperty<T>(BehaviorSubject.create(defaultValue));
    }

    public void set(T value) {
        subject.onNext(value);
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
        Log.e("RxProperty", "onError", e);
    }

    @Override
    public void onNext(final T t) {
        subject.onNext(t);
    }

}
