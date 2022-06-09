import {
	ApiUtils,
	_axios
}from '@/api/apiUtil.js';
import constant from '@/util/constant.js';
const api = {
	list: (params, successCallback, failCallback, exceptionCallback) => {
		ApiUtils.getLoadingAjax(constant.serverUrl + "/ebp_zbxm/ebpZbxm/list", params, successCallback, failCallback, exceptionCallback);
	},
	save: (params, successCallback, failCallback, exceptionCallback) => {
		ApiUtils.postLoadingAjax(constant.serverUrl + "/ebp_zbxm/ebpZbxm/save", params, successCallback, failCallback, exceptionCallback);
	},
	toUpdate: (params, successCallback, failCallback, exceptionCallback) => {
		ApiUtils.getLoadingAjax(constant.serverUrl + "/ebp_zbxm/ebpZbxm/toUpdate", params, successCallback, failCallback, exceptionCallback);
	},
	update: (params, successCallback, failCallback, exceptionCallback) => {
		ApiUtils.putLoadingAjax(constant.serverUrl + "/ebp_zbxm/ebpZbxm/update", params, successCallback, failCallback, exceptionCallback);
	},
	remove: (params, successCallback, failCallback, exceptionCallback) => {
		ApiUtils.deleteLoadingAjax(constant.serverUrl + "/ebp_zbxm/ebpZbxm/remove", params, successCallback, failCallback, exceptionCallback);
	},
	cgrwPack: (params, successCallback, failCallback, exceptionCallback) => {
		ApiUtils.postLoadingAjax(constant.serverUrl + "/ebp_zbxm/ebpZbxm/cgrwPack", params, successCallback, failCallback, exceptionCallback);
	},
}

export default api;
