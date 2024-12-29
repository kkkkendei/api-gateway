package com.wuzeyu.gateway.bind;
import org.apache.dubbo.rpc.service.GenericService;

public interface IGenericReference {

    String $invoke(String args);

}
