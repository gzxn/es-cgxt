<template>
	<popup ref="popup" :loading="popupLoading" append-to-body>
		<div slot="body">
			<el-form class="dataForm" ref="form" :disabled="true" :model="formParameter" :inline="true" label-width="100px">
				<el-row>
					<el-col :span="12">
						<el-form-item label="流程名称" prop="lcmc">
							<el-input v-model="formParameter.lcmc" autocomplete="off"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="12">
						<el-form-item label="审核节点" prop="jdmc">
							<el-input v-model="formParameter.jdmc" autocomplete="off"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="12">
						<el-form-item label="审核结果" prop="shjg">
							<el-select v-model="formParameter.shjg" placeholder="请选择">
								<el-option label="不通过" value="0"></el-option>
								<el-option label="通过" value="1"></el-option>
							</el-select>
							<!-- <el-input v-model="formParameter.shjg" autocomplete="off"></el-input> -->
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="18">
						<el-form-item label="审核意见" prop="shyj">
							<el-input type="textarea" show-word-limit v-model="formParameter.shyj" autocomplete="off"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="12">
						<el-form-item label="审核时间" prop="crtm">
							<el-date-picker v-model="formParameter.crtm" type="datetime" placeholder="选择日期" value-format="yyyy-MM-dd hh:mm:ss"></el-date-picker>
						</el-form-item>
					</el-col>
				</el-row>
			</el-form>
		</div>
		<div slot="footer">
			<!-- <el-button v-if="!disabled" type="primary" :loading="confirmLoading" @click="confirm()">确 定</el-button> -->
			<el-button @click="close">取 消</el-button>
		</div>
	</popup>
</template>

<script>
	import popup from '@/components/popup/dialogPopup.vue';
	// import popup from '@/components/popup/drawerPopup.vue';
	import api from '@/api/lcgl/lcb/lcb.js';
	export default {
		name: 'viewSh',
		components: {
			popup,
		},
		data() {
			return {
				disabled: false,
				popupLoading: false,
				confirmLoading: false,
				parameter: {},
				formParameter: {},
				rules: {
					/* name: [
						{
							validator: this.$validate.required,
							trigger: 'blur'
						},
						{
							validator: this.$validate.length,
							min: 0,
							max: 50,
							trigger: 'blur'
						}
					], */
					
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
			init() {
				this.popupLoading = true;
				//清除等待
				this.confirmLoading = false;
				//清空内容
				this.formParameter = {};
				//清空校验
				this.$refs.form.clearValidate();debugger
				if (this.parameter.id) {
					//编辑查询数据
					let parameter = {
						id: this.parameter.id
					};
					api.getShNode(parameter, response => {debugger
						this.formParameter = response.data;
						this.formParameter.shjg = this.formParameter.shjg + '';
						this.popupLoading = false;
						// this.$emit("popupLoading",false);
					});
				} else {
					//添加
					this.popupLoading = false;
					/* this.formParameter = {
						area: ["100000", "900000"]
					}; */
				}
			}
		}
	};
</script>

<style lang="scss" scoped="scoped">
	@import '~common/custom/css/common.scss';
	@import '~common/custom/css/popup/popup.scss';
</style>
