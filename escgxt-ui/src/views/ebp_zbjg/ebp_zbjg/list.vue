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
						<el-table-column prop="cgjg" label="采购结果"></el-table-column>
						<el-table-column prop="jgbh" label="采购编号"></el-table-column>
						<el-table-column prop="xmmc" label="采购项目名称"></el-table-column>
						<el-table-column prop="cjgys" label="采购供应商"></el-table-column>
						<el-table-column prop="cjjg" label="采购金额(元)"></el-table-column>
						<el-table-column prop="gyslxdh" label="供应商联系电话"></el-table-column>
						<el-table-column prop="gyslxr" label="供应商联系人"></el-table-column>
						<el-table-column prop="cjrq" label="采购日期"></el-table-column>
						<el-table-column prop="djsj" label="登记时间"></el-table-column>
						<el-table-column prop="zbjgtzs" label="中标结果通知书"></el-table-column>
						<el-table-column prop="jglx" label="采购结果类型"></el-table-column>
						<el-table-column prop="zbid" label="招标id"></el-table-column>
						<el-table-column prop="sbid" label="设备id"></el-table-column>
						<el-table-column prop="cgbid" label="采购包id"></el-table-column>
						<el-table-column prop="gysid" label="供应商id"></el-table-column>
						<el-table-column prop="cgrwid" label="采购任务id"></el-table-column>
						<el-table-column prop="confirmResult" label="确认结果"></el-table-column>
						<el-table-column prop="isConfirm" label="是否已确认：0-否、1-是"></el-table-column>
						<el-table-column prop="cgfs" label="采购方式"></el-table-column>
						<el-table-column prop="cgStatus" label="采购状态"></el-table-column>
						<el-table-column prop="htUserdh" label="合同用户电话"></el-table-column>
						<el-table-column prop="isQdht" label="是否需要签订合同：0-否、1-是"></el-table-column>
						<el-table-column prop="tbdw" label="投标单位"></el-table-column>
						<el-table-column prop="isDh" label="是否到货：0-否、1-是"></el-table-column>
						<el-table-column prop="isApply" label="是否发起验收申请：0-否、1-是"></el-table-column>
						<el-table-column prop="isDjys" label="是否需要登记验收：0-否、1-是"></el-table-column>
						<el-table-column prop="isFk" label="是否完成付款：0-否、1-是"></el-table-column>
						<el-table-column prop="isSubmit" label="是否提交登记：0-否、1-是"></el-table-column>
						<el-table-column prop="isWcdj" label="是否完成提交登记：0-否、1-是"></el-table-column>
						<el-table-column prop="isYsConfirm" label="是否验收确认：0-否、1-是"></el-table-column>
						<el-table-column prop="ysUserName" label="验收申请人"></el-table-column>
						<el-table-column prop="ysUserCode" label="验收申请人用户名"></el-table-column>
						<el-table-column prop="ysUserPhone" label="验收申请人电话"></el-table-column>
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
import api from '@/api/ebp_zbjg/ebp_zbjg/ebp_zbjg.js';
export default {
	name: 'ebp_zbjg',
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

