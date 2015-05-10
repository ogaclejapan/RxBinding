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

import java.lang.ref.WeakReference;

import rx.Observable;
import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action1;

public class RxWeakRef<T> implements Rx<T> {

  private final WeakReference<T> referent;

  protected RxWeakRef(T referent) {
    this.referent = new WeakReference<T>(referent);
  }

  public static <T> RxWeakRef<T> of(T referent) {
    return new RxWeakRef<T>(referent);
  }

  @Override
  public T get() {
    return referent.get();
  }

  @Override
  public <E> Subscription bind(final Observable<E> observable,
      final Action<? super T, ? super E> action) {
    return observable
        .observeOn(observeOn())
        .subscribe(onBind(action), ERROR_ACTION_EMPTY, COMPLETE_ACTION_EMPTY);
  }

  @Override
  public final <E> Subscription bind(final RxObservable<E> observable,
      final Action<? super T, ? super E> action) {
    return bind(observable.asObservable(), action);
  }

  protected boolean isBindable(T target) {
    return true;
  }

  protected Scheduler observeOn() {
    return MAIN_THREAD_SCHEDULER;
  }

  protected <E> Action1<E> onBind(final Action<? super T, E> action) {
    return new Action1<E>() {
      @Override
      public void call(final E e) {
        final T target = get();
        if (target != null && isBindable(target)) {
          action.call(target, e);
        }
      }
    };
  }

}
