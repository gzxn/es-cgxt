<template>
	<popup ref="popup" :loading="popupLoading">
		<div slot="body">
			<div class="card-body">
				<el-card class="box-card1">
					<div slot="header" class="clearfix">
						<span>采购结果信息</span>
					</div>
					<div class="text item">
						<el-form class="dataForm" ref="form1" :disabled="true" :model="zbjgFormParameter" :inline="true" label-width="110px" :rules="rules">
							<el-row>
								<el-col :span="10">
									<el-form-item label="采购结果" prop="cgjg">
										<el-select v-model="zbjgFormParameter.cgjg" placeholder="请选择">
											<el-option label="采购失败" value="1"></el-option>
											<el-option label="采购成功" value="2"></el-option>
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
									<el-form-item label="登记时间" prop="djsj">
										<!-- <el-input v-model="zbjgFormParameter.djsj" autocomplete="off"></el-input> -->
										<el-date-picker v-model="zbjgFormParameter.djsj" type="date" placeholder="选择日期" value-format="yyyy-MM-dd">
										</el-date-picker>
									</el-form-item>
								</el-col>
							</el-row>
							<el-row v-if="zbjgFormParameter.cgjg == 1">
								<el-col :span="10">
									<el-form-item label="采购供应商" prop="cjgys">
										<el-input v-model="zbjgFormParameter.cjgys" autocomplete="off"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="10">
									<el-form-item label="成交金额(元)" prop="cjjg">
										<el-input v-model="zbjgFormParameter.cjjg" autocomplete="off"></el-input>
									</el-form-item>
								</el-col>
							</el-row>
							<el-row v-if="zbjgFormParameter.cgjg == 1">
								<el-col :span="10">
									<el-form-item label="供应商联系人" prop="gyslxr">
										<el-input v-model="zbjgFormParameter.gyslxr" autocomplete="off"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="10">
									<el-form-item label="供应商联系电话" prop="gyslxdh">
										<el-input v-model="zbjgFormParameter.gyslxdh" autocomplete="off"></el-input>
									</el-form-item>
								</el-col>
							</el-row>
						</el-form>
					</div>
				</el-card>
				<el-card class="box-card2">
					<div slot="header" class="clearfix">
						<span>采购结果信息</span>
					</div>
					<div class="text item">
						<el-form class="dataForm" ref="form2" :disabled="disabled" :model="ysdFormParameter" :inline="true" label-width="100px" :rules="rules">
							<el-row>
								<el-col :span="10">
									<el-form-item label="是否验收" prop="isSuccess" :rules="rules.isSuccess">
										<el-select v-model="ysdFormParameter.isSuccess" placeholder="请选择">
											<el-option label="是" value="1"></el-option>
											<el-option label="否" value="0"></el-option>
										</el-select>
									</el-form-item>
								</el-col>
								<el-col :span="10" v-if="ysdFormParameter.isSuccess == 1">
									<el-form-item label="到货时间" prop="dhsj" :rules="rules.dhsj">
										<el-date-picker v-model="ysdFormParameter.dhsj" type="date" placeholder="选择日期" value-format="yyyy-MM-dd"></el-date-picker>
									</el-form-item>
								</el-col>
							</el-row>
							<el-row v-if="ysdFormParameter.isSuccess == 1">
								<el-col :span="24">
									<el-form-item label="验收报告" prop="zsysbg">
										<!-- <el-input v-model="ysdFormParameter.zsysbg" autocomplete="off"></el-input> -->
										<image-upload v-if="!dialogVisible" v-model="ysdFormParameter.zsysbg" :isBase64="true" statisPath="/icon/head">
										</image-upload>
										<el-image append-to-body v-if="dialogVisible" :src="ysdFormParameter.zsysbg" 
											:preview-src-list="img" z-index="3000">
										</el-image>
										<!-- <image-upload v-if="dialogVisible" v-model="ysdFormParameter.zsysbg" :isBase64="true" statisPath="/icon/head">
										</image-upload> -->
										<!-- </el-dialog> -->
										
									</el-form-item>
								</el-col>
							</el-row>
							<!-- <el-row>
								<el-col :span="24">
									<el-form-item label="验收申请附件" prop="yssqfj"><el-input v-model="ysdFormParameter.yssqfj" autocomplete="off"></el-input></el-form-item>
								</el-col>
							</el-row>
							<el-row>
								<el-col :span="24">
									<el-form-item label="验收状态" prop="yszt"><el-input v-model="ysdFormParameter.yszt" autocomplete="off"></el-input></el-form-item>
								</el-col>
							</el-row> -->
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
	import ysdApi from '@/api/ebp_ysd/ebp_ysd/ebp_ysd.js';
	import imgUpload from '@/components/biz/imageUpload/imgUpload.vue';
	import imageUpload from '@/components/biz/imageUpload/imageUpload.vue';
	export default {
		name: 'ysdForm',
		components: {
				popup,
				imgUpload,
				imageUpload,
			},
		data() {
			return {
				disabled: false,
				popupLoading: false,
				confirmLoading: false,
				parameter: {},
				formParameter: {},
				zbjgFormParameter: {},
				ysdFormParameter: {},
				dialogVisible: false,
				img: [],
				/* action: this.$constant.serverUrl + "/components/uploadComponent/imageUpload",
				headers: {
					jwttoken: this.$caches.token.get()
				},
				data: {
					statisPath: this.statisPath
				},
				dialogVisible: false, */
				rules: {
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
						/* {
							validator: this.$validate.length,
							max: 2000,
							trigger: 'blur'
						} */
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
					this.$utils.checkForm(this.$refs.form2, () => {debugger
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
							let zbjgParameter = this.zbjgFormParameter;
							let ysdParameter = this.$utils.merger(this.ysdFormParameter);
							parameters.zbjgParameter = zbjgParameter;
							parameters.ysdParameter = ysdParameter;
							ysdApi.update(parameters,
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
					this.ysdFormParameter = {};
					//清楚校验
					// this.$refs.form.clearValidate();
					this.$refs.form2.clearValidate();
					if (this.parameter.id) {
						//编辑查询数据
						let parameter = {
							id: this.parameter.id
						};
						ysdApi.toUpdate(parameter, response => {
							// this.formParameter = response.data;
							this.zbjgFormParameter = response.data.zbjgParameter
							if (response.data.ysdParameter != undefined) {
								this.ysdFormParameter = response.data.ysdParameter;
								this.img[0] = this.ysdFormParameter.zsysbg;
								this.dialogVisible = true;
							}
							this.popupLoading = false;
						});
					} else {
						//添加
						this.popupLoading = false;
					}
				},
				
				
				/* handlePictureCardPreview(file) {
					this.dialogImageUrl = file.url;
					this.dialogVisible = true;
				} */
					  
			},
			
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
	
	.box-card2 {
		margin-top: 20px;
		margin-bottom: 50px;
	}
	
	.el-row {
		padding: 15px 0px 0px 70px;
	}
	
	.footer-button {
		text-align: center;
	}
	
	.avatar {
		height: auto;
		width: auto;
	}
	
</style>
