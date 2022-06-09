<template>
	<div class="tab-body">
		<div class="search">
			<el-form ref="form" :model="searchParams" label-width="80px">
				<el-row>
					<el-col :span="6">
						<el-form-item label="姓名">
							<el-input v-model="searchParams.name"></el-input>
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
            <el-button type="primary" icon="el-icon-plus" @click="toSave()">添加</el-button>
            <el-button type="danger" icon="el-icon-delete" @click="batchRemove()">批量删除</el-button>
        </div>
        <div class="dataTable autoFlex" v-if="listData">
			<el-table class="table" :data="listData.records" stripe border height="auto" @selection-change="selectionChange" v-loading="listLoading">
						<el-table-column type="selection" width="55" fixed="left"></el-table-column>
						<el-table-column prop="htbh" label="合同编号"></el-table-column>
						<el-table-column prop="htmc" label="合同名称"></el-table-column>
						<el-table-column prop="cgjg" label="采购项目"></el-table-column>
						<el-table-column prop="htje" label="合同金额(元)"></el-table-column>
						<el-table-column prop="cjgys" label="乙方"></el-table-column>
						<el-table-column prop="gyslxdh" label="乙方联系人手机"></el-table-column>
						<el-table-column prop="gyslxr" label="乙方联系人"></el-table-column>
						<el-table-column prop="jgid" label="结果id"></el-table-column>
						<el-table-column prop="htlx" label="合同类型"></el-table-column>
						<el-table-column prop="zbj" label="质保金(元)"></el-table-column>
						<el-table-column prop="zbsj" label="质保时间(月)"></el-table-column>
						<el-table-column prop="scsmj" label="上传扫描件"></el-table-column>
						<el-table-column prop="bf" label="丙方"></el-table-column>
						<el-table-column prop="jflxfs" label="甲方联系人手机"></el-table-column>
						<el-table-column prop="jflxr" label="甲方联系人"></el-table-column>
						<el-table-column prop="bflxrfs" label="丙方联系人手机"></el-table-column>
						<el-table-column prop="bflxr" label="丙方联系人"></el-table-column>
						<el-table-column prop="jfht" label="甲方"></el-table-column>
						<el-table-column prop="lsclryhm" label="历史处理人"></el-table-column>
						<el-table-column prop="dqjdid" label="当前节点id"></el-table-column>
						<el-table-column prop="content" label="合同主要内容"></el-table-column>
						<el-table-column prop="jfly" label="经费来源"></el-table-column>
						<el-table-column prop="xmfzr" label="项目负责人"></el-table-column>
						<el-table-column prop="xmfzrdh" label="项目负责人电话"></el-table-column>
						<el-table-column prop="xmfzrid" label="项目负责人id"></el-table-column>
						<el-table-column prop="xmfzrbmbh" label="项目负责人部门编号"></el-table-column>
						<el-table-column prop="xmfzrbm" label="项目负责人部门"></el-table-column>
						<el-table-column prop="gysid" label="供应商id"></el-table-column>
						<el-table-column prop="htUsername" label="合同执行人用户名"></el-table-column>
						<el-table-column prop="htUser" label="合同执行人"></el-table-column>
						<el-table-column prop="zhonggaoDate" label="上传终稿时间"></el-table-column>
						<el-table-column prop="isZbj" label="是否有质保金：0-否、1-是"></el-table-column>
						<el-table-column prop="gkglbm" label="归口管理部门"></el-table-column>
						<el-table-column prop="gkglbmbh" label="归口管理部门编号"></el-table-column>
						<el-table-column prop="auditDate" label="审核完成时间"></el-table-column>
						<el-table-column prop="isGd" label="是否归档：0-否、1-是"></el-table-column>
						<el-table-column prop="isFk" label="是否完成付款：0-否、1-是"></el-table-column>
						<el-table-column prop="zyr" label="掌印人"></el-table-column>
						<el-table-column prop="isYy" label="是有用印：0-否、1-是"></el-table-column>
						<el-table-column prop="yyTime" label="用印时间"></el-table-column>
						<el-table-column prop="zyrYhm" label="掌印人用户名"></el-table-column>
						<el-table-column prop="cglx" label="采购类型"></el-table-column>
						<el-table-column prop="jjyz" label="计价原则"></el-table-column>
						<el-table-column prop="xmlx" label="项目类型"></el-table-column>
						<el-table-column prop="htjk" label="合同价款"></el-table-column>
						<el-table-column prop="yycs" label="用印次数"></el-table-column>
						<el-table-column prop="gdSjqr" label="审计确认归档：0-否、1-是"></el-table-column>
						<el-table-column prop="contractType" label="合同类别"></el-table-column>
						<el-table-column prop="kyfx" label="科研方向"></el-table-column>
						<el-table-column prop="deleteFlag" label="删除状态：0-否、1-是"></el-table-column>
						<el-table-column prop="crer" label="创建人id"></el-table-column>
						<el-table-column prop="crtm" label="创建时间"></el-table-column>
						<el-table-column prop="mder" label="修改人id"></el-table-column>
						<el-table-column prop="mdtm" label="修改时间"></el-table-column>
				<el-table-column label="操作" fixed="right" width="200px">
					<template slot-scope="scope">
						<div class="dataTable-operator">
							<el-button v-has-permission-code-and="''" size="mini" icon="el-icon-search"@click="toDetail(scope.$index, scope.row)">详情</el-button>
						    <el-button v-has-permission-code-and="''" size="mini" icon="el-icon-edit" @click="toUpdate(scope.$index, scope.row)">编辑</el-button>
                            <el-button v-has-permission-code-and="''" size="mini" icon="el-icon-delete" type="danger" @click="remove(scope.$index, scope.row)">删除</el-button>
                        </div>
					</template>
				</el-table-column>
			</el-table>
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
	</div>
</template>
<script>
import edit from './edit.vue';
import api from '@/api/ebp_ht/ebp_ht/ebp_ht.js';
export default {
	name: 'ebp_ht',
	components: {
		edit
	},
	data() {
		return {
			searchParams: {},
			isShowMoreSearch: false,
			listLoading: false,
			listData: [],
			selected: [],
			page: new this.$constant.pageObj(),
		};
	},
	methods: {
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
			this.$refs.edit.open({}, '添加');
		},
		//去编辑
		toUpdate(index, row) {
			let editParameter = {
				id: row.id
			};
			this.$refs.edit.open(editParameter, '编辑');
		},
		//去详情
		toDetail(index, row) {
			let detailParameter = {
				id: row.id
			};
			this.$refs.edit.open(detailParameter, '详情', true);
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

