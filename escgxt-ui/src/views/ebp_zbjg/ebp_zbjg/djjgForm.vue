<template>
	<popup ref="popup" :loading="popupLoading">
		<div slot="body">
			<div class="card-body">
				<el-card class="box-card1">
					<div slot="header" class="clearfix">
						<span>采购申请信息</span>
					</div>
					<div class="text item">
						<el-form class="dataForm" ref="form1" :disabled="true" :model="cgsqFormParameter" :inline="true" label-width="100px" :rules="rules">
							<el-row>
								<el-col :span="10">
									<el-form-item label="项目名称" prop="xmmc">
										<el-input v-model="cgsqFormParameter.xmmc" autocomplete="off"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="10">
									<el-form-item label="采购项目编号" prop="cgbh">
										<el-input v-model="cgsqFormParameter.cgbh" autocomplete="off"></el-input>
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
									<el-form-item label="申报部门名称" prop="sbbm">
										<!-- <select-department-tree-dialog v-model="cgsqFormParameter.sbbmId"></select-department-tree-dialog> -->
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
									<el-form-item label="项目负责人" prop="xmfzr"><el-input v-model="cgsqFormParameter.xmfzr" autocomplete="off"></el-input></el-form-item>
								</el-col>
								<el-col :span="10">
									<el-form-item label="联系人电话" prop="lxrdh"><el-input v-model="cgsqFormParameter.lxrdh" autocomplete="off"></el-input></el-form-item>
								</el-col>
							</el-row>
							<el-row>
								<el-col :span="10">
									<el-form-item label="申报日期" prop="sbrq">
										<!-- <el-input v-model="cgsqFormParameter.sbrq" autocomplete="off"></el-input> -->
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
				<el-card class="box-card2">
					<div slot="header" class="clearfix">
						<span>采购清单信息</span>
					</div>
					<div class="text item">
						<el-form class="dataForm" ref="form2" :disabled="true" :model="cgsqqdFormParameter" :inline="true" label-width="100px" :rules="rules">
							<el-row>
								<el-col :span="10">
									<el-form-item label="流水号" prop="hwbh">
										<el-input v-model="cgsqqdFormParameter.hwbh" autocomplete="off"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="10">
									<el-form-item label="清单名称" prop="mc">
										<el-input v-model="cgsqqdFormParameter.mc" autocomplete="off"></el-input>
									</el-form-item>
								</el-col>
							</el-row>
							<el-row>
								<el-col :span="10">
									<el-form-item label="数量" prop="sl"><el-input v-model="cgsqqdFormParameter.sl" autocomplete="off"></el-input></el-form-item>
								</el-col>
								<el-col :span="10">
									<el-form-item label="单位" prop="dw"><el-input v-model="cgsqqdFormParameter.dw" autocomplete="off"></el-input></el-form-item>
								</el-col>
							</el-row>
							<el-row>
								<el-col :span="10">
									<el-col :span="24">
										<el-form-item label="单价(元)" prop="dj"><el-input v-model="cgsqqdFormParameter.dj" autocomplete="off"></el-input></el-form-item>
									</el-col>
								</el-col>
								<el-col :span="10">
									<el-form-item label="总金额(元)" prop="je">
										<el-input v-model="cgsqqdFormParameter.je" autocomplete="off"></el-input>
									</el-form-item>
								</el-col>
							</el-row>
						</el-form>
					</div>
				</el-card>
				<el-card class="box-card3">
					<div slot="header" class="clearfix">
						<span>采购包信息</span>
					</div>
					<div class="text item">
						<el-form class="dataForm" ref="form3" :disabled="true" :model="cgbFormParameter" :inline="true" label-width="100px" :rules="rules">
							<el-row>
								<el-col :span="10">
									<el-form-item label="采购包名称" prop="cgbmc">
										<el-input v-model="cgbFormParameter.cgbmc" autocomplete="off"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="10">
									<el-form-item label="采购包编号" prop="cgbbh">
										<el-input v-model="cgbFormParameter.cgbbh" autocomplete="off" :disabled="true"></el-input>
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
									<el-form-item label="采购执行人" prop="cgzxryhm">
										<el-input v-model="cgbFormParameter.cgzxryhm" autocomplete="off" :disabled="true"></el-input>
									</el-form-item>
								</el-col>
							</el-row>
						</el-form>
					</div>
				</el-card>
				<el-card class="box-card4">
					<div slot="header" class="clearfix">
						<span>采购结果信息</span>
					</div>
					<div class="text item">
						<el-form class="dataForm" ref="form4" :disabled="disabled" :model="zbjgFormParameter" :inline="true" label-width="110px" :rules="rules">
							<el-row>
								<el-col :span="10">
									<el-form-item label="采购结果" prop="cgjg" :rules="rules.cgjg">
										<el-select v-model="zbjgFormParameter.cgjg" placeholder="请选择">
											<el-option label="采购成功" value="1"></el-option>
											<el-option label="采购失败" value="0"></el-option>
										</el-select>
									</el-form-item>
								</el-col>
								<el-col :span="10">
									<el-form-item label="采购编号" prop="jgbh">
										<el-input v-model="zbjgFormParameter.jgbh" autocomplete="off" :disabled="true"></el-input>
									</el-form-item>
								</el-col>
							</el-row>
							<el-row>
								<el-col :span="10">
									<el-form-item label="采购项目名称" prop="xmmc">
										<el-input v-model="zbjgFormParameter.xmmc" autocomplete="off" :disabled="true"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="10">
									<el-form-item label="登记时间" prop="djsj" :rules="rules.djsj">
										<!-- <el-input v-model="zbjgFormParameter.djsj" autocomplete="off"></el-input> -->
										<el-date-picker v-model="zbjgFormParameter.djsj" type="date" placeholder="选择日期" value-format="yyyy-MM-dd">
										</el-date-picker>
									</el-form-item>
								</el-col>
							</el-row>
							<el-row v-if="zbjgFormParameter.cgjg == 1">
								<el-col :span="10">
									<!-- <el-form-item label="采购供应商" prop="cjgys" :rules="rules.cjgys">
										<select-user-table-dialog v-model="zbjgFormParameter.cjgys"></select-user-table-dialog>
									</el-form-item> -->
									<el-form-item label="采购供应商" prop="gysid">
										<select-user-table-dialog v-model="zbjgFormParameter.gysid" @change="handleGysid"></select-user-table-dialog>
									</el-form-item>
								</el-col>
								<el-col :span="10">
									<el-form-item label="成交金额(元)" prop="cjjg" :rules="rules.cjjg">
										<el-input v-model="zbjgFormParameter.cjjg" autocomplete="off"></el-input>
									</el-form-item>
								</el-col>
							</el-row>
							<el-row v-if="zbjgFormParameter.cgjg == 1">
								<el-col :span="10">
									<el-form-item label="供应商联系人" prop="gyslxr" :rules="rules.gyslxr">
										<el-input v-model="zbjgFormParameter.gyslxr" autocomplete="off"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="10">
									<el-form-item label="供应商联系电话" prop="gyslxdh" :rules="rules.gyslxdh">
										<el-input v-model="zbjgFormParameter.gyslxdh" autocomplete="off"></el-input>
									</el-form-item>
								</el-col>
							</el-row>
						</el-form>
					</div>
				</el-card>
			</div>
		</div>
		<div slot="footer" class="footer-button">
			<el-button v-if="!disabled" type="primary" :loading="confirmLoading" @click="confirm()">确 定</el-button>
			<el-button @click="close">取 消</el-button>
		</div>
	</popup>
</template>

<script>
	import popup from '@/components/popup/drawerPopup.vue';
	import selectUserTableDialog from '@/components/biz/selectUserTableDialog/selectUserTableDialog.vue';
	import userApi from '@/api/components/biz/selectUserTableDialog/selectUserTableDialog.js';
	import zbjgApi from '@/api/ebp_zbjg/ebp_zbjg/ebp_zbjg.js';
	export default {
		name: 'cgbEdit',
		components: {
				popup,
				selectUserTableDialog
			},
		data() {
			return {
				disabled: false,
				popupLoading: false,
				confirmLoading: false,
				parameter: {},
				formParameter: {},
				cgsqFormParameter: {},
				cgsqqdFormParameter: {},
				cgbFormParameter: {},
				zbjgFormParameter: {},
				userParameter: [],
				rules: {
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
					//登记时间
					djsj: [
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
					//成交金额(元)
					cjjg: [
						{
							validator: this.$validate.required,
							trigger: 'blur'
						},
						{
							validator: this.$validate.number,
							// max: 12,
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
							max: 100,
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
							validator: this.$validate.phone,
							// max: 11,
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
					this.$utils.checkForm(this.$refs.form4, () => {
						this.confirmLoading = true;
						if (!this.parameter.id) {
							//添加
							let parameter = this.$utils.merger(this.formParameter);
							/* api.save(parameter,
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
							// let parameter = this.$utils.merger(this.formParameter);
							let parameters = {};
							let cgsqParameter = this.cgsqFormParameter;
							let cgsqqdParameter = this.cgsqqdFormParameter;
							let cgbParameter = this.cgbFormParameter;
							let zbjgParameter = this.$utils.merger(this.zbjgFormParameter);
							parameters.cgsqParameter = cgsqParameter;
							parameters.cgsqqdParameter = cgsqqdParameter;
							parameters.cgbParameter = cgbParameter;
							parameters.zbjgParameter = zbjgParameter;
							zbjgApi.update(parameters,
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
					// this.cgbFormParameter = {};
					this.zbjgFormParameter = {};
					//清楚校验
					// this.$refs.form.clearValidate();
					// this.$refs.form1.clearValidate();
					// this.$refs.form2.clearValidate();
					this.$refs.form4.clearValidate();
					
					if (this.parameter.id) {
						//编辑查询数据
						let parameter = {
							id: this.parameter.id
						};
						zbjgApi.toUpdate(parameter, response => {
							// this.formParameter = response.data;
							this.cgsqFormParameter = response.data.cgsqParameter;
							this.cgsqqdFormParameter = response.data.cgsqqdParameter[0];
							this.cgbFormParameter = response.data.cgbParameter;
							if (response.data.zbjgParameter != undefined) {
								this.zbjgFormParameter = response.data.zbjgParameter;
							} else {
								this.zbjgFormParameter.jgbh = this.cgsqFormParameter.cgbh;
								this.zbjgFormParameter.xmmc = this.cgsqFormParameter.xmmc;
							}
							this.popupLoading = false;
						});
					} else {
						//添加
						this.popupLoading = false;
					}
				},
				
				handleGysid: function(val) {
					let gysid = this.$refs.form4._props.model.gysid;
					let parameter = {
						ids: val
					};
					if (!_.isEmpty(gysid)) {
						userApi.getByIds(parameter, response => {
							if (!_.isEmpty(response.data)) {
								this.userParameter = response.data;
								this.$set(this.zbjgFormParameter,'cjgys',this.userParameter[0].name);
								this.$set(this.zbjgFormParameter,'gyslxr',this.userParameter[0].lname);
								this.$set(this.zbjgFormParameter,'gyslxdh',this.userParameter[0].mobile);
							} else {
								this.$set(this.zbjgFormParameter,'cjgys','');
								this.$set(this.zbjgFormParameter,'gyslxr','');
								this.$set(this.zbjgFormParameter,'gyslxdh','');
							}
						});
					} else {
						this.$set(this.zbjgFormParameter,'cjgys','');
						this.$set(this.zbjgFormParameter,'gyslxr','');
						this.$set(this.zbjgFormParameter,'gyslxdh','');
					}
				},
				
			},
			
			/* watch: {
				'zbjgFormParameter.gysid' : function(val) {
					let gysid = this.$refs.form4._props.model.gysid;
					let parameter = {
						ids: val
					};
					if (!_.isEmpty(gysid)) {
						userApi.getByIds(parameter, response => {
							if (!_.isEmpty(response.data)) {
								this.userParameter = response.data;
								this.$set(this.zbjgFormParameter,'cjgys',this.userParameter[0].name);
								this.$set(this.zbjgFormParameter,'gyslxr',this.userParameter[0].lname);
								this.$set(this.zbjgFormParameter,'gyslxdh',this.userParameter[0].mobile);
							} else {
								this.$set(this.zbjgFormParameter,'cjgys','');
								this.$set(this.zbjgFormParameter,'gyslxr','');
								this.$set(this.zbjgFormParameter,'gyslxdh','');
							}
						});
					} else {
						this.$set(this.zbjgFormParameter,'cjgys','');
						this.$set(this.zbjgFormParameter,'gyslxr','');
						this.$set(this.zbjgFormParameter,'gyslxdh','');
					}
				},
			}, */
			
		};
</script>

<style lang="scss" scoped="scoped">
	@import '~common/custom/css/common.scss';
	@import '~common/custom/css/popup/popup.scss';
	
	.text {
		background-color: #f4f4f4;
	}
	
	.card-body {
	    width: 1035px;
	}
	
	.box-card2,.box-card3 {
		margin-top: 20px;
	    // width: 1035px;
	}
	
	.box-card4 {
		margin-top: 20px;
		margin-bottom: 50px;
	}
	
	.el-row {
		padding: 15px 0px 0px 70px;
	}
	
	.footer-button {
		text-align: center;
	}
</style>
