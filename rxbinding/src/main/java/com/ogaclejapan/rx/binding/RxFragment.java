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
import android.app.Fragment;

import rx.Scheduler;

@TargetApi(11)
public class RxFragment<T extends Fragment> extends RxWeakRef<T> {

    protected RxFragment(final T fragment) {
        super(fragment);
    }

    public static <T extends Fragment> RxFragment<T> of(T fragment) {
        return new RxFragment<T>(fragment);
    }

    @Override
    protected boolean isBindable(final T fragment) {
        return fragment.isAdded() && !fragment.getActivity().isFinishing();
    }

    @Override
    protected final Scheduler observeOn() {
        return MAIN_THREAD_SCHEDULER;
    }

}
