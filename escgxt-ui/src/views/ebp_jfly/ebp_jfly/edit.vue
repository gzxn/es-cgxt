<template>
	<popup ref="popup" :loading="popupLoading">
		<div slot="body">
			<el-card class="box-card">
				<div slot="header" class="clearfix">
					<span>基本信息</span>
					<!-- <el-button style="float: right; padding: 3px 0" type="warning">修改</el-button> -->
				</div>
				<div class="text item">
					<el-form class="dataForm" ref="form" :disabled="disabled" :model="formParameter" :inline="true" label-width="100px" :rules="rules">
							<el-row>
								<el-col :span="10">
									<el-form-item label="预算项目名称" prop="ysmc" :rules="rules.ysmc">
										<el-input v-model="formParameter.ysmc" autocomplete="off" placeholder="请输入预算项目名称"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="10">
									<el-form-item label="预算项目编码" prop="ysbm">
										<el-input v-model="formParameter.ysbm" autocomplete="off" placeholder="请输入预算项目编码"></el-input>
									</el-form-item>
								</el-col>
							</el-row>
							<el-row>
								<el-col :span="10">
									<el-form-item label="经办部门" prop="jbbm">
										<!-- <el-input v-model="formParameter.jbbm" autocomplete="off"></el-input> -->
										<select-department-tree-dialog v-model="formParameter.jbbm" @change="handleJbbm"></select-department-tree-dialog>
									</el-form-item>
								</el-col>
								<el-col :span="10">
									<el-form-item label="经办部门编码" prop="jbbmbm">
										<el-input v-model="formParameter.jbbmbm" autocomplete="off" :disabled="true" placeholder="自动回填"></el-input>
									</el-form-item>
								</el-col>
							</el-row>
							<el-row>
								<el-col :span="10">
									<el-form-item label="年度" prop="year">
										<el-input v-model="formParameter.year" autocomplete="off" style="width: 100px;">
										</el-input>
									</el-form-item>
								</el-col>
								<el-col :span="10">
									<el-form-item label="经费类型" prop="jfType">
										<el-input v-model="formParameter.jfType" autocomplete="off" placeholder="请输入经费类型">
										</el-input>
									</el-form-item>
								</el-col>
							</el-row>
							<!-- <el-row>
								<el-col :span="6">
									<el-form-item label="流程状态" prop="bpmStatus">
										<el-input v-model="formParameter.bpmStatus" autocomplete="off" :disabled="parameter.id ? true : false"></el-input>
									</el-form-item>
								</el-col>
							</el-row>
							<el-row>
								<el-col :span="6">
									<el-form-item label="删除状态：0-否、1-是" prop="deleteFlag">
										<el-input v-model="formParameter.deleteFlag" autocomplete="off"></el-input>
									</el-form-item>
								</el-col>
							</el-row> -->
					</el-form>
				</div>
			</el-card>
			<!-- <el-form class="dataForm" ref="form" :disabled="disabled" :model="formParameter" :inline="true" label-width="100px" :rules="rules">
				<el-divider>基本信息</el-divider>
					<el-row>
						<el-col :span="10" >
							<el-form-item label="预算项目名称" prop="ysmc" :rules="rules.ysmc">
								<el-input v-model="formParameter.ysmc" autocomplete="off"></el-input>
							</el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="10">
							<el-form-item label="预算项目编码" prop="ysbm">
								<el-input v-model="formParameter.ysbm" autocomplete="off"></el-input>
							</el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="6">
							<el-form-item label="年度" prop="year">
								<el-input v-model="formParameter.year" autocomplete="off"></el-input>
							</el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="10">
							<el-form-item label="经办部门" prop="jbbm">
								<el-input v-model="formParameter.jbbm" autocomplete="off"></el-input>
							</el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="10">
							<el-form-item label="经办部门编码" prop="jbbmbm">
								<el-input v-model="formParameter.jbbmbm" autocomplete="off"></el-input>
							</el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="8">
							<el-form-item label="经费类型" prop="jfType">
								<el-input v-model="formParameter.jfType" autocomplete="off"></el-input>
							</el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="6">
							<el-form-item label="流程状态" prop="bpmStatus">
								<el-input v-model="formParameter.bpmStatus" autocomplete="off" :disabled="parameter.id ? true : false"></el-input>
							</el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="6">
							<el-form-item label="删除状态：0-否、1-是" prop="deleteFlag">
								<el-input v-model="formParameter.deleteFlag" autocomplete="off"></el-input>
							</el-form-item>
						</el-col>
					</el-row>
			</el-form> -->
		</div>
		<div slot="footer" class="footer-button">
			<el-button v-if="!disabled" type="primary" :loading="confirmLoading" @click="confirm()">保 存</el-button>
			<el-button @click="close">取 消</el-button>
		</div>
	</popup>
</template>

<script>
import popup from '@/components/popup/drawerPopup.vue';
import selectDepartmentTreeDialog from '@/components/biz/selectDepartmentTreeDialog/selectDepartmentTreeDialog.vue';
import api from '@/api/ebp_jfly/ebp_jfly/ebp_jfly.js';
import departmentApi from '@/api/system/department/department.js'
export default {
	name: 'edit',
	components: {
			popup,
			selectDepartmentTreeDialog
		},
	data() {
		return {
			disabled: false,
			popupLoading: false,
			confirmLoading: false,
			parameter: {},
			formParameter: {},
			selectedListData: {},
			rules: {
				//预算项目名称
				ysmc: [
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
				//预算项目编码
				ysbm: [
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
				//年度
				year: [
					{
						validator: this.$validate.required,
						trigger: 'blur'
					},
					{
						validator: this.$validate.number,
						// max: 36,
						trigger: 'blur'
					}
				],
				//经办部门
				jbbm: [
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
				//经办部门编码
				jbbmbm: [
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
				this.$utils.checkForm(this.$refs.form, () => {debugger
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
						id: this.parameter.id,
					};
					api.toUpdate(parameter, response => {debugger
						this.formParameter = response.data;
						this.popupLoading = false;
					});
				} else {
					//添加
					this.popupLoading = false;
				}
			},
			handleJbbm(val) {debugger
				let jbbm = this.$refs.form._props.model.jbbm;
				let departmentData = {}
				let paramData = {
					id: val
				}
				if(!_.isEmpty(jbbm)) {
					api.getDepartmentListById(paramData, response => {
						this.departmentData = response.data[0];
						this.$set(this.formParameter,'jbbmbm',this.departmentData.code);
						// this.formParameter.jbbmbm = this.departmentData.code;
					});
				}else {
					this.$set(this.formParameter,'jbbmbm','');
					// this.formParameter.jbbmbm = "";
				}
				/* api.getDepartmentListById(paramData, response => {debugger
					
					this.departmentData = response.data[0];
					if(!_.isEmpty(jbbm)) {
						this.formParameter.jbbmbm = this.departmentData.code;
					}else {
						this.formParameter.jbbmbm = "";
					}
				}); */
				
				/* let jbbm = this.$refs.popup.$parent.formParameter.jbbm
				let departmentData = {}
				departmentApi.list(jbbm, response => {debugger
					this.departmentData = response.data
					if(this.formParameter.jbbm) {
						this.formParameter.jbbmbm = departmentData.code;
					}else {
						this.formParameter.jbbmbm = "";
					}
					this.popupLoading = false;
				}); */
				/* if (!_.isEmpty(this.formParameter.jbbm)) {
					this.formParameter.jbbmbm = val.jbbmbm;
				}else {
					this.formParameter.jbbmbm = "";
				} */
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
	};
</script>

<style lang="scss" scoped="scoped">
	@import '~common/custom/css/common.scss';
	@import '~common/custom/css/popup/popup.scss';
	
	.text {
		// font-size: 14px;
		background-color: #f4f4f4;
	}
	
	.item {
	    // margin-bottom: 18px;
		// background-color: #eaeaea;
	}
	
	/* .clearfix:before,
	.clearfix:after {
	    display: table;
	    content: "";
	}
	 
	.clearfix:after {
	    clear: both
	} */
	
	.box-card {
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
