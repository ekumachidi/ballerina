/*
 * Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.ballerinalang.nativeimpl.net.http;

import org.ballerinalang.bre.Context;
import org.ballerinalang.model.types.TypeEnum;
import org.ballerinalang.model.values.BInteger;
import org.ballerinalang.model.values.BMessage;
import org.ballerinalang.model.values.BValue;
import org.ballerinalang.nativeimpl.actions.http.Constants;
import org.ballerinalang.natives.AbstractNativeFunction;
import org.ballerinalang.natives.annotations.Argument;
import org.ballerinalang.natives.annotations.Attribute;
import org.ballerinalang.natives.annotations.BallerinaAnnotation;
import org.ballerinalang.natives.annotations.BallerinaFunction;
import org.ballerinalang.natives.annotations.ReturnType;
import org.ballerinalang.util.exceptions.BallerinaException;

/**
 * Get Content-Length header from the Message.
 */
@BallerinaFunction(
        packageName = "ballerina.net.http",
        functionName = "getContentLength",
        args = {@Argument(name = "m", type = TypeEnum.MESSAGE)},
        returnType = {@ReturnType(type = TypeEnum.INT)},
        isPublic = true
)
@BallerinaAnnotation(annotationName = "Description", attributes = {@Attribute(name = "value",
        value = "Gets the Content-Length header from the message") })
@BallerinaAnnotation(annotationName = "Param", attributes = {@Attribute(name = "m",
        value = "A message object") })
@BallerinaAnnotation(annotationName = "Return", attributes = {@Attribute(name = "int",
        value = "length of the message") })
public class GetContentLength extends AbstractNativeFunction {

    @Override
    public BValue[] execute(Context context) {
        int contentLength = -1;
        BMessage bMsg = (BMessage) getRefArgument(context, 0);
        String lengthStr = (String) bMsg.getHeader(Constants.HTTP_CONTENT_LENGTH);

        try {
            contentLength = Integer.parseInt(lengthStr);
        } catch (NumberFormatException e) {
            throw new BallerinaException("Invalid content length");
        }
        return getBValues(new BInteger(contentLength));
    }
}
