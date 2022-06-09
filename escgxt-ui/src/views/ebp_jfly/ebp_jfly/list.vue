<template>
	<div class="tab-body">
		<div class="search">
			<el-form ref="form" :model="searchParams" label-width="100px">
				<el-row>
					<el-col :span="6">
						<el-form-item label="经费名称">
							<el-input v-model="searchParams.ysmc"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="6">
						<el-form-item label="经费编码">
							<el-input v-model="searchParams.ysbm"></el-input>
						</el-form-item>
					</el-col>
					<el-collapse-transition>
						<div v-if="isShowMoreSearch">
							<!--隐藏区域-->
						</div>
					</el-collapse-transition>
					<div class="search-btn">
						<el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
						<el-button @click="clearSearchVal">清空</el-button>
						<el-button type="text" v-if="!isShowMoreSearch" @click="isShowMoreSearch = !isShowMoreSearch">
							更多
							<i class="el-icon-arrow-down"></i>
						</el-button>
						<el-button type="text" v-else @click="isShowMoreSearch = !isShowMoreSearch">
							收起
							<i class="el-icon-arrow-up"></i>
						</el-button>
					</div>
				</el-row>
			</el-form>
		</div>
		<div class="operator">
            <el-button type="primary" icon="el-icon-plus" @click="toSave()" v-has-permission-code-and="'5000010005'">添加</el-button>
            <el-button type="danger" icon="el-icon-delete" @click="batchRemove()" v-has-permission-code-and="'5000010015'">批量删除</el-button>
        </div>
        <div class="dataTable autoFlex" v-if="listData">
			<qlh-table class="table" :data="listData.records" stripe border height="auto" @selection-change="selectionChange"
				v-loading="listLoading" :tableToolButton="tableToolButton" :excelProp="excelProp">
						<qlh-table-column type="selection" width="55" fixed="left" ignored></qlh-table-column>
						<qlh-table-column prop="ysmc" label="预算项目名称" sortable></qlh-table-column>
						<qlh-table-column prop="ysbm" label="预算项目编码" sortable></qlh-table-column>
						<qlh-table-column prop="year" label="年度"></qlh-table-column>
						<!-- <qlh-table-column prop="jbbm" label="经办部门"></qlh-table-column>
						<qlh-table-column prop="jbbmbm" label="经办部门编码"></qlh-table-column> -->
						<qlh-table-column prop="dname" label="经办部门" sortable></qlh-table-column>
						<qlh-table-column prop="dcode" label="经办部门编码" sortable></qlh-table-column>
						<qlh-table-column prop="jfType" label="经费类型"></qlh-table-column>
						<!-- <qlh-table-column prop="dictionary_bpmStatus" label="流程状态"></qlh-table-column>
						<qlh-table-column prop="dictionary_deleteFlag" label="删除状态"></qlh-table-column> -->
						<!-- <qlh-table-column prop="bpmStatus" label="流程状态"></qlh-table-column>
						<qlh-table-column prop="deleteFlag" label="删除状态"></qlh-table-column> -->
						<!-- <qlh-table-column prop="crer" label="创建人id"></qlh-table-column>
						<qlh-table-column prop="crtm" label="创建时间"></qlh-table-column>
						<qlh-table-column prop="mder" label="修改人id"></qlh-table-column>
						<qlh-table-column prop="mdtm" label="修改时间"></qlh-table-column> -->
				<qlh-table-column label="操作" fixed="right" width="300" ignored>
					<template slot-scope="scope">
						<div class="dataTable-operator">
							<el-button v-has-permission-code-and="'5000010020'" size="mini" icon="el-icon-search" @click="toDetail(scope.$index, scope.row)">详情</el-button>
						    <el-button v-has-permission-code-and="'5000010010'" size="mini" icon="el-icon-edit" @click="toUpdate(scope.$index, scope.row)">编辑</el-button>
                            <el-button v-has-permission-code-and="'5000010015'" size="mini" icon="el-icon-delete" type="danger" @click="remove(scope.$index, scope.row)">删除</el-button>
                        </div>
					</template>
				</qlh-table-column>
			</qlh-table>
			<div class="pageBar">
				<el-pagination 
				@size-change="sizeChange" 
				@current-change="currentChange" 
				:total="listData.total" 
				:page-size="page.size"
				:current-page="page.current" 
				:layout="this.$constant.page.layout" 
				:page-sizes="this.$constant.page.pageSizes"></el-pagination>
			</div>
		</div>
		<edit ref="edit" :disabled="false"></edit>
	</div>
</template>
<script>
	import edit from './edit.vue';
	import qlhTable from '@/components/qlhTable/qlhTable.vue';
	import qlhTableColumn from '@/components/qlhTable/qlhTableColumn.vue';
	import selectDepartmentTreeDialog from '@/components/biz/selectDepartmentTreeDialog/selectDepartmentTreeDialog.vue';
	import api from '@/api/ebp_jfly/ebp_jfly/ebp_jfly.js';
	export default {
		name: 'ebp_jfly',
		components: {
			edit,
			qlhTable,
			qlhTableColumn,
			selectDepartmentTreeDialog,
		},
		data() {
			return {
				searchParams: {},
				isShowMoreSearch: false,
				listLoading: false,
				listData: [],
				selected: [],
				page: new this.$constant.pageObj(),
				showedCol: [],
				editableCol: [],
				/**
				 * 表格自定义工具栏按钮
				 * name:按钮名
				 * icon:按钮图标
				 * func:按钮点击事件
				 */
				tableToolButton: [{
					name: "customButton",
					icon: "el-icon-arrow-down",
					func: this.customButton
				}],
				excelProp: {
					excelName: '经费来源信息',	// 文件名称
					sheetname: '经费来源信息'	// xlsx里面的sheet
				}
			};
		},
		methods: {
			customButton() {
				console.log("自定义事件");
			},
			//获取数据
			list() {
				this.listLoading = true;
				let params = this.$utils.merger(this.searchParams, this.page);
				api.list(params, response => {
					this.listData = response.data;
					this.listLoading = false;
				})
			},
			//去添加
			toSave() {
				this.$refs.edit.open({}, '录入经费信息', false, "70%");
			},
			//去编辑
			toUpdate(index, row) {
				let editParameter = {
					id: row.id,
					did: row.did,
					dcode: row.dcode,
					dname: row.dname,
				};
				this.$refs.edit.open(editParameter, '编辑经费信息', false, '70%');
			},
			//去详情
			toDetail(index, row) {
				let detailParameter = {
					id: row.id
				};
				this.$refs.edit.open(detailParameter, '经费信息详情', true, '70%');
			},
			//删除
			remove(index, row) {
				let parameter = {
					ids: row.id
				}
				this.$utils.confirm.warning('提示', '确定删除吗？', () => {
					api.remove(
						parameter,
						response => {
							this.$utils.msg.success(response.msg);
							this.list();
						},
						response => {
							this.$utils.msg.warning(response.msg);
						}
					);
				});
			},
			//批量删除
			batchRemove() {
				if (!this.selected || this.selected.length == 0) {
					this.$utils.msg.info('请选择至少一行');
					return;
				}
				let parameter = {
					ids: this.selected
				};
				this.$utils.confirm.warning('提示', '确定删除吗？', () => {
					api.remove({
							ids: this.selected
						},
						response => {
							this.$utils.msg.success(response.msg);
							this.list();
						},
						response => {
							this.$utils.msg.warning(response.msg);
						}
					);
				});
			},
			//改变选择项
			selectionChange(val) {
				//清空
				this.selected = [];
				for (let item of val) {
					this.selected.push(item.id);
				}
			},
			//改变每页显示数量
			sizeChange(val) {
				this.page.size = val;
				this.list();
			},
			//改变现在的页码
			currentChange(val) {
				this.page.current = val;
				this.list();
			},

			//搜索
			search() {
				this.list();
			},
			//清空搜索框
			clearSearchVal() {
				this.searchParams = {};
			}
		},
		mounted() {
			this.list();
		}
	};
</script>

<style lang="scss" scoped="scoped">
	@import '~common/custom/css/common.scss';
</style>

