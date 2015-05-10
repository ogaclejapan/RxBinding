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

import com.ogaclejapan.rx.binding.tuple.Tuple;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import rx.Observable;
import rx.Subscription;

public interface RxReadOnlyList<E> {

  void setOnDataSetChangeListener(OnDataSetChangeListener listener);

  E get(int index);

  int indexOf(Object object);

  int lastIndexOf(Object object);

  boolean isEmpty();

  boolean contains(Object object);

  boolean containsAll(Collection<?> c);

  int size();

  Iterator<E> iterator();

  ListIterator<E> listIterator();

  ListIterator<E> listIterator(int index);

  List<E> subList(int fromIndex, int toIndex);

  Observable<Kind<Event, Tuple>> onDataSetChanged();

  Subscription bind(RxReadOnlyList<E> list);

  enum Event {
    add, addAt, addAll, addAllAt, clear,
    removeAt, remove, removeAll, retainAll, set
  }

  interface OnDataSetChangeListener {

    void onDataSetChanged(Event type);
  }

}
