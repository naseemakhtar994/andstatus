/**
 * Copyright (C) 2013 yvolk (Yuri Volkov), http://yurivolkov.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.andstatus.app.net;

import android.net.Uri;

import org.andstatus.app.origin.OriginConnectionData;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Specific implementation of the {@link ApiEnum.STATUSNET_TWITTER}
 * @author yvolk
 */
public class ConnectionTwitterStatusNet extends ConnectionTwitter1p0 {
    private static final String TAG = ConnectionTwitterStatusNet.class.getSimpleName();

    protected ConnectionTwitterStatusNet(OriginConnectionData connectionData) {
        super(connectionData);
    }

    @Override
    public List<String> getFriendsIds(String userId) throws ConnectionException {
        Uri sUri = Uri.parse(getApiPath(ApiRoutineEnum.GET_FRIENDS_IDS));
        Uri.Builder builder = sUri.buildUpon();
        builder.appendQueryParameter("user_id", userId);
        List<String> list = new ArrayList<String>();
        JSONArray jArr = httpConnection.getRequestAsArray(builder.build().toString());
        try {
            for (int index = 0; index < jArr.length(); index++) {
                list.add(jArr.getString(index));
            }
        } catch (JSONException e) {
            throw ConnectionException.loggedJsonException(TAG, e, null, "Parsing friendsIds");
        }
        return list;
    }
}
