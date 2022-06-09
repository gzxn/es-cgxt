<template>
	<div class="tab-body">
		<div class="search">
			<el-form ref="form" :model="searchParams" label-width="100px">
				<el-row>
					<el-col :span="6">
						<el-form-item label="采购包编号">
							<el-input v-model="searchParams.cgbbh"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="6">
						<el-form-item label="采购包名称">
							<el-input v-model="searchParams.cgbmc"></el-input>
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
					<qlh-table-column prop="cgbbh" label="采购包编号"></qlh-table-column>
					<qlh-table-column prop="cgbmc" label="采购包名称"></qlh-table-column>
					<qlh-table-column prop="zzxs" label="组织形式">
						<template slot-scope="scope">
							<el-tag type="primany" v-if="scope.row.zzxs==1">校内集中采购</el-tag>
						</template>
					</qlh-table-column>
					<qlh-table-column prop="cgfs" label="采购方式">
						<template slot-scope="scope">
							<el-tag type="primany" v-if="scope.row.cgfs==1">公开招标</el-tag>
							<el-tag type="primany" v-else-if="scope.row.cgfs==2">竞争性谈判</el-tag>
							<el-tag type="primany" v-else-if="scope.row.cgfs==3">单一来源</el-tag>
							<el-tag type="primany" v-else-if="scope.row.cgfs==4">询价</el-tag>
							<el-tag type="primany" v-else-if="scope.row.cgfs==5">部门自行采购</el-tag>
							<el-tag type="primany" v-else-if="scope.row.cgfs==6">电子卖场</el-tag>
						</template>
					</qlh-table-column>
					<qlh-table-column prop="ysje" label="预算金额(元)"></qlh-table-column>
					<qlh-table-column prop="cgzxryhm" label="采购执行人"></qlh-table-column>
				<qlh-table-column label="操作" fixed="right" width="150px">
					<template slot-scope="scope">
						<div class="dataTable-operator">
							<el-button v-has-permission-code-and="'8000024001'" size="mini" icon="el-icon-search"@click="toDetail(scope.$index, scope.row)">详情</el-button>
						    <el-button v-has-permission-code-and="'8000022001'" size="mini" type="primary" icon="el-icon-edit" @click="toUpdate(scope.$index, scope.row)">登记结果</el-button>
                            <!-- <el-button v-has-permission-code-and="''" size="mini" icon="el-icon-delete" type="danger" @click="remove(scope.$index, scope.row)">删除</el-button> -->
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
		<edit ref="edit"></edit>
		<djjgForm ref="djjgForm"></djjgForm>
	</div>
</template>
<script>
	import edit from './edit.vue';
	import djjgForm from './djjgForm.vue';
	import zbjgApi from '@/api/ebp_zbjg/ebp_zbjg/ebp_zbjg.js';
	import cgbApi from '@/api/ebp_cgb/ebp_cgb/ebp_cgb.js';
	import qlhTable from '@/components/qlhTable/qlhTable.vue';
	import qlhTableColumn from '@/components/qlhTable/qlhTableColumn.vue';
	export default {
		name: 'ebp_zbjg_wdj',
		components: {
			qlhTable,
			qlhTableColumn,
			edit,
			djjgForm,
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
			list() {debugger
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
				this.parameter.route_path = path;
				this.parameter.route_name = route.name;
				let params = this.$utils.merger(this.searchParams, this.page, this.parameter);
				zbjgApi.list(params, response => {
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
				this.$refs.djjgForm.open(editParameter, '登记采购结果', false, '70%');
			},
			//去详情
			toDetail(index, row) {
				let detailParameter = {
					id: row.id
				};
				// this.$refs.edit.open(detailParameter, '详情', true);
				this.$refs.djjgForm.open(detailParameter, '详情', true, '70%');
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

