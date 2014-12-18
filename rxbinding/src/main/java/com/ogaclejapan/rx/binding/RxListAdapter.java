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

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import rx.Subscription;

public abstract class RxListAdapter<T> extends BaseAdapter
        implements RxReadOnlyList.OnDataSetChangeListener {

    private final Context context;

    private final LayoutInflater inflater;

    private final RxReadOnlyList<T> list;

    protected RxListAdapter(Context context) {
        this(context, RxList.<T>create());
    }

    protected RxListAdapter(Context context, RxList<T> list) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.list = list;
        this.list.setOnDataSetChangeListener(this);
    }

    public Context getContext() {
        return context;
    }

    public Subscription bind(RxReadOnlyList<T> items) {
        return list.bind(items);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public T getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = newView(inflater, position, parent);
        }
        bindView(getItem(position), position, convertView);
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = newView(inflater, position, parent);
        }
        bindView(getItem(position), position, convertView);
        return convertView;
    }

    @Override
    public void onDataSetChanged(final RxReadOnlyList.Event type) {
        switch (type) {
            case clear:
                notifyDataSetInvalidated();
                break;
            default:
                notifyDataSetChanged();
        }
    }

    protected RxReadOnlyList<T> getItems() {
        return list;
    }

    protected abstract View newView(LayoutInflater inflater, int position, ViewGroup parent);

    protected abstract void bindView(T item, int position, View v);


}
