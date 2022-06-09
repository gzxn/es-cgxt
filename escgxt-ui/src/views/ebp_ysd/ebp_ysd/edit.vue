<template>
	<popup ref="popup" :loading="popupLoading">
		<div slot="body">
			<el-form class="dataForm" ref="form" :disabled="disabled" :model="formParameter" :inline="true" label-width="80px" :rules="rules">
					<el-row>
						<el-col :span="24">
							<el-form-item label="招标结果id" prop="zbjgId"><el-input v-model="formParameter.zbjgId" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="合同id" prop="htid"><el-input v-model="formParameter.htid" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="到货时间" prop="dhsj"><el-input v-model="formParameter.dhsj" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="是否验收：0-否、1-是" prop="isSuccess"><el-input v-model="formParameter.isSuccess" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="验收状态" prop="yszt"><el-input v-model="formParameter.yszt" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="验收申请附件" prop="yssqfj"><el-input v-model="formParameter.yssqfj" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="正式验收报告" prop="zsysbg"><el-input v-model="formParameter.zsysbg" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="货物类型" prop="hwlx"><el-input v-model="formParameter.hwlx" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="当前节点" prop="dqjd"><el-input v-model="formParameter.dqjd" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="历史处理人用户名" prop="lsclryhm"><el-input v-model="formParameter.lsclryhm" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="当前处理人" prop="dqclr"><el-input v-model="formParameter.dqclr" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="当前处理人用户名" prop="dqclryhm"><el-input v-model="formParameter.dqclryhm" autocomplete="off"></el-input></el-form-item>
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
import api from '@/api/ebp_ysd/ebp_ysd/ebp_ysd.js';
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
						//招标结果id
						zbjgId: [
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
						//合同id
						htid: [
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
						//到货时间
						dhsj: [
							{
								validator: this.$validate.required,
								trigger: 'blur'
							},
							{
								validator: this.$validate.length,
								max: 20,
								trigger: 'blur'
							}
						],
						//是否验收：0-否、1-是
						isSuccess: [
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
						//验收状态
						yszt: [
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
						//验收申请附件
						yssqfj: [
							{
								validator: this.$validate.required,
								trigger: 'blur'
							},
							{
								validator: this.$validate.length,
								max: 2000,
								trigger: 'blur'
							}
						],
						//正式验收报告
						zsysbg: [
							{
								validator: this.$validate.required,
								trigger: 'blur'
							},
							{
								validator: this.$validate.length,
								max: 2000,
								trigger: 'blur'
							}
						],
						//货物类型
						hwlx: [
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
						//当前节点
						dqjd: [
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
						//历史处理人用户名
						lsclryhm: [
							{
								validator: this.$validate.required,
								trigger: 'blur'
							},
							{
								validator: this.$validate.length,
								max: 300,
								trigger: 'blur'
							}
						],
						//当前处理人
						dqclr: [
							{
								validator: this.$validate.required,
								trigger: 'blur'
							},
							{
								validator: this.$validate.length,
								max: 300,
								trigger: 'blur'
							}
						],
						//当前处理人用户名
						dqclryhm: [
							{
								validator: this.$validate.required,
								trigger: 'blur'
							},
							{
								validator: this.$validate.length,
								max: 200,
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
