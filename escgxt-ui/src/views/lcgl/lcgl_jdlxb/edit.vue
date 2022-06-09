<template>
	<popup ref="popup" :loading="popupLoading">
		<div slot="body">
			<el-form class="dataForm" ref="form" :disabled="disabled" :model="formParameter" :inline="true" label-width="80px" :rules="rules">
					<el-row>
						<el-col :span="24">
							<el-form-item label="顺序号" prop="sxh"><el-input v-model="formParameter.sxh" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="流程id" prop="lcid"><el-input v-model="formParameter.lcid" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="起始节点主键" prop="qsjdid"><el-input v-model="formParameter.qsjdid" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="指向节点主键" prop="zxjdid"><el-input v-model="formParameter.zxjdid" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="流程实例id" prop="lcslid"><el-input v-model="formParameter.lcslid" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="表单id" prop="bdid"><el-input v-model="formParameter.bdid" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="始节点是否会签：0-否、1-是" prop="qjdsfhq"><el-input v-model="formParameter.qjdsfhq" autocomplete="off"></el-input></el-form-item>
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
import api from '@/api/lcgl/lcgl_jdlxb/lcgl_jdlxb.js';
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
						//顺序号
						sxh: [
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
						//流程id
						lcid: [
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
						//起始节点主键
						qsjdid: [
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
						//指向节点主键
						zxjdid: [
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
						//流程实例id
						lcslid: [
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
						//表单id
						bdid: [
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
						//始节点是否会签：0-否、1-是
						qjdsfhq: [
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
