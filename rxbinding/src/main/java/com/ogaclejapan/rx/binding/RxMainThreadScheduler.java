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

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.TimeUnit;

import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action0;
import rx.internal.schedulers.ScheduledAction;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;

/**
 * Originally from RxAndroid:
 * https://github.com/ReactiveX/RxAndroid/blob/0.x/rxandroid/src/main/java/rx/android/schedulers/HandlerThreadScheduler.java
 */
public class RxMainThreadScheduler extends Scheduler {

  private static final Handler MAIN_THREAD_HANDLER = new Handler(Looper.getMainLooper());

  @Override
  public Worker createWorker() {
    return new MainThreadWorker();
  }

  private static class MainThreadWorker extends Worker {

    private final CompositeSubscription subscription = new CompositeSubscription();
    private Handler handler = MAIN_THREAD_HANDLER;

    @Override
    public Subscription schedule(final Action0 action) {
      return schedule(action, 0, TimeUnit.MILLISECONDS);
    }

    @Override
    public Subscription schedule(final Action0 action, final long delayTime,
        final TimeUnit unit) {

      final ScheduledAction scheduledAction = new ScheduledAction(action);
      scheduledAction.add(Subscriptions.create(new Action0() {
        @Override
        public void call() {
          handler.removeCallbacks(scheduledAction);
        }
      }));
      scheduledAction.addParent(subscription);

      handler.postDelayed(scheduledAction, unit.toMillis(delayTime));

      return scheduledAction;
    }

    @Override
    public void unsubscribe() {
      subscription.unsubscribe();
    }

    @Override
    public boolean isUnsubscribed() {
      return subscription.isUnsubscribed();
    }

  }

}
