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
						<el-form class="dataForm" ref="form1" :disabled="true" :model="htFormParameter" :inline="true" label-width="110px" :rules="rules">
							<el-row>
								<el-col :span="10">
									<el-form-item label="合同名称" prop="htmc" placeholder="请输入项目名称">
										<el-input v-model="htFormParameter.htmc" autocomplete="off"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="10">
									<el-form-item label="合同编号" prop="htbh">
										<el-input v-model="htFormParameter.htbh" autocomplete="off" placeholder="保存后自动生成"></el-input>
									</el-form-item>
								</el-col>
							</el-row>
							<el-row>
								<el-col :span="10">
									<el-form-item label="合同金额(元)" prop="htje">
										<el-input v-model="htFormParameter.htje" autocomplete="off"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="10">
									<el-form-item label="经费来源" prop="jfly">
										<el-input v-model="htFormParameter.jfly" autocomplete="off"></el-input>
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
										<el-input v-model="htFormParameter.cjgys" autocomplete="off"></el-input>
									</el-form-item>
								</el-col>
							</el-row>
							<el-row>
								<el-col :span="10">
									<el-form-item label="乙方联系人" prop="gyslxr">
										<el-input v-model="htFormParameter.gyslxr" autocomplete="off"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="10">
									<el-form-item label="乙方联系人手机" prop="gyslxdh">
										<el-input v-model="htFormParameter.gyslxdh" autocomplete="off"></el-input>
									</el-form-item>
								</el-col>
							</el-row>
							<el-row>
								<el-col :span="10">
									<el-form-item label="项目负责人" prop="xmfzr">
										<el-input v-model="htFormParameter.xmfzr" autocomplete="off"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="10">
									<el-form-item label="项目负责人电话" prop="xmfzrdh">
										<el-input v-model="htFormParameter.xmfzrdh" autocomplete="off">
									</el-input></el-form-item>
								</el-col>
							</el-row>
							<el-row>
								<el-col :span="10">
									<el-form-item label="采购结果" prop="cgjg">
										<el-input v-model="htFormParameter.cgjg" autocomplete="off"></el-input>
									</el-form-item>
								</el-col>
							</el-row>
						</el-form>
					</div>
				</el-card>
				<el-card class="box-card2">
					<div slot="header" class="clearfix">
						<span>采购项目信息</span>
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
										<el-input v-model="cgsqFormParameter.cgbh" autocomplete="off" placeholder="提交后自动生成"></el-input>
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
										<el-input v-model="cgsqqdFormParameter.hwbh" autocomplete="off" placeholder="自动生成"></el-input>
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
										<el-input v-model="cgsqqdFormParameter.je" autocomplete="off" placeholder="自动计算"></el-input>
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
				
				<el-card class="box-card5">
					<div slot="header" class="clearfix">
						<span>审核信息</span>
					</div>
					<div class="dataTable autoFlex" v-if="listData">
						<!-- <el-table :data="lcbTableParameter"> -->
						<el-card class="box-card6">
							<div slot="header" class="clearfix">
								<span>审核历史</span>
							</div>
							<!-- <div>
								<el-steps :active="1" align-center>
								  <el-step title="提交"></el-step>
								  <el-step title="审核1"></el-step>
								  <el-step title="审核2"></el-step>
								  <el-step title="结束"></el-step>
								</el-steps>
							</div> -->
							<div class="text item">
								<el-table class="table" :data="listData.records" stripe border height="248">
									<el-table-column type="index" label="序号" fixed="left" width="55"></el-table-column>
									<el-table-column label="审核节点" prop="jdmc" width="150"></el-table-column>
									<el-table-column label="审核结果" prop="shjg" width="100">
										<template slot-scope="scope">
											<el-tag type="warning" v-if="scope.row.shjg==0">不通过</el-tag>
											<el-tag type="success" v-else-if="scope.row.shjg==1">审批通过</el-tag>
										</template>
									</el-table-column>
									<el-table-column label="审核意见" prop="shyj" width="330"></el-table-column>
									<el-table-column label="审核人" prop="crer" width="150"></el-table-column>
									<el-table-column label="审核时间" prop="crtm" width="160"></el-table-column>
									<el-table-column label="操作" fixed="right" width="80">
										<template slot-scope="scope">
											<div class="dataTable-operator">
												<el-button size="mini" icon="el-icon-search"@click="toDetail(scope.$index, scope.row)">详情</el-button>
									        </div>
										</template>
									</el-table-column>
								</el-table>
							</div>
						</el-card>
					</div>
					<div style="margin-top: 10px;">
						<template>
							<label class="el-form-item__label" style="width: 100px; margin-right: 10px;">当前审核节点:</label>
							<el-tag v-if="this.htFormParameter.bpmStatus == '1'" type="warning" size="medium" style="margin-top: 7px;">{{lcbNodeParameter.jdmc}}</el-tag>
							<el-tag v-else-if="this.htFormParameter.bpmStatus == '5'"  type="success" size="medium" style="margin-top: 7px;">{{lcbNodeParameter.jdmc}}</el-tag>
							<el-tag v-else type="primany" size="medium" style="margin-top: 7px;">{{lcbNodeParameter.jdmc}}</el-tag>
						</template>
					</div>
					<div class="text item audit" v-if="isSh">
						<el-form class="dataForm" ref="form5" :disabled="disabled" :model="lcbFormParameter" :inline="true" label-width="100px" :rules="rules">
							<el-row>
								<el-col :span="10">
									<el-form-item label="审核结果" prop="shjg" :rules="rules.shjg">
										<el-select v-model="lcbFormParameter.shjg" placeholder="请选择">
											<el-option label="通过" value="1"></el-option>
											<el-option label="不通过" value="0"></el-option>
										</el-select>
										<!-- <el-input v-model="lcbFormParameter.shjg" autocomplete="off"></el-input> -->
									</el-form-item>
								</el-col>
							</el-row>
							<el-row>
								<el-col :span="24">
									<el-form-item label="审核意见" prop="shyj" :rules="rules.shyj">
										<!-- <wang-edit v-model="lcbFormParameter.shyj"></wang-edit> -->
										<el-input type="textarea" placeholder="请输入审核意见" v-model="lcbFormParameter.shyj" maxlength="1000" 
										show-word-limit :autosize="{ minRows: 6, maxRows: 10 }">
										</el-input>
										<!-- <el-input v-model="lcbFormParameter.shyj" autocomplete="off"></el-input> -->
									</el-form-item>
								</el-col>
							</el-row>
						</el-form>
					</div>
				</el-card>
				
			</div>
			<viewSh ref="viewSh"></viewSh>
		</div>
		<div slot="footer" class="footer-button">
			<el-button v-if="!disabled" type="primary" :loading="confirmLoading" @click="confirm()">保 存</el-button>
			<!-- <el-button v-if="!disabled" type="primary" style="margin-left: 20px;" :loading="tjshLoading" @click="tjsh()">提 交</el-button> -->
			<el-button style="margin-left: 20px;" @click="close">取 消</el-button>
		</div>
	</popup>
</template>

<script>
	import popup from '@/components/popup/drawerPopup.vue';
	import htApi from '@/api/ebp_ht/ebp_ht/ebp_ht.js';
	import lcbApi from	'@/api/lcgl/lcb/lcb.js';
	import viewSh from '../../lcgl/lcgl_lcb/viewSh.vue';
	export default {
		name: 'shForm',
		components: {
				popup,
				viewSh,
			},
		data() {
			return {
				disabled: false,
				isSh: false,
				popupLoading: false,
				confirmLoading: false,
				tjshLoading: false,
				listData: [],
				listDatas: false,
				parameter: {},
				formParameter: {},
				cgsqFormParameter: {},
				cgsqqdFormParameter: {},
				jfsyFormParameter: {},
				zbjgFormParameter: {},
				htFormParameter: {},
				lcbFormParameter:{},
				lcbNodeParameter: {},
				shNodeParameter: {},
				rules: {
					//审核结果
					shjg: [
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
					//审核意见
					shyj: [
						{
							validator: this.$validate.required,
							trigger: 'blur'
						},
						{
							validator: this.$validate.length,
							max: 1000,
							trigger: 'blur'
						}
					],
					
				}
			};
		},
		methods: {
				//打开弹框
				open(parameter, title, disabled, size, isSh) {debugger
					this.parameter = parameter;
					this.disabled = disabled;
					this.isSh = isSh;
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
					this.$utils.checkForm(this.$refs.form5, () => {debugger
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
						} else {debugger
							//编辑
							// let parameter = this.$utils.merger(this.formParameter);
							let parameter = {};
							let htParameter = this.$utils.merger(this.htFormParameter);
							let lcbParameter = this.$utils.merger(this.lcbFormParameter);
							parameter.htParameter = htParameter;
							parameter.lcbParameter = lcbParameter;
							lcbApi.update(parameter,
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
					// this.formParameter = {};
					this.lcbFormParameter = {};
					//清楚校验
					// this.$refs.form.clearValidate();
					// this.$refs.form1.clearValidate();
					if (this.isSh) {
						this.$refs.form5.clearValidate();
					}
					if (this.parameter.id) {
						//编辑查询数据
						let parameter = {
							id: this.parameter.id,
							bdid: this.parameter.id,
						};
						htApi.toUpdate(parameter, response => {debugger
							this.formParameter = response.data;
							this.cgsqFormParameter = this.formParameter.cgsqParameter;
							this.cgsqqdFormParameter = this.formParameter.cgsqqdParameter;
							this.jfsyFormParameter = this.formParameter.jfsyParameter;
							this.zbjgFormParameter = this.formParameter.zbjgParameter;
							this.shNodeParameter.id = this.formParameter.htParameter.dqjdid;
							
							if (this.formParameter.htParameter != undefined) {
								this.htFormParameter = this.formParameter.htParameter;
							} 
							
							if (this.shNodeParameter.id) {
								lcbApi.getShNode(this.shNodeParameter, response => {debugger
									this.lcbNodeParameter = response.data;
								});
							} else {
								if (this.htFormParameter.bpmStatus == '1') {
									this.lcbNodeParameter = {
										jdmc: '未提交',
									}
								}
								if (this.htFormParameter.bpmStatus == '5') {
									this.lcbNodeParameter = {
										jdmc: '审批通过',
									}
								}
							}
							
							this.popupLoading = false;
						});
						lcbApi.list(parameter, response => {debugger
							this.listData = response.data;
							this.listLoading = false;
							if (this.listData.total > 0) {
								this.listDatas = true;
							} else {
								this.listDatas = false;
							}
						});
					} else {
						//添加
						this.popupLoading = false;
					}
				},
				
				//去审核历史详情
				toDetail(index, row) {
					let detailParameter = {
						id: row.id
					};
					this.$refs.viewSh.open(detailParameter, '审核历史详情');
				},
				
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
	
	.audit {
		margin-top: 20px;
	}
	
	.card-body {
		// background-color: #eaeaea;
	    width: 1100px;
	}
	
	.box-card2,.box-card3,.box-card4,.box-card5 {
		margin-top: 20px;
	}
	
	.el-row {
		padding: 2px 0px 0px 50px;
	}
	
	.footer-button {
		text-align: center;
		
	}
	
</style>
