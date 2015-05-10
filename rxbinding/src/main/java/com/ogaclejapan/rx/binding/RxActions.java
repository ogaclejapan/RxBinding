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

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Map;

public class RxActions {

  //__/__/__/__/__/__/__/__/__/__/
  // AbsListView
  //__/__/__/__/__/__/__/__/__/__/

  private RxActions() {
    //No instances.
  }

  //__/__/__/__/__/__/__/__/__/__/
  // ImageView
  //__/__/__/__/__/__/__/__/__/__/

  public static <T extends AbsListView> RxAction<T, String> setFilterText() {
    return new RxAction<T, String>() {
      @Override
      public void call(T view, String filterText) {
        if (TextUtils.isEmpty(filterText)) {
          view.clearTextFilter();
        } else {
          view.setFilterText(filterText);
        }
      }
    };
  }

  public static <T extends ImageView> RxAction<T, Integer> setImageResource() {
    return new RxAction<T, Integer>() {
      @Override
      public void call(T view, Integer resId) {
        view.setImageResource(resId);
      }
    };
  }

  public static <T extends ImageView> RxAction<T, Bitmap> setImageBitmap() {
    return new RxAction<T, Bitmap>() {
      @Override
      public void call(T view, Bitmap bm) {
        view.setImageBitmap(bm);
      }
    };
  }

  public static <T extends ImageView> RxAction<T, Drawable> setImageDrawable() {
    return new RxAction<T, Drawable>() {
      @Override
      public void call(T view, Drawable drawable) {
        view.setImageDrawable(drawable);
      }
    };
  }

  //__/__/__/__/__/__/__/__/__/__/
  // TextView
  //__/__/__/__/__/__/__/__/__/__/

  public static <T extends ImageView> RxAction<T, Integer> setImageLevel() {
    return new RxAction<T, Integer>() {
      @Override
      public void call(T view, Integer level) {
        view.setImageLevel(level);
      }
    };
  }

  public static <T extends TextView> RxAction<T, CharSequence> setText() {
    return new RxAction<T, CharSequence>() {
      @Override
      public void call(T view, CharSequence s) {
        view.setText(s);
      }
    };
  }

  public static <T extends TextView> RxAction<T, Integer> setTextFromResId() {
    return new RxAction<T, Integer>() {
      @Override
      public void call(T view, Integer resId) {
        view.setText(resId);
      }
    };
  }

  public static <T extends TextView> RxAction<T, Integer> setTextColor() {
    return new RxAction<T, Integer>() {
      @Override
      public void call(T view, Integer color) {
        view.setTextColor(color);
      }
    };
  }

  //__/__/__/__/__/__/__/__/__/__/
  // View
  //__/__/__/__/__/__/__/__/__/__/

  public static <T extends TextView> RxAction<T, Float> setTextSize() {
    return new RxAction<T, Float>() {
      @Override
      public void call(T view, Float size) {
        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
      }
    };
  }

  public static <T extends View> RxAction<T, Boolean> setVisibility() {
    return setVisibility(View.GONE);
  }

  public static <T extends View> RxAction<T, Boolean> setVisibility(final int hiddenVisibility) {
    return new RxAction<T, Boolean>() {
      @Override
      public void call(T view, Boolean visible) {
        final int visibility = (visible) ? View.VISIBLE : hiddenVisibility;
        if (view.getVisibility() != visibility) {
          view.setVisibility(visibility);
        }
      }
    };
  }

  public static <T extends View> RxAction<T, Boolean> setSelected() {
    return new RxAction<T, Boolean>() {
      @Override
      public void call(T view, Boolean selected) {
        view.setSelected(selected);
      }
    };
  }

  public static <T extends View> RxAction<T, Boolean> setEnabled() {
    return new RxAction<T, Boolean>() {
      @Override
      public void call(T view, Boolean enabled) {
        view.setEnabled(enabled);
      }
    };
  }

  public static <T extends View> RxAction<T, Integer> setBackgroundResource() {
    return new RxAction<T, Integer>() {
      @Override
      public void call(T view, Integer resId) {
        view.setBackgroundResource(resId);
      }
    };
  }

  //__/__/__/__/__/__/__/__/__/__/
  // WebView
  //__/__/__/__/__/__/__/__/__/__/

  public static <T extends View> RxAction<T, Integer> setBackgroundColor() {
    return new RxAction<T, Integer>() {
      @Override
      public void call(T view, Integer color) {
        view.setBackgroundColor(color);
      }
    };
  }

  public static <T extends WebView> RxAction<T, String> loadUrl() {
    return new RxAction<T, String>() {
      @Override
      public void call(T webview, String url) {
        webview.loadUrl(url);
      }
    };
  }

  public static <T extends WebView> RxAction<T, String> loadUrl(
      final Map<String, String> addtionalHttpHeaders) {
    return new RxAction<T, String>() {
      @Override
      public void call(T webview, String url) {
        webview.loadUrl(url, addtionalHttpHeaders);
      }
    };
  }

  public static <T extends WebView> RxAction<T, String> loadData(final String mimeType,
      final String enconding) {
    return new RxAction<T, String>() {
      @Override
      public void call(T webview, String data) {
        webview.loadData(data, mimeType, enconding);
      }
    };
  }

  public static <T extends WebView> RxAction<T, String> loadDataWithBaseURL() {
    return loadDataWithBaseURL("file:///android_asset/", "text/html", "utf-8", null);
  }

  public static <T extends WebView> RxAction<T, String> loadDataWithBaseURL(
      final String baseUrl) {
    return loadDataWithBaseURL(baseUrl, "text/html", "utf-8", null);
  }

  public static <T extends WebView> RxAction<T, String> loadDataWithBaseURL(final String baseUrl,
      final String mimeType, final String enconding, final String failUrl) {
    return new RxAction<T, String>() {
      @Override
      public void call(final T webview, final String data) {
        webview.loadDataWithBaseURL(baseUrl, data, mimeType, enconding, failUrl);
      }
    };
  }

}
