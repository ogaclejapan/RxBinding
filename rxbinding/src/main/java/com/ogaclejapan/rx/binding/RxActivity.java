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

import android.app.Activity;

import rx.Scheduler;

public class RxActivity<T extends Activity> extends RxWeakRef<T> {

  protected RxActivity(final T activity) {
    super(activity);
  }

  public static <T extends Activity> RxActivity<T> of(T activity) {
    return new RxActivity<T>(activity);
  }

  @Override
  protected boolean isBindable(final T activity) {
    return !activity.isFinishing();
  }

  @Override
  protected final Scheduler observeOn() {
    return MAIN_THREAD_SCHEDULER;
  }

}
