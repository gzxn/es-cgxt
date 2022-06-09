<template>
	<div class="tab-body">
		<div slot="index" class="body-index">
			<div id="tip" style="margin: 1% 15% 1% 15%">
				<p style="text-align: center;"><strong><span>东方学院采购工作程序</span></strong></p>
				<p style="text-align: center;"><strong><span>——《东方学院采购管理办法》东方学院（2022）40号第二十七条</span></strong></p>
				<p><span style="margin-left: 40px;">采购项目按“谁采购，谁负责”的原则具体实施。凡纳入学院定点统一采购的项目，由招标管理中心组织统一采购，凡有教学科研机构管理部门的采购项目由教学科研机构管理部门组织采购，未纳入学院定点采购且无教学科研机构管理部门的采购项目，由各需求部门自行组织采购。</span></p>
				<p><span style="margin-left: 40px;">采购项目的程序：</span></p>
				<p><span style="font-size:20px;">（一）采购申请的项目由学院各需求部门（教学科研机构管理部门）自行依法组织采购，可采取竞争性谈判、询价、“政采云”平台电子卖场采购等方式采购。</span></p>
				<!-- <p><span style="font-size:20px;">（一）预算金额在10万元（含）以上20万元以下的项目。由学院各需求部门（教学科研机构管理部门）自行依法组织采购，可采取竞争性谈判、询价、“政采云”平台电子卖场采购等方式采购。原则上</span></p> -->
				<!-- <p><span>执行以下规定：1.各需求部门按年度计划或实际需求提出采购要求，填写《东方学院部门自行采购申请表》，经教学科研机构管理部门审核后，报招标管理中心、财务与资产管理处审批。</span></p>
				<p><span>2.成立谈判（询价）小组。由各需求部门（教学科研机构管理部门）主要负责人牵头的3人（含）以上的单数。采购要求“勤俭节约”、“货比三家”。</span></p>
				<p><span>3.制订谈判（询价）文件。谈判（询价）文件由采购需求部门(教学科研机构管理部门)制订，谈判文件中应当明确谈判程序（询价方法）、价格构成、评定标准、合同主要条款等。</span></p>
				<p><span>4.确定谈判（询价）对象。各需求部门（教学科研机构管理部门）从符合相应资格条件的供应商名单中确定谈判不少于3家的供应商，并向其提供谈判文件或发出询价通知书。</span></p>
				<p><span>5.组织谈判（询价）并确定供应商，形成谈判（询价）记录，并由采购小组成员签字确认。</span></p>
				<p><span>6.签订合同。采购合同由分管项目经费的部门领导（中层一支笔）签字。合同的内容、格式必须符合《中华人民共和国合同法》的规定。合同的内容应包括物资名称、规格型号、性能与技术指标、配置及附件、数量、价格、交货期、交货地点、运输及保险、付款方式、安装调试、验收方式以及质量保证期等条款，合同内容与采购内容不得有实质性区别。资产管理部提供合同通用模板。</span></p>
				<p><span>7.零星物资采购到货后，需求部门需安排专人严格按合同及相关文件进行验收。经办人与物资验收人不能为同一人。采购人员在验收合格签字后，凭《浙江财经大学东方学院部门自行采购申请表》、采购合同（1万元以上采购项目）、验收单和相关票据办理入库和财务报销手续。</span></p>
				<p><span>8．实行结果公开制度。项目所在单位应在15个工作日内将项目经费实施（采购）信息在所在单位范围内公布。若有疑义，可向院纪监部门、院招标办反映。</span></p>
				<p><span>9.部门自行采购流程执行完毕，各需求部门将谈判（询价）过程文件和合同复印件交招标办备案（或上传到采购系统）。</span></p> -->
				<!-- <p><span>（二）预算金额在1万元（含）以上5万元以下的项目。原则上按照1(申请)、2（招标）、3（结果公开）、7（验收）、6（合同）执行，相关采购文件及合同由各需求部门（教学科研机构管理部门）自行留底备查。</span></p> -->
				<p><span>原则上按照流程1(申请)、2（任务）、3（招标）、4（验收）、5（合同）执行，相关采购文件及合同由各需求部门（教学科研机构管理部门）自行留底备查。</span></p>
				<!-- <p><span>（二）预算金额在10万元以下的项目，由项目需求部门（教学科研机构管理部门）自行采购，相关采购文件由各需求部门（教学科研机构管理部门）自行留底备查。</span></p> -->
				<!-- <p><span>（四）通过“政采云”平台电子卖场方式采购的项目，采购程序按照政采云平台等采购程序执行。</span></p> -->
				<!-- <p><span>（五）工程（维修）项目按教学科研机构管理权限审批，按《浙江财经大学东方学院修缮管理办法》执行并报招标办备案。</span></p> -->
			</div>
		</div>
		<div slot="footer" class="footer-button">
			<el-button v-if="!disabled" type="primary" :loading="confirmLoading" @click="addConfirm()">填报采购申请</el-button>
		</div> 
        <addForm ref="addForm"></addForm>
		<edit ref="edit"></edit>
	</div>
</template>
<script>
	import edit from './edit.vue';
	import addForm from './addForm.vue';
	import api from '@/api/ebp_cgsq/ebp_cgsq/ebp_cgsq.js';
	export default {
		name: 'ebp_cgsq',
		components: {
			edit,
			addForm
		},
		data() {
			return {
				disabled: false,
				isShowMoreSearch: false,
				listLoading: false,
				confirmLoading: false,
				listData: [],
				selected: [],
				page: new this.$constant.pageObj(),
			};
		},
		methods: {

			//去添加
			addConfirm() {
				this.listLoading = true;
				this.$refs.addForm.open({}, '采购申请填报', false, '80%');
			}

		},
		mounted() {
			// this.list();
		}
	};
</script>

<style lang="scss" scoped="scoped">
	@import '~common/custom/css/common.scss';
	
	.body-index {
		// text-align: center;
		// width: 800px;
		// height: 450px;
		background-color: #f4f4f4;
	}
	
	
	p {
		text-align: left;
		line-height: "28px";
	}
	
	span {
		font-size: 20px;
		font-family: "宋体, SimSun";
	} 
	
	/* .text {
		// font-size: 14px;
		background-color: #f4f4f4;
	} */
	
	.footer-button {
		text-align: center;
		margin-top: 10px;
	}
</style>

