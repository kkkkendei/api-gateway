import request from '../utils/request';
import {config} from "md-editor-v3/lib/MdEditor/config";

export const fetchData = () => {
    return request({
        url: './table.json',
        method: 'get'
    });
};

// export const gatewayServerData = () => {
//     return request({
//         url: './gateway_server.json',
//         method: 'get'
//     });
// };

// function 方式定义函数
// @ts-ignore
export function gatewayServerData(query) {
    return request({
        url: 'http://localhost:8001/wg/admin/data/queryGatewayServer?groupId=' + query.groupId + '&page=' + query.pageIndex + '&limit=' + query.pageSize,
        method: 'get'
    });
}

// const 方式定义函数
// @ts-ignore
export const gatewayServerDetailData = (query) => {
    return request({
        // url: './gateway_server_detail.json',
        url: 'http://localhost:8001/wg/admin/data/queryGatewayServerDetail?groupId=' + query.groupId + '&gatewayId=' + query.gatewayId + '&page=' + query.pageIndex + '&limit=' + query.pageSize,
        method: 'get'
    });
};
// @ts-ignore
export const gatewayDistributionData = (query) => {
    return request({
        // url: './gateway_distribution.json',
        url: 'http://localhost:8001/wg/admin/data/queryGatewayDistribution?groupId=' + query.groupId + '&gatewayId=' + query.gatewayId + '&page=' + query.pageIndex + '&limit=' + query.pageSize,
        method: 'get'
    });
};
// @ts-ignore
export const applicationSystemData = (query) => {
    return request({
        // url: './application_system.json',
        url: 'http://localhost:8001/wg/admin/data/queryApplicationSystem?systemId=' + query.systemId + '&systemName=' + query.systemName + '&page=' + query.pageIndex + '&limit=' + query.pageSize,
        method: 'get'
    });
};
// @ts-ignore
export const applicationInterfaceData = (query) => {
    return request({
        // url: './application_interface.json',
        url: 'http://localhost:8001/wg/admin/data/queryApplicationInterface?systemId=' + query.systemId + '&interfaceId=' + query.interfaceId + '&page=' + query.pageIndex + '&limit=' + query.pageSize,
        method: 'get'
    });
};
// @ts-ignore
export const applicationInterfaceMethodData = (query) => {
    return request({
        // url: './application_interface_method.json',
        url: 'http://localhost:8001/wg/admin/data/queryApplicationInterfaceMethodList?systemId=' + query.systemId + '&interfaceId=' + query.interfaceId + '&page=' + query.pageIndex + '&limit=' + query.pageSize,
        method: 'get'
    });
};
