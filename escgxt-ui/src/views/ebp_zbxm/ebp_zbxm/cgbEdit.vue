<template>
	<popup ref="popup" :loading="popupLoading">
		<div slot="body">
			<!-- <el-card class="box-card1">
				<div slot="header" class="clearfix">
					<span>采购任务信息</span>
				</div>
				<div class="text item">
					<el-form class="dataForm" ref="form1" :disabled="disabled" :model="cgrwFormParameter" :inline="true" label-width="100px" :rules="rules">
						<el-row>
							<el-col :span="10">
								<el-form-item label="采购任务名称" prop="cgrwmc">
									<el-input v-model="cgrwFormParameter.cgrwmc" autocomplete="off" placeholder="请输入采购任务名称"></el-input>
								</el-form-item>
							</el-col>
							<el-col :span="10">
								<el-form-item label="采购任务编号" prop="cgrwbh">
									<el-input v-model="cgrwFormParameter.cgrwbh" autocomplete="off" :disabled="true" placeholder="保存后自动生成"></el-input>
								</el-form-item>
							</el-col>
						</el-row>
						<el-row>
							<el-col :span="10">
								<el-form-item label="组织形式" prop="zzxs" :rules="rules.zzxs">
									<el-select v-model="cgrwFormParameter.zzxs" placeholder="请选择组织形式">
										<el-option label="校内集中采购" value="1"></el-option>
									</el-select>
								</el-form-item>
							</el-col>
							<el-col :span="10">
								<el-form-item label="采购方式" prop="cgfs" :rules="rules.cgfs">
									<el-select v-model="cgrwFormParameter.cgfs" placeholder="请选择采购方式">
										<el-option label="公开招标" value="1"></el-option>
										<el-option label="竞争性谈判" value="2"></el-option>
										<el-option label="单一来源" value="3"></el-option>
										<el-option label="询价" value="4"></el-option>
										<el-option label="部门自行采购" value="5"></el-option>
										<el-option label="电子卖场" value="6"></el-option>
									</el-select>
								</el-form-item>
							</el-col>
						</el-row>
						<el-row>
							<el-col :span="10">
								<el-form-item label="预算金额(元)" prop="ysje" :rules="rules.ysje">
									<el-input v-model="cgrwFormParameter.ysje" autocomplete="off" :disabled="true"></el-input>
								</el-form-item>
							</el-col>
							<el-col :span="10">
								<el-form-item label="采购执行人" prop="cgzxr">
									<el-input v-model="cgrwFormParameter.cgzxr" autocomplete="off" :disabled="true"></el-input>
								</el-form-item>
							</el-col>
						</el-row>
					</el-form>
				</div>
			</el-card> -->
			<el-card class="box-card">
				<div slot="header" class="clearfix">
					<span>采购包信息</span>
				</div>
				<div class="text item">
					<el-form class="dataForm" ref="form" :disabled="disabled" :model="cgbFormParameter" :inline="true" label-width="100px" :rules="rules">
						<el-row>
							<el-col :span="10">
								<el-form-item label="采购包名称" prop="cgrwmc">
									<el-input v-model="cgbFormParameter.cgbmc" autocomplete="off" placeholder="请输入采购任务名称"></el-input>
								</el-form-item>
							</el-col>
							<el-col :span="10">
								<el-form-item label="采购包编号" prop="cgrwbh">
									<el-input v-model="cgbFormParameter.cgbbh" autocomplete="off" :disabled="true" placeholder="保存后自动生成"></el-input>
								</el-form-item>
							</el-col>
						</el-row>
						<el-row>
							<el-col :span="10">
								<el-form-item label="组织形式" prop="zzxs" :rules="rules.zzxs">
									<el-select v-model="cgbFormParameter.zzxs" placeholder="请选择组织形式">
										<el-option label="校内集中采购" value="1"></el-option>
									</el-select>
								</el-form-item>
							</el-col>
							<el-col :span="10">
								<el-form-item label="采购方式" prop="cgfs" :rules="rules.cgfs">
									<el-select v-model="cgbFormParameter.cgfs" placeholder="请选择采购方式">
										<el-option label="公开招标" value="1"></el-option>
										<el-option label="竞争性谈判" value="2"></el-option>
										<el-option label="单一来源" value="3"></el-option>
										<el-option label="询价" value="4"></el-option>
										<el-option label="部门自行采购" value="5"></el-option>
										<el-option label="电子卖场" value="6"></el-option>
									</el-select>
								</el-form-item>
							</el-col>
						</el-row>
						<el-row>
							<el-col :span="10">
								<el-form-item label="预算金额(元)" prop="ysje" :rules="rules.ysje">
									<el-input v-model="cgbFormParameter.ysje" autocomplete="off" :disabled="true"></el-input>
								</el-form-item>
							</el-col>
							<el-col :span="10">
								<el-form-item v-if="cgbFormParameter.cgzxr != undefined" label="采购执行人" prop="cgzxryhm">
									<el-input v-model="cgbFormParameter.cgzxryhm" autocomplete="off" :disabled="true"></el-input>
								</el-form-item>
								<el-form-item v-else label="采购执行人" prop="cgzxr">
									<el-input v-model="cgbFormParameter.cgzxr" autocomplete="off" :disabled="true"></el-input>
								</el-form-item>
							</el-col>
						</el-row>
					</el-form>
				</div>
			</el-card>
		</div>
		<div slot="footer">
			<el-button v-if="!disabled" type="primary" :loading="confirmLoading" @click="confirm()">确 定</el-button>
			<el-button @click="close">取 消</el-button>
		</div>
	</popup>
</template>

<script>
	import popup from '@/components/popup/drawerPopup.vue';
	import zbxmApi from '@/api/ebp_zbxm/ebp_zbxm/ebp_zbxm.js';
	export default {
		name: 'cgbEdit',
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
				cgrwFormParameter: {},
				cgbFormParameter: {},
				rules: {
					//招标项目编码
					zbxmbm: [
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
					//招标项目名称
					zbxmmc: [
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
					//组织形式
					zzxs: [
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
					//预算金额(元)
					ysje: [
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
				}
			};
		},
		methods: {
				//打开弹框
				open(parameter, title, disabled, size) {debugger
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
				init() {debugger
					this.popupLoading = true;
					//清除等待
					this.confirmLoading = false;
					//清空内容
					// this.formParameter = {};
					this.cgrwFormParameter = {};
					this.cgbFormParameter = {};
					//清楚校验
					// this.$refs.form.clearValidate();
					// this.$refs.form1.clearValidate();
					// this.$refs.form2.clearValidate();
					
					if (this.parameter.id) {
						//编辑查询数据
						let parameter = {
							id: this.parameter.id
						};
						zbxmApi.toUpdate(parameter, response => {debugger
							// this.formParameter = response.data;
							this.cgrwFormParameter = response.data.cgrwParameter;
							if (response.data.cgbParameter != undefined) {
								this.cgbFormParameter = response.data.cgbParameter;
							}
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
	
	.text {
		// font-size: 14px;
		background-color: #f4f4f4;
	}
	
	.box-card, {
		// background-color: #eaeaea;
	    width: 1035px;
	}
	
	.el-row {
		padding: 15px 0px 0px 70px;
	}
	
	.footer-button {
		text-align: center;
	}
</style>
