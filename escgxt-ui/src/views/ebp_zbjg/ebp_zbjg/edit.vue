<template>
	<popup ref="popup" :loading="popupLoading">
		<div slot="body">
			<el-form class="dataForm" ref="form" :disabled="disabled" :model="formParameter" :inline="true" label-width="80px" :rules="rules">
					<el-row>
						<el-col :span="24">
							<el-form-item label="采购结果" prop="cgjg"><el-input v-model="formParameter.cgjg" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="采购编号" prop="jgbh"><el-input v-model="formParameter.jgbh" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="采购项目名称" prop="xmmc"><el-input v-model="formParameter.xmmc" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="采购供应商" prop="cjgys"><el-input v-model="formParameter.cjgys" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="采购金额(元)" prop="cjjg"><el-input v-model="formParameter.cjjg" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="供应商联系电话" prop="gyslxdh"><el-input v-model="formParameter.gyslxdh" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="供应商联系人" prop="gyslxr"><el-input v-model="formParameter.gyslxr" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="采购日期" prop="cjrq"><el-input v-model="formParameter.cjrq" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="登记时间" prop="djsj"><el-input v-model="formParameter.djsj" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="中标结果通知书" prop="zbjgtzs"><el-input v-model="formParameter.zbjgtzs" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="采购结果类型" prop="jglx"><el-input v-model="formParameter.jglx" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="招标id" prop="zbid"><el-input v-model="formParameter.zbid" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="设备id" prop="sbid"><el-input v-model="formParameter.sbid" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="采购包id" prop="cgbid"><el-input v-model="formParameter.cgbid" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="供应商id" prop="gysid"><el-input v-model="formParameter.gysid" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="采购任务id" prop="cgrwid"><el-input v-model="formParameter.cgrwid" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="确认结果" prop="confirmResult"><el-input v-model="formParameter.confirmResult" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="是否已确认：0-否、1-是" prop="isConfirm"><el-input v-model="formParameter.isConfirm" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="采购方式" prop="cgfs"><el-input v-model="formParameter.cgfs" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="采购状态" prop="cgStatus"><el-input v-model="formParameter.cgStatus" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="合同用户电话" prop="htUserdh"><el-input v-model="formParameter.htUserdh" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="是否需要签订合同：0-否、1-是" prop="isQdht"><el-input v-model="formParameter.isQdht" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="投标单位" prop="tbdw"><el-input v-model="formParameter.tbdw" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="是否到货：0-否、1-是" prop="isDh"><el-input v-model="formParameter.isDh" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="是否发起验收申请：0-否、1-是" prop="isApply"><el-input v-model="formParameter.isApply" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="是否需要登记验收：0-否、1-是" prop="isDjys"><el-input v-model="formParameter.isDjys" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="是否完成付款：0-否、1-是" prop="isFk"><el-input v-model="formParameter.isFk" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="是否提交登记：0-否、1-是" prop="isSubmit"><el-input v-model="formParameter.isSubmit" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="是否完成提交登记：0-否、1-是" prop="isWcdj"><el-input v-model="formParameter.isWcdj" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="是否验收确认：0-否、1-是" prop="isYsConfirm"><el-input v-model="formParameter.isYsConfirm" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="验收申请人" prop="ysUserName"><el-input v-model="formParameter.ysUserName" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="验收申请人用户名" prop="ysUserCode"><el-input v-model="formParameter.ysUserCode" autocomplete="off"></el-input></el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-form-item label="验收申请人电话" prop="ysUserPhone"><el-input v-model="formParameter.ysUserPhone" autocomplete="off"></el-input></el-form-item>
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
import api from '@/api/ebp_zbjg/ebp_zbjg/ebp_zbjg.js';
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
						//采购结果
						cgjg: [
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
						//采购编号
						jgbh: [
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
						//采购项目名称
						xmmc: [
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
						//采购供应商
						cjgys: [
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
						//采购金额(元)
						cjjg: [
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
						//供应商联系电话
						gyslxdh: [
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
						//供应商联系人
						gyslxr: [
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
						//采购日期
						cjrq: [
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
						//登记时间
						djsj: [
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
						//中标结果通知书
						zbjgtzs: [
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
						//采购结果类型
						jglx: [
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
						//招标id
						zbid: [
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
						//设备id
						sbid: [
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
						//采购包id
						cgbid: [
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
						//供应商id
						gysid: [
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
						//采购任务id
						cgrwid: [
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
						//确认结果
						confirmResult: [
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
						//是否已确认：0-否、1-是
						isConfirm: [
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
						//采购方式
						cgfs: [
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
						//采购状态
						cgStatus: [
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
						//合同用户电话
						htUserdh: [
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
						//是否需要签订合同：0-否、1-是
						isQdht: [
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
						//投标单位
						tbdw: [
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
						//是否到货：0-否、1-是
						isDh: [
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
						//是否发起验收申请：0-否、1-是
						isApply: [
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
						//是否需要登记验收：0-否、1-是
						isDjys: [
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
						//是否完成付款：0-否、1-是
						isFk: [
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
						//是否提交登记：0-否、1-是
						isSubmit: [
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
						//是否完成提交登记：0-否、1-是
						isWcdj: [
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
						//是否验收确认：0-否、1-是
						isYsConfirm: [
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
						//验收申请人
						ysUserName: [
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
						//验收申请人用户名
						ysUserCode: [
							{
								validator: this.$validate.required,
								trigger: 'blur'
							},
							{
								validator: this.$validate.length,
								max: 10,
								trigger: 'blur'
							}
						],
						//验收申请人电话
						ysUserPhone: [
							{
								validator: this.$validate.required,
								trigger: 'blur'
							},
							{
								validator: this.$validate.length,
								max: 10,
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
