package com.admiralfivetigers.fiveghost.db.factor;

import android.content.Context;

import com.admiralfivetigers.fiveghost.db.product.IOpenHelper;
import com.admiralfivetigers.fiveghost.http.products.HttpRequest;

/**
 * @author 农民伯伯
 * @version 2017/12/26
 */

public interface DatabaseCreat {
    <T extends HttpRequest> IOpenHelper conCreate(Context context, Class<T> clz);
}
