<template>
	<popup ref="popup" :loading="popupLoading">
		<div slot="body">
			<div class="card-body">
				<el-card class="box-card1">
					<div slot="header" class="clearfix">
						<span>采购项目基本信息</span>
						<!-- <el-button style="float: right; padding: 3px 0" type="warning">修改</el-button> -->
					</div>
					<div class="text item">
						<el-form class="dataForm" ref="form1" :disabled="disabled" :model="cgsqFormParameter" :inline="true" label-width="100px" :rules="rules">
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
								<!-- <el-col :span="10">
									<el-form-item label="申报部门名称" prop="sbbm" :rules="rules.sbbm" v-if="false">
										<select-department-tree-dialog v-model="cgsqFormParameter.sbbm" @change="handleSbbm"></select-department-tree-dialog>
										<el-input v-model="cgsqFormParameter.sbbm" autocomplete="off" :disabled="true" placeholder="自动回填"></el-input>
									</el-form-item>
								</el-col> -->
								<el-col :span="10">
									<el-form-item label="申报部门名称" prop="sbbmId" :rules="rules.sbbmId">
										<select-department-tree-dialog v-model="cgsqFormParameter.sbbmId" @change="handleSbbm"></select-department-tree-dialog>
									</el-form-item>
								</el-col>
								<el-col :span="10">
									<el-form-item label="申报部门编号" prop="sbbmbh" :rules="rules.sbbmbh">
										<el-input v-model="cgsqFormParameter.sbbmbh" autocomplete="off" :disabled="true" placeholder="自动回填"></el-input>
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
										<!-- <el-input v-model="cgsqFormParameter.sbrq" autocomplete="off"></el-input> -->
										<el-date-picker v-model="cgsqFormParameter.sbrq" type="date" placeholder="选择日期" value-format="yyyy-MM-dd"></el-date-picker>
									</el-form-item>
								</el-col>
								<el-col :span="10">
									<el-form-item label="预算金额(元)" prop="ysje">
										<el-input v-model="cgsqFormParameter.ysje" :disabled="true" autocomplete="off"></el-input>
									</el-form-item>
								</el-col>
							</el-row>
							<!-- <el-row>
								<el-col :span="10">
									<el-form-item label="组织形式" prop="zzxs" :rules="rules.zzxs"><el-input v-model="cgsqFormParameter.zzxs" autocomplete="off"></el-input></el-form-item>
								</el-col>
								<el-col :span="10">
									<el-form-item label="采购方式" prop="cgfs" :rules="rules.cgfs"><el-input v-model="cgsqFormParameter.cgfs" autocomplete="off"></el-input></el-form-item>
								</el-col>
							</el-row>
							<el-row>
								<el-col :span="10">
									<el-form-item label="申报日期" prop="year"><el-input v-model="cgsqFormParameter.sbrq" autocomplete="off" style="width: 100px;"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="10">
									<el-form-item label="采购执行人" prop="cgzxr" :rules="rules.cgzxr"><el-input v-model="cgsqFormParameter.cgzxr" autocomplete="off"></el-input></el-form-item>
								</el-col>
							</el-row> -->
						</el-form>
					</div>
				</el-card>
				<el-card class="box-card2">
					<div slot="header" class="clearfix">
						<span>清单信息</span>
						<!-- <el-button style="float: right; padding: 3px 0" type="warning">修改</el-button> -->
					</div>
					<div class="text item">
						<el-form class="dataForm" ref="form2" :disabled="disabled" :model="cgsqqdFormParameter" :inline="true" label-width="100px" :rules="rules">
							<el-row>
								<el-col :span="10">
									<el-form-item label="流水号" prop="hwbh" :rules="rules.hwbh">
										<el-input v-model="cgsqqdFormParameter.hwbh" autocomplete="off" :disabled="true" placeholder="自动生成"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="10">
									<el-form-item label="清单名称" prop="mc" :rules="rules.mc">
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
				<el-card class="box-card3">
					<div slot="header" class="clearfix">
						<span>经费信息</span>
						<!-- <el-button style="float: right; padding: 3px 0" icon="el-icon-edit" type="primary">添加经费</el-button> -->
					</div>
					<div class="text item">
						<el-form class="dataForm" ref="form3" :disabled="disabled" :model="jfsyFormParameter" :inline="true" label-width="100px" :rules="rules">
							<el-row>
								<el-col :span="10">
									<el-form-item label="预算名称" prop="jflyid" :rules="rules.jflyid">
										<select-jfly-table-dialog v-model="jfsyFormParameter.jflyid" @change="handleJfly"></select-jfly-table-dialog>
										<!-- <el-input v-model="jfsyFormParameter.ysmc" autocomplete="off"></el-input> -->
									</el-form-item>
								</el-col>
								<el-col :span="10">
									<el-form-item label="使用金额(元)" prop="syje" :rules="rules.je">
										<el-input v-model="jfsyFormParameter.syje" :disabled="true" autocomplete="off"></el-input>
									</el-form-item>
								</el-col>
							</el-row>
							<!-- <el-row>
								<el-col :span="10">
									<el-col :span="10">
										<el-form-item label="预算编码" prop="ysbm">
											<el-input v-model="jfsyFormParameter.ysbm" autocomplete="off" :disabled="true" placeholder="自动回填"></el-input>
										</el-form-item>
									</el-col>
									<el-form-item label="经费类型" prop="jf_type">
										<el-input v-model="jfsyFormParameter.jf_type" autocomplete="off" :disabled="true" placeholder="自动回填"></el-input>
									</el-form-item>
								</el-col>
							</el-row> -->
						</el-form>
					</div>
				</el-card>
			</div>
		</div>
		<div slot="footer" class="footer-button">
			<el-button v-if="!disabled" type="primary" :loading="confirmLoading" @click="confirm()">保 存</el-button>
			<el-button v-if="!disabled" type="primary" style="margin-left: 20px;" :loading="tjshLoading" @click="tjsh()">提 交</el-button>
			<el-button style="margin-left: 20px;" @click="close">取 消</el-button>
		</div>
	</popup>
</template>

<script>
	import popup from '@/components/popup/drawerPopup.vue';
	import selectDepartmentTreeDialog from '@/components/biz/selectDepartmentTreeDialog/selectDepartmentTreeDialog.vue';
	import selectJflyTableDialog from '@/components/biz/selectJflyTableDialog/selectJflyTableDialog.vue';
	import jflyApi from '@/api/ebp_jfly/ebp_jfly/ebp_jfly.js';
	import cgsqApi from '@/api/ebp_cgsq/ebp_cgsq/ebp_cgsq.js';
	import cgsqqdApi from '@/api/ebp_cgsq/ebp_cgsq_qd/ebp_cgsq_qd.js';
	import departmentApi from '@/api/system/department/department.js';
	export default {
		name: 'addForm',
		components: {
				popup,
				selectDepartmentTreeDialog,
				selectJflyTableDialog,
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
				selectedListData: [],
				listData: [],
				listLoading: false,
				rules: {
					//采购项目编号
					/* cgbh: [
						{
							validator: this.$validate.required,
							trigger: 'blur'
						},
						{
							validator: this.$validate.length,
							max: 32,
							trigger: 'blur'
						}
					], */
					//项目名称
					xmmc: [
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
					//申报部门编号
					sbbmbh: [
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
					//申报部门名称
					sbbm: [
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
					//申报部门id
					sbbmId: [
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
					//预算金额(元)
					/* ysje: [
						{
							validator: this.$validate.required,
							trigger: 'blur'
						},
						{
							validator: this.$validate.length,
							max: 12,
							trigger: 'blur'
						}
					], */
					//项目负责人
					xmfzr: [
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
					//联系人电话
					lxrdh: [
						{
							validator: this.$validate.required,
							trigger: 'blur'
						},
						{
							validator: this.$validate.phone,
							// max: 36,
							trigger: 'blur'
						}
					],
					//申报日期
					sbrq: [
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
					//采购执行人
					cgzxr: [
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
					//流水号
					/* hwbh: [
						{
							validator: this.$validate.required,
							trigger: 'blur'
						},
						{
							validator: this.$validate.length,
							max: 36,
							trigger: 'blur'
						}
					], */
					//设备名称
					mc: [
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
					//数量
					sl: [
						{
							validator: this.$validate.required,
							trigger: 'blur'
						},
						{
							validator: this.$validate.digits,
							trigger: 'blur'
						}
					],
					//单位
					dw: [
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
					//单价(元)
					dj: [
						{
							validator: this.$validate.required,
							trigger: 'blur'
						},
						{
							validator: this.$validate.number,
							trigger: 'blur'
						}
					],
					//金额(元)
					je: [
						{
							validator: this.$validate.required,
							trigger: 'blur'
						},
						{
							validator: this.$validate.number,
							trigger: 'blur'
						}
					],
					//预算名称
					/* ysmc: [
						{
						
						},
						{
							
						},
					], */
					
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
					this.$utils.checkForm(this.$refs.form1, () => {
						this.confirmLoading = true;
						if (this.cgsqqdFormParameter.je == undefined || isNaN(this.cgsqqdFormParameter.je)) {
							// this.$utils.msg.warning("请填写清单信息");
							this.$alert("<strong>请填写清单信息</strong>","提示",{dangerouslyUseHTMLString: true});
							this.confirmLoading = false;
							return false;
						}
						if (this.jfsyFormParameter.jflyid == undefined) {
							this.$alert("<strong>请填写经费信息</strong>","提示",{dangerouslyUseHTMLString: true});
							this.confirmLoading = false;
							return false;
						}
						if (!this.parameter.id) {debugger
							//添加
							let parameter = {};
							let cgsqParameter = this.$utils.merger(this.cgsqFormParameter);
							let cgsqqdParameter = this.$utils.merger(this.cgsqqdFormParameter);
							let jfsyParameter = this.$utils.merger(this.jfsyFormParameter);
							parameter.cgsqParameter = cgsqParameter;
							parameter.cgsqqdParameter = cgsqqdParameter;
							parameter.jfsyParameter = jfsyParameter;
							cgsqApi.save(parameter,
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
							cgsqApi.update(parameter,
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
							this.$utils.checkForm(this.$refs.form2, () => {
								this.$utils.checkForm(this.$refs.form3, () => {
									if (this.jfsyFormParameter.jflyid == undefined) {
										this.$alert("<strong>请填写经费信息</strong>","提示",{dangerouslyUseHTMLString: true});
										this.confirmLoading = false;
										return false;
									}
									this.tjshLoading = true;
									if (!this.parameter.id) {debugger
										//添加
										let parameter = {};
										let cgsqParameter = this.$utils.merger(this.cgsqFormParameter);
										let cgsqqdParameter = this.$utils.merger(this.cgsqqdFormParameter);
										let jfsyParameter = this.$utils.merger(this.jfsyFormParameter);
										parameter.cgsqParameter = cgsqParameter;
										parameter.cgsqqdParameter = cgsqqdParameter;
										parameter.jfsyParameter = jfsyParameter;
										cgsqApi.cgsqTjsh(parameter,
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
						});
					});
				},
				init() {debugger
					this.popupLoading = true;
					//清除等待
					this.confirmLoading = false;
					this.tjshLoading = false;
					//清空内容
					this.cgsqFormParameter = {};
					this.cgsqqdFormParameter = {};
					this.jfsyFormParameter = {};
					//清楚校验
					this.$refs.form1.clearValidate();
					this.$refs.form2.clearValidate();
					this.$refs.form3.clearValidate();
					
					if (this.parameter.id) {
						//编辑查询数据
						let parameter = {
							id: this.parameter.id,
						};
						cgsqApi.toUpdate(parameter, response => {
							this.formParameter = response.data;
							this.popupLoading = false;
						});
					} else {
						//添加
						this.popupLoading = false;
					}
				},
				handleSbbm(val) {debugger
					let sbbmId = this.$refs.form1._props.model.sbbmId;
					let departmentData = {}
					let paramData = {
						id: val
					}
					if(!_.isEmpty(sbbmId)) {
						jflyApi.getDepartmentListById(paramData, response => {debugger
							this.departmentData = response.data[0];
							this.$set(this.cgsqFormParameter,'sbbmbh',this.departmentData.code);
							this.$set(this.cgsqFormParameter,'sbbm',this.departmentData.name);
							// this.cgsqFormParameter.sbbmbh = this.departmentData.code;
							// this.cgsqFormParameter.sbbm = this.departmentData.name;
						});
					}else {
						this.$set(this.cgsqFormParameter,'sbbmbh','');
						this.$set(this.cgsqFormParameter,'sbbm','');
						// this.cgsqFormParameter.sbbmbh = "";
						// this.cgsqFormParameter.sbbm = "";
					}
					
				},
				handleJfly(val) {debugger
					
				}
			},
			/* watch : {
				'formParameter.jbbm' : {
					handler(val){
						let jbbm = this.$refs.form._props.model.jbbm;
						let departmentData = {}
						let paramData = {
							id: val
						}
						if(!_.isEmpty(jbbm)) {
							api.getDepartmentListById(paramData, response => {
								this.departmentData = response.data[0];
								this.formParameter.jbbmbm = this.departmentData.code;
							});
						}else {
							this.formParameter.jbbmbm = "";
						}
					}	
				}
			} */
			watch: {
				'cgsqqdFormParameter.sl' : function(val) {
					let sum = this.cgsqqdFormParameter.je;
					sum = parseFloat(this.cgsqqdFormParameter.dj) * parseFloat(val);
					this.cgsqqdFormParameter.je = sum.toFixed(2);
					this.cgsqFormParameter.ysje = sum.toFixed(2);
					this.jfsyFormParameter.syje = sum.toFixed(2);
					/* if (isNaN(this.cgsqqdFormParameter.je)) {
						this.cgsqqdFormParameter.je = sum.toFixed(2);
					} else {
						this.cgsqqdFormParameter.je = sum.toFixed(2);
						this.cgsqFormParameter.ysje = sum.toFixed(2);
						this.jfsyFormParameter.syje = sum.toFixed(2);
					} */
				},	
				
				'cgsqqdFormParameter.dj' : function(val) {
					let sum = this.cgsqqdFormParameter.je;
					sum = parseFloat(this.cgsqqdFormParameter.sl) * parseFloat(val);
					this.cgsqqdFormParameter.je = sum.toFixed(2);
					this.cgsqFormParameter.ysje = sum.toFixed(2);
					this.jfsyFormParameter.syje = sum.toFixed(2);
					/* if (isNaN(this.cgsqqdFormParameter.je)) {
						this.cgsqqdFormParameter.je = sum.toFixed(2);
					} else {
						this.cgsqqdFormParameter.je = sum.toFixed(2);
						this.cgsqFormParameter.ysje = sum.toFixed(2);
						this.jfsyFormParameter.syje = sum.toFixed(2);
					} */
				}
			},
			
		};
</script>

<style lang="scss" scoped="scoped">
	@import '~common/custom/css/common.scss';
	@import '~common/custom/css/popup/popup.scss';
	

	.text {
		// font-size: 14px;
		background-color: #f4f4f4;
	}
	
	
	/* .clearfix:before,
	.clearfix:after {
	    display: table;
	    content: "";
	}
	 
	.clearfix:after {
	    clear: both
	} */
	
	.card-body {
		// background-color: #eaeaea;
	    width: 1100px;
	}
	
	.box-card2,.box-card3 {
		margin-top: 20px;
	}
	
	.el-row {
		padding: 2px 0px 0px 50px;
	}
	
	.footer-button {
		text-align: center;
		
	}
	
	
</style>
