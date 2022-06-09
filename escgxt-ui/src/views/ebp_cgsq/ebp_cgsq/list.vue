<template>
	<div class="tab-body">
		<div class="search">
			<el-form ref="form" :model="searchParams" label-width="100px">
				<el-row>
					<el-col :span="6">
						<el-form-item label="采购项目编号">
							<el-input v-model="searchParams.cgbh"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="6">
						<el-form-item label="采购项目名称">
							<el-input v-model="searchParams.xmmc"></el-input>
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
		<!-- <div class="operator">
            <el-button type="primary" icon="el-icon-plus" @click="toSave()">添加</el-button>
            <el-button type="danger" icon="el-icon-delete" @click="batchRemove()">批量删除</el-button>
        </div> -->
        <div class="dataTable autoFlex" v-if="listData">
			<qlh-table class="table" :data="listData.records" stripe border height="auto" @selection-change="selectionChange" 
				v-loading="listLoading" :tableToolButton="tableToolButton" :excelProp="excelProp">
						<qlh-table-column type="selection" width="55" fixed="left" ignored></qlh-table-column>
						<qlh-table-column prop="cgbh" label="采购项目编号"></qlh-table-column>
						<qlh-table-column prop="xmmc" label="采购项目名称"></qlh-table-column>
						<qlh-table-column prop="sbbmbh" label="申报部门编号"></qlh-table-column>
						<qlh-table-column prop="sbbm" label="申报部门名称"></qlh-table-column>
						<qlh-table-column prop="ysje" label="预算金额(元)"></qlh-table-column>
						<qlh-table-column prop="lxrdh" label="联系人电话"></qlh-table-column>
						<qlh-table-column prop="sbrq" label="申报日期"></qlh-table-column>
						<qlh-table-column prop="bpmStatus" label="流程状态">
							<template slot-scope="scope">
								<el-tag type="warning" v-if="scope.row.bpmStatus==1">未提交</el-tag>
								<el-tag type="primany" v-else-if="scope.row.bpmStatus==2">审核中</el-tag>
								<el-tag type="success" v-else-if="scope.row.bpmStatus==5">审批通过</el-tag>
								<el-tag type="danger" v-else-if="scope.row.bpmStatus==4">退回</el-tag>
								<el-tag type="danger" v-else-if="scope.row.bpmStatus==4">不通过</el-tag>
								<!-- <el-tag effect="dark">{{scope.row.dictionary_type}}</el-tag> -->
							</template>
						</qlh-table-column>
						<!-- <qlh-table-column prop="dictionary_bpmStatus" label="流程状态"></qlh-table-column> -->
						<!-- <el-table-column prop="cgsqTblx" label="采购申请填报类型"></el-table-column> -->
						<!-- <el-table-column prop="xmlx" label="采购类型(货物类/工程类/服务类)"></el-table-column> -->
						<!-- <el-table-column prop="jflyId" label="经费来源id"></el-table-column>
						<el-table-column prop="xmzt" label="项目状态"></el-table-column>
						<el-table-column prop="nsqcgfs" label="拟申请采购方式"></el-table-column>
						<el-table-column prop="cglxType" label="项目类型(教学型/科研型)"></el-table-column>
						<el-table-column prop="cgsqType" label="采购申请类型(xmlx、cgjh)"></el-table-column>
						<el-table-column prop="dqjd" label="当前节点id"></el-table-column>
						<el-table-column prop="dqclr" label="当前处理人"></el-table-column>
						<el-table-column prop="dqclryhm" label="当前处理人用户名"></el-table-column>
						<el-table-column prop="isZx" label="是否注销：0-否、1-是"></el-table-column>
						<el-table-column prop="isBg" label="是否变更：0-否、1-是"></el-table-column>
						<el-table-column prop="isPackage" label="是否打包分包：0-否、1-是"></el-table-column>
						<el-table-column prop="jycgfs" label="建议采购方式"></el-table-column>
						<el-table-column prop="deleteFlag" label="删除状态：0-否、1-是"></el-table-column>
						<el-table-column prop="crer" label="创建人id"></el-table-column>
						<el-table-column prop="crtm" label="创建时间"></el-table-column>
						<el-table-column prop="mder" label="修改人id"></el-table-column>
						<el-table-column prop="mdtm" label="修改时间"></el-table-column> -->
				<qlh-table-column label="操作" fixed="right" width="200px">
					<template slot-scope="scope">
						<div class="dataTable-operator">
							<el-button v-has-permission-code-and="'3000011015'" size="mini" icon="el-icon-search"@click="toDetail(scope.$index, scope.row)">详情</el-button>
						    <el-button v-has-permission-code-and="'3000012015'" size="mini" icon="el-icon-edit" @click="toUpdate(scope.$index, scope.row)">编辑</el-button>
                            <el-button v-has-permission-code-and="'3000013015'" size="mini" icon="el-icon-delete" type="danger" @click="remove(scope.$index, scope.row)">删除</el-button>
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
		<editForm ref="editForm"></editForm>
		<shForm ref="shForm"></shForm>
	</div>
</template>
<script>
	import edit from './edit.vue';
	import editForm from './editForm.vue';
	import shForm from './shForm.vue';
	import qlhTable from '@/components/qlhTable/qlhTable.vue';
	import qlhTableColumn from '@/components/qlhTable/qlhTableColumn.vue';
	import api from '@/api/ebp_cgsq/ebp_cgsq/ebp_cgsq.js';
	export default {
		name: 'ebp_cgsq_list',
		components: {
			qlhTable,
			qlhTableColumn,
			edit,
			editForm,
			shForm,
		},
		data() {
			return {
				searchParams: {},
				isShowMoreSearch: false,
				listLoading: false,
				listData: [],
				selected: [],
				page: new this.$constant.pageObj(),
				parameter: {},
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
					excelName: '采购申请信息',	// 文件名称
					sheetname: '采购申请信息'	// xlsx里面的sheet
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
				// 取得当前tab的route
				let route = this.$route;
				let arr = route.path.split("/");
				let path = '';
				for(let i=0; i<arr.length; i++){
					if(i===1) {
						path += arr[i]; 
					}
					if(i===2) {
						path = path + '_' + arr[i];
					}
				}
				// let caches = this.$caches.currentTabId.get();
				this.parameter.route_path = path;
				this.parameter.route_name = route.name;
				let params = this.$utils.merger(this.searchParams, this.page, this.parameter);
				api.list(params, response => {
					this.listData = response.data;
					this.listLoading = false;
				})
			},
			//去添加
			toSave() {
				this.$refs.edit.open({}, '添加');
			},
			//去编辑
			toUpdate(index, row) {
				let editParameter = {
					id: row.id
				};
				// this.$refs.edit.open(editParameter, '编辑');
				this.$refs.editForm.open(editParameter, '编辑', false, '80%');
			},
			//去详情
			toDetail(index, row) {
				let detailParameter = {
					id: row.id
				};
				// this.$refs.edit.open(detailParameter, '详情', true);
				this.$refs.shForm.open(detailParameter, '详情', true, '80%', false);
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

