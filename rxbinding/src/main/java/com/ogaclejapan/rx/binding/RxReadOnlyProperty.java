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

import rx.Observable;
import rx.subjects.BehaviorSubject;

public class RxReadOnlyProperty<T> extends RxObservable<T> {

    protected RxReadOnlyProperty(Observable<T> observable) {
        super(observable);
    }

    public static <T> RxReadOnlyProperty<T> of(T value) {
        return of(BehaviorSubject.create(value));
    }

    public static <T> RxReadOnlyProperty<T> of(Observable<T> observable) {
        return new RxReadOnlyProperty<T>(observable);
    }

    public T get() {
        return observable.toBlocking().latest().iterator().next();
    }

}
