<template>
	<popup ref="popup" :loading="popupLoading">
		<div slot="body">
			<div class="card-body">
				<el-card class="box-card1">
					<div slot="header" class="clearfix">
						<span>合同基本信息</span>
						<!-- <el-button style="float: right; padding: 3px 0" type="warning">修改</el-button> -->
					</div>
					<div class="text item">
						<el-form class="dataForm" ref="form1" :disabled="disabled" :model="htFormParameter" :inline="true" label-width="110px" :rules="rules">
							<el-row>
								<el-col :span="10">
									<el-form-item label="合同名称" prop="htmc" placeholder="请输入项目名称">
										<el-input v-model="htFormParameter.htmc" autocomplete="off"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="10">
									<el-form-item label="合同编号" prop="htbh">
										<el-input v-model="htFormParameter.htbh" :disabled="true" autocomplete="off" placeholder="保存后自动生成"></el-input>
									</el-form-item>
								</el-col>
							</el-row>
							<el-row>
								<el-col :span="10">
									<el-form-item label="合同金额(元)" prop="htje">
										<el-input v-model="htFormParameter.htje" :disabled="true" autocomplete="off"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="10">
									<el-form-item label="经费来源" prop="jfly">
										<el-input v-model="htFormParameter.jfly" :disabled="true" autocomplete="off"></el-input>
									</el-form-item>
								</el-col>
							</el-row>
							<el-row>
								<el-col :span="10">
									<el-form-item label="甲方" prop="jfht"><el-input v-model="htFormParameter.jfht" autocomplete="off"></el-input></el-form-item>
								</el-col>
							</el-row>
							<el-row>
								<el-col :span="10">
									<el-form-item label="甲方联系人" prop="jflxr"><el-input v-model="htFormParameter.jflxr" autocomplete="off"></el-input></el-form-item>
								</el-col>
								<el-col :span="10">
									<el-form-item label="甲方联系人手机" prop="jflxfs"><el-input v-model="htFormParameter.jflxfs" autocomplete="off"></el-input></el-form-item>
								</el-col>
							</el-row>
							<el-row>
								<el-col :span="10">
									<el-form-item label="乙方" prop="cjgys">
										<el-input v-model="htFormParameter.cjgys" :disabled="true" autocomplete="off"></el-input>
									</el-form-item>
								</el-col>
							</el-row>
							<el-row>
								<el-col :span="10">
									<el-form-item label="乙方联系人" prop="gyslxr">
										<el-input v-model="htFormParameter.gyslxr" :disabled="true" autocomplete="off"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="10">
									<el-form-item label="乙方联系人手机" prop="gyslxdh">
										<el-input v-model="htFormParameter.gyslxdh" :disabled="true" autocomplete="off"></el-input>
									</el-form-item>
								</el-col>
							</el-row>
							<el-row>
								<el-col :span="10">
									<el-form-item label="项目负责人" prop="xmfzr">
										<el-input v-model="htFormParameter.xmfzr" :disabled="true" autocomplete="off"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="10">
									<el-form-item label="项目负责人电话" prop="xmfzrdh">
										<el-input v-model="htFormParameter.xmfzrdh" :disabled="true" autocomplete="off">
									</el-input></el-form-item>
								</el-col>
							</el-row>
							<el-row>
								<el-col :span="10">
									<el-form-item label="采购结果" prop="cgjg">
										<el-input v-model="htFormParameter.cgjg" :disabled="true" autocomplete="off"></el-input>
									</el-form-item>
								</el-col>
							</el-row>
							<!-- <el-row>
								<el-col :span="10">
								</el-col>
								<el-col :span="10">
								</el-col>
							</el-row> -->
						</el-form>
					</div>
				</el-card>
				<el-card class="box-card2">
					<div slot="header" class="clearfix">
						<span>采购项目信息</span>
						<!-- <el-button style="float: right; padding: 3px 0" type="warning">修改</el-button> -->
					</div>
					<div class="text item">
						<el-form class="dataForm" ref="form2" :disabled="true" :model="cgsqFormParameter" :inline="true" label-width="100px" :rules="rules">
							<el-row>
								<el-col :span="10">
									<el-form-item label="项目名称" prop="xmmc" :rules="rules.xmmc">
										<el-input v-model="cgsqFormParameter.xmmc" autocomplete="off" placeholder="请输入项目名称"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="10">
									<el-form-item label="采购项目编号" prop="cgbh" :rules="rules.cgbh">
										<el-input v-model="cgsqFormParameter.cgbh" autocomplete="off" :disabled="true" placeholder="提交后自动生成"></el-input>
									</el-form-item>
								</el-col>
							</el-row>
							<el-row>
								<el-col :span="10">
									<el-form-item label="申报部门名称" prop="sbbm">
										<el-input v-model="cgsqFormParameter.sbbm" autocomplete="off"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="10">
									<el-form-item label="申报部门编号" prop="sbbmbh">
										<el-input v-model="cgsqFormParameter.sbbmbh" autocomplete="off"></el-input>
									</el-form-item>
								</el-col>
							</el-row>
							<el-row>
								<el-col :span="10">
									<el-form-item label="项目负责人" prop="xmfzr" :rules="rules.xmfzr"><el-input v-model="cgsqFormParameter.xmfzr" autocomplete="off"></el-input></el-form-item>
								</el-col>
								<el-col :span="10">
									<el-form-item label="联系人电话" prop="lxrdh" :rules="rules.lxrdh"><el-input v-model="cgsqFormParameter.lxrdh" autocomplete="off"></el-input></el-form-item>
								</el-col>
							</el-row>
							<el-row>
								<el-col :span="10">
									<el-form-item label="申报日期" prop="sbrq" :rules="rules.sbrq">
										<el-date-picker v-model="cgsqFormParameter.sbrq" type="date" placeholder="选择日期" value-format="yyyy-MM-dd"></el-date-picker>
									</el-form-item>
								</el-col>
								<el-col :span="10">
									<el-form-item label="预算金额(元)" prop="ysje" :rules="rules.ysje"><el-input v-model="cgsqFormParameter.ysje" autocomplete="off"></el-input></el-form-item>
								</el-col>
							</el-row>
						</el-form>
					</div>
				</el-card>
				<el-card class="box-card3">
					<div slot="header" class="clearfix">
						<span>采购清单信息</span>
					</div>
					<div class="text item">
						<el-form class="dataForm" ref="form3" :disabled="true" :model="cgsqqdFormParameter" :inline="true" label-width="100px" :rules="rules">
							<el-row>
								<el-col :span="10">
									<el-form-item label="流水号" prop="hwbh" :rules="rules.hwbh">
										<el-input v-model="cgsqqdFormParameter.hwbh" autocomplete="off" :disabled="true" placeholder="自动生成"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="10">
									<el-form-item label="设备名称" prop="mc" :rules="rules.mc">
										<el-input v-model="cgsqqdFormParameter.mc" autocomplete="off"></el-input>
									</el-form-item>
								</el-col>
							</el-row>
							<el-row>
								<el-col :span="10">
									<el-form-item label="数量" prop="sl" :rules="rules.sl"><el-input v-model="cgsqqdFormParameter.sl" autocomplete="off"></el-input></el-form-item>
								</el-col>
								<el-col :span="10">
									<el-form-item label="单位" prop="dw" :rules="rules.dw"><el-input v-model="cgsqqdFormParameter.dw" autocomplete="off"></el-input></el-form-item>
								</el-col>
							</el-row>
							<el-row>
								<el-col :span="10">
									<el-col :span="24">
										<el-form-item label="单价(元)" prop="dj" :rules="rules.dj"><el-input v-model="cgsqqdFormParameter.dj" autocomplete="off"></el-input></el-form-item>
									</el-col>
								</el-col>
								<el-col :span="10">
									<el-form-item label="总金额(元)" prop="je" :rules="rules.je">
										<el-input v-model="cgsqqdFormParameter.je" autocomplete="off" :disabled="true" placeholder="自动计算"></el-input>
									</el-form-item>
								</el-col>
							</el-row>
						</el-form>
					</div>
				</el-card>
				<el-card class="box-card4">
					<div slot="header" class="clearfix">
						<span>经费信息</span>
					</div>
					<div class="text item">
						<el-form class="dataForm" ref="form4" :disabled="true" :model="jfsyFormParameter" :inline="true" label-width="100px" :rules="rules">
							<el-row>
								<el-col :span="10">
									<el-form-item label="预算名称" prop="ysmc">
										<el-input v-model="jfsyFormParameter.ysmc" autocomplete="off"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="10">
									<el-form-item label="使用金额(元)" prop="syje" :rules="rules.je">
										<el-input v-model="jfsyFormParameter.syje" autocomplete="off"></el-input>
									</el-form-item>
								</el-col>
							</el-row>
						</el-form>
					</div>
				</el-card>
			</div>	
		</div>
		<div slot="footer" class="footer-button">
			<el-button v-if="!disabled" type="primary" :loading="confirmLoading" @click="confirm()">保 存</el-button>
			<el-button v-if="!disabled && parameter.route_path != 'ebp_ht_ytj'" type="primary" style="margin-left: 20px;" :loading="tjshLoading" @click="tjsh()">提 交</el-button>
			<el-button style="margin-left: 20px;" @click="close">取 消</el-button>
		</div>
	</popup>
</template>

<script>
	import popup from '@/components/popup/drawerPopup.vue';
	import htApi from '@/api/ebp_ht/ebp_ht/ebp_ht.js';
	export default {
		name: 'editForm',
		components: {
				popup
			},
		data() {
			return {
				disabled: false,
				popupLoading: false,
				confirmLoading: false,
				tjshLoading: false,
				parameter: {},
				formParameter: {},
				cgsqFormParameter: {},
				cgsqqdFormParameter: {},
				jfsyFormParameter: {},
				zbjgFormParameter: {},
				htFormParameter: {},
				rules: {
					//合同名称
					htmc: [
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
					//采购项目
					cgjg: [
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
					//合同金额(元)
					htje: [
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
					//乙方
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
					//乙方联系人手机
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
					//乙方联系人
					gyslxr: [
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
					//甲方联系人手机
					jflxfs: [
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
					//甲方联系人
					jflxr: [
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
					//甲方
					jfht: [
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
					//合同执行人用户名
					htUsername: [
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
					//合同执行人
					htUser: [
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
					this.$utils.checkForm(this.$refs.form1, () => {debugger
						this.confirmLoading = true;
						if (!this.parameter.id) {
							//添加
							// let parameter = this.$utils.merger(this.formParameter);
							let parameter = {};
							let htParameter = this.$utils.merger(this.htFormParameter);
							let cgsqParameter = this.cgsqFormParameter;
							let cgsqqdParameter = this.cgsqqdFormParameter;
							let jfsyParameter = this.jfsyFormParameter;
							let zbjgParameter = this.zbjgFormParameter;
							parameter.cgsqParameter = cgsqParameter;
							parameter.cgsqqdParameter = cgsqqdParameter;
							parameter.jfsyParameter = jfsyParameter;
							parameter.zbjgParameter = zbjgParameter;
							parameter.htParameter = htParameter;
							/* htApi.save(parameter,
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
							); */
						} else {
							//编辑
							let parameter = this.$utils.merger(this.formParameter);
							htApi.update(parameter,
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
				tjsh() {
					this.$utils.confirm.warning('提示', '确定提交吗？', () => {
						this.$utils.checkForm(this.$refs.form1, () => {
							this.tjshLoading = true;
							if (this.parameter.id) {debugger
								//添加
								let parameter = {};
								let htParameter = this.$utils.merger(this.htFormParameter);
								let cgsqParameter = this.cgsqFormParameter;
								let cgsqqdParameter = this.cgsqqdFormParameter;
								let jfsyParameter = this.jfsyFormParameter;
								let zbjgParameter = this.zbjgFormParameter;
								parameter.cgsqParameter = cgsqParameter;
								parameter.cgsqqdParameter = cgsqqdParameter;
								parameter.jfsyParameter = jfsyParameter;
								parameter.zbjgParameter = zbjgParameter;
								parameter.htParameter = htParameter;
								htApi.htTjsh(parameter,
									response => {
										this.$utils.msg.success(response.msg);
										this.tjshLoading = false;
										this.close();
										this.$parent.list();
									},
									response => {
										this.$utils.msg.warning(response.msg);
										this.tjshLoading = false;
									}
								);
							} 
						});
					});
				},
				init() {
					this.popupLoading = true;
					//清除等待
					this.confirmLoading = false;
					//清空内容
					// this.formParameter = {};
					this.htFormParameter = {};
					//清楚校验
					// this.$refs.form.clearValidate();
					this.$refs.form1.clearValidate();
					
					if (this.parameter.id) {
						//编辑查询数据
						let parameter = {
							id: this.parameter.id
						};
						htApi.toUpdate(parameter, response => {debugger
							this.formParameter = response.data;
							this.cgsqFormParameter = this.formParameter.cgsqParameter;
							this.cgsqqdFormParameter = this.formParameter.cgsqqdParameter;
							this.jfsyFormParameter = this.formParameter.jfsyParameter;
							this.zbjgFormParameter = this.formParameter.zbjgParameter;
							if (this.formParameter.htParameter != undefined) {
								this.htFormParameter = this.formParameter.htParameter;
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
	
	.card-body {
		// background-color: #eaeaea;
	    width: 1100px;
	}
	
	.box-card2,.box-card3,.box-card4 {
		margin-top: 20px;
	}
	
	.el-row {
		padding: 2px 0px 0px 50px;
	}
	
	.footer-button {
		text-align: center;
		
	}
	
</style>
