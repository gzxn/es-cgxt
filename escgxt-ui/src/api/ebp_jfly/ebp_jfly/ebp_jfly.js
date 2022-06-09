import {
	ApiUtils,
	_axios
}from '@/api/apiUtil.js';
import constant from '@/util/constant.js';
const api = {
	list: (params, successCallback, failCallback, exceptionCallback) => {
		ApiUtils.getLoadingAjax(constant.serverUrl + "/ebp_jfly/ebpJfly/list", params, successCallback, failCallback, exceptionCallback);
	},
	save: (params, successCallback, failCallback, exceptionCallback) => {
		ApiUtils.postLoadingAjax(constant.serverUrl + "/ebp_jfly/ebpJfly/save", params, successCallback, failCallback, exceptionCallback);
	},
	toUpdate: (params, successCallback, failCallback, exceptionCallback) => {
		ApiUtils.getLoadingAjax(constant.serverUrl + "/ebp_jfly/ebpJfly/toUpdate", params, successCallback, failCallback, exceptionCallback);
	},
	update: (params, successCallback, failCallback, exceptionCallback) => {
		ApiUtils.putLoadingAjax(constant.serverUrl + "/ebp_jfly/ebpJfly/update", params, successCallback, failCallback, exceptionCallback);
	},
	remove: (params, successCallback, failCallback, exceptionCallback) => {
		ApiUtils.deleteLoadingAjax(constant.serverUrl + "/ebp_jfly/ebpJfly/remove", params, successCallback, failCallback, exceptionCallback);
	},
	getDepartmentListById: (params, successCallback, failCallback, exceptionCallback) => {
		ApiUtils.getLoadingAjax(constant.serverUrl + "/ebp_jfly/ebpJfly/getDepartmentListById", params, successCallback, failCallback, exceptionCallback);
	},
	getByIds: (params, successCallback, failCallback, exceptionCallback) => {
		ApiUtils.getLoadingAjax(constant.serverUrl + "/ebp_jfly/ebpJfly/getByIds", params, successCallback, failCallback,
			exceptionCallback);
	},
}

export default api;
