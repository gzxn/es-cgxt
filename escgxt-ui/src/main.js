import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from 'store';
import Axios from 'axios';
import VueAxios from 'vue-axios';
import qs from 'qs';
import './util/element.js';
import $ from 'jquery'
//md编辑器
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'

//图标选择器
import iconPicker from 'e-icon-picker';
import 'e-icon-picker/dist/index.css';
import 'e-icon-picker/dist/main.css';

//echarts
import echarts from 'echarts'
//自定义工具
import caches from 'util/caches.js';
import constant from 'util/constant.js';
import validate from 'util/validate.js';
import utils from 'util/utils.js';
//权限自定义指令
import hasPermissionCodeAnd from 'directives/permissions/permission/hasPermissionCodeAnd.js';
import hasPermissionCodeOr from 'directives/permissions/permission/hasPermissionCodeOr.js';
import hasRoleCodeAnd from 'directives/permissions/role/hasRoleCodeAnd.js';
import hasRoleCodeOr from 'directives/permissions/role/hasRoleCodeOr.js';
import hasRoleCodesAnd from 'directives/permissions/role/hasRoleCodesAnd.js';
import hasRoleCodesOr from 'directives/permissions/role/hasRoleCodesOr.js';
import hasDepartmentCodeAnd from 'directives/permissions/department/hasDepartmentCodeAnd.js';
import hasDepartmentCodeOr from 'directives/permissions/department/hasDepartmentCodeOr.js';
import hasDepartmentCodesAnd from 'directives/permissions/department/hasDepartmentCodesAnd.js';
import hasDepartmentCodesOr from 'directives/permissions/department/hasDepartmentCodesOr.js';
//可拖拽dialog
import drag from 'directives/drag/drag.js';


import ElementUI from "element-ui";
//禁用遮罩关闭
// ElementUI.Dialog.props.closeOnClickModal.default = false;
// ElementUI.Drawer.props.wrapperClosable.default = false;

Vue.use(iconPicker);
Vue.use(mavonEditor);

Vue.prototype.$caches = caches
Vue.prototype.$constant = constant
Vue.prototype.$validate = validate
Vue.prototype.$utils = utils
Vue.prototype.$echarts = echarts
Vue.prototype.$http = Axios;
Vue.prototype.$qs = qs;
var _ = require('lodash');
Vue.config.productionTip = false;

Vue.directive('hasPermissionCodeAnd', hasPermissionCodeAnd);
Vue.directive('hasPermissionCodeOr', hasPermissionCodeOr);
Vue.directive('hasRoleCodeAnd', hasRoleCodeAnd);
Vue.directive('hasRoleCodeOr', hasRoleCodeOr);
Vue.directive('hasRoleCodesAnd', hasRoleCodesAnd);
Vue.directive('hasRoleCodesOr', hasRoleCodesOr);
Vue.directive('hasDepartmentCodeAnd', hasDepartmentCodeAnd);
Vue.directive('hasDepartmentCodeOr', hasDepartmentCodeOr);
Vue.directive('hasDepartmentCodesAnd', hasDepartmentCodesAnd);
Vue.directive('hasDepartmentCodesOr', hasDepartmentCodesOr);
Vue.directive('dialogDrag', drag);

new Vue({
	router,
	store,
	render: h => h(App)
}).$mount('#app');
