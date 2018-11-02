package com.baoshi.wcs.common.response;

import com.baoshi.wcs.entity.Requestor;

public class WCSApiResponse<T> extends ApiResponse<T> {

    private static final long serialVersionUID = 3571818710910772372L;

    Requestor requestor;

    public Requestor getRequestor() {
        return requestor;
    }

    public void setRequestor(Requestor requestor) {
        this.requestor = requestor;
    }
}
