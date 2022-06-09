<template>
	<popup ref="popup" :loading="popupLoading">
		<div slot="body">
			<el-form class="dataForm" ref="form" :disabled="disabled" :model="formParameter" :inline="true" label-width="80px" :rules="rules">
					<el-row>
						<el-col :span="24">
							<el-form-item label="采购申请id" prop="cgsqid"><el-input v-model="formParameter.cgsqid" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="经费来源id" prop="jflyid"><el-input v-model="formParameter.jflyid" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="使用金额(元)" prop="syje"><el-input v-model="formParameter.syje" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="预算编码" prop="ysbm"><el-input v-model="formParameter.ysbm" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="预算名称" prop="ysmc"><el-input v-model="formParameter.ysmc" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="经费类型" prop="jfType"><el-input v-model="formParameter.jfType" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="是否为年度预算内资金：0-否、1-是" prop="isNdysnzj"><el-input v-model="formParameter.isNdysnzj" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="删除状态：0-否、1-是" prop="deleteFlag"><el-input v-model="formParameter.deleteFlag" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
			</el-form>
		</div>
		<div slot="footer">
			<el-button v-if="!disabled" type="primary" :loading="confirmLoading" @click="confirm()">确 定</el-button>
			<el-button @click="close">取 消</el-button>
		</div>
	</popup>
</template>

<script>
import popup from '@/components/popup/drawerPopup.vue';
import api from '@/api/ebp_jfly/ebp_jfsy/ebp_jfsy.js';
export default {
	name: 'edit',
	components: {
			popup
		},
	data() {
		return {
			disabled: false,
			popupLoading: false,
			confirmLoading: false,
			parameter: {},
			formParameter: {},
			rules: {
						//采购申请id
						cgsqid: [
							{
								validator: this.$validate.required,
								trigger: 'blur'
							},
							{
								validator: this.$validate.length,
								max: 32,
								trigger: 'blur'
							}
						],
						//经费来源id
						jflyid: [
							{
								validator: this.$validate.required,
								trigger: 'blur'
							},
							{
								validator: this.$validate.length,
								max: 32,
								trigger: 'blur'
							}
						],
						//使用金额(元)
						syje: [
							{
								validator: this.$validate.required,
								trigger: 'blur'
							},
							{
								validator: this.$validate.length,
								max: 12,
								trigger: 'blur'
							}
						],
						//预算编码
						ysbm: [
							{
								validator: this.$validate.required,
								trigger: 'blur'
							},
							{
								validator: this.$validate.length,
								max: 36,
								trigger: 'blur'
							}
						],
						//预算名称
						ysmc: [
							{
								validator: this.$validate.required,
								trigger: 'blur'
							},
							{
								validator: this.$validate.length,
								max: 100,
								trigger: 'blur'
							}
						],
						//经费类型
						jfType: [
							{
								validator: this.$validate.required,
								trigger: 'blur'
							},
							{
								validator: this.$validate.length,
								max: 36,
								trigger: 'blur'
							}
						],
						//是否为年度预算内资金：0-否、1-是
						isNdysnzj: [
							{
								validator: this.$validate.required,
								trigger: 'blur'
							},
							{
								validator: this.$validate.length,
								max: 11,
								trigger: 'blur'
							}
						],
						//删除状态：0-否、1-是
						deleteFlag: [
							{
								validator: this.$validate.required,
								trigger: 'blur'
							},
							{
								validator: this.$validate.length,
								max: 11,
								trigger: 'blur'
							}
						],
			}
		};
	},
	methods: {
			//打开弹框
			open(parameter, title, disabled, size) {
				this.parameter = parameter;
				this.disabled = disabled;
				this.$refs.popup.open(title, size);
				this.$nextTick(() => {
					this.init();
				})
			},
			//关闭弹框
			close() {
				this.disabled = false;
				this.$refs.popup.close();
			},
			//提交表单
			confirm() {
				this.$utils.checkForm(this.$refs.form, () => {
					this.confirmLoading = true;
					if (!this.parameter.id) {
						//添加
						let parameter = this.$utils.merger(this.formParameter);
						api.save(parameter,
							response => {
								this.$utils.msg.success(response.msg);
								this.confirmLoading = false;
								this.close();
								this.$parent.list();
							},
							response => {
								this.$utils.msg.warning(response.msg);
								this.confirmLoading = false;
							}
						);
					} else {
						//编辑
						let parameter = this.$utils.merger(this.formParameter);
						api.update(parameter,
							response => {
								this.$utils.msg.success(response.msg);
								this.confirmLoading = false;
								this.close();
								this.$parent.list();
							},
							response => {
								this.$utils.msg.warning(response.msg);
								this.confirmLoading = false;
							}
						);
					}
				});
			},
			init() {
				this.popupLoading = true;
				//清除等待
				this.confirmLoading = false;
				//清空内容
				this.formParameter = {};
				//清楚校验
				this.$refs.form.clearValidate();
				
				if (this.parameter.id) {
					//编辑查询数据
					let parameter = {
						id: this.parameter.id
					};
					api.toUpdate(parameter, response => {
						this.formParameter = response.data;
						this.popupLoading = false;
					});
				} else {
					//添加
					this.popupLoading = false;
				}
			}
		}
	};
</script>

<style lang="scss" scoped="scoped">
	@import '~common/custom/css/common.scss';
	@import '~common/custom/css/popup/popup.scss';
</style>
