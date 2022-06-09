import {
	ApiUtils,
	_axios
}from '@/api/apiUtil.js';
import constant from '@/util/constant.js';
const api = {
	list: (params, successCallback, failCallback, exceptionCallback) => {
		ApiUtils.getLoadingAjax(constant.serverUrl + "/ebp_ht/ebpHt/list", params, successCallback, failCallback, exceptionCallback);
	},
	save: (params, successCallback, failCallback, exceptionCallback) => {
		ApiUtils.postLoadingAjax(constant.serverUrl + "/ebp_ht/ebpHt/save", params, successCallback, failCallback, exceptionCallback);
	},
	toUpdate: (params, successCallback, failCallback, exceptionCallback) => {
		ApiUtils.getLoadingAjax(constant.serverUrl + "/ebp_ht/ebpHt/toUpdate", params, successCallback, failCallback, exceptionCallback);
	},
	update: (params, successCallback, failCallback, exceptionCallback) => {
		ApiUtils.putLoadingAjax(constant.serverUrl + "/ebp_ht/ebpHt/update", params, successCallback, failCallback, exceptionCallback);
	},
	remove: (params, successCallback, failCallback, exceptionCallback) => {
		ApiUtils.deleteLoadingAjax(constant.serverUrl + "/ebp_ht/ebpHt/remove", params, successCallback, failCallback, exceptionCallback);
	},
	toHtDraft: (params, successCallback, failCallback, exceptionCallback) => {
		ApiUtils.getLoadingAjax(constant.serverUrl + "/ebp_ht/ebpHt/toHtDraft", params, successCallback, failCallback, exceptionCallback);
	},
	htTjsh: (params, successCallback, failCallback, exceptionCallback) => {
		ApiUtils.postLoadingAjax(constant.serverUrl + "/ebp_ht/ebpHt/htTjsh", params, successCallback, failCallback, exceptionCallback);
	},
}

export default api;
