/**
 * Copyright (C) 2015 ogaclejapan <ogaclejapan@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ogaclejapan.rx.binding;

import rx.Observable;
import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Action2;
import rx.functions.Actions;

public interface Rx<T> {

  Scheduler MAIN_THREAD_SCHEDULER = new RxMainThreadScheduler();
  Action1<Throwable> ERROR_ACTION_EMPTY = Actions.empty();
  Action0 COMPLETE_ACTION_EMPTY = Actions.empty();

  T get();

  <E> Subscription bind(Observable<E> observable, Action<? super T, ? super E> action);

  <E> Subscription bind(RxObservable<E> observable, Action<? super T, ? super E> action);

  static interface Action<T, E> extends Action2<T, E> {

    @Override void call(T target, E e);
  }

}
