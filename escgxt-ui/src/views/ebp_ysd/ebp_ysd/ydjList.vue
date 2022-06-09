<template>
	<div class="tab-body">
		<div class="search">
			<el-form ref="form" :model="searchParams" label-width="100px">
				<el-row>
					<!-- <el-col :span="6">
						<el-form-item label="采购项目编号">
							<el-input v-model="searchParams.lsclryhm"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="6">
						<el-form-item label="采购项目名称">
							<el-input v-model="searchParams.hwlx"></el-input>
						</el-form-item>
					</el-col> -->

					<el-collapse-transition>
						<div v-if="isShowMoreSearch">
						 <!--隐藏区域-->
						</div>
					</el-collapse-transition>
					<!-- <div class="search-btn">
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
					</div> -->
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
						<qlh-table-column type="selection" width="55" fixed="left"></qlh-table-column>
						<qlh-table-column prop="isSuccess" label="是否验收" width="100">
							<template slot-scope="scope">
								<el-tag type="danger" v-if="scope.row.isSuccess==0">否</el-tag>
								<el-tag type="success" v-else-if="scope.row.isSuccess==1">是</el-tag>
							</template>
						</qlh-table-column>
						<qlh-table-column prop="lsclryhm" label="采购项目编号" width="150"></qlh-table-column>
						<qlh-table-column prop="hwlx" label="采购项目名称" width="250"></qlh-table-column>
						<qlh-table-column prop="dhsj" label="到货时间" width="150"></qlh-table-column>
						<!-- <qlh-table-column prop="crer" label="登记人"></qlh-table-column> -->
						<qlh-table-column prop="crtm" label="登记时间" width="200"></qlh-table-column>
						<qlh-table-column prop="zsysbg" label="验收报告" width="200">
							<template slot-scope="scope">
								<el-avatar size="large" :src="scope.row.zsysbg" v-if="scope.row.zsysbg"></el-avatar>
							</template>
						</qlh-table-column>
						<!-- 
						<el-table-column prop="zbjgId" label="招标结果id"></el-table-column>
						<el-table-column prop="htid" label="合同id"></el-table-column>
						<el-table-column prop="yszt" label="验收状态"></el-table-column>
						<el-table-column prop="yssqfj" label="验收申请附件"></el-table-column>
						<el-table-column prop="hwlx" label="货物类型"></el-table-column>
						<el-table-column prop="dqjd" label="当前节点"></el-table-column>
						<el-table-column prop="lsclryhm" label="历史处理人用户名"></el-table-column>
						<el-table-column prop="dqclr" label="当前处理人"></el-table-column>
						<el-table-column prop="dqclryhm" label="当前处理人用户名"></el-table-column>
						<el-table-column prop="deleteFlag" label="删除状态：0-否、1-是"></el-table-column>
						<el-table-column prop="mder" label="修改人id"></el-table-column>
						<el-table-column prop="mdtm" label="修改时间"></el-table-column>
						-->
				<qlh-table-column label="操作" fixed="right" width="150px">
					<template slot-scope="scope">
						<div class="dataTable-operator">
							<el-button v-has-permission-code-and="'6000010001'" size="mini" icon="el-icon-search"@click="toDetail(scope.$index, scope.row)">详情</el-button>
						    <el-button v-has-permission-code-and="''" size="mini" icon="el-icon-edit" @click="toUpdate(scope.$index, scope.row)">编辑</el-button>
                            <el-button v-has-permission-code-and="''" size="mini" icon="el-icon-delete" type="danger" @click="remove(scope.$index, scope.row)">删除</el-button>
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
		<ysdForm ref="ysdForm"></ysdForm>
	</div>
</template>
<script>
	import edit from './edit.vue';
	import ysdForm from './ysdForm.vue';
	import ysdApi from '@/api/ebp_ysd/ebp_ysd/ebp_ysd.js';
	import qlhTable from '@/components/qlhTable/qlhTable.vue';
	import qlhTableColumn from '@/components/qlhTable/qlhTableColumn.vue';
	export default {
		name: 'ebp_ysd_ydj',
		components: {
			qlhTable,
			qlhTableColumn,
			edit,
			ysdForm,
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
				this.parameter.route_path = path;
				this.parameter.route_name = route.name;
				let params = this.$utils.merger(this.searchParams, this.page, this.parameter);
				ysdApi.list(params, response => {
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
					id: row.zbjg_id
				};
				this.$refs.edit.open(editParameter, '编辑', false, '70%');
			},
			//去详情
			toDetail(index, row) {
				let detailParameter = {
					id: row.zbjgId
				};
				this.$refs.ysdForm.open(detailParameter, '验收单详情', true, '70%');
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

