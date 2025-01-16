package com.wuzeyu.gateway.center.interfaces;


import com.wuzeyu.gateway.center.application.IRegisterManageService;
import com.wuzeyu.gateway.center.domain.register.model.vo.ApplicationInterfaceMethodVO;
import com.wuzeyu.gateway.center.domain.register.model.vo.ApplicationInterfaceVO;
import com.wuzeyu.gateway.center.domain.register.model.vo.ApplicationSystemVO;
import com.wuzeyu.gateway.center.infrastructure.common.ResponseCode;
import com.wuzeyu.gateway.center.infrastructure.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wuzeyu
 * @description RPC 服务注册管理
 * @github github.com/kkkkendei
 */
@RestController
@RequestMapping("/wg/admin/register")
public class RpcRegisterManage {

    private Logger LOG = LoggerFactory.getLogger(RpcRegisterManage.class);

    @Resource
    private IRegisterManageService registerManageService;

    @PostMapping(value = "registerApplication", produces = "application/json;charset=utf-8")
    public Result<Boolean> registerApplication(@RequestParam String systemId,
                                               @RequestParam String systemName,
                                               @RequestParam String systemType,
                                               @RequestParam String systemRegistry) {

        try {
            LOG.info("注册应用服务 systemId：{}", systemId);
            ApplicationSystemVO applicationSystemVO = new ApplicationSystemVO();
            applicationSystemVO.setSystemId(systemId);
            applicationSystemVO.setSystemName(systemName);
            applicationSystemVO.setSystemType(systemType);
            applicationSystemVO.setSystemRegistry(systemRegistry);
            registerManageService.registerApplication(applicationSystemVO);
            return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), true);
        } catch (DuplicateKeyException e) {
            LOG.warn("注册应用服务重复 systemId：{}", systemId, e);
            return new Result<>(ResponseCode.INDEX_DUP.getCode(), e.getMessage(), true);
        } catch (Exception e) {
            LOG.error("注册应用服务失败 systemId：{}", systemId, e);
            return new Result<>(ResponseCode.UN_ERROR.getCode(), e.getMessage(), false);
        }

    }

    @PostMapping(value = "registerApplicationInterface", produces = "application/json;charset=utf-8")
    public Result<Boolean> registerApplicationInterface(@RequestParam String systemId,
                                                        @RequestParam String interfaceId,
                                                        @RequestParam String interfaceName,
                                                        @RequestParam String interfaceVersion) {
        try {
            LOG.info("注册应用接口 systemId：{} interfaceId：{}", systemId, interfaceId);
            ApplicationInterfaceVO applicationInterfaceVO = new ApplicationInterfaceVO();
            applicationInterfaceVO.setSystemId(systemId);
            applicationInterfaceVO.setInterfaceId(interfaceId);
            applicationInterfaceVO.setInterfaceName(interfaceName);
            applicationInterfaceVO.setInterfaceVersion(interfaceVersion);
            registerManageService.registerApplicationInterface(applicationInterfaceVO);
            return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), true);
        } catch (DuplicateKeyException e) {
            LOG.warn("注册应用接口重复 systemId：{} interfaceId：{}", systemId, interfaceId);
            return new Result<>(ResponseCode.INDEX_DUP.getCode(), e.getMessage(), true);
        } catch (Exception e) {
            LOG.error("注册应用接口失败 systemId：{}", systemId, e);
            return new Result<>(ResponseCode.UN_ERROR.getCode(), e.getMessage(), false);
        }
    }

    @PostMapping(value = "registerApplicationInterfaceMethod", produces = "application/json;charset=utf-8")
    public Result<Boolean> registerApplicationInterfaceMethod(@RequestParam String systemId,
                                                              @RequestParam String interfaceId,
                                                              @RequestParam String methodId,
                                                              @RequestParam String methodName,
                                                              @RequestParam String parameter_type,
                                                              @RequestParam String uri,
                                                              @RequestParam String httpCommandType,
                                                              @RequestParam Integer auth) {
        try {
            LOG.info("注册应用接口方法 systemId：{} interfaceId：{} methodId：{}", systemId, interfaceId, methodId);
            ApplicationInterfaceMethodVO applicationInterfaceVO = new ApplicationInterfaceMethodVO();
            applicationInterfaceVO.setSystemId(systemId);
            applicationInterfaceVO.setInterfaceId(interfaceId);
            applicationInterfaceVO.setMethodId(methodId);
            applicationInterfaceVO.setMethodName(methodName);
            applicationInterfaceVO.setParameterType(parameter_type);
            applicationInterfaceVO.setUri(uri);
            applicationInterfaceVO.setHttpCommandType(httpCommandType);
            applicationInterfaceVO.setAuth(auth);
            registerManageService.registerApplicationInterfaceMethod(applicationInterfaceVO);
            return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), true);
        } catch (DuplicateKeyException e) {
            LOG.warn("注册应用接口重复 systemId：{} interfaceId：{}", systemId, interfaceId);
            return new Result<>(ResponseCode.INDEX_DUP.getCode(), e.getMessage(), true);
        } catch (Exception e) {
            LOG.error("注册应用接口失败 systemId：{}", systemId, e);
            return new Result<>(ResponseCode.UN_ERROR.getCode(), e.getMessage(), false);
        }
    }

}
