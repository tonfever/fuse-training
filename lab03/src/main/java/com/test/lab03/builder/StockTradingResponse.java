package com.test.lab03.builder;

import org.apache.camel.Exchange;
import org.apache.camel.Header;
import com.test.lab03.model.Stocktrading;
import com.test.lab03.common.ResponseWrapper;
import com.test.lab03.common.Constant;

public class StockTradingResponse {
    public void buildGetContractStatusResponse(@Header("versionNo") String versionNo,
                                               @Header("serviceFunction") String serviceFunction,
                                               Exchange exchange){

        Stocktrading stocktrading = exchange.getIn().getBody(Stocktrading.class);

        ResponseWrapper responseWrapper = populateResponseBody(stocktrading);

        exchange.getIn().setBody(responseWrapper);
    }

    private ResponseWrapper populateResponseBody(Stocktrading stocktrading)
    {
        ResponseWrapper response = new ResponseWrapper();
        response.setBody(stocktrading, false);
        response.setErrorCode(Constant.RESPONSE_CODE_SUCCESS);

        return response;
    }
}
