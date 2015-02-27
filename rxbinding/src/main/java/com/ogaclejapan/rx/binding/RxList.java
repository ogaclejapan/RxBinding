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


import com.ogaclejapan.rx.binding.tuple.Tuple;
import com.ogaclejapan.rx.binding.tuple.Tuple1;
import com.ogaclejapan.rx.binding.tuple.Tuple2;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import rx.Subscription;

public class RxList<E> extends RxObject<List<E>> implements RxReadOnlyList<E>, List<E> {

    private final RxAction<List<E>, Kind<Event, Tuple>> BIND_ACTION =
            new RxAction<List<E>, Kind<Event, Tuple>>() {
                @Override
                public void call(List<E> list, Kind<Event, Tuple> kind) {
                    switch (kind.type) {
                        case add:
                            Tuple1<E> listAddData = Tuple.of(kind.data);
                            add(list, listAddData.item1);
                            break;
                        case addAt:
                            Tuple2<Integer, E> listAddAtData = Tuple.of(kind.data);
                            add(list, listAddAtData.item1, listAddAtData.item2);
                            break;
                        case addAll:
                            Tuple1<Collection<E>> listAddAllData = Tuple.of(kind.data);
                            addAll(list, listAddAllData.item1);
                            break;
                        case addAllAt:
                            Tuple2<Integer, Collection<E>> listAddAllAtData = Tuple.of(kind.data);
                            addAll(list, listAddAllAtData.item1, listAddAllAtData.item2);
                            break;
                        case clear:
                            clear();
                            break;
                        case removeAt:
                            Tuple1<Integer> listRemoveAtData = Tuple.of(kind.data);
                            remove(list, listRemoveAtData.item1);
                            break;
                        case remove:
                            Tuple1<E> listRemoveData = Tuple.of(kind.data);
                            remove(list, listRemoveData.item1);
                            break;
                        case removeAll:
                            Tuple1<Collection<E>> listRemoveAllData = Tuple.of(kind.data);
                            removeAll(list, listRemoveAllData.item1);
                            break;
                        case retainAll:
                            Tuple1<Collection<E>> listRetainAllData = Tuple.of(kind.data);
                            retainAll(list, listRetainAllData.item1);
                            break;
                        case set:
                            Tuple2<Integer, E> listSetData = Tuple.of(kind.data);
                            set(list, listSetData.item1, listSetData.item2);
                            break;
                        default:
                            return;
                    }

                }
            };

    private final RxEvent<Kind<Event, Tuple>> onDataSetChanged;

    private OnDataSetChangeListener onDataSetChangeListener;

    protected RxList(List<E> list) {
        super(list);
        onDataSetChanged = RxEvent.create();
    }

    public static <E> RxList<E> create() {
        return of(new ArrayList<E>());
    }

    public static <E> RxList<E> of(List<E> initialList) {
        return new RxList<E>(initialList);
    }

    @Override
    public void setOnDataSetChangeListener(final OnDataSetChangeListener listener) {
        this.onDataSetChangeListener = listener;
    }

    @Override
    public boolean add(E e) {
        return add(list(), e);
    }

    @Override
    public void add(int index, E element) {
        add(list(), index, element);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return addAll(list(), c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return addAll(list(), index, c);
    }

    @Override
    public void clear() {
        clear(list());
    }

    @Override
    public E remove(int index) {
        return remove(list(), index);
    }

    @Override
    public boolean remove(Object object) {
        return remove(list(), object);
    }

    @Override
    public boolean removeAll(@NonNull Collection<?> c) {
        return removeAll(list(), c);
    }

    @Override
    public boolean retainAll(@NonNull Collection<?> c) {
        return retainAll(list(), c);
    }

    @Override
    public E set(int index, E element) {
        return set(list(), index, element);
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return list().toArray();
    }

    @NonNull
    @Override
    public <T> T[] toArray(@NonNull T[] array) {
        return list().toArray(array);
    }

    @Override
    public E get(int index) {
        return list().get(index);
    }

    @Override
    public int indexOf(Object object) {
        return list().indexOf(object);
    }

    @Override
    public int lastIndexOf(Object object) {
        return list().lastIndexOf(object);
    }

    @Override
    public boolean isEmpty() {
        return list().isEmpty();
    }

    @Override
    public boolean contains(Object object) {
        return list().contains(object);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return list().containsAll(c);
    }

    @Override
    public int size() {
        return list().size();
    }

    @NonNull
    @Override
    public Iterator<E> iterator() {
        return list().iterator();
    }

    @NonNull
    @Override
    public ListIterator<E> listIterator() {
        return list().listIterator();
    }

    @NonNull
    @Override
    public ListIterator<E> listIterator(int index) {
        return list().listIterator(index);
    }

    @NonNull
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return list().subList(fromIndex, toIndex);
    }

    @Override
    public rx.Observable<Kind<Event, Tuple>> onDataSetChanged() {
        return onDataSetChanged.asObservable();
    }

    @Override
    public Subscription bind(final RxReadOnlyList<E> list) {
        if (list.size() > 0) {
            addAll(list.subList(0, list.size()));
        }
        return bind(list.onDataSetChanged(), BIND_ACTION);
    }

    private List<E> list() {
        return get();
    }

    private void notifyDataSetChanged(Event type, Tuple data) {
        final Kind<Event, Tuple> event = new Kind<Event, Tuple>(type, data);
        onDataSetChanged.post(event);
        if (onDataSetChangeListener != null) {
            onDataSetChangeListener.onDataSetChanged(type);
        }
    }

    private boolean add(List<E> list, E e) {
        final boolean result = list.add(e);
        notifyDataSetChanged(Event.add, Tuple.create(e));
        return result;
    }

    private void add(List<E> list, int index, E element) {
        list.add(index, element);
        notifyDataSetChanged(Event.addAt, Tuple.create(index, element));
    }

    private boolean addAll(List<E> list, Collection<? extends E> c) {
        final boolean result = list.addAll(c);
        notifyDataSetChanged(Event.addAll, Tuple.create(c));
        return result;
    }

    private boolean addAll(List<E> list, int index, Collection<? extends E> c) {
        final boolean result = list.addAll(index, c);
        notifyDataSetChanged(Event.addAllAt, Tuple.create(index, c));
        return result;
    }

    private void clear(List<E> list) {
        list.clear();
        notifyDataSetChanged(Event.clear, Tuple.create());
    }

    private E remove(List<E> list, int index) {
        final E result = list.remove(index);
        notifyDataSetChanged(Event.removeAt, Tuple.create(index));
        return result;
    }

    private boolean remove(List<E> list, Object object) {
        final boolean result = list.remove(object);
        notifyDataSetChanged(Event.remove, Tuple.create(object));
        return result;
    }

    private boolean removeAll(List<E> list, @NonNull Collection<?> c) {
        final boolean result = list.removeAll(c);
        notifyDataSetChanged(Event.removeAll, Tuple.create(c));
        return result;
    }

    private boolean retainAll(List<E> list, @NonNull Collection<?> c) {
        final boolean result = list.retainAll(c);
        notifyDataSetChanged(Event.retainAll, Tuple.create(c));
        return result;
    }

    private E set(List<E> list, int index, E element) {
        final E result = list.set(index, element);
        notifyDataSetChanged(Event.set, Tuple.create(index, element));
        return result;
    }

}
