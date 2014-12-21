/*
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

import android.annotation.TargetApi;
import android.app.Activity;
import android.view.View;

import rx.Scheduler;

@TargetApi(12)
public class RxView<T extends View> extends RxWeakRef<T>
        implements View.OnAttachStateChangeListener {

    private boolean isDetached = false;

    protected RxView(final T view) {
        super(view);
        view.addOnAttachStateChangeListener(this);
    }

    public static <T extends View> RxView<T> of(T view) {
        return new RxView<T>(view);
    }

    @SuppressWarnings("unchecked")
    public static <T extends View> RxView<T> findById(Activity activity, int id) {
        return of((T) activity.findViewById(id));
    }

    @SuppressWarnings("unchecked")
    public static <T extends View> RxView<T> findById(View view, int id) {
        return of((T) view.findViewById(id));
    }

    @Override
    public void onViewAttachedToWindow(final View v) {
        isDetached = false;
    }

    @Override
    public void onViewDetachedFromWindow(final View v) {
        isDetached = true;
    }

    @Override
    protected boolean isBindable(final T view) {
        return !isDetached;
    }

    @Override
    protected final Scheduler observeOn() {
        return MAIN_THREAD_SCHEDULER;
    }

}
