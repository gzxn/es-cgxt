import {
	ApiUtils,
	_axios
}from '@/api/apiUtil.js';
import constant from '@/util/constant.js';
const api = {
	list: (params, successCallback, failCallback, exceptionCallback) => {
		ApiUtils.getLoadingAjax(constant.serverUrl + "/lcgl/lcb/list", params, successCallback, failCallback, exceptionCallback);
	},
	save: (params, successCallback, failCallback, exceptionCallback) => {
		ApiUtils.postLoadingAjax(constant.serverUrl + "/lcgl/lcb/save", params, successCallback, failCallback, exceptionCallback);
	},
	toUpdate: (params, successCallback, failCallback, exceptionCallback) => {
		ApiUtils.getLoadingAjax(constant.serverUrl + "/lcgl/lcb/toUpdate", params, successCallback, failCallback, exceptionCallback);
	},
	update: (params, successCallback, failCallback, exceptionCallback) => {
		ApiUtils.putLoadingAjax(constant.serverUrl + "/lcgl/lcb/update", params, successCallback, failCallback, exceptionCallback);
	},
	remove: (params, successCallback, failCallback, exceptionCallback) => {
		ApiUtils.deleteLoadingAjax(constant.serverUrl + "/lcgl/lcb/remove", params, successCallback, failCallback, exceptionCallback);
	},
	getShNode: (params, successCallback, failCallback, exceptionCallback) => {
		ApiUtils.getLoadingAjax(constant.serverUrl + "/lcgl/lcb/getShNode", params, successCallback, failCallback, exceptionCallback);
	},
}

export default api;
