import {
	ApiUtils,
	_axios
}from '@/api/apiUtil.js';
import constant from '@/util/constant.js';
const api = {
	list: (params, successCallback, failCallback, exceptionCallback) => {
		ApiUtils.getLoadingAjax(constant.serverUrl + "/ebp_cgsq/ebpCgsq/list", params, successCallback, failCallback, exceptionCallback);
	},
	save: (params, successCallback, failCallback, exceptionCallback) => {
		ApiUtils.postLoadingAjax(constant.serverUrl + "/ebp_cgsq/ebpCgsq/save", params, successCallback, failCallback, exceptionCallback);
	},
	toUpdate: (params, successCallback, failCallback, exceptionCallback) => {
		ApiUtils.getLoadingAjax(constant.serverUrl + "/ebp_cgsq/ebpCgsq/toUpdate", params, successCallback, failCallback, exceptionCallback);
	},
	update: (params, successCallback, failCallback, exceptionCallback) => {
		ApiUtils.putLoadingAjax(constant.serverUrl + "/ebp_cgsq/ebpCgsq/update", params, successCallback, failCallback, exceptionCallback);
	},
	remove: (params, successCallback, failCallback, exceptionCallback) => {
		ApiUtils.deleteLoadingAjax(constant.serverUrl + "/ebp_cgsq/ebpCgsq/remove", params, successCallback, failCallback, exceptionCallback);
	},
	cgsqTjsh: (params, successCallback, failCallback, exceptionCallback) => {
		ApiUtils.postLoadingAjax(constant.serverUrl + "/ebp_cgsq/ebpCgsq/cgsqTjsh", params, successCallback, failCallback, exceptionCallback);
	},
}

export default api;
